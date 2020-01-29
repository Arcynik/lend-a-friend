package com.wildcodeschool.lendafriend.controller;
import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    //TODO: @PostMapping("/incription") public String postRegistration() {return: home}

    //TODO: @GetMapping("/home") public String getHome() {return: home}

    @GetMapping("/home")
    public String getHome() {

        return "home";
    }

    //TODO: @PostMapping("/home") public String postHome() {return: home + signal visuel d'update}
}
