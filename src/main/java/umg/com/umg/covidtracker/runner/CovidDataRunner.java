package umg.com.umg.covidtracker.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import umg.com.umg.covidtracker.service.CovidService;

@Component
public class CovidDataRunner implements ApplicationRunner {

    private final CovidService covidService;

    public CovidDataRunner(CovidService covidService) {
        this.covidService = covidService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread.sleep(15000); // Espera 15 segundos
        covidService.fetchCovidData(); // Llama al servicio que consume la API
    }
    @Autowired
    private RestTemplate restTemplate;
}

