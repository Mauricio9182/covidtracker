package umg.com.umg.covidtracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.repository.CovidReportRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovidServiceTest {

    private CovidService service;

    @Mock
    private CovidReportRepository reportRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CovidService(restTemplate);
        ReflectionTestUtils.setField(service, "reportRepository", reportRepository);
    }

    @Test
    public void testGetAllReportsReturnsList() {
        // Arrange
        CovidReport dummyReport = new CovidReport();
        dummyReport.setRegion("Guatemala");
        dummyReport.setConfirmed(100);
        dummyReport.setDeaths(5);
        dummyReport.setRecovered(80);

        List<CovidReport> dummyList = Collections.singletonList(dummyReport);

        // Simula que los datos ya fueron cargados
        ReflectionTestUtils.setField(service, "allReports", dummyList);

        // Act
        List<CovidReport> result = service.getAllReports();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Guatemala", result.get(0).getRegion());
        assertEquals(100, result.get(0).getConfirmed());
    }
}
