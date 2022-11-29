package com.example.demo.model;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Plan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String time;
    @Embedded
    private Address address;

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getTime() {
		return time;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Plan() {
    	
    }
    @ManyToOne
    private Patient patient;
	public Plan(String description, String time, Patient patient) {
		this.description = description;
		this.time = time;
		this.patient = patient;
	}
	public Plan(Long id,String description, String time, Patient patient) {
		this.description = description;
		this.time = time;
		this.patient = patient;
		this.id=id;
	}
    
	public Plan(String description, String time, Patient patient, String city, String bairro, String rua) {
		this.description = description;
		this.time = time;
		this.patient = patient;
		this.address = new Address(city, bairro, rua);
	}
    
	
	
}
