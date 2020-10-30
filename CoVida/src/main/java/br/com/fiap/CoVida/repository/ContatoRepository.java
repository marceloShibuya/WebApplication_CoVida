package br.com.fiap.CoVida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Contato;
import br.com.fiap.CoVida.model.Doador;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
	
	List<Contato> findByDoador(Doador doador);

}
