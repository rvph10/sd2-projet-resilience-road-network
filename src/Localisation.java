public class Localisation {

    private long id;
    private double latitude;
    private double longitude;
    private String name;
    private double altitude;

    public Localisation(long id, double latitude, double longitude, String name, double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.altitude = altitude;
    }

    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public double getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return "Localisation{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", altitude=" + altitude +
                '}';
    }
}
