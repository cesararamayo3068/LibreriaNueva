package com.libreria.nueva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.nueva.entity.Libro;



@Repository
public interface LibroRepository extends JpaRepository <Libro,Long> {

}
