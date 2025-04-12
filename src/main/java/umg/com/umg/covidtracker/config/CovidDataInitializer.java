package umg.com.umg.covidtracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import umg.com.umg.covidtracker.service.CovidService;

@Configuration
public class CovidDataInitializer {

    @Bean
    public CommandLineRunner dataLoader(CovidService covidService) {
        return args -> {
            System.out.println("⏳ Iniciando carga de datos COVID...");
            covidService.fetchCovidData();
            System.out.println("✅ Datos cargados exitosamente");
        };
    }
}