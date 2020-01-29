package com.wildcodeschool.lendafriend.repository;

import com.wildcodeschool.lendafriend.entity.ObjectLended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectLendedRepository extends JpaRepository<ObjectLended, Long> {

}
