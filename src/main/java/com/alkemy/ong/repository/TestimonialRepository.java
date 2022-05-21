package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.ong.models.entity.TestimonialEntity;

public interface TestimonialRepository extends JpaRepository<TestimonialEntity, Long>{
  
}
