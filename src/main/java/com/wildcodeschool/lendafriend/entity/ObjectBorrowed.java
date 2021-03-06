package com.wildcodeschool.lendafriend.entity;

import javax.persistence.*;

@Entity
public class ObjectBorrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjectBorrowed;
    private String borrowName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User userObjectBorrowed;

    public ObjectBorrowed() {
    }

    public Long getIdObjectBorrowed() {
        return idObjectBorrowed;
    }

    public void setIdObjectBorrowed(Long idObjectBorrowed) {
        this.idObjectBorrowed = idObjectBorrowed;
    }

    public String getName() {
        return borrowName;
    }

    public void setName(String name) {
        this.borrowName = name;
    }

    public User getUserObjectBorrowed() {
        return userObjectBorrowed;
    }

    public void setUserObjectBorrowed(User userObjectBorrowed) {
        this.userObjectBorrowed = userObjectBorrowed;
    }
}
