package umg.com.umg.covidtracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.model.ReportResponse;
import umg.com.umg.covidtracker.repository.CovidReportRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CovidService {

    @Autowired
    private CovidReportRepository reportRepository;
    private List<CovidReport> allReports;

    public List<CovidReport> getAllReports() {
        return allReports;
    }
    public List<CovidReport> obtenerTodosLosReportes() {
        return reportRepository.findAll();
    }



    private final RestTemplate restTemplate;


    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    // Método mejorado con tus System.out.println
    @Scheduled(cron = "0 0 12 * * ?") // Ejecución diaria a mediodía
    public void fetchCovidData() {
        String iso = "GTM";
        String date = LocalDate.now().toString();

        System.out.println("⏳ Iniciando obtención de datos COVID...");
        System.out.println("🔗 URL de API: " + "https://covid-19-statistics.p.rapidapi.com/reports?iso=" + iso + "&date=" + date);

        ReportResponse report = this.getCovidReport(iso, date);

        if (report != null && report.getData() != null) {
            System.out.println("📊 Datos recibidos de la API. Procesando...");

            report.getData().forEach(d -> {
                CovidReport covidReport = new CovidReport();

                String regionName = (d.getRegion() != null) ? d.getRegion().getName() : "Desconocido";
                String provincia = d.getProvince() != null ? d.getProvince() : "N/A";

                // Tus mensajes de depuración
                System.out.println("📍 Región: " + regionName);
                System.out.println("🏘️ Provincia: " + provincia);
                System.out.println("🦠 Confirmados: " + d.getConfirmed());
                System.out.println("💀 Muertes: " + d.getDeaths());
                System.out.println("💚 Recuperados: " + d.getRecovered());

                covidReport.setRegion(regionName);
                covidReport.setProvince(provincia);
                covidReport.setConfirmed(d.getConfirmed());
                covidReport.setDeaths(d.getDeaths());
                covidReport.setRecovered(d.getRecovered());
                covidReport.setDate(LocalDate.parse(date));

                reportRepository.save(covidReport);
                System.out.println("💾 Registro guardado en BD: " + regionName + " - " + provincia);
            });
            System.out.println("✅ " + report.getData().size() + " registros guardados exitosamente");
        } else {
            System.out.println("⚠️ ¡Advertencia! No se recibieron datos de la API o el cuerpo está vacío");
        }
    }


    // Métodos nuevos con System.out.println
    public List<CovidReport> filtrarReportes(String region, LocalDate fechaInicio, LocalDate fechaFin, int minimoCasos) {
        System.out.println("🔍 Aplicando filtros:");
        System.out.println("   - Región: " + (region != null ? region : "Todas"));
        System.out.println("   - Fecha inicio: " + (fechaInicio != null ? fechaInicio : "Sin límite"));
        System.out.println("   - Fecha fin: " + (fechaFin != null ? fechaFin : "Sin límite"));
        System.out.println("   - Mínimo casos: " + minimoCasos);

        List<CovidReport> resultados = reportRepository.filtrarReportes(
                region != null ? "%" + region + "%" : null,
                fechaInicio,
                fechaFin,
                minimoCasos
        );

        System.out.println("📌 Resultados encontrados: " + resultados.size());
        return resultados;
    }

    public Map<String, Object> obtenerEstadisticas() {
        System.out.println("📈 Generando estadísticas...");

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalRegistros", reportRepository.count());
        stats.put("ultimaActualizacion", reportRepository.findMaxDate());
        stats.put("casosConfirmados", reportRepository.sumConfirmed());

        System.out.println("📊 Estadísticas generadas:");
        stats.forEach((k, v) -> System.out.println("   - " + k + ": " + v));

        return stats;
    }

    // Método existente sin cambios
    public ReportResponse getCovidReport(String iso, String date) {
        String url = "https://covid-19-statistics.p.rapidapi.com/reports?iso=" + iso + "&date=" + date;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("🔗 Llamando a API externa: " + url);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ReportResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ReportResponse.class
        );

        System.out.println("📥 Respuesta recibida. Status: " + response.getStatusCode());
        return response.getBody();
    }

}