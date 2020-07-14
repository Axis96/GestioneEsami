package it.dstech.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class DocenteController {
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private EsameService esamiService;

	@GetMapping(value= {"/docente/dettagli"})
	public ModelAndView dettagli(Long idEsame) {
		ModelAndView modelAndView = new ModelAndView();
		Esame esame = esamiService.get(idEsame);
		List<Integer> voti = new ArrayList<>();
		for (Studente studente : esame.getListaStudentiPrenotati()) {
			for (Esame esameStudente : studente.getStoricoEsami()) {
				voti.add(esameStudente.getVoto());
			}
		}
		modelAndView.addObject("listaVoti", esame.getListaStudentiPrenotati());
		modelAndView.addObject("listaStudenti", esame.getListaStudentiPrenotati());
		modelAndView.setViewName("docente/dettagli");
		return modelAndView;
	}
	@PostMapping(value= {"/docente/creaEsame"})
	public ModelAndView creaEsame(Esame esame, Long matricola) {
		ModelAndView modelAndView = new ModelAndView();
		Docente docente = docenteService.get(matricola);
		Esame esameDocente = new Esame();
		esamiService.save(esameDocente);
		esameDocente.setNome(esame.getNome());
		esameDocente.setData(LocalDate.now());
		esameDocente.setDocente(docente);
		docente.getListaEsami().add(esameDocente);
		esamiService.save(esameDocente);
		modelAndView.addObject("listaEsami", docente.getListaEsami());
		modelAndView.addObject("esame", new Esame());
		modelAndView.setViewName("docente/home");
		return modelAndView;
	}
	
	
	
	
	
}