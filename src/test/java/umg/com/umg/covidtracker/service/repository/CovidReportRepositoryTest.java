package umg.com.umg.covidtracker.service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import umg.com.umg.covidtracker.model.CovidReport;
import umg.com.umg.covidtracker.repository.CovidReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CovidReportRepositoryTest {

    @Autowired
    private CovidReportRepository repository;

    @Test
    public void testSaveAndFindAll() {
        CovidReport report = new CovidReport();
        report.setRegion("Guatemala");
        report.setProvince("Guatemala");
        report.setConfirmed(123);
        report.setDeaths(10);
        report.setRecovered(100);
        report.setDate(LocalDate.now());

        repository.save(report);

        List<CovidReport> allReports = repository.findAll();
        assertThat(allReports).hasSize(1);
        assertThat(allReports.get(0).getRegion()).isEqualTo("Guatemala");
    }
}
