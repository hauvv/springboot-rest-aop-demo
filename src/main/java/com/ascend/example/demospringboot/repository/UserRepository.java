package com.ascend.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ascend.example.demospringboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
