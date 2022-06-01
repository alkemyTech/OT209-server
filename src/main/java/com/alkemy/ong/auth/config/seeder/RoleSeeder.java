package com.alkemy.ong.auth.config.seeder;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.repository.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		this.loadRoles();
		
	}
	
	private void loadRoles() {
		if (roleRepository.count() == 0) {
			roleRepository.save(new RoleEntity(RoleEnum.USER.getFullRoleName(),null,new Timestamp(System.currentTimeMillis())));
			roleRepository.save(new RoleEntity(RoleEnum.ADMIN.getFullRoleName(),null,new Timestamp(System.currentTimeMillis())));
		}
	}

}
