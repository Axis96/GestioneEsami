package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Docente;
import it.dstech.model.Studente;
import it.dstech.service.DocenteService;
import it.dstech.service.EsameService;
import it.dstech.service.StudenteService;

@Controller
public class UserController {
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private EsameService esamiService;

	@GetMapping(value = "/utente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Docente docente = docenteService.findUserByUsername(auth.getName());
		if(docente != null) {
			modelAndView.addObject("username", "Welcome " + docente.getUsername());
			modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
			modelAndView.setViewName("docente/home");
			modelAndView.addObject("listaEsami", docente.getListaEsami());
			return modelAndView;
		}	
		Studente studente = studenteService.findUserByUsername(auth.getName());
		modelAndView.addObject("username", "Welcome " + studente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("studente/home");
		modelAndView.addObject("listaEsami", studente.getStoricoEsami());
		return modelAndView;
	}
}