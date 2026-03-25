package com.example.autoservis.repository;

import com.example.autoservis.model.Zakaznik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZakaznikRepository extends JpaRepository<Zakaznik, Long> {
}