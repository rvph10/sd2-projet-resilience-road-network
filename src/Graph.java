import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private HashMap<Long, Localisation> nodes;
    private HashMap<Long, List<Road>> adjacencyList;

    public Graph(String localisations, String roads) {
        nodes = new HashMap<>();
        adjacencyList = new HashMap<>();
        init(localisations, roads);
    }

    private void init(String localisationsFile, String roadsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(localisationsFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0]);
                String name = parts[1];
                double lat = Double.parseDouble(parts[2]);
                double lon = Double.parseDouble(parts[3]);
                double alt = Double.parseDouble(parts[4]);
                Localisation loc = new Localisation(id, lat, lon, name, alt);
                System.out.println("Loaded localisation: " + loc);
                nodes.put(id, loc);
                adjacencyList.put(id, new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(roadsFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                long sourceId = Long.parseLong(parts[0]);
                long targetId = Long.parseLong(parts[1]);
                double dist = Double.parseDouble(parts[2]);
                String streetName = parts[3];
                Localisation origin = nodes.get(sourceId);
                Localisation destination = nodes.get(targetId);
                if (origin != null && destination != null) {
                    adjacencyList.get(sourceId).add(new Road(origin, destination, dist, streetName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Localisation[] determinerZoneInondee(long[] idsOrigin, double epsilon) {
        // TODO
        return null;
    }

    public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(long idOrigin, long idDestination,
            Localisation[] floodedZone) {
        // TODO
        return null;
    }

    public Map<Localisation, Double> determinerChronologieDeLaCrue(long[] idsOrigin, double vWaterInit, double k) {
        // TODO
        return null;
    }

    public Deque<Localisation> trouverCheminDEvacuationLePlusCourt(long idOrigin, long idEvacuation, double vVehicule,
            Map<Localisation, Double> tFlood) {
        // TODO
        return null;
    }

}
