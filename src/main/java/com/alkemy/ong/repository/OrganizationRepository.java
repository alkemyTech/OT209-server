package com.alkemy.ong.repository;


import com.alkemy.ong.models.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {

   Organization findByEmail(String email);

}
