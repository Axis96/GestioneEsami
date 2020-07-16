package it.dstech.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.dstech.model.Docente;
import it.dstech.model.Esame;
import it.dstech.model.Risultato;
import it.dstech.repository.EsameRepository;
import it.dstech.service.DocenteService;
import it.dstech.service.EsameService;
import it.dstech.service.StudenteService;
import it.dstech.service.RisultatoService;

@Controller
public class DocenteController {
	@Autowired
	private DocenteService docenteService;

	@Autowired
	private RisultatoService risultatoService;
	
	@Autowired
	private StudenteService studenteService;

	@Autowired
	private EsameService esamiService;
	


	@GetMapping(value = { "/docente/dettagli" })
	public ModelAndView dettagli(Long idEsame,@RequestParam("matricola") Long matricola) {
		ModelAndView modelAndView = new ModelAndView();
		Esame esame = esamiService.get(idEsame);
		List<Risultato> listaRisultato= risultatoService.listaRisultatiEsame(esame);
		

		modelAndView.addObject("risultato", new Risultato());
		modelAndView.addObject("idEsame", idEsame);
		modelAndView.addObject("matricola", matricola);
		modelAndView.addObject("listaRisultati" , listaRisultato);
		modelAndView.setViewName("docente/dettagli");
		return modelAndView;
	}

	@PostMapping(value = { "/docente/inserisciVoto" })
	public ModelAndView inserisciRisultato(Long idEsame, Long matricola, int voto, Long risultatoId) {
		Risultato risultato = risultatoService.get(risultatoId);	
		risultato.setVoto(voto);
		risultatoService.save(risultato);
		
		
		return dettagli(idEsame,matricola);
	}
	

	@PostMapping(value = { "/docente/creaEsame" })
	public ModelAndView creaEsame(Esame esame, Long matricola) {
		ModelAndView modelAndView = new ModelAndView();
		Docente docente = docenteService.get(matricola);
		Esame esameDocente = new Esame();
		

		esameDocente.setNome(esame.getNome());
		

		for (Esame esamePassato : docente.getListaEsami()) {
			if (esameDocente.getNome().equalsIgnoreCase(esamePassato.getNome())) {
				esameDocente.setSessione(esameDocente.getSessione() +1);
			}
		}
		
		esameDocente.setData(esamiService.reversedDateFormat(esame));
		esameDocente.setDocente(docente);
		docente.getListaEsami().add(esameDocente);
		esamiService.save(esameDocente);
		
		modelAndView.addObject("matricola", matricola);
		modelAndView.addObject("listaEsami", docente.getListaEsami());
		modelAndView.addObject("esame", new Esame());
		modelAndView.setViewName("docente/home");
		return modelAndView;
	}



}