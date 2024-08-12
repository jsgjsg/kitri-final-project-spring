package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.AnimalInformation;
import com.kitri.bark_meow_party_server.service.AnimalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalInformationController {
    @Autowired
    private AnimalInformationService animalInformationService;

    @PostMapping("")
    public AnimalInformation save(@RequestBody AnimalInformation animalInformation) {
        animalInformationService.insertAnimal(animalInformation);
        return animalInformation;
    }
    @GetMapping("")
    public List<AnimalInformation> getAll() {
        return animalInformationService.getAllAnimals();
    }

    @GetMapping("/{userId}")
    public List<AnimalInformation> getUserAnimal(@PathVariable Long userId) {
        return animalInformationService.getUserAnimals(userId);
    }

    @PutMapping("/{animalId}/update")
    public AnimalInformation updateAnimal(@PathVariable Long animalId, @RequestBody AnimalInformation animalInformation) {
        animalInformation.setId(animalId);
        animalInformationService.updateAnimal(animalInformation);
        return animalInformation;
    }
    @DeleteMapping("/{animalId}/delete")
    public void deleteAnimal(@PathVariable Long animalId) {
        animalInformationService.deleteAnimal(animalId);
    }
}
