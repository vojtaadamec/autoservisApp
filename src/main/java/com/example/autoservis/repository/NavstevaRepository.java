package com.example.autoservis.repository;

import com.example.autoservis.model.Navsteva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavstevaRepository extends JpaRepository<Navsteva, Long> {
}
