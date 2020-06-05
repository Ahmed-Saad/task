package com.example.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.models.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

}
