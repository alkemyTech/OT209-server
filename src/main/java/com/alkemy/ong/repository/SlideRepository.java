package com.alkemy.ong.repository;

import com.alkemy.ong.models.slide.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
    @Query(value = "SELECT MAX(s.numberOrder) FROM Slide s")
    int maxOrder();

    default Slide findByNumberOrder(int numberOrder) {
        return null;
    }

    Slide getById(Long id);


    @Query("SELECT m FROM Slide m JOIN m.organizationId n WHERE n.id LIKE :id ORDER BY m.numberOrder")
    List<Slide> findAllByOrganizationId(@Param("id") Long id);


}

