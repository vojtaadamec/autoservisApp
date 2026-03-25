package com.example.autoservis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Zakaznik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jmeno;
    private String telefon;
    @OneToMany(mappedBy = "zakaznik", cascade = CascadeType.ALL)
    private List<Auto> auta = new ArrayList<>();
}
