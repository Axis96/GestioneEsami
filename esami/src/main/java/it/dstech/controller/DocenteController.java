//package it.dstech.controller;
//
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import it.dstech.model.Docente;
//import it.dstech.model.Studente;
//import it.dstech.service.DocenteService;
//import it.dstech.service.EsameService;
//
//
//
//@Controller
//public class DocenteController {
//	@Autowired
//	private DocenteService docenteService;
//
//	@Autowired
//	private EsameService esamiService;
//
//	@PostMapping(value = "/accessoUtente")
//	public ModelAndView accesso(@Valid User user, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		User userExists = userService.findUserByUsername(user.getUsername());
//		if (userExists != null) {
//			modelAndView.setViewName("profiloUtente");
//		} else {
//			modelAndView.addObject("successMessage", "Utente registrato con successo!");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("registrazione");
//		}
//		return modelAndView;
//	}
//
//	@GetMapping(value = "/admin/home")
//	public ModelAndView home() {
//		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findUserByUsername(auth.getName());
//		modelAndView.addObject("username", "Welcome " + user.getUsername() + "/" + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
//		modelAndView.setViewName("admin/home");
//		modelAndView.addObject("listaLibri", libroService.listaLibri());
//		modelAndView.addObject("libro", new Libro());
//
//		return modelAndView;
//	}
//
//	@PostMapping(value = { "/admin/creaLibro" })
//	public ModelAndView creaLibro(Libro libro) {
//		libroService.save(libro);
//
//		return home();
//	}
//
//	@GetMapping(value={"/admin/eliminaLibro/{id}"})
//		    public ModelAndView eliminaLibro(@PathVariable("id") Long id){
//		        libroService.delete(id);
//		        
//		        return home();
//		    }
//	
//}