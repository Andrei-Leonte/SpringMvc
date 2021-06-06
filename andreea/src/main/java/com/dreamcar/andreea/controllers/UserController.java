package com.dreamcar.andreea.controllers;

import java.util.List;

import com.dreamcar.andreea.entites.User;
import com.dreamcar.andreea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
	private UserRepository userRepo;

    @GetMapping("/all")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
        
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
}
