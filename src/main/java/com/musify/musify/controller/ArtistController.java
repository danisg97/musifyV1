package com.musify.musify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepository;
import com.musify.musify.service.ArtistService;

@Controller
public class ArtistController {
	
	@Autowired
    private ArtistService artistService;
    
    @GetMapping("/artist")
	public String artist(Model model) {
    	model.addAttribute("allArtist", artistService.allArtist());
	    
	    return "artist";
	}

	 @PostMapping("/artist")
     public String addArtist(@RequestParam("name") String name,
    	@RequestParam("year") int year, Model model) {		 
		 
		 Artist artistToInsert = new Artist();
		 artistToInsert.setName(name);
		 artistToInsert.setYear(year);
		 
		artistService.addArtist(artistToInsert);
		return "redirect:/artist";
     }
	 
	 public String redirectToPeopleView() {		    
	    return "people";
	 }
	 
}
