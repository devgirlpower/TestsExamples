package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	
	private String logradouro;
	private String bairro;
	private String localidade;

	public String getLogradouro() {
		return logradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Address(String logradouro, String bairro, String localidade) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
	}
	public Address() {
		
	}
	
	
}
