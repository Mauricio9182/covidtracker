package umg.com.umg.covidtracker.repository;

import umg.com.umg.covidtracker.model.CovidReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CovidReportRepository extends JpaRepository<CovidReport, Long> {

    @Query("SELECT r FROM CovidReport r WHERE " +
            "(:region IS NULL OR r.region LIKE :region) AND " +
            "(:fechaInicio IS NULL OR r.date >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR r.date <= :fechaFin) AND " +
            "r.confirmed >= :minimoCasos " +
            "ORDER BY r.date DESC")
    List<CovidReport> filtrarReportes(
            @Param("region") String region,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("minimoCasos") int minimoCasos);

    @Query("SELECT MAX(r.date) FROM CovidReport r")
    LocalDate findMaxDate();

    @Query("SELECT SUM(r.confirmed) FROM CovidReport r")
    Integer sumConfirmed();
}