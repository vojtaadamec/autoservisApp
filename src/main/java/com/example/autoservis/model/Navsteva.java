package com.example.autoservis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate; // Musí být LocalDate

@Entity
@Getter @Setter
public class Navsteva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;
    private String popisPrace;
    private Double cena;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    @JsonIgnore
    private Auto auto;
}