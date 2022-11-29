package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.exceptions.base.MockitoException;
//import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Patient;
import com.example.demo.model.PatientDoesNotExist;
import com.example.demo.model.Plan;
import com.example.demo.model.PlanDto;
import com.example.demo.repository.PatientRepo;
import com.example.demo.repository.PlanRepo;
import com.example.demo.service.PlanService;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class PlanServiceTest {
	@InjectMocks
	PlanService service;
	@Mock
	PatientRepo repoP;
	@Mock
	PlanRepo repository;
	@Captor
	ArgumentCaptor<Plan> planCaptor;
//salvar com sucesso
	@Test
	void testSave() throws Exception {
		
        Patient patient = new Patient();
        patient.setId(2L);
        patient.setName("Paciente");
        patient.setCpf("123");
       // Plan plan = new Plan("agendamento","12:30",patient);
        PlanDto dto = new PlanDto();
        dto.setCpf("123");
        dto.setDescription("test");
        dto.setCep("93228120");
        dto.setTime("12:30");
        
        Mockito.when(repoP.findByCpf(dto.getCpf())).thenReturn(Optional.of(patient));
        		
        
        service.savePlan(dto);

        // assertions
        Mockito.verify(repoP).findByCpf(ArgumentMatchers.any());
        
        Mockito.verify(repoP).findByCpf(dto.getCpf());
       
        Mockito.verify(repository).save(planCaptor.capture());
        Plan planSave = planCaptor.getValue();

        Assertions.assertThat(planSave.getPatient()).isNotNull();
  
		
	}
	
	
	//Não deve salvar
	
	@Test
	void notSave() {
		Patient patient = new Patient();
        patient.setId(2L);
        patient.setName("Paciente");
        patient.setCpf("123");
       // Plan plan = new Plan("agendamento","12:30",patient);
        PlanDto dto = new PlanDto();
        dto.setCpf("123");
        dto.setDescription("test");
        dto.setTime("12:30");
        dto.setCep("93228120");
		  

	        Mockito.when(repoP.findByCpf(ArgumentMatchers.any())).thenReturn(Optional.empty());

	        PatientDoesNotExist patientDoesNotExist = assertThrows(PatientDoesNotExist.class, () -> {
	            service.savePlan(dto);
	        });

	        Assertions.assertThat(patientDoesNotExist.getMessage()).isEqualTo("Paciente não encontrado");
		
	}
}
