package com.example.khoj.Services;

public class ImageDTO {
    Long id;

    String image;


    public ImageDTO(){

    }

    public ImageDTO(Long id, String image){
        this.id= id;
        this.image = image;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
