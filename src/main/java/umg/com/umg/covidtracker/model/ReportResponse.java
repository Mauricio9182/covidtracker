package umg.com.umg.covidtracker.model;

import lombok.Data;

import java.util.List;

@Data
public class ReportResponse {
    private List<DataItem> data;

    @Data
    public static class DataItem {
        private String region;
        private String province;
        private int confirmed;
        private int deaths;
        private int recovered;
    }
}