package com.musify.musify.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musify.musify.model.People;
import com.musify.musify.service.PeopleService;

public class PeopleController {
	
	PeopleService peopleService = new PeopleService();
	
	@GetMapping("/")
	public String people(Model model) {
	    
	    return "people";
	}
	
	@PostMapping("/people")
     public String addPeople(@RequestParam("name") String name,
    	@RequestParam("years") int years, @RequestParam("member") int member, Model model) {		 
		 
		 People peopleToInsert = new People();
		 peopleToInsert.setName(name);
		 peopleToInsert.setYears(years);
		 peopleToInsert.setMember(member);
		 
		 peopleService.addPeople(peopleToInsert);
		 
		return redirectToPeopleView();
     }
	
	public String redirectToPeopleView() {		    
	    return "redirect:/home";
	}

}
