package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.entity.TestimonialEntity;
@Repository
public interface TestimonialRepository extends JpaRepository<TestimonialEntity, Long>{
  
}
