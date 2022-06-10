package com.alkemy.ong.auth.config.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.repository.OrganizationRepository;

@Component
public class OrganizationSeeder implements CommandLineRunner{

	@Autowired
	private OrganizationRepository orgRepository;
	
	@Override
	public void run(String... args) throws Exception {
		this.loadOrganizations();
	}

	private void loadOrganizations() {
		this.orgRepository.save(new Organization("Pañuelos de la tía","stringPath,", "Calle Pañu 225", 1234567, "tia_pañu@gmail.com", "Bienvenido a Pañuelos del a Tía", "Hacemos pañuelos", null, null, null));
		this.orgRepository.save(new Organization("Marolio","stringPath,", "Calle Sabor 490", 999911, "marolio@gmail.com", "Bienvenido a Marolio", "Le damos sabor a tu vida", null, "https://www.facebook.com/MarolioOficial", null));
	}
}