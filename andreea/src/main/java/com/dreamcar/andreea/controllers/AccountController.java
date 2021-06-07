package com.dreamcar.andreea.controllers;

import com.dreamcar.andreea.entites.Provider;
import com.dreamcar.andreea.entites.User;
import com.dreamcar.andreea.entites.misc.UserType;
import com.dreamcar.andreea.repositories.ProviderRepository;
import com.dreamcar.andreea.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProviderRepository providerRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }

    @PostMapping("/register")
	public ModelAndView processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		
        if (user.getType() == UserType.Dealer)
        {
            var provider = new Provider();
            provider.setId(user.getId());
            provider.setPhoneNumber("-1");
            provider.setUser(user);
            providerRepository.save(provider);

            user.setProvider(provider);
            
            userRepository.save(user);
        }

		return new ModelAndView("redirect:/login");
	}
}
