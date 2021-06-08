package com.dreamcar.andreea.controllers;

import com.dreamcar.andreea.repositories.RequestRepository;
import com.dreamcar.andreea.utils.CurrentAccountDetails;

import java.util.ArrayList;
import java.util.Collection;

import com.dreamcar.andreea.dtos.RequestDto;
import com.dreamcar.andreea.entites.Component;
import com.dreamcar.andreea.entites.Request;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		var dtos = new ArrayList<RequestDto>();
		var user = CurrentAccountDetails.GetUser();
		
		requests.forEach(request -> {
			dtos.add(new RequestDto(
				request.getId(),
				request.getProvider(),
				request.getComponent(),
				request.getAmount(),
				request.getPrice(),
				request.getTimeout(),
				request.getDate(),
				request.getStatus(),
				request.getProvider().getUser().getEmail().equals(user.getEmail())
			));
		});

		model.addAttribute("requests", dtos);
		
		return "activeRequest";
	}

    @GetMapping("/all/inactive")
	public String listInactive(Model model) {
		Collection<Request> requests = requestRepository.getAllInactive();
        
		model.addAttribute("requests", requests);
		
		return "inactiveRequest";
	}

    @GetMapping("/create")
	public String showCreate(Model model) {
		var request = new Request();
		request.setComponent(new Component());

        model.addAttribute("request", request);

		return "createRequest";
	}

    @PostMapping("/create")
	public ModelAndView proccessCreate(Request model) {
        model.setStatus(true);
        
		var user = CurrentAccountDetails.GetUser();

		model.setProvider(user.getProvider());
		requestRepository.save(model);

        return new ModelAndView("redirect:/request/all/active");
	}

}