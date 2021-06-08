package com.dreamcar.andreea.controllers;

import java.io.IOException;
import com.sendgrid.*;

import com.dreamcar.andreea.dtos.AcceptDealDto;
import com.dreamcar.andreea.dtos.CreateDealDto;
import com.dreamcar.andreea.dtos.DealDto;
import com.dreamcar.andreea.entites.Deal;
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

    @Autowired
    private DealRepository dealRepository;

    @GetMapping("request/active/{requestId}")
	public String getAllActiveDealsByRequestId(@PathVariable Long requestId, Model model) {
        var request = requestRepository.getById(requestId);
        var user = CurrentAccountDetails.GetUser();

        var dto = new DealDto(
            request,
            request.getProvider().getUser().getEmail().equals(user.getEmail()),
            user.getEmail());

        var createDto = new CreateDealDto();
        createDto.setRequestId(requestId);

        var acceptDealDto = new AcceptDealDto();

        model.addAttribute("acceptDeal", acceptDealDto);
        model.addAttribute("deal", createDto);
        model.addAttribute("deals", dto);
       
        return "activeDeal";
    }

    @GetMapping("request/inactive/{requestId}")
	public String getAllDealsByRequestId(@PathVariable Long requestId, Model model) {
        var request = requestRepository.getById(requestId);
        var user = CurrentAccountDetails.GetUser();

        var dto = new DealDto(
            request,
            request.getProvider().getUser().getEmail().equals(user.getEmail()),
            user.getEmail());
    
        model.addAttribute("deals", dto);

        return "inactiveDeal";
    }

    @PostMapping("create")
	public ModelAndView create(CreateDealDto dto) {
        var request = requestRepository.getById(dto.getRequestId());
        var user = CurrentAccountDetails.GetUser();

        var deal = new Deal();

        deal.setRequests(request);
        deal.setProvider(user.getProvider());

        dealRepository.save(deal);

        return new ModelAndView("redirect:/deal/request/active/" + dto.getRequestId());
    }

    @PostMapping("accept")
	public ModelAndView acceptDeal(AcceptDealDto dto) {
        var deal = dealRepository.getById(dto.getId());
        deal.setAccepted(true);
        deal.getRequest().setStatus(false);

        dealRepository.save(deal);
        requestRepository.save(deal.getRequest());

        try
        {
            sendmail(deal.getProvider().getUser().getEmail(), deal.getRequest().getComponent().getName());
        }
        catch(Exception e){ }

        return new ModelAndView("redirect:/deal/request/inactive/" + deal.getRequest().getId());
    }

    private void sendmail(String email, String name) {
        var from = new Email("Testjava76@gmail.com");
        String subject = "Felicitari ai castigat: ";
        Email to = new Email("leonteiandrei@gmail.com");
        Content content = new Content("text/plain", "Felicitari ai castigat: " + name);
        Mail mail = new Mail(from, subject, to, content);
    
        SendGrid sg = new SendGrid("--Introdu aici--");
        Request request = new Request();
        try {
          request.setMethod(Method.POST);
          request.setEndpoint("mail/send");
          request.setBody(mail.build());
          Response response = sg.api(request);
          System.out.println(response.getStatusCode());
          System.out.println(response.getBody());
          System.out.println(response.getHeaders());
        } catch (IOException ex) {
            return;
        }
     }
}
