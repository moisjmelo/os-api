package com.moises.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.moises.os.domain.Client;

import jakarta.validation.constraints.NotEmpty;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O Nome é obrigatório")
	private String name;

	@CPF
	@NotEmpty(message = "O CPF é obrigatório")
	private String cpf;
	@NotEmpty(message = "O Telefone é obrigatório")
	private String telephone;

	public ClientDTO() {
		super();
	}

	public ClientDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.telephone = obj.getTelephone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
