package com.wildcodeschool.lendafriend.repository;

import com.wildcodeschool.lendafriend.entity.ObjectBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectBorrowedRepository extends JpaRepository<ObjectBorrowed, Long> {

}
