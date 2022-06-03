package com.alkemy.ong.auth.config.seeder;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;

@Component
public class UserSeeder implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		this.loadUsers();
	}

	private void loadUsers() {
		Set<RoleEntity> roleAdmin = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
		Set<RoleEntity> roleUser = roleRepository.findByName(RoleEnum.USER.getFullRoleName());
		
		this.userRepository.save(new UserEntity("Patricia","Brett","patri-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Ronald","Kathleen","ronald-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Carlos","Brandi","carlos-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Nathaniel","Craig","nathaniel-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Caitlin","Katrina","caitlin-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Juan","Peralta","juan-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Thanos","Peralta","thanos-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Will","Smith","will-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Albert","Doll","albert-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		this.userRepository.save(new UserEntity("Javiera","Ignacia","javiera-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));
		
		this.userRepository.save(new UserEntity("Javier","Trebb","javier-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Katherine","Ronaldinho","katherine-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Locas","Cruce","locas-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Creit","Natham","creit-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Katerina","Caitlon","katerina-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Peras","Juanjo","peras-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Peralto","Thinas","peralto-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Jhon","Smith","jhon-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Albert","Dickenson","albertpen-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
		this.userRepository.save(new UserEntity("Ignacio","Javier","ignaciop-email@gmail.com",encoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));

	}
}
