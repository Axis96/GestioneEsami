package it.dstech.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.dstech.service.DocenteDetailsService;
import it.dstech.service.StudenteDetailsService;

@Configuration
	@EnableWebSecurity
	public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    private StudenteDetailsService studenteDetailsService;
	    
	    @Autowired
	    private DocenteDetailsService docenteDetailsService;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	                auth
	                    .userDetailsService(studenteDetailsService)
	                    .passwordEncoder(bCryptPasswordEncoder);
	                auth
                    .userDetailsService(docenteDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
	    }

	    
	    
	    @Configuration
	    @Order(1)
	    public static class StudSecurityConfig extends WebSecurityConfigurerAdapter {

	    	 @Override
	 	    protected void configure(HttpSecurity http) throws Exception {

	 	        String loginPage = "/login";
	 	        String logoutPage = "/logout";

	 	        http.
	 	                authorizeRequests()
	 	                .antMatchers("/").permitAll()
	 	                .antMatchers(loginPage).permitAll()
	 	                .antMatchers("/registrazione","/registrazioneStudente").permitAll()
	 	                .antMatchers("/registrazioneStudente").permitAll()
	 					.antMatchers("/registrazioneDocente").permitAll()
	 	                .antMatchers("/studente/**").hasRole("STUDENTE")
	 	                .anyRequest()
	 	                .authenticated()
	 	                
	 	                .and().csrf().disable()
	 	                .formLogin()
	 	                .loginPage(loginPage)
	 	                .loginPage("/")
	 	                .failureUrl("/login?error=true")
	 	                .defaultSuccessUrl("/studente/home")
	 	                .usernameParameter("username")
	 	                .passwordParameter("password")
	 	                
	 	                .and().logout()
	 	                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
	 	                .logoutSuccessUrl(loginPage).and().exceptionHandling();
	    }
	    	 
	    	 
//	    	 @Override
//	 	    protected void configure(HttpSecurity http) throws Exception {
//	 	        http.antMatcher("/studente*")
//	 	          .authorizeRequests()
//	 	          .anyRequest()
//	 	          .hasRole("STUDENTE")
//
//	 	          .and()
//	 	          .formLogin()
//	 	          .loginPage("/login")
//	 	          .failureUrl("/login?error=true")
//	 	          .defaultSuccessUrl("/studente/home")
//
//
//	 	          .and()
//	 	          .exceptionHandling()
//	 	          .accessDeniedPage("/403")
//
//	 	          .and()
//	 	          .csrf().disable();
//	 	    }
	    }
	    
	    
	    @Configuration
	    @Order(2)
	    public static class DocSecurityConfig extends WebSecurityConfigurerAdapter {
	    	
	    	
//	    	@Override
//		    @Order(2)
//		    protected void configure(HttpSecurity http) throws Exception {
//		        http.antMatcher("/docente*")
//		          .authorizeRequests()
//		          .anyRequest()
//		          .hasRole("DOCENTE")
//
//		          .and()
//		          .formLogin()
//		          .loginPage("/login")
//		          .failureUrl("/login?error=true")
//		          .defaultSuccessUrl("/docente/home")
//
//
//		          .and()
//		          .exceptionHandling()
//		          .accessDeniedPage("/403")
//
//		          .and()
//		          .csrf().disable();
//		    }

	    	 @Override
	 	    protected void configure(HttpSecurity http) throws Exception {

	 	        String loginPage = "/login";
	 	        String logoutPage = "/logout";

	 	        http.
	 	                authorizeRequests()
	 	                .antMatchers("/").permitAll()
	 	                .antMatchers(loginPage).permitAll()
	 	                .antMatchers("/registrazione","/registrazioneDocente").permitAll()
	 					.antMatchers("/registrazioneDocente").permitAll()
	 					.antMatchers("/docente/**").hasRole("DOCENTE")
	 	                .anyRequest()
	 	                .authenticated()
	 	                
	 	                .and().csrf().disable()
	 	                .formLogin()
	 	                .loginPage(loginPage)
	 	                .loginPage("/")
	 	                .failureUrl("/login?error=true")
	 	                .defaultSuccessUrl("/docente/home")
	 	                .usernameParameter("username")
	 	                .passwordParameter("password")
	 	                
	 	                .and().logout()
	 	                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
	 	                .logoutSuccessUrl(loginPage).and().exceptionHandling();
	 	        
	    }
	    }
	    
	    
	    
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	    }  
}

