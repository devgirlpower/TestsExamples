package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Address;
import com.example.demo.model.Plan;
import com.example.demo.model.PlanDto;
import com.example.demo.model.ResponseDTO;
import com.example.demo.repository.PlanRepo;
import com.example.demo.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/clinic")
public class Controller {

	private PlanService service;
	private PlanRepo repo;
	 @Autowired
	    ObjectMapper mapper;


	public Controller(PlanService service, PlanRepo repo) {
		this.service = service;
		this.repo = repo;
	}
	@GetMapping
	public ResponseEntity<List<Plan>> list() {
			List<Plan> listPlan = repo.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(listPlan);
	
	}
	@GetMapping("/{id}")
	public ResponseEntity<Plan> list(@PathVariable Long id) {
			Optional<Plan> plan = repo.findById(id);
			if(plan.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(plan.get());
			
	}
	//integrando com API externa 
	
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@RequestBody PlanDto dto) throws Exception {
		Plan plan_obj = service.savePlan(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(plan_obj));
			
	}
}
