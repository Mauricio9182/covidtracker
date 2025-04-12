package umg.com.umg.covidtracker.config.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import umg.com.umg.covidtracker.config.service.CovidService;
import umg.com.umg.covidtracker.config.model.ReportResponse;

@Component
@RequiredArgsConstructor
public class CovidRunner implements CommandLineRunner {

    private final CovidService covidService;

    @Override
    public void run(String... args) {
        // Hilo que se ejecuta 15 segundos después de iniciar la app
        new Thread(() -> {
            try {
                System.out.println("⏳ Esperando 15 segundos...");
                Thread.sleep(15000); // espera 15 segundos

                System.out.println("✅ Ejecutando llamado a la API...");
                ReportResponse response = covidService.getCovidReport("GTM", "2022-04-16");

                // Muestra los resultados en consola
                System.out.println("📊 Reporte recibido:");
                if (response.getData() != null) {
                    response.getData().forEach(data -> {
                        System.out.println("Region: " + data.getRegion());
                        System.out.println("Provincia: " + data.getProvince());
                        System.out.println("Confirmados: " + data.getConfirmed());
                        System.out.println("Muertes: " + data.getDeaths());
                        System.out.println("Recuperados: " + data.getRecovered());
                        System.out.println("----------");
                    });
                } else {
                    System.out.println("⚠️ No se recibió información en la respuesta.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}