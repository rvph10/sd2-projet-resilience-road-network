# Resilience Road Network

## Overview

Flood crisis simulator for the city of Brussels. Given a road network represented as a weighted directed graph, the system determines which zones get flooded and computes optimal evacuation routes in both static and time-aware scenarios.

The simulator is designed to handle graphs of up to **1,000,000 nodes** with optimal time and memory complexity.

---

## Project Structure

```
.
├── src/
│   ├── Localisation.java       # Graph node (geographic point)
│   ├── Road.java               # Graph edge (road segment)
│   └── Graph.java              # Core graph + all 4 algorithms
├── data/
│   ├── nodes_{N}.csv           # Node datasets (N = 10 to 1,000,000)
│   └── edges_{N}.csv           # Edge datasets (N = 10 to 1,000,000)
├── tests/
│   ├── TestSimulator10.java
│   ├── TestSimulator1000a.java
│   ├── TestSimulator1000b.java
│   ├── TestSimulator1000000a.java
│   └── TestSimulator1000000b.java
├── results/
│   └── TestSimulator*_Resultats.txt   # Expected outputs
└── SD2_Projet2026.pdf                 # Assignment specification
```

---

## Data Format

**nodes\_{N}.csv**

```
id,name,lat,lon,alt
3060525655,Street Unknown road,50.8674252,4.6988278,32.2
```

**edges\_{N}.csv**

```
source,target,dist,street_name
1132625443,1132625495,3.81,Unnamed road
```

---

## Physical Model

Water propagation is governed by two rules:

**Slope** between node A and node B:

```
S = (Altitude(A) - Altitude(B)) / Distance(A, B)
```

**Propagation condition** (with tolerance ε in metres):

```
Altitude(Y) ≤ Altitude(X) + ε
```

Water only flows from X to Y if a road exists and the above condition holds.

---

## Algorithms

### Phase 1 — Static Analysis

| #   | Method                                                | Algorithm | Description                                                              |
| --- | ----------------------------------------------------- | --------- | ------------------------------------------------------------------------ |
| 1   | `determinerZoneInondee`                               | BFS       | Determines all nodes reachable by water from given starting points       |
| 2   | `trouverCheminLePlusCourtPourContournerLaZoneInondee` | BFS       | Shortest path (by number of roads) from A to B avoiding the flooded zone |

### Phase 2 — Temporal Analysis

| #   | Method                                | Algorithm | Description                                                                              |
| --- | ------------------------------------- | --------- | ---------------------------------------------------------------------------------------- |
| 3   | `determinerChronologieDeLaCrue`       | Dijkstra  | Computes the exact time t at which water reaches each node                               |
| 4   | `trouverCheminDEvacuationLePlusCourt` | Dijkstra  | Fastest evacuation route, avoiding roads that will be flooded before the vehicle arrives |

**Water speed** evolves along the path:

```
V_water(p2) = V_water(p1) + k × S        (k = 0.05)
Arc weight  = Distance / V_water
```

Water stops if its speed becomes zero or negative.

**Vehicle arc weight:**

```
t = Distance / V_vehicle
```

A node is unreachable if `t_arrival ≥ t_flood[node]`.

---

## Method Signatures

The following signatures are fixed by the assignment and must not be changed:

```java
public Localisation[] determinerZoneInondee(long[] idsDepart, double epsilon)

public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(
    long idDepart, long idArrivee, Localisation[] zoneInondee)


## Overview

Flood crisis simulator for the city of
public Map<Localisation, Double> determinerChronologieDeLaCrue(
    long[] idsDepart, double vWaterInit, double k)

public Deque<Localisation> trouverCheminDEvacuationLePlusCourt(
    long idDepart, long idEvacuation, double vVehicule, Map<Localisation, Double> tFlood)
```

---

## Build & Run

Requires **Java 11+**. No external dependencies.

**Compile** (from project root):

```bash
javac src/*.java tests/TestSimulator10.java -d out/
```

**Run a test:**

```bash
java -cp out TestSimulator10
```

**Available test classes:**

| Class                   | Graph size      | Notes                           |
| ----------------------- | --------------- | ------------------------------- |
| `TestSimulator10`       | 10 nodes        | Small graph, good for debugging |
| `TestSimulator1000a`    | 1,000 nodes     | Standard case                   |
| `TestSimulator1000b`    | 1,000 nodes     | Nearly fully flooded city       |
| `TestSimulator1000000a` | 1,000,000 nodes | Multiple flood start points     |
| `TestSimulator1000000b` | 1,000,000 nodes | Very long paths, stress test    |

Expected outputs are in `results/`.

---

## Validation Criteria

1. **Physical correctness** — water never flows uphill beyond tolerance ε, stops when speed ≤ 0
2. **Temporal correctness** — vehicle never enters a node that is already flooded
3. **Complexity** — structures and algorithms must scale to 1,000,000 nodes
4. **Code quality** — clean, readable, non-redundant code
