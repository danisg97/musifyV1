package com.musify.musify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("musify")
public class ArtistController {

	@GetMapping("home")
	public ModelAndView homeView() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("index");
	    return mav;
        }
	
}
