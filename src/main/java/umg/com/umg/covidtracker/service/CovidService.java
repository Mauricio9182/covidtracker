package umg.com.umg.covidtracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import umg.com.umg.covidtracker.model.CovidStats;
import umg.com.umg.covidtracker.model.ReportResponse;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CovidService {

    private final RestTemplate restTemplate;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    public ReportResponse getCovidReport(String iso, String date) {
        String url = "https://covid-19-statistics.p.rapidapi.com/reports?iso=" + iso + "&date=" + date;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ReportResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ReportResponse.class
        );

        return response.getBody();
    }
}