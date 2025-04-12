package umg.com.umg.covidtracker.model;

import lombok.Data;
import java.util.List;

@Data
public class ReportResponse {
    private List<DataItem> data;

    @Data
    public static class DataItem {
        private Region region;  // Ahora es un objeto, no String
        private String province;
        private int confirmed;
        private int deaths;
        private int recovered;
    }

    @Data
    public static class Region {
        private String name;  // Campo que contiene el nombre real
        private String iso;   // Campo adicional si lo necesitas
    }
}