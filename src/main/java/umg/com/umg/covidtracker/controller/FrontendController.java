package umg.com.umg.covidtracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.service.CovidService;

import java.util.List;

@RestController
public class FrontendController {
    @Autowired
    private CovidService covidService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<CovidReport> reports = covidService.getAllReports();
        model.addAttribute("reports", reports);
        return "dashboard";
    }
}


