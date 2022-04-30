package com.example.proekt.model;

import javax.persistence.*;

@Entity
public class Performance {


    public Performance() {
    }

    public Performance(String name,  Double price,PerformanceGenre type,Director director,String imageUrl,String date) {
        this.name = name;
        this.price = price;
        this.type=type;
        this.director=director;
        this.imageUrl=imageUrl;
        this.date=date;
        this.likes = 0;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private Double price;



    @ManyToOne
    private Director director;

    @Enumerated(EnumType.STRING)
    private PerformanceGenre type;

    private Integer likes=0;

    private String imageUrl;
    private String date;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public PerformanceGenre getType() {
        return type;
    }

    public void setType(PerformanceGenre type) {
        this.type = type;
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public String toString() {
        Long var10000 = this.getId();
        return "Performance(id=" + var10000 + ", name=" + this.getName() + ", price=" + this.getPrice() + ", type=" + this.getType() + ",  director=" + this.getDirector() + ", imageUrl=" + this.getImageUrl() +", date=" +this.getDate() + ")";
    }
}
