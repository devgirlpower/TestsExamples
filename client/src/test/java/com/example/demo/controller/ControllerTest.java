package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Patient;
import com.example.demo.model.PatientDoesNotExist;
import com.example.demo.model.Plan;
import com.example.demo.model.PlanDto;
import com.example.demo.repository.PatientRepo;
import com.example.demo.repository.PlanRepo;
import com.example.demo.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PlanRepo repository;
    @Autowired
    PlanService service;
    @Autowired
    PatientRepo repoP;
    private Long id;
	@BeforeEach
	void setUp() throws Exception {
		Patient patient = new Patient();
		patient.setName("Testeee");
		patient.setCpf("123456");
		patient.setEmail("testando@gmail");
        repoP.save(patient);
        Plan plan = new Plan("descricao","12:30",patient);
        Plan plan_save= repository.save(plan);
        this.id = plan_save.getId();
        
       
	}

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
		repoP.deleteAll();
	}
	//paciente por id
	@Test
    void listPlanId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clinic/"+this.id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
	@Test
    //salva plan com sucesso
    void savePlanSucess() throws Exception {
		//Git está me vendo -- agora estou modificado
       PlanDto dto = new PlanDto();
       dto.setCpf("123456");
       dto.setDescription("consulta");
       dto.setTime("12:30");
       
      String planMapper = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/clinic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(planMapper)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

	 @Test
	 //n salva plan com erro
	    void notSavePlan() throws Exception {
		 PlanDto dto = new PlanDto();
	       dto.setCpf("126");
	       dto.setDescription("consulta");
	       dto.setTime("12:30");
	       
	      String planMapper = mapper.writeValueAsString(dto);

	        mockMvc.perform(MockMvcRequestBuilders.post("/clinic")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .characterEncoding("UTF-8")
	                        .content(planMapper)
	                )
	       .andExpect(MockMvcResultMatchers.status().is4xxClientError())
	      .andDo(MockMvcResultHandlers.print());
	    }

}
