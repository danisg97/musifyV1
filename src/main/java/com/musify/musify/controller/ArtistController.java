package com.musify.musify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.musify.musify.model.Artist;
import com.musify.musify.service.ArtistService;

@Controller
public class ArtistController {
	
    private ArtistService artistService;

	@GetMapping("home")
	public ModelAndView homeView() {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("index");
	    return mav;
	}

	 @PostMapping("home")
     public String addArtist(@RequestParam("name") String name,
    	@RequestParam("year") int year, Model model) {		 
		 
		 Artist artistToInsert = new Artist();
		 artistToInsert.setName(name);
		 artistToInsert.setYear(year);
		 
		artistService.addArtist(artistToInsert);
		return "redirect:/home";
     }
	
}
