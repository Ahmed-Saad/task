package com.example.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
