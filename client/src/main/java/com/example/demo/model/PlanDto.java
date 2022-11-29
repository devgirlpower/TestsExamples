package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.PatientRepo;

import lombok.Setter;
@Getter
@Setter
public class PlanDto {
	
	private String description;
    private String time;
    private String cpf;
    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
