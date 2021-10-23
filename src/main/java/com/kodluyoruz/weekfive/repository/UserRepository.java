package com.kodluyoruz.weekfive.repository;

import com.kodluyoruz.weekfive.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
