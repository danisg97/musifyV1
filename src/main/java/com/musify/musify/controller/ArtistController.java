package com.musify.musify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepository;
import com.musify.musify.service.ArtistService;

@Controller
public class ArtistController {
	
    private ArtistService artistService = new ArtistService();

    // Home Page.
	/*@GetMapping("home")
	public ModelAndView home(BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("allArtist", artistService.allArtist());
	    mav.setViewName("index");
	    
	    return mav;
	}*/
    
    @GetMapping("/home")
	public String home(Model model) {
    	model.addAttribute("allArtist", artistService.allArtist());
	    
	    return "index";
	}

	 @PostMapping("/home")
     public String addArtist(@RequestParam("name") String name,
    	@RequestParam("year") int year, Model model) {		 
		 
		 Artist artistToInsert = new Artist();
		 artistToInsert.setName(name);
		 artistToInsert.setYear(year);
		 
		artistService.addArtist(artistToInsert);
		return "redirect:/home";
     }
	 
	 @GetMapping("/add-people")
		public String addPeople() {		    
		    return "add-people";
		}
	 
}
