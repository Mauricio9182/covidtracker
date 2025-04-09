package umg.com.umg.covidtracker.model;

import lombok.Data;

@Data
public class Region {
    private String iso;
    private String name;
    private String province;
    private String lat;
    private String _long; // cuidado: 'long' es palabra reservada

    // Si no estás usando Lombok, genera getters/setters
}