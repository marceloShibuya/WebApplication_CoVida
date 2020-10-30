package br.com.fiap.CoVida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Convenio;
import br.com.fiap.CoVida.model.Doador;

public interface ConvenioRepository extends JpaRepository<Convenio, Long>{
	
	List<Convenio> findByDoador(Doador doador);

}
