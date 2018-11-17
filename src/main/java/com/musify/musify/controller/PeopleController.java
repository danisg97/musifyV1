package com.musify.musify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musify.musify.model.People;
import com.musify.musify.service.PeopleService;

@Controller
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@GetMapping("/people")
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
		 
		return "redirect:/people";
     }
}
