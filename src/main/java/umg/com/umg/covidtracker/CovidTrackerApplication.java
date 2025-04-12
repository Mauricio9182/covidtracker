package umg.com.umg.covidtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "umg.com.umg.covidtracker")
public class CovidTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CovidTrackerApplication.class, args);
    }
}