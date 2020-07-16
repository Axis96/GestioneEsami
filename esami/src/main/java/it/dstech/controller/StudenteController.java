package it.dstech.controller;



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
		
		if (studenteService.mediaVoti(studente) >0 ) {
			modelAndView.addObject("media","La tua media è: "+ studenteService.mediaVoti(studente));
		} else {
			modelAndView.addObject("media","");
		}
		
		
		if (esamiService.checkEsame(esame, studente)) {
			modelAndView.addObject("messaggio", "L'esame è già stato prenotato o hai sostenuto questo esame meno di 2 sessioni fa");
		} else {
			
			
			Risultato risultato = new Risultato(esame, studente);
			
				risultato.setSessioneSostenuta(esame.getSessione());
				risultatoService.save(risultato);
				esame.getListaRisultati().add(risultato);
				esame.getListaStudentiPrenotati().add(studente);
				studente.getEsamiPrenotati().add(esame);
				studenteService.save(studente);
				esamiService.save(esame);
				modelAndView.addObject("messaggioSucc", "L'esame è  stato prenotato");
					
		}
		
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("listaEsami", esamiService.listaEsami());
		modelAndView.setViewName("studente/home");
		return modelAndView;
	}
	

	
	@PostMapping(value= {"/studente/esamiPassati"})
	public ModelAndView esamiPassati(@RequestParam("idStudente") Long idStudente) {
		ModelAndView modelAndView = new ModelAndView();
		Studente studente = studenteService.get(idStudente);
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("username", "Welcome " + studente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.addObject("listaRisultati", risultatoService.sortVotoDesc(idStudente));
		modelAndView.setViewName("studente/esamiPassati");
		return modelAndView;
	}
	
	
	@PostMapping(value= {"/studente/ordina"})
	public ModelAndView ordinaEsame(@RequestParam("idStudente") Long idStudente, int scelta) {
		ModelAndView modelAndView = new ModelAndView();
		Studente studente = studenteService.get(idStudente);
		if(scelta == 1) {
			modelAndView.addObject("listaEsami", risultatoService.sortVotoDesc(idStudente));
		}else {
			modelAndView.addObject("listaEsami", risultatoService.sortVotoAsc(idStudente));
		}
		modelAndView.addObject("idStudente", studente.getMatricola());
		modelAndView.addObject("username", "Welcome " + studente.getUsername());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("studente/esamiPassati");
		return modelAndView;
	}
}
