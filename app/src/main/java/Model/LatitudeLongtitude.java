package Model;

public class LatitudeLongtitude {
    private double latitude;
    private double longtitude;
    private String marker;


    public LatitudeLongtitude(double latitude, double longtitude, String marker) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.marker = marker;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }







}
