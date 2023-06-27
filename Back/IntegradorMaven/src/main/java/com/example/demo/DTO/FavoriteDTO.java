package com.example.demo.DTO;

import com.example.demo.entity.User;

public class FavoriteDTO {
    private Integer idFavorite;
    private Integer userId;
    private Integer petDayCareId;

    public FavoriteDTO(Integer userId, Integer petDayCareId) {
        this.userId = userId;
        this.petDayCareId = petDayCareId;
    }

    public FavoriteDTO() {
    }

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPetDayCareId() {
        return petDayCareId;
    }

    public void setPetDayCareId(Integer petDayCareId) {
        this.petDayCareId = petDayCareId;
    }
}
