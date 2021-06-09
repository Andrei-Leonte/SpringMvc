package com.dreamcar.andreea.controllers;

import com.dreamcar.andreea.repositories.DealRepository;
import com.dreamcar.andreea.repositories.RequestRepository;
import com.dreamcar.andreea.utils.CurrentAccountDetails;
import com.dreamcar.andreea.utils.EmailUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dreamcar.andreea.dtos.CreateRequestDto;
import com.dreamcar.andreea.dtos.RequestDto;
import com.dreamcar.andreea.entites.Component;
import com.dreamcar.andreea.entites.Deal;
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

    @Autowired
    private DealRepository dealRepository;
	
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
				request.getProvider().getUser().getEmail().equals(user.getEmail()),
				request.getTime()
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
		var request = new CreateRequestDto();
		request.setComponent(new Component());

        model.addAttribute("request", request);

		return "createRequest";
	}

    @PostMapping("/create")
	public ModelAndView proccessCreate(CreateRequestDto model) {
		var request = new Request(
			model.getComponent(),
			model.getAmount(),
			model.getPrice(),
			model.getTimeout(),
			model.getTimeoutTime(),
			model.getStatus()
		);

        model.setStatus(true);
        
		var user = CurrentAccountDetails.GetUser();

		request.setProvider(user.getProvider());
		requestRepository.save(request);
		schedule(request);

        return new ModelAndView("redirect:/request/all/active");
	}

	private void schedule(Request request)
	{
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		OneShotTask runnable = new OneShotTask(request.getId());

		LocalDateTime now = LocalDateTime.now();  
        Timestamp timestamp = Timestamp.valueOf(now);

		long seconds = Math.abs(request.getTimeout().getTime()
		 + TimeUnit.MINUTES.toMillis(request.getTime().getHours()* 60 + request.getTime().getMinutes())
		 - timestamp.getTime()) / 100;
 
        scheduler.schedule(runnable, seconds, TimeUnit.SECONDS);
        scheduler.shutdown();
	}

    class OneShotTask implements Runnable {
        long id;
        OneShotTask(Long id) { this.id = id; }
        public void run() {
           var request = requestRepository.getById(id);

		   if (request.getDeals().size() > 0 && request.getStatus())
		   {
			   Deal deal = request.getDeals().stream().max(Comparator.comparing(v -> v.getPrice())).get();
			   deal.setAccepted(true);
			   deal.getRequest().setStatus(false);
			   deal.getRequest().setModifiedBy("System");
			  
			   dealRepository.save(deal);
			   requestRepository.save(deal.getRequest());

			   EmailUtil.sendmail(
                deal.getProvider().getUser().getEmail(),
                "Felicitari, tia fost acceptata ofera de catre system: " + deal.getRequest().getComponent().getName(),
                "Felicitari, tia fost acceptata ofera de catre system");
		   }
		   else
		   {
				request.setStatus(false);
				request.setModifiedBy("System");
				requestRepository.save(request);

				EmailUtil.sendmail(
					request.getProvider().getUser().getEmail(),
					"Nu ai primit nici o oferta.",
					"Felicitari, tia fost acceptata ofera de catre system");
		   }
        }
    }
}