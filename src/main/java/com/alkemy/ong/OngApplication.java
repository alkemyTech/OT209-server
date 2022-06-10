package com.alkemy.ong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
public class OngApplication {
	public static void main(String[] args){
		try {
			log.info("Inicializando componentes");
			SpringApplication.run(OngApplication.class, args);
			log.info("Carga de componentes exitosa. Servidor en linea.");
		}catch (BeanCreationException ex) {
			Throwable realCause = unwrap(ex);
			log.info("Error. " + realCause);
		}
	}
	public static Throwable unwrap(Throwable ex) {
		if (ex != null && BeanCreationException.class.isAssignableFrom(ex.getClass())) {
			return unwrap(ex.getCause());
		} else {
			return ex;
		}
	}
}
