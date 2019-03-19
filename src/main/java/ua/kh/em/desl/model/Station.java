package ua.kh.em.desl.model;


public class Station {

    private int stationIcon;
    private String stationName;

    public Station(int stationIcon, String stationName) {
        this.stationIcon = stationIcon;
        this.stationName = stationName;
    }

    public int getStationIcon() {
        return stationIcon;
    }

    public String getStationName() {
        return stationName;
    }


}
