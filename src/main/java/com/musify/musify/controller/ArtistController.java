package com.musify.musify.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepositoryImpl;
import com.musify.musify.service.ArtistService;
import com.musify.musify.service.StyleService;

@Controller
public class ArtistController {
	
	List<Artist> listaArtistStyle = new ArrayList<>();
	List<Artist> listaArtist = new ArrayList<>();
	
	private int idPath = 0;
	
	@Autowired
    private ArtistService artistService;
	
	@Autowired
    private StyleService styleService;

	@Autowired
    private ArtistRepositoryImpl artistRepository;
    
    @GetMapping("/artist")
	public String artistView(Model model) {
    	// Listado de artistas.
    	listaArtist = artistService.allArtist();
    	model.addAttribute("allArtist", artistService.allArtist());
    	
    	// Si viene una lista filtrada por estilo...
    	if(listaArtistStyle.size() != 0) {
    		model.addAttribute("allArtist", listaArtistStyle);
    	}
    	
    	// List all styles.
    	model.addAttribute("allStyles", styleService.allStyles());    	
	    
	    return "artist";
	}
    
    // Devolvemos una lista a la template en función de un estilo.
    @PostMapping("/artist")
    public String artistsByStyle(@RequestParam("id") int id, Model model) {		 
		Artist artistToShow = new Artist();
		artistToShow.setId(id);
		 
		listaArtistStyle = artistRepository.findArtistByStyle(artistToShow.getId()); 
		model.addAttribute("allArtist", listaArtistStyle);
		
		return "redirect:/artist";
    }
    
    @GetMapping("/add-artist")
	public String addArtistView(Model model) {
	    
	    return "add-artist";
	}

    // Damoe de alta un artista en la DB.
	@PostMapping("/add-artist")
    public String addArtist(@RequestParam("name") String name, @RequestParam("year") String year, Model model) {		 
		 
		Artist artistToInsert = new Artist();
		artistToInsert.setName(name);
		 
		if(!StringUtils.isEmpty(year)) {
			artistToInsert.setYear(Integer.parseInt(year));
		}
		 
		artistService.addArtist(artistToInsert);
		
		return "redirect:/artist";
     }
	 
	// Devolvemos los artistas relacionados con el artista deseado.
	@GetMapping("/related-artist/{id}")
	public String relatedArtist(Model model, @PathVariable int id) {
		 // Lista artistas relacionados.
	    model.addAttribute("allRelatedArtist", artistRepository.findRelatedArtist(id));
	    model.addAttribute("idPath", id);
	    idPath = id;    
	    
		return "related-artist";
	 }
	 
	 public String redirectToPeopleView() {		    
	    return "people";
	 }
	 
	// Redirect to Related Artist View.
	/*public String redirectToRelatedArtistView(Model model) {
			
		return "related-artist";
	}*/
	
	public int getIdPath() {
		return idPath;
	}

	public void setIdPath(int idPath) {
		this.idPath = idPath;
	}
	 
}
