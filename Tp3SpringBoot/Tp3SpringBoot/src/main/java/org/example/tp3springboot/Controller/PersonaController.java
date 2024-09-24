package org.example.tp3springboot.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.tp3springboot.Model.Persona;
import org.example.tp3springboot.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personas")
@Api(value = "PersonaController", description = "REST API Person description")
public class PersonaController {

    @Qualifier("personaRepository")
    private final PersonaRepository repository;

    public PersonaController(@Qualifier("personaRepository") PersonaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Persona>getPersonas(){
        return repository.findAll();
    }

    @ApiOperation(value = "Get list of persons by surname ", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/BySurname/{apellido}")
    public Iterable<Persona> getPersonsBySurname(@PathVariable String apellido) {
        return repository.findAllBySurname(apellido);
    }

    @GetMapping("/ByName/{nombre}")
    public Iterable<Persona> getPersonsByName(@PathVariable String nombre) {
        return repository.findAllByName(nombre);
    }

}
