package org.tudai.entregable3.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tudai.entregable3.dto.CarreraConCantidadInscriptosDTO;
import org.tudai.entregable3.repository.CarreraRepository;
import org.tudai.entregable3.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("carreras")
@Api(value = "CarreraController", description = "REST API Carrera description")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/carreraCantidadInscriptos")
    public List<CarreraConCantidadInscriptosDTO> getCarreraCantidadInscriptos() {
        return carreraService.getCarreraCantidadInscriptos();
    }

}
