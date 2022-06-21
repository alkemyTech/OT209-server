package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.models.entity.Slide;
import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    Slide findBySlideOrder(int slideOrder);

    Slide getById(Long id);

    Slide findTopByOrderBySlideOrderDesc();

    List<Slide> findByOrganizationIdIs(Long id);

    boolean existsById(Long id);

    boolean existsBySlideOrder(int order);
}
