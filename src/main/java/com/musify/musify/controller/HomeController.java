package com.musify.musify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model) {  
		String id = "1";
		model.addAttribute("id", id);
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
	
	// Redirect to Related Artist View.
	public String redirectToRelatedArtistView(Model model) {
		
		return "related-artist";
	}
	 
}
