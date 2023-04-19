package com.example.dogservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dogservice.models.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long>{
    
}
