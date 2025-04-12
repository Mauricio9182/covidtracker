package umg.com.umg.covidtracker.config.model;

import lombok.Data;

@Data
public class Report {
    private String date;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int confirmed_diff;
    private int deaths_diff;
    private int recovered_diff;
    private String last_update;
    private int active;
    private int active_diff;
    private double fatality_rate;
    private Region region;
}