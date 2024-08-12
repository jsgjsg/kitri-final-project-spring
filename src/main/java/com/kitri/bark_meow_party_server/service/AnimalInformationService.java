package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.AnimalInformation;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.AnimalInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalInformationService {
    @Autowired
    AnimalInformationMapper animalInformationMapper;
    @Autowired
    UserService userService;

    public void insertAnimal(AnimalInformation animalInformation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();

        User user = userService.findByUsername(username);
        animalInformation.setUserId(user.getId());

        animalInformationMapper.insertAnimal(animalInformation);
    }
//    //후기 댓글 조회
//    public List<ReviewComment> getReviewComments() {
//        return reviewCommentMapper.selectAll();
//    }
    public List<AnimalInformation> getAllAnimals () {
        return animalInformationMapper.selectAllAnimal();
    }
    public List<AnimalInformation> getUserAnimals(Long userId) {
        return animalInformationMapper.selectByAnimalUserId(userId);
    }

    public void updateAnimal(AnimalInformation animalInformation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        animalInformation.setUserId(user.getId());

        animalInformationMapper.updateAnimalInformation(animalInformation);
    }

    public void deleteAnimal(Long animalId) {
        animalInformationMapper.deleteAnimalInformation(animalId);
    }
}
