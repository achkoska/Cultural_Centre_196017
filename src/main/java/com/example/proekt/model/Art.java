package com.example.proekt.model;


import javax.persistence.*;
import java.io.ByteArrayInputStream;

@Entity
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;





    @ManyToOne
    private Artist artist;

    private Integer likes=0;

    public Art() {
    }

    public Art(String name, String description, Double price, Artist artist,String imageUrl) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.artist = artist;
        this.imageUrl=imageUrl;
        this.likes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }




    public String toString() {
        Long var10000 = this.getId();
        return "Art(id=" + var10000 + ", name=" + this.getName() + ",description=" + this.getDescription() + ", price=" + this.getPrice() + ",   director=" + this.getArtist() + ",imageUrl=" + this.getImageUrl() + ")";
    }

}
