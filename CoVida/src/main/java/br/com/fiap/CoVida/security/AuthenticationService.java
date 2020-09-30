package br.com.fiap.CoVida.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.CoVida.model.Doador;
import br.com.fiap.CoVida.repository.DoadorRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private DoadorRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Doador> doador = repository.findByEmail(username);
		if(doador.isPresent()) return doador.get();
		
		throw new UsernameNotFoundException("usuário não encontrado"); 
	}

}
