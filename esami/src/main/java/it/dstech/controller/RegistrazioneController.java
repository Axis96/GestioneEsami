package it.dstech.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import it.dstech.model.Docente;
import it.dstech.model.Studente;
import it.dstech.service.DocenteService;
import it.dstech.service.StudenteService;

@Controller
public class RegistrazioneController {
	@Autowired
	private DocenteService docenteService;

	@Autowired
	private StudenteService studenteService;

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
			
		}
		return registrazione();
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
			modelAndView.addObject("successMessage", "Utente registrato con successo!");
			modelAndView.addObject("studente", new Studente());
			modelAndView.setViewName("registrazione");
		}
		return modelAndView;
	}

}
