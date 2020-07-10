package it.dstech.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dstech.model.Studente;
import it.dstech.model.Ruolo;

@Service
public class StudenteDetailsService implements UserDetailsService {

    @Autowired
    private StudenteService studenteService;


    @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Studente studente = studenteService.findUserByUsername(username);
	        List<GrantedAuthority> authorities = getUserAuthority(studente.getRuolo());
	        return buildUserForAuthentication(studente, authorities);
	}


   private List<GrantedAuthority> getUserAuthority(Set<Ruolo> userRoles) {
       Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
       for (Ruolo role : userRoles) {
           roles.add(new SimpleGrantedAuthority(role.getRuolo()));
       }
       return new ArrayList<GrantedAuthority>(roles);
   }

   private UserDetails buildUserForAuthentication(Studente docente, List<GrantedAuthority> authorities) {
       return new org.springframework.security.core.userdetails.User(docente.getUsername(), docente.getPassword(),
               docente.getActive(), true, true, true, authorities);
   }


}

