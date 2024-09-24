package org.example.tp3springboot.Controller;

import io.swagger.annotations.Api;
import org.example.tp3springboot.Model.Direccion;
import org.example.tp3springboot.Repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("direcciones")
@Api(value = "DireccionController", description = "REST API Direccion description")
public class DireccionController {

    @Qualifier("direccionRepository")
    private final DireccionRepository reposiroty;


    public DireccionController(@Qualifier("direccionRepository")DireccionRepository reposiroty) {
        this.reposiroty = reposiroty;
    }

    @GetMapping("/")
    public Iterable<Direccion> direcciones(){
        return reposiroty.findAll();
    }

}
