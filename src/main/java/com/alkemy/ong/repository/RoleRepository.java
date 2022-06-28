package com.alkemy.ong.repository;

import com.alkemy.ong.models.entity.RoleEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    public Set<RoleEntity> findByName(String roleName);
}
