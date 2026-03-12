public class Road {

    private Integer id;
    private Localisation origin;
    private Localisation destination;
    private double distance;
    private String streetName;

    public Road(Integer id, Localisation origin, Localisation destination, double distance, String streetName) {

        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.streetName = streetName;
        this.id = id; 
    }  

    public Integer getId() {
        return this.id;
    }
    
    public Localisation getDestination() {
        return this.destination;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public Localisation getOrigin() {
        return this.origin;
    }

}
