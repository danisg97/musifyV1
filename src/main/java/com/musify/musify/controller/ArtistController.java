package com.musify.musify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepository;
import com.musify.musify.service.ArtistService;
import com.musify.musify.service.StyleService;

@Controller
public class ArtistController {
	
	@Autowired
    private ArtistService artistService;
	
	@Autowired
    private StyleService styleService;
	
	@Autowired
    private ArtistRepository artistRepository;
    
    @GetMapping("/artist")
	public String artist(Model model) {
    	// List all artist.
    	model.addAttribute("allArtist", artistService.allArtist());
    	// List all styles.
    	model.addAttribute("allStyles", styleService.allStyles());
	    
	    return "artist";
	}
    
    @GetMapping("/related-artist/{id}")
	public String relatedArtist(Model model, @PathVariable int id) {
    	// List all related artist.
    	model.addAttribute("allRelatedArtist", artistRepository.findRelatedArtist(id));
	    
	    return "related-artist";
	}
    
    @GetMapping("/add-artist")
	public String addArtistView(Model model) {
	    
	    return "add-artist";
	}

	 @PostMapping("/add-artist")
     public String addArtist(@RequestParam("name") String name,
    	@RequestParam("year") String year, Model model) {		 
		 
		 Artist artistToInsert = new Artist();
		 artistToInsert.setName(name);
		 
		 if(!StringUtils.isEmpty(year)) {
			 artistToInsert.setYear(Integer.parseInt(year));
		 }
		 
		artistService.addArtist(artistToInsert);
		return "redirect:/artist";
     }
	 
	 public String redirectToPeopleView() {		    
	    return "people";
	 }
	 
	// Redirect to Related Artist View.
	public String redirectToRelatedArtistView(Model model) {
			
		return "related-artist";
	}
	 
}
