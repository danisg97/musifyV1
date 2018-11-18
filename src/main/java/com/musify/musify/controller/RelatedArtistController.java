package com.musify.musify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musify.musify.model.Related;
import com.musify.musify.service.ArtistService;
import com.musify.musify.service.RelatedService;

@Controller
public class RelatedArtistController {
	
	@Autowired
	RelatedService relatedService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	ArtistController artistController;
	
	private int idPath = 0;
	private int idToDelete = 0;

	@GetMapping("/add-related/{id}")
	public String addRelatedArtistView(Model model, @PathVariable int id) {
		model.addAttribute("allArtist", artistService.allArtist());
		model.addAttribute("idPath", id);
		idPath = id;
		
	    return "add-related";
	}
	
	// Damos de alta un artista relacionado.
	@PostMapping("/add-related")
    public String addRelatedArtist(@RequestParam("idRelated") int idRelated, Model model) {
		
		Related related = new Related(idPath, idRelated);
		 
		 relatedService.addRelated(related);
		 
		 System.out.println("Se ha añadido un related : " + idRelated + " al artista : " + idPath);
		 
		return "redirect:/related-artist/" + idPath;
    }
	
	@GetMapping("/delete-related/{id}")
	public String deleteRelatedArtistView(Model model, @PathVariable int id) {
		model.addAttribute("allArtist", artistService.allArtist());
		model.addAttribute("idToDelete", id);
		idToDelete = id;
		
	    return "delete-related";
	}
	
	// Damos de baja un artista relacionado.
	@PostMapping("/delete-related")
    public String deleteRelatedArtist(Model model) {
		
		Related related = new Related(artistController.getIdPath(), idToDelete);
		 
		 relatedService.deleteRelated(related);
		 
		 System.out.println("Se ha eliminado un related : " + idToDelete + " al artista : " + artistController.getIdPath());
		 
		return "redirect:/related-artist/" + artistController.getIdPath();
    }
	
}
