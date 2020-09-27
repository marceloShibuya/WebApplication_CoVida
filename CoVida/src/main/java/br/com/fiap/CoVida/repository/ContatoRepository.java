package br.com.fiap.CoVida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.CoVida.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
