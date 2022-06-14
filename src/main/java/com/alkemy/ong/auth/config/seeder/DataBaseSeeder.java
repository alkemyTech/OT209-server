package com.alkemy.ong.auth.config.seeder;

import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataBaseSeeder {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    private OrganizationRepository orgRepository;

    private static final String PASSWORD = "12345678";
    private static final String HOST_EMAIL = "@test.com";
    private static final String firstNameUser[] = {"Patricia", "Ronald", "Carlos", "Nathaniel", "Caitlin", "Juan", "Thanos", "Will"};
    private static final String lastNameUser[] = {"Brett", "Kathleen", "Brandi", "Craig", "Katrina", "Peralta", "Smith", "Doll"};
    private static final String organization_info[]={"Marolio",
                                                     "stringPath,", 
                                                     "Calle Sabor 490", 
                                                     "999911", 
                                                     "marolio@gmail.com", 
                                                     "Bienvenido a Marolio", 
                                                     "Le damos sabor a tu vida", 
                                                     "https://www.instagram.com/MarolioOficial", //Instagram
                                                     "https://www.facebook.com/MarolioOficial",
                                                     "https://www.linkedin.com/MarolioOficial"};//Linkedin


    @EventListener
    public void seed(ContextRefreshedEvent event) throws IOException {
        if (roleRepository.findAll().isEmpty()) {
            createRoles();
        }
        if (userRepository.findAll().isEmpty()) {
            createUsers();
        }
        if (activityRepository.findAll().isEmpty()) {
            createActivity();
        }
        if (orgRepository.findAll().isEmpty()){
            createOrganization();
       }
    }

    private void createRoles() {
        createRole(1L, RoleEnum.ADMIN);
        createRole(2L, RoleEnum.USER);
    }

    private void createUsers() {
        createUsers(RoleEnum.ADMIN);
        createUsers(RoleEnum.USER);
    }

    private void createUsers(RoleEnum applicationRole) {

        for (int index = 0; index < 8; index++) {
            userRepository.save(
                    UserEntity.builder()
                            .firstName(firstNameUser[index])
                            .lastName(lastNameUser[index])
                            .email(applicationRole.getSimpleRoleName().toLowerCase() + (index + 1) + HOST_EMAIL)
                            .password(encoder.encode(PASSWORD))
                            .rol(createListRole(applicationRole))
                            .build());
        }
    }

    private Set<RoleEntity> createListRole(RoleEnum applicationRole) {
        Set<RoleEntity> roles = roleRepository.findByName(applicationRole.getFullRoleName());
        return roles;
    }

    private void createRole(Long id, RoleEnum applicationRole) {
        RoleEntity role = new RoleEntity();
        role.setId(id);
        role.setName(applicationRole.getFullRoleName());
        role.setDescription(applicationRole.name());
        roleRepository.save(role);
    }

    private void createActivity() {
        for (int index = 0; index < 8; index++) {
            activityRepository.save(
                    ActivityEntity.builder()
                            .content("content " + (index + 1))
                            .image("image " + (index + 1))
                            .name("name " + (index + 1))
                            .build());
        }

    }

    private void createOrganization() {
        orgRepository.save(Organization.builder()
                .name(organization_info[0])
                .image(organization_info[1])
                .address(organization_info[2])
                .phone(Integer.valueOf(organization_info[3]))  
                .email(organization_info[4])
                .welcomeText(organization_info[5])
                .aboutUsText(organization_info[6])
                .urlInstagram(organization_info[7])
                .urlFacebook(organization_info[8])
                .urlLinkedin(organization_info[9])                
                .build() );
    
    }

}
