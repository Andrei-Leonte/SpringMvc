package com.dreamcar.andreea.controllers;

import java.util.ArrayList;

import com.dreamcar.andreea.dtos.DealDetailsDto;
import com.dreamcar.andreea.dtos.DealDto;
import com.dreamcar.andreea.dtos.RequestDto;
import com.dreamcar.andreea.entites.Component;
import com.dreamcar.andreea.entites.Request;
import com.dreamcar.andreea.repositories.DealRepository;
import com.dreamcar.andreea.repositories.RequestRepository;
import com.dreamcar.andreea.utils.CurrentAccountDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deal")
public class DealController {
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping("request/{requestId}")
	public String getAllDealsByRequestId(@PathVariable Long requestId) {
        var request = requestRepository.getById(requestId);
        var user = CurrentAccountDetails.GetUser();

        var dto = new DealDto(
            request.getProvider().getUser().getEmail().equals(user.getEmail()),
            request);

        model.addAttribute("deals", dto);

        return "";
    }
}
