package br.com.fiap.CoVida.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "doadores")
public class Doador implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "o campo nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "o campo email é obrigatório")
	@Email
	private String email;
	
	@Size(min = 8)
	private String senha;
	
	@OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<Contato>();
	
	@OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<Documento>();
	
	@OneToOne(mappedBy = "doador",cascade = CascadeType.ALL)
	private Convenio convenio;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	public List<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}	
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	@Override
	public String toString() {
		return "Doador [nome=" + nome + ", email=" + email + "]";
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
