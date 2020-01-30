package com.wildcodeschool.lendafriend.repository;

import com.wildcodeschool.lendafriend.entity.ObjectBorrowed;
import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjectBorrowedRepository extends JpaRepository<ObjectBorrowed, Long> {

    List<ObjectBorrowed> findAllByUserObjectBorrowed(User user);
}
