package org.example.tp3springboot.Controller;

import io.swagger.annotations.Api;
import jakarta.transaction.Transactional;
import org.example.tp3springboot.Model.Direccion;
import org.example.tp3springboot.Service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("direcciones")
@Api(value = "DireccionController", description = "REST API Direccion description")
public class DireccionController {

    @Qualifier("DireccionService")
    @Autowired
    private final DireccionService service;


    public DireccionController(@Qualifier("DireccionService") DireccionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Direccion> direcciones() throws Exception {
        return service.findAll();
    }
    @GetMapping("direcciones/{id}")
    public Optional<Direccion> findById(Integer id) throws Exception {
        return service.findById((id));
    }
    @PutMapping("/{id}")
    Direccion replaceDireccion(@RequestBody Direccion newDireccion, @PathVariable Integer id) throws Exception {
        return service.findById(id)
                .map(persona -> {
                    persona.setCalle(newDireccion.getCalle());
                    persona.setNumero(newDireccion.getNumero());
                    try {
                        return service.save(newDireccion);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseGet(() -> {
                    newDireccion.setId(id);
                    try {
                        return service.save(newDireccion);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    @DeleteMapping("/{id}")
    void deleteDireccion(@PathVariable Integer id) throws Exception {
       service.deleteById(id);
    }
    @GetMapping("/{calle}")
    public Iterable<Direccion> findByCalle(String calle) throws Exception {
        return service.findByCalle(calle);
    }
    @GetMapping("/{numero}")
    public Iterable<Direccion> findByNumero(Integer numero) throws Exception {
        return service.findByNumero(numero);
    }
    @PostMapping("/")
    public Direccion save(Direccion direccion) throws Exception {
        return service.save(direccion);
    }



}
