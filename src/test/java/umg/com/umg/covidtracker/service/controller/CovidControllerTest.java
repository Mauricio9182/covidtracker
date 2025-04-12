package umg.com.umg.covidtracker.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import umg.com.umg.covidtracker.controller.CovidController;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.service.CovidService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CovidController.class)
public class CovidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CovidService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllReportsEndpoint() throws Exception {
        CovidReport report = new CovidReport();
        report.setRegion("Guatemala");
        report.setProvince("Guatemala");

        when(service.getAllReports()).thenReturn(Collections.singletonList(report));

        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].region").value("Guatemala"));
    }
}
