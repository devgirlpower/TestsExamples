package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.IntegrationClassCep;
import com.example.demo.model.Address;
import com.example.demo.model.Patient;
import com.example.demo.model.PatientDoesNotExist;
import com.example.demo.model.Plan;
import com.example.demo.model.PlanDto;
import com.example.demo.repository.PatientRepo;
import com.example.demo.repository.PlanRepo;


@Service
public class PlanService {
	@Autowired
	private PlanRepo repo;
	@Autowired
	private PatientRepo repop;
	//outros métodos omitidos -- > listar e etc
	public Plan savePlan(PlanDto planDto) throws Exception {
	//interação
		String g = planDto.getCpf();
		Optional<Patient> patient = repop.findByCpf(g);
		Address address = IntegrationClassCep.integrationCep(planDto.getCep());
	      if (patient.isEmpty()) {
	           throw new  PatientDoesNotExist("Paciente não encontrado");
	      }
	     // estado - agenda
	    Plan plan_obj= new Plan (planDto.getDescription(), planDto.getTime(),patient.get(),
	    		address.getLogradouro(),address.getBairro(), address.getLocalidade());
	    return 	repo.save(plan_obj);
	}

}
