package org.example.tp3springboot.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.tp3springboot.Model.Persona;
import org.example.tp3springboot.Repository.PersonaRepository;
import org.example.tp3springboot.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("personas")
@Api(value = "PersonaController", description = "REST API Person description")
public class PersonaController {

    @Qualifier("PersonaService")
    @Autowired//Inyecta dependencias en Spring
    private final PersonaService service;

    public PersonaController(@Qualifier("PersonaService") PersonaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Persona>getPersonas() throws Exception {
        return service.findAll();
    }

    @ApiOperation(value = "Get list of persons by surname ", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/BySurname/{apellido}")
    public Iterable<Persona> getPersonsByApellido(@PathVariable String apellido) {
        return service.findAllByApellido(apellido);
    }

    @GetMapping("/ByName/{nombre}")
    public Iterable<Persona> getPersonsByName(@PathVariable String nombre) {
        return service.findAllByNombre(nombre);
    }

    @PostMapping("/")
    public Persona newPerson(@RequestBody Persona p) throws Exception {
        return service.save(p);
    }

    @PutMapping("/{id}")
    Persona replacePerson(@RequestBody Persona newPersona, @PathVariable Long id) throws Exception {
        return service.findById(id)
                .map(persona -> {
                    persona.setNombre(newPersona.getNombre());
                    persona.setApellido(newPersona.getApellido());
                    try {
                        return service.save(persona);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseGet(() -> {
                    newPersona.setIdPersona(id);
                    try {
                        return service.save(newPersona);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
         });
    }

    @DeleteMapping("/{id}")
    void deletePersona(@PathVariable Long id) throws Exception {
        service.deleteById (id);
    }

    @GetMapping("/ById/{id}")
    public Optional<Persona> getPersonaById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }
}
