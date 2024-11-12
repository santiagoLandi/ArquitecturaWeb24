package org.tudai.reportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.reportservice.dto.ScooterDTO;
import org.tudai.reportservice.dto.ScooterReportTripYearDTO;
import org.tudai.reportservice.dto.ScooterStatusReportDTO;
import org.tudai.reportservice.dto.ScooterUsageReportDTO;
import org.tudai.reportservice.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // a. Reporte de uso de monopatines por kilómetros (con opción para incluir tiempos de pausa)
    @GetMapping("/scooter-usage")
    public List<ScooterUsageReportDTO> getScooterUsageReport(
            @RequestParam(required = false, defaultValue = "false") boolean includePauseTime) {
        return reportService.getScooterUsageReport(includePauseTime);
    }

    // b. Anular una cuenta (inhabilitar temporalmente)
    @PutMapping("/disable-account/{accountId}")
    public ResponseEntity<Void> disableAccount(@PathVariable Long accountId) {
        reportService.disableAccount(accountId);
        return ResponseEntity.ok().build();
    }

    // c. Consultar monopatines con más de X viajes en un cierto año
    @GetMapping("/scooters-with-min-trips")
    public ResponseEntity<List<ScooterReportTripYearDTO>> getScootersWithMinTrips(@RequestParam int year,@RequestParam int minTrips) {
        try{
            List<ScooterReportTripYearDTO> report = reportService.getScootersWithMoreThanXTrips(year, minTrips);
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    // d. Consultar el total facturado en un rango de meses de un año
    @GetMapping("/total-revenue")
    public BigDecimal getTotalRevenue(
            @RequestParam int year,
            @RequestParam int startMonth,
            @RequestParam int endMonth) {
        return reportService.getTotalRevenue(year, startMonth, endMonth);
    }

    // e. Consultar la cantidad de monopatines en operación versus en mantenimiento
    @GetMapping("/scooter-status")
    public ScooterStatusReportDTO getScooterStatusReport() {
        return reportService.getScooterStatusReport();
    }

    // f. Ajuste de precios para habilitar a partir de cierta fecha
    @PutMapping("/adjust-prices")
    public ResponseEntity<Void> adjustPrices(
            @RequestParam BigDecimal newPrice,
            @RequestParam LocalDate effectiveDate) {
        reportService.adjustPrices(newPrice, effectiveDate);
        return ResponseEntity.ok().build();
    }

    // g. Listado de monopatines cercanos a una ubicación
    @GetMapping("/nearby-scooters")
    public List<ScooterDTO> findNearbyScooters(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double radius) {
        return reportService.findNearbyScooters(latitude, longitude, radius);
    }

}
