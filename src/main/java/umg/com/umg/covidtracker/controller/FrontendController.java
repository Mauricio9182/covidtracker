package umg.com.umg.covidtracker.config.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import umg.com.umg.covidtracker.config.service.CovidService;

@Controller
public class FrontendController {

    @Autowired
    private CovidService covidService;


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // sin extensión .html
    }


}



