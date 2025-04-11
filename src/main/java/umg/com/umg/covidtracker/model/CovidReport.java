package umg.com.umg.covidtracker.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "covid_reports")
@Data
public class CovidReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String region;  // Almacenará solo el nombre de la región

    @Column(length = 100)
    private String province;

    @Column(nullable = false)
    private int confirmed;

    @Column(nullable = false)
    private int deaths;

    @Column(nullable = false)
    private int recovered;

    @Column(nullable = false)
    private LocalDate date;

    // Versión para auditoría/concurrencia
    @Version
    private Integer version;
}