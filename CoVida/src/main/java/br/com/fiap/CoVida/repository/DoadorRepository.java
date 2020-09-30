package br.com.fiap.CoVida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Doador;

public interface DoadorRepository extends JpaRepository<Doador, Long>{
	
	Optional<Doador> findByEmail(String email);

}
