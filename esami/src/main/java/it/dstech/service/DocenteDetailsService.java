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
import it.dstech.model.Docente;
import it.dstech.model.Ruolo;


@Service
public class DocenteDetailsService implements UserDetailsService {

    @Autowired
    private DocenteService docenteService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Docente docente = docenteService.findUserByUsername(username);
	        List<GrantedAuthority> authorities = getUserAuthority(docente.getRuolo());
	        return buildUserForAuthentication(docente, authorities);
	}


    private List<GrantedAuthority> getUserAuthority(Set<Ruolo> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Ruolo role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRuolo()));
        }
        return new ArrayList<GrantedAuthority>(roles);
    }

    private UserDetails buildUserForAuthentication(Docente docente, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(docente.getUsername(), docente.getPassword(),
                docente.getActive(), true, true, true, authorities);
    }



}

