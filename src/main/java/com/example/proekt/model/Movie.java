package com.example.proekt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Movie {


    public Movie() {}

    public Movie(String name,  Double price,MovieGenre type,Director director,String imageUrl,String date) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.director=director;
        this.imageUrl=imageUrl;
        this.date=date;
        this.likes = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double price;

    @ManyToOne
    private Director director;

    @Enumerated(EnumType.STRING)
    private MovieGenre type;

    private Integer likes=0;
    private String imageUrl;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public MovieGenre getType() {
        return type;
    }

    public void setType(MovieGenre type) {
        this.type = type;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




    public String toString() {
        Long var10000 = this.getId();
        return "Movie(id=" + var10000 + ", name=" + this.getName() + ", price=" + this.getPrice() + ", type=" + this.getType() + ",  director=" + this.getDirector() + ", imageUrl=" + this.getImageUrl() +", date = "+this.getDate() + ")";
    }
}
