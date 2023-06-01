package com.smart.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
    
	@GetMapping("/")
	public String home(Model model) {
		System.out.println("HOME PAGE REQUESTED");
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		System.out.println("ABOUT PAGE REQUESTED");
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		System.out.println("SIGNUP PAGE REQUESTED");
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	// handler for registering user
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,Model model) {
	    if(!agreement) {
	    	System.out.println("You have not agreed the terms and conditions");
	    	
	    }
		user.setRole("ROLE_USER");
		user.setEnabled(true);
     

		System.out.println("Agreement: " + agreement);
	   System.out.println("User: " + user);
      
	   User result = this.userRepository.save(user);

	   model.addAttribute("user", result);
	   return "signup";
	}
}
