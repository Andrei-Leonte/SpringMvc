package com.dreamcar.andreea.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
	public String viewHomePage(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		
		return "home";
	}
}
