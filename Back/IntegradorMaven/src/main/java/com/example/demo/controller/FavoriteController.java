package com.example.demo.controller;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;
import com.example.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/favorite")
public class FavoriteController {


    private FavoriteService service;

    @Autowired

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @PostMapping("/")
    public boolean save(FavoriteDTO favorite){
        return service.save(favorite);
    }

    @GetMapping("/list")
    public List<Favorite> favorites(Integer id){
        return service.userFavorites(id);
    }
}
