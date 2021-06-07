package com.dreamcar.andreea.controllers;

import com.dreamcar.andreea.repositories.RequestRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.dreamcar.andreea.entites.Request;
import com.dreamcar.andreea.entites.User;
import com.dreamcar.andreea.entites.base.DreamcarUser;
import com.dreamcar.andreea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
	private RequestRepository requestRepository;

    @GetMapping("/all/active")
	public String listActive(Model model) {
		Collection<Request> requests = requestRepository.getAllActive();
        
		model.addAttribute("requests", requests);
		
		return "activeRequest";
	}

    @GetMapping("/all/inactive")
	public String listInactive(Model model) {
		Collection<Request> requests = requestRepository.getAllActive();
        
		model.addAttribute("requests", requests);
		
		return "inactiveRequest";
	}

    @GetMapping("/create")
	public String showCreate(Model model) {
        model.addAttribute("request", new Request());

		return "createRequest";
	}

    @PostMapping("/create")
	public ModelAndView proccessCreate(Request model) {
        model.setStatus(true);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = auth.getPrincipal();
		var dreamcarUser =(DreamcarUser)principal;
		var user = dreamcarUser.getUser();
		model.setProvider(user.getProvider());
		requestRepository.save(model);
		
        return new ModelAndView("redirect:/request/all/active");
	}
}
