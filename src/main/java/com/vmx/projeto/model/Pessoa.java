package com.vmx.projeto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode estar vazio")
	@NotNull(message = "O campo nome não pde ser nulo")
	private String nome;
	
	@NotBlank(message = "O campo sobrenome não pode estar vazio")
	@NotNull(message = "O campo sobrenome não pode ser nulo")
	private String sobrenome;
	
	@NotBlank(message = "O campo email não pode estar vazio")
	@NotNull(message = "O campo email não pode ser nulo")
	private String email;
	
	@OneToMany(mappedBy="pessoa", orphanRemoval = true , cascade= CascadeType.ALL)
	private List<PessoaDetail> pessoaDetails;

	
	public List<PessoaDetail> getPessoaDetails() {
		return pessoaDetails;
	}

	public void setPessoaDetails(List<PessoaDetail> pessoaDetails) {
		this.pessoaDetails = pessoaDetails;
	}

	public Pessoa() {
	};

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
