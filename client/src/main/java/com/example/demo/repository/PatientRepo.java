package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Patient;
import com.example.demo.model.Plan;

public interface PatientRepo extends JpaRepository<Patient, Long>{
	Optional<Patient> findByCpf(String cpf);

}
