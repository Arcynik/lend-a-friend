package com.wildcodeschool.lendafriend.controller;
import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.wildcodeschool.lendafriend.repository.ObjectBorrowedRepository;
import com.wildcodeschool.lendafriend.repository.ObjectLendedRepository;
import com.wildcodeschool.lendafriend.repository.UserRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectBorrowedRepository borrowedRepository;

    @Autowired
    ObjectLendedRepository lendedRepository;


    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        return allUsers;
    }

    @GetMapping("/")
    public String getIndex() {

        return "index";
    }

    //TODO: @PostMapping("/") public String postLogin() {return: home}

    @GetMapping("/inscription")
    public String getRegistration() {

        return "register";
    }

    @PostMapping("/inscription")
    public String postRegistration(Model out,
                                   @RequestParam(required = false) String username, @RequestParam String password,
                                   @RequestParam String passwordValidation, @ModelAttribute User user) {

        if (!password.equals(passwordValidation)) {
            out.addAttribute("valid", false);
            return "register";
        }

        if (username.isEmpty()) {
            out.addAttribute("emptyUsername", true);
            return "register";
        }

        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        out.addAttribute("userCreated", true);
        return "register";
    }

    //TODO: @GetMapping("/home") public String getHome() {return: home}

    @GetMapping("/home")
    public String getHome() {

        return "home";
    }

    //TODO: @PostMapping("/home") public String postHome() {return: home + signal visuel d'update}
}
