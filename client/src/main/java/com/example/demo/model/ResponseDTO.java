package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	private String description;
    private String time;
    private String namePatient;
    private Address address;

	public String getDescription() {
		return description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNamePatient() {
		return namePatient;
	}

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResponseDTO (Plan plan) {
    	this.description=plan.getDescription();
    	this.time=plan.getTime();
    	this.namePatient = plan.getPatient().getName();
    	this.address = plan.getAddress();
    	
    }
	public ResponseDTO(String description, String time, String namePatient, Address address) {
		super();
		this.description = description;
		this.time = time;
		this.namePatient = namePatient;
		this.address = address;
	}

}
