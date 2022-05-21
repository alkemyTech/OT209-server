package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.ong.models.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

}
