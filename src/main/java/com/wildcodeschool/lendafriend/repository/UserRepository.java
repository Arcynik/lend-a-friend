package com.wildcodeschool.lendafriend.repository;

import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
