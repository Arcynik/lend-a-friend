package com.wildcodeschool.lendafriend.controller;

import com.wildcodeschool.lendafriend.entity.User;
import com.wildcodeschool.lendafriend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{idUser}")
    public User findUserById(@PathVariable Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @PostMapping("/users")
    public String postUser(@RequestBody User user) {

        if (user.getUsername().isEmpty()) {
            return "L'username est vide: c'est interdit !";
        }

        if (user.getPassword().length() < 3) {
            return "Le mot de passe fait moins de 3 caractères: c'est interdit !";
        }

        if (!userRepository.findAllByUsername(user.getUsername()).isEmpty()) {
            return "L'username est déjà utilisé: merci d'en choisir un autre !";
        }
        userRepository.save(user);
        return "Utilisateur enregistré !";
    }

    @PutMapping("/users/{idUser}")
    public String putUserById(@PathVariable Long idUser, @RequestBody User updatedUser) {

        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            return "Cet utilisateur n'existe pas";
        }

        if (updatedUser.getUsername().isEmpty()) {
            return "L'username est vide: c'est interdit !";
        }

        if (updatedUser.getPassword().length() < 3) {
            return "Le mot de passe fait moins de 3 caractères: c'est interdit !";
        }

        if (!userRepository.findAllByUsername(updatedUser.getUsername()).isEmpty()) {
            return "L'username est déjà utilisé: merci d'en choisir un autre !";
        }

        updatedUser.setIdUser(idUser);
        userRepository.save(updatedUser);
        return "L'utilisateur a été mis à jour";
    }

    @DeleteMapping("/users/{idUser}")
    public String deleteUserById(@PathVariable Long idUser) {

        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            return "Cet utilisateur n'existe pas";
        }
        userRepository.delete(user.get());
        return "L'utilisateur a été supprimé";
    }
}
