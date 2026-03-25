package com.example.autoservis.controller;

import com.example.autoservis.model.Auto;
import com.example.autoservis.model.Navsteva;
import com.example.autoservis.model.Zakaznik;
import com.example.autoservis.repository.AutoRepository;
import com.example.autoservis.repository.NavstevaRepository;
import com.example.autoservis.repository.ZakaznikRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zakaznici")
@CrossOrigin
public class ZakaznikController {

    private final ZakaznikRepository repository;
    private final AutoRepository autoRepository;
    private final NavstevaRepository navstevaRepository;

    public ZakaznikController(ZakaznikRepository repository, AutoRepository autoRepository, NavstevaRepository navstevaRepository) {
        this.repository = repository;
        this.autoRepository = autoRepository;
        this.navstevaRepository = navstevaRepository;
    }

    @GetMapping
    public List<Zakaznik> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Zakaznik getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Zakaznik create(@RequestBody Zakaznik zakaznik) {
        return repository.save(zakaznik);
    }

    // --- ÚPRAVY (UPDATE) ---
    @PutMapping("/{id}")
    public Zakaznik updateZakaznik(@PathVariable Long id, @RequestBody Zakaznik detaily) {
        return repository.findById(id).map(z -> {
            z.setJmeno(detaily.getJmeno());
            z.setTelefon(detaily.getTelefon());
            return repository.save(z);
        }).orElseThrow();
    }

    // --- AUTA ---
    @PostMapping("/{id}/auta")
    public Auto addAuto(@PathVariable Long id, @RequestBody Auto auto) {
        return repository.findById(id).map(zakaznik -> {
            auto.setZakaznik(zakaznik);
            return autoRepository.save(auto);
        }).orElseThrow();
    }

    @DeleteMapping("/auta/{autoId}")
    public void deleteAuto(@PathVariable Long autoId) {
        autoRepository.deleteById(autoId);
    }

    // --- NÁVŠTĚVY (OPRAVENÁ CESTA) ---
    @PostMapping("/auta/{autoId}/navstevy")
    public Navsteva addNavsteva(@PathVariable Long autoId, @RequestBody Navsteva navsteva) {
        return autoRepository.findById(autoId).map(auto -> {
            navsteva.setAuto(auto);
            if (navsteva.getDatum() == null) {
                navsteva.setDatum(java.time.LocalDate.now());
            }
            return navstevaRepository.save(navsteva);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteZakaznik(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Získání detailu jednoho auta
    @GetMapping("/auta/{autoId}")
    public Auto getAuto(@PathVariable Long autoId) {
        return autoRepository.findById(autoId).orElseThrow();
    }

    // Úprava existující návštěvy
    @PutMapping("/navstevy/{id}")
    public Navsteva updateNavsteva(@PathVariable Long id, @RequestBody Navsteva detaily) {
        return navstevaRepository.findById(id).map(n -> {
            n.setPopisPrace(detaily.getPopisPrace());
            n.setCena(detaily.getCena());
            n.setDatum(detaily.getDatum());
            return navstevaRepository.save(n);
        }).orElseThrow();
    }

    @DeleteMapping("/navstevy/{id}")
    public void deleteNavsteva(@PathVariable Long id) {
        navstevaRepository.deleteById(id);
    }


}