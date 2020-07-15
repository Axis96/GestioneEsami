package it.dstech.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import it.dstech.model.Docente;
import it.dstech.model.Esame;
import it.dstech.model.Studente;
import it.dstech.service.DocenteService;
import it.dstech.service.EsameService;
import it.dstech.service.StudenteService;

@Controller
public class RegistrazioneController {
	@Autowired
	private DocenteService docenteService;

	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private EsameService esameService;

	@GetMapping(value = { "/", "/login" })
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homepage");
		return modelAndView;
	}

	@GetMapping(value = "/registrazione")
	public ModelAndView registrazione() {
		ModelAndView modelAndView = new ModelAndView();
		Docente docente = new Docente();
		Studente studente = new Studente();
		modelAndView.addObject("docente", docente);
		modelAndView.addObject("studente", studente);
		modelAndView.setViewName("registrazione");
		return modelAndView;

	}

	@PostMapping(value = "/registrazioneDocente")
	public ModelAndView registrazioneDocente(@Valid Docente docente, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Docente userExists = docenteService.findUserByUsername(docente.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Utente gia registrato");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrazione");
		} else {
			docenteService.save(docente);
			modelAndView.addObject("successMessage", "Docente registrato con successo!");
		}
		Docente docente2 = new Docente();
		Studente studente = new Studente();
		modelAndView.addObject("docente", docente2);
		modelAndView.addObject("studente", studente);
		modelAndView.setViewName("registrazione");
		return modelAndView;
	}

	@PostMapping(value = "/registrazioneStudente")
	public ModelAndView registrazioneStudente(@Valid Studente studente, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Studente userExists = studenteService.findUserByUsername(studente.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Utente gia registrato");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrazione");
		} else {
			studenteService.save(studente);
			modelAndView.addObject("successMessage2", "Studente registrato con successo!");
		}
		Docente docente2 = new Docente();
		Studente studente2 = new Studente();
		modelAndView.addObject("docente", docente2);
		modelAndView.addObject("studente", studente2);
		modelAndView.setViewName("registrazione");
		return modelAndView;
	}
	
	@GetMapping(value = "/utente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Docente docente = docenteService.findUserByUsername(auth.getName());
		if(docente != null) {
			modelAndView.addObject("matricola", docente.getMatricola());
			modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
			modelAndView.setViewName("docente/home");
			modelAndView.addObject("listaEsami", docente.getListaEsami());
			modelAndView.addObject("esame", new Esame());
			return modelAndView;
		}	
		Studente studente = studenteService.findUserByUsername(auth.getName());
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("username", "Welcome " + studente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.addObject("listaEsami", esameService.listaEsami());
		modelAndView.setViewName("studente/home");
		return modelAndView;
	}
	
}