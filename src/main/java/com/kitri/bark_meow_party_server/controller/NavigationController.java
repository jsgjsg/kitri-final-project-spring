package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.AnimalHospital;
import com.kitri.bark_meow_party_server.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/navigate")
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    @GetMapping("/animal-hospital")
    public ResponseEntity<?> getAnimalHospital(@RequestParam String city) {
        try {
            List<AnimalHospital> animalHospitals = navigationService.findHospital(city);
            return ResponseEntity.ok(animalHospitals);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
