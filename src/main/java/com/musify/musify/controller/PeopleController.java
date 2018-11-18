package com.musify.musify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.musify.musify.model.People;
import com.musify.musify.repository.PeopleRepository;
import com.musify.musify.repository.PeopleRepositoryImpl;
import com.musify.musify.service.PeopleService;

@Controller
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	PeopleRepositoryImpl peopleRepository;
	
	private int idPath = 0;
	
	@GetMapping("/people")
	public String people(Model model) {
	    
	    return "people";
	}
	
	@GetMapping("/add-people")
	public String addPeopleView(Model model) {
	    
	    return "add-people";
	}
	
	// Creamos una persona en el sistema.
	@PostMapping("/add-people")
     public String addPeople(@RequestParam("name") String name, @RequestParam("years") String years, Model model) {		 
		 People peopleToInsert = new People();
		 peopleToInsert.setName(name);
		 
		 if(!StringUtils.isEmpty(years)) {
			 peopleToInsert.setYears(Integer.parseInt(years));
		 }
		 
		 peopleService.addPeople(peopleToInsert);
		 
		 System.out.println("Name : " + name + ", Years : " + years);
		 
		return "redirect:/people";
     }
	
	@GetMapping("/add-peopleartist/{id}")
	public String addPeopleArtistView(Model model, @PathVariable int id) {
		model.addAttribute("allPeople", peopleService.allPeople());
		idPath = id;
		
	    return "add-peopleartist";
	}
	
	@PostMapping("/add-peopleartist")
    public String addPeopleArtist(@RequestParam("idPeople") int idPeople, Model model) {		 		 
		 
		 peopleRepository.addPeopleToAnArtist(idPeople, idPath);
		 
		 System.out.println("Se ha añadido el artista : " + idPath + " a la persona : " + idPeople);
		 
		return "redirect:/add-peopleartist/" + idPath;
    }
}
