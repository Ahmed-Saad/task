package com.example.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
