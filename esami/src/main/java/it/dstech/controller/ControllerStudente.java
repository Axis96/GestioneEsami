package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Studente;
import it.dstech.service.EsameService;
import it.dstech.service.StudenteService;

@Controller
public class ControllerStudente {
	@Autowired
	private StudenteService studenteService;
	@Autowired
	private EsameService esamiService;

	@GetMapping(value = "/studente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Studente studente = studenteService.findUserByUsername(auth.getName());
		modelAndView.addObject("username", "Welcome " + studente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("studente/home");
		return modelAndView;
	}

}
