package umg.com.umg.covidtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umg.com.umg.covidtracker.model.CovidStats;
import umg.com.umg.covidtracker.model.ReportResponse;
import umg.com.umg.covidtracker.service.CovidService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/covid")
public class CovidController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/guatemala")
    public ReportResponse getGuatemalaStats() {
        String iso = "GT"; // Guatemala
        String date = LocalDate.now().toString();
        return covidService.getCovidReport(iso, date); // Usa el método que ya tienes
    }
}