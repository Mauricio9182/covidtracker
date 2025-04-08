package umg.com.umg.covidtracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import umg.com.umg.covidtracker.model.CovidStats;

@Service
public class CovidService {
    private final String API_URL = "https://disease.sh/v3/covid-19/countries/Guatemala";

    public CovidStats getStats() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, CovidStats.class);
    }
}