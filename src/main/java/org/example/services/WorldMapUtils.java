package org.example.services;

import org.example.dinamics.Herbivore;
import org.example.dinamics.Predator;
import org.example.parts.Coordinates;
import org.example.parts.Entities;
import org.example.statics.*;

import java.util.*;

public class WorldMapUtils {
    private final WorldMap worldMap;

    public WorldMapUtils(WorldMap map) {
        this.worldMap = map;
    }

    public Coordinates getRandomEmptyCell() {
        Random random = new Random();
        while (true) {
            int y = random.nextInt(worldMap.getY());
            int x = random.nextInt(worldMap.getX());
            Coordinates cell = new Coordinates(y, x);
            if (worldMap.isEmpty(cell)) {
                return cell;
            }
        }
    }

    public Map<Entities, Integer> getPoolObjectsForMap() {
        int sizeMap = worldMap.getY() * worldMap.getX();
        Map<Entities, Integer> poolObjects = new HashMap<>();

        int trees = sizeMap / 12;
        poolObjects.put(Entities.TREE, trees);

        int rocks = sizeMap / 12;
        poolObjects.put(Entities.ROCK, rocks);

        int grasses = sizeMap / 6;
        poolObjects.put(Entities.GRASS, grasses);

        int herbivores = grasses / 2;
        poolObjects.put(Entities.HERBIVORE, herbivores);

        int predators = herbivores / 2;
        poolObjects.put(Entities.PREDATOR, predators);

        return poolObjects;
    }

    public Map<Entities, Integer> getQuantityEntitiesOnMap() {
        List<Entity> entitiesOnMap = new ArrayList<>(worldMap.getEntities().values());
        Map<Entities, Integer> quantity = new HashMap<>();
        int trees = 0, rocks = 0, grasses = 0, herbivores = 0, predators = 0;
        for (Entities n : Entities.values()) {
            quantity.put(n, 0);
        }
        for (Entity entity : entitiesOnMap) {
            if (entity instanceof Tree) {
                quantity.put(Entities.TREE, ++trees);
            }
            if (entity instanceof Rock) {
                quantity.put(Entities.ROCK, ++rocks);
            }
            if (entity instanceof Grass) {
                quantity.put(Entities.GRASS, ++grasses);
            }
            if (entity instanceof Herbivore) {
                quantity.put(Entities.HERBIVORE, ++herbivores);
            }
            if (entity instanceof Predator) {
                quantity.put(Entities.PREDATOR, ++predators);
            }
        }
        return quantity;
    }
}

