package umg.com.umg.covidtracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.service.CovidService;

import java.util.List;
@Controller
public class FrontendController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<CovidReport> reports = covidService.getAllReports(); // o como se llame tu método
        model.addAttribute("reports", reports);
        return "dashboard";
    }



}



