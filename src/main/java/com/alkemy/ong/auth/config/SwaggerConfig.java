package  com.alkemy.ong.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SwaggerConfig {


    public static final String TESTIMONIAL_CONTROLLER= "Testimonial!";
	public static final String CATEGORY_CONTROLLER = "Category!";
    public static final String NEWS_CONTROLLER = "News!";
    public static final String USER_CONTROLLER = "Users!";
    public static final String AUTH_CONTROLLER = "Authentication!";
    public static final String MEMBER_CONTROLLER = "Member!";
    public static final String SLIDES_CONTROLLER = "Slides!";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()



                .tags(new Tag(TESTIMONIAL_CONTROLLER, "Services for creating, updating and deleting testimonials"))
                .tags(new Tag(AUTH_CONTROLLER, "Service for creating, authenticate and retrieve users"))
                .tags(new Tag(USER_CONTROLLER, "Service for update. delete and retrieve all users data"))
                .tags(new Tag(CATEGORY_CONTROLLER, "Services for creating, reading, updating and deleting categories"))
                .tags(new Tag(NEWS_CONTROLLER, "Services for creating, reading, updating and deleting news"))
                .tags(new Tag(SLIDES_CONTROLLER, "Service for create, update, delete and list slides"));

    }
  
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "ONG Alkemy",
                "Description: ONG BackEnd Java With SpringBoot",
                "1.0",
                "Terminos y Condiciones. ",
                new Contact("group209.com",
                        "www.Alkemy.org",
                        "alkemy@gmail.com"),
                "Licencia: Alkemy.org and Group pointer.",
                "www.ApiLicencia.com",
                Collections.emptyList());    
    }

    private SecurityContext securityContext(){
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}
