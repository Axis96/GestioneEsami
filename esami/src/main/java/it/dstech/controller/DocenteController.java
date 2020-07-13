package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Docente;
import it.dstech.service.DocenteService;
import it.dstech.service.EsameService;

@Controller
public class DocenteController {
	@Autowired
	private DocenteService docenteService;
	@Autowired
	private EsameService esamiService;

	@GetMapping(value = "/docente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Docente docente = docenteService.findUserByUsername(auth.getName());
		modelAndView.addObject("username", "Welcome " + docente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("docente/home");
		modelAndView.addObject("listaEsami", docente.getListaEsami());
		return modelAndView;
	}

	
}