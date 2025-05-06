package org.example.services;

import org.example.parts.Coordinates;
import org.example.statics.Entity;
import org.example.statics.WorldMap;

import java.util.*;

public class BFS {
    private final WorldMap worldMap;
    private final Map<Coordinates, List<Coordinates>> adjList = new HashMap<>();

    public BFS(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void addEdge(Coordinates source, Coordinates destination) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(destination, new ArrayList<>());

        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }

    public void initMatrix(Coordinates start, Entity target) {
        int total_directions = 4;
        for (int y = 0; y < worldMap.getY(); y++) {
            for (int x = 0; x < worldMap.getX(); x++) {
                Coordinates current = new Coordinates(y, x);
                Entity cell = worldMap.getEntity(current);

                if ((cell == null) || cell.getClass().equals(target.getClass()) || current.equals(start)) {

                    for (int i = 0; i < total_directions; i++) {
                        Coordinates neighbor = new Coordinates();
                        setCoordinatesForNeighbor(neighbor, i, y, x);
                        if (isCellValid(neighbor)) {
                            if (worldMap.getEntity(neighbor) == null) {
                                addEdge(current, neighbor);
                            } else {
                                boolean isNeighborEqualsTarget = worldMap.getEntity(neighbor).getClass().
                                        equals(target.getClass());
                                boolean isNeighborEqualsStart = neighbor.equals(start);
                                if (isNeighborEqualsTarget || isNeighborEqualsStart) {
                                    addEdge(current, neighbor);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isCellValid(Coordinates cell) {
        if ((cell.getX() >= 0) && (cell.getY() >= 0)) {
            return (cell.getX() < worldMap.getX()) && (cell.getY() < worldMap.getY());
        }
        return false;
    }

    private void setCoordinatesForNeighbor(Coordinates coordinates, int i, int y, int x) {
        if (i == 0) {
            coordinates.setY(y);
            coordinates.setX(x + 1); // neighbor on the right
        } else if (i == 1) {
            coordinates.setY(y);
            coordinates.setX(x - 1); // neighbor on the left
        } else if (i == 2) {
            coordinates.setY(y - 1);
            coordinates.setX(x); // neighbor downstairs
        } else if (i == 3) {
            coordinates.setY(y + 1);
            coordinates.setX(x); // neighbor upstairs
        }
    }

    public List<Coordinates> start(Coordinates start, Entity target) {
        Set<Coordinates> visited = new HashSet<>();
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (worldMap.getEntity(current) != null) {
                if (worldMap.getEntity(current).getClass().equals(target.getClass())) {
                    return constructorPath(parentMap, start, current); // found entity
                }
            }
            for (Coordinates neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, current); // save parent
                }
            }
        }
        return Collections.emptyList(); // if path don't find
    }

    private List<Coordinates> constructorPath(Map<Coordinates, Coordinates> parentMap, Coordinates start,
                                              Coordinates target) {
        List<Coordinates> path = new LinkedList<>();
        for (Coordinates at = target; at != null; at = parentMap.get(at)) {
            if (!at.equals(start)) {
                path.add(at);
            }
        }
        Collections.reverse(path); // path need reverse
        return path;
    }
}

