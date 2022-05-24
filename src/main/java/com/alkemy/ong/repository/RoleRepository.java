package com.alkemy.ong.repository;


import com.alkemy.ong.models.entity.Role;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	
	public Set<Role> findByName(String roleName);
}
