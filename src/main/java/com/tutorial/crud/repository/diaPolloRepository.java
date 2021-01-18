package com.tutorial.crud.repository;

import com.tutorial.crud.entity.diaPollos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface diaPolloRepository extends JpaRepository<diaPollos,Integer> {


    List<diaPollos> findAllById(int id);
}
