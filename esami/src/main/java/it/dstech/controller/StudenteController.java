package it.dstech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Esame;
import it.dstech.model.Studente;
import it.dstech.service.DocenteService;
import it.dstech.service.EsameService;
import it.dstech.service.StudenteService;

@Controller
public class StudenteController {
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private EsameService esamiService;

	@PostMapping(value= {"/studente/prenota"})
	public ModelAndView prenota(@RequestParam("idEsame")Long idEsame, @RequestParam("idStudente") Long idStudente) {
		ModelAndView modelAndView = new ModelAndView();
		Studente studente = studenteService.get(idStudente);
		Esame esame = esamiService.get(idEsame);
		
		
		if (esamiService.checkEsame(esame, studente)) {
			modelAndView.addObject("messaggio", "L'esame è già stato prenotato");
		} else {
			esame.getListaStudentiPrenotati().add(studente);
			studente.getEsamiPrenotati().add(esame);
			studenteService.save(studente);
			esamiService.save(esame);
		}
		
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("listaEsami", esamiService.listaEsami());
		modelAndView.setViewName("studente/home");
		return modelAndView;
	}
	

}
