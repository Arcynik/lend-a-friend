package com.wildcodeschool.lendafriend.repository;

import com.wildcodeschool.lendafriend.entity.ObjectBorrowed;
import com.wildcodeschool.lendafriend.entity.ObjectLended;
import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectLendedRepository extends JpaRepository<ObjectLended, Long> {

    List<ObjectLended> findAllByUserObjectLended(User user);

}
