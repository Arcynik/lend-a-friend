package com.wildcodeschool.lendafriend.controller;

import com.wildcodeschool.lendafriend.entity.ObjectBorrowed;
import com.wildcodeschool.lendafriend.entity.ObjectLended;
import com.wildcodeschool.lendafriend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.wildcodeschool.lendafriend.repository.ObjectBorrowedRepository;
import com.wildcodeschool.lendafriend.repository.ObjectLendedRepository;
import com.wildcodeschool.lendafriend.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectBorrowedRepository borrowedRepository;

    @Autowired
    ObjectLendedRepository lendedRepository;

    @GetMapping("/")
    public String getIndex() {

        return "index";
    }

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

        if (password.length() < 3) {
            out.addAttribute("tooShortPassword", true);
            return "register";
        }

        if (!userRepository.findAllByUsername(username).isEmpty()) {
            out.addAttribute("userAlreadyExists", true);
            return "register";
        }

        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        out.addAttribute("userCreated", true);
        return "register";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model out) {

        if (userRepository.findByUsernameAndPassword(username, password).isPresent()) {
            User user = userRepository.findByUsernameAndPassword(username, password).get();
            session.setAttribute("user", user);
            out.addAttribute("username", user.getUsername());
            return "redirect:/home";
        }
        out.addAttribute("invalidUser", false);
        return "index";
    }

    @GetMapping("/home")
    public String getHomepage(Model out, HttpSession session,
                              @ModelAttribute ObjectBorrowed objectBorrowed,
                              @ModelAttribute ObjectLended objectLended) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "index";
        }

        out.addAttribute("username", user.getUsername());
        List<ObjectBorrowed> borrowedObjects = borrowedRepository.findAllByUserObjectBorrowed(user);
        List<ObjectLended> lendedObjects = lendedRepository.findAllByUserObjectLended(user);
        out.addAttribute("objectsBorrowed", borrowedObjects);
        out.addAttribute("objectsLended", lendedObjects);
        return "home";
    }

    @PostMapping("/submit-form")
    public String postHomeForm(HttpSession session, Model out,
                               @ModelAttribute ObjectBorrowed objectBorrowed,
                               @RequestParam(required = false) String borrowName,
                               @RequestParam(required = false, defaultValue = "") String addBorrow,
                               @RequestParam(required = false, defaultValue = "") String removeBorrow,
                               @RequestParam(required = false) Long selectBorrow,
                               @ModelAttribute ObjectLended objectLended,
                               @RequestParam(required = false) String lendName,
                               @RequestParam(required = false, defaultValue = "") String addLend,
                               @RequestParam(required = false, defaultValue = "") String removeLend,
                               @RequestParam(required = false) Long selectLend) {

        User user = (User) session.getAttribute("user");
        out.addAttribute("objectBorrowed", new ObjectBorrowed());

        // Ajout d'un objet emprunté
        if (addBorrow.equals("+")) {
          if (borrowName.equals("")) {
              out.addAttribute("emptyBorrowObject", true);
              out.addAttribute("username", user.getUsername());
              List<ObjectBorrowed> borrowedObjects = borrowedRepository.findAllByUserObjectBorrowed(user);
              List<ObjectLended> lendedObjects = lendedRepository.findAllByUserObjectLended(user);
              out.addAttribute("objectsBorrowed", borrowedObjects);
              out.addAttribute("objectsLended", lendedObjects);
                return "home";
            }
            objectBorrowed.setName(borrowName);
            objectBorrowed.setUserObjectBorrowed(user);
            borrowedRepository.save(objectBorrowed);
            return "redirect:/home";
        }

        // Suppression d'un objet emprunté
        if (removeBorrow.equals("Rendu :)")) {
            if (selectBorrow == 0) {
                out.addAttribute("noSelectedBorrow", true);
                out.addAttribute("username", user.getUsername());
                List<ObjectBorrowed> borrowedObjects = borrowedRepository.findAllByUserObjectBorrowed(user);
                List<ObjectLended> lendedObjects = lendedRepository.findAllByUserObjectLended(user);
                out.addAttribute("objectsBorrowed", borrowedObjects);
                out.addAttribute("objectsLended", lendedObjects);
                return "home";
            }
            borrowedRepository.deleteById(selectBorrow);
            return "redirect:/home";
        }
        // Ajout d'un objet prêté
        if (addLend.equals("+")) {
            if (lendName.equals("")) {
                out.addAttribute("emptyLendObject", true);
                out.addAttribute("username", user.getUsername());
                List<ObjectBorrowed> borrowedObjects = borrowedRepository.findAllByUserObjectBorrowed(user);
                List<ObjectLended> lendedObjects = lendedRepository.findAllByUserObjectLended(user);
                out.addAttribute("objectsBorrowed", borrowedObjects);
                out.addAttribute("objectsLended", lendedObjects);
                return "home";
            }
            objectLended.setName(lendName);
            objectLended.setUserObjectLended(user);
            lendedRepository.save(objectLended);
            return "redirect:/home";
        }

        // Suppression d'un objet prêté
        if (removeLend.equals("Rendu :)")) {
            if (selectLend == 0) {
                out.addAttribute("noSelectedLend", true);
                out.addAttribute("username", user.getUsername());
                List<ObjectBorrowed> borrowedObjects = borrowedRepository.findAllByUserObjectBorrowed(user);
                List<ObjectLended> lendedObjects = lendedRepository.findAllByUserObjectLended(user);
                out.addAttribute("objectsBorrowed", borrowedObjects);
                out.addAttribute("objectsLended", lendedObjects);
                return "home";
            }
            lendedRepository.deleteById(selectLend);
            return "redirect:/home";
        }

        return "redirect:/home";
    }


    @GetMapping("/disconnect")
    public String getDisconnected(HttpSession session) {

        session.removeAttribute("user");
        return "redirect:/";
    }
}
