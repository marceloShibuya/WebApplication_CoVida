package br.com.fiap.CoVida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
