package com.musify.musify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model) {  
	    return "home";
	}
	
	// Redirect to Artist View.
	public String redirectToArtistView() {		    
		return "artist";
	}
	
	// Redirect to People View.
	public String redirectToPeopleView() {		    
		return "people";
	}
	 
}
