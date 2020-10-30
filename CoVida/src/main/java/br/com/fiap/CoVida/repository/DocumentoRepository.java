package br.com.fiap.CoVida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Doador;
import br.com.fiap.CoVida.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	
	List<Documento> findByDoador(Doador doador);

}
