package org.example.tp3springboot.Utils;

import lombok.extern.slf4j.Slf4j;
import org.example.tp3springboot.Model.Direccion;
import org.example.tp3springboot.Model.Persona;
import org.example.tp3springboot.Repository.DireccionRepository;
import org.example.tp3springboot.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(@Qualifier("personaRepository") PersonaRepository repository, DireccionRepository direccionRepository, PersonaRepository personaRepository) {
        return args -> {
            /*
            // Crear instancias de Direccion
            Direccion d = new Direccion("Pinto",615);
            Direccion d1 = new Direccion("Avellaneda",1415);

            d=direccionRepository.save(d);
            d1=direccionRepository.save(d1);


            // Crear instancias de Persona directamente
            Persona p1 = new Persona((long) 16697123, "Seba", "Perez", d);
            Persona p2 = new Persona((long) 22365789, "Juan", "Dominguez", d1);

            // Guardar las personas en la base de datos
            p1 = personaRepository.save(p1);
            p2 = personaRepository.save(p2);
            /* hace lo mismo que la anterior
            log.info("Preloading " + repository.save(new Persona((long) 16697123,"Seba", "Perez",d)));
            log.info("Preloading " + repository.save(new Persona((long) 22365789, "Juan", "Dominguez",d1)));
             */
        };
    }
}
