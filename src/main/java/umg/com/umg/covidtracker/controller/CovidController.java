package umg.com.umg.covidtracker.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.com.umg.covidtracker.config.model.CovidReport;
import umg.com.umg.covidtracker.config.model.ReportResponse;
import umg.com.umg.covidtracker.config.service.CovidService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/covid")
public class CovidController {

    @Autowired
    private CovidService covidService;

    // Endpoint existente
    @GetMapping("/guatemala")
    public ReportResponse getGuatemalaStats() {
        String iso = "GTM"; // Corregido a GTM (código correcto)
        String date = LocalDate.now().toString();
        return covidService.getCovidReport(iso, date);
    }

    // Endpoint existente mejorado
    @GetMapping("/guardar")
    public String guardarCovidStats() {
        covidService.fetchCovidData();
        return "✅ Datos de Guatemala guardados correctamente en la base de datos.";
    }

    // Nuevo endpoint para consultar reportes con filtros
    @GetMapping("/reportes")
    public ResponseEntity<List<CovidReport>> getReportesFiltrados(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false, defaultValue = "0") int minimoCasos) {

        return ResponseEntity.ok(
                covidService.filtrarReportes(region, fechaInicio, fechaFin, minimoCasos)
        );
    }

    // Nuevo endpoint para estadísticas
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> getEstadisticas() {
        return ResponseEntity.ok(covidService.obtenerEstadisticas());
    }
}