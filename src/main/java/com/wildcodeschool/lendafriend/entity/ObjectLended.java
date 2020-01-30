package com.wildcodeschool.lendafriend.entity;

import javax.persistence.*;

@Entity
public class ObjectLended {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjectLended;
    private String lendName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User userObjectLended;

    public ObjectLended() {
    }

    public Long getIdObjectLended() {
        return idObjectLended;
    }

    public void setIdObjectLended(Long idObjectLended) {
        this.idObjectLended = idObjectLended;
    }

    public String getName() {
        return lendName;
    }

    public void setName(String name) {
        this.lendName = name;
    }

    public User getUserObjectLended() {
        return userObjectLended;
    }

    public void setUserObjectLended(User userObjectLended) {
        this.userObjectLended = userObjectLended;
    }
}