package com.example.autoservis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spz;
    private String model;
    private String vin;

    @ManyToOne
    @JoinColumn(name = "zakaznik_id")
    @JsonIgnore // Důležité: zabrání nekonečné smyčce při odesílání dat
    private Zakaznik zakaznik;

    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private List<Navsteva> navstevy = new ArrayList<>();
}