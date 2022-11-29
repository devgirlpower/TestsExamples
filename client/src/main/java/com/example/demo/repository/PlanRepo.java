package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long>{
	Optional<Plan> findByTime(String time);
}
