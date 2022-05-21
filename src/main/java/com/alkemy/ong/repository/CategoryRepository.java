package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.ong.models.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
