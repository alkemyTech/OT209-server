package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.models.entity.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

    boolean existsById(Long id);
}
