package it.dstech.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Esame;
import it.dstech.model.Risultato;
import it.dstech.model.Studente;
import it.dstech.service.EsameService;
import it.dstech.service.RisultatoService;
import it.dstech.service.StudenteService;

@Controller
public class StudenteController {
	@Autowired
	private RisultatoService risultatoService;
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private EsameService esamiService;

	@PostMapping(value= {"/studente/prenota"})
	public ModelAndView prenota(@RequestParam("idEsame")Long idEsame, @RequestParam("idStudente") Long idStudente) {
		ModelAndView modelAndView = new ModelAndView();
		Studente studente = studenteService.get(idStudente);
		Esame esame = esamiService.get(idEsame);
		modelAndView.addObject("media","la media è:"+ studenteService.mediaVoti(studente));
		
		if (esamiService.checkEsame(esame, studente)) {
			modelAndView.addObject("messaggio", "L'esame è già stato prenotato");
		} else {
			modelAndView.addObject("messaggioSucc", "L'esame è  stato prenotato");
			
			Risultato risultato = new Risultato(esame, studente);
			risultatoService.save(risultato);
			esame.getListaRisultati().add(risultato);
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
	
	@PostMapping(value= {"/studente/esamiPssati"})
	public ModelAndView esamiPassati(@RequestParam("idEsame")Long idEsame, @RequestParam("idStudente") Long idStudente) {
		ModelAndView modelAndView = new ModelAndView();
		Studente studente = studenteService.get(idStudente);
			
		List<Esame> listaEsamiPassati= studenteService.getListaEsamiPassati(studente);
	
		
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("listaEsami", esamiService.listaEsami());
		modelAndView.setViewName("studente/home");
		return modelAndView;
	}
}
