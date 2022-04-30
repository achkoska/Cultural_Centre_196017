package com.example.proekt.model;

import com.example.proekt.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDateTime dateCreated;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Art> arts;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.arts = new ArrayList();
        this.status = ShoppingCartStatus.CREATED;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public User getUser() {
        return this.user;
    }

    public List<Art> getArts() {
        return this.arts;
    }

    public ShoppingCartStatus getStatus() {
        return this.status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setDateCreated(final LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setArts(final List<Art> arts) {
        this.arts = arts;
    }

    public void setStatus(final ShoppingCartStatus status) {
        this.status = status;
    }


    public String toString() {
        Long var10000 = this.getId();
        return "ShoppingCart(id=" + var10000 + ", dateCreated=" + this.getDateCreated() + ", user=" + this.getUser() + ", products=" + this.getArts() + ", status=" + this.getStatus() + ")";
    }
}
