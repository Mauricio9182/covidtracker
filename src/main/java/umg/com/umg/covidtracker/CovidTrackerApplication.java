package umg.com.umg.covidtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import umg.com.umg.covidtracker.config.service.CovidService;

@SpringBootApplication
public class CovidTrackerApplication implements CommandLineRunner {

    @Autowired
    private CovidService covidService;

    public static void main(String[] args) {
        SpringApplication.run(CovidTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        covidService.fetchCovidData();
    }
}
