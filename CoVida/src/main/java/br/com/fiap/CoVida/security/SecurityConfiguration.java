package br.com.fiap.CoVida.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(authenticationService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/contato/**", "/doador/**", "/convenio/**", "/documento/**")
				.authenticated()
			.antMatchers("/h2.console/**")
				.permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
		;
	}
	
	// Método apenas para resgatar a senha criptografada do Pedro e que foi salvo no arquivo data.sql
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("855533@df##"));
	}

}
