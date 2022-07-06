package com.example.khoj.Services;

public class CommentDTO {
    Long id;
    String comment;
    String rating;
    String username;

    public CommentDTO(){

    }

    public CommentDTO(Long id, String comment, String rating, String username){
        this.id= id;
        this.comment = comment;
        this.rating = rating;
        this.username = username;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
