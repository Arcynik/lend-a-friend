package com.wildcodeschool.lendafriend.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String username;
    private String password;

    @OneToMany(mappedBy = "userObjectBorrowed")
    @Cascade({CascadeType.ALL})
    private List<ObjectBorrowed> objectsBorrowed = new ArrayList<>();

    @OneToMany(mappedBy = "userObjectLended")
    @Cascade({CascadeType.ALL})
    private List<ObjectLended> objectsLended = new ArrayList<>();

    public User() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ObjectBorrowed> getObjectsBorrowed() {
        return objectsBorrowed;
    }

    public void setObjectsBorrowed(List<ObjectBorrowed> objectsBorrowed) {
        this.objectsBorrowed = objectsBorrowed;
    }

    public List<ObjectLended> getObjectsLended() {
        return objectsLended;
    }

    public void setObjectsLended(List<ObjectLended> objectsLended) {
        this.objectsLended = objectsLended;
    }
}
