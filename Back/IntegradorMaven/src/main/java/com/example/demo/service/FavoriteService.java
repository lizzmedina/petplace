package com.example.demo.service;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.PetDayCare;
import com.example.demo.entity.User;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.PetDayCareRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private FavoriteRepository repository;

    private UserRepository userRepository;
    private PetDayCareRepository petDayCareRepository;

    @Autowired
    public FavoriteService(FavoriteRepository repository, UserRepository userRepository, PetDayCareRepository petDayCareRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.petDayCareRepository = petDayCareRepository;
    }

    public boolean save(FavoriteDTO favoriteDTO){

        if (favoriteDTO == null) {
            throw new IllegalArgumentException("La reserva no puede ser nulo");
        }   
        
        Optional<User> user = this.userRepository.findById(favoriteDTO.getUserId());
        Optional<PetDayCare> petDayCare = this.petDayCareRepository.findById(favoriteDTO.getPetDayCareId());

        Integer response = repository.searchInFavorites(petDayCare.get().getId(), user.get().getId());
        
        if(response == null) {

            Favorite favorite = new Favorite(
                    user.get(),
                    petDayCare.get()
            );

            repository.save(favorite);

            favoriteDTO.setIdFavorite(favorite.getIdFavorite());
            
            return true;
        }else{
            Optional<Favorite> favoriteFound = repository.findById(response);
            repository.delete(favoriteFound.get());
            return false;
        }
        
    }


    public Optional<Favorite> findByID(Integer id){
      return   repository.findById(id);

    }

    public List<Favorite> userFavorites(Integer userId){
      return  repository.findAllByUserId(userId).stream().collect(Collectors.toList());

    }
}
