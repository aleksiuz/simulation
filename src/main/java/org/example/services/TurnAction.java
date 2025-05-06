package org.example.services;

import org.example.dinamics.Creature;
import org.example.dinamics.Herbivore;
import org.example.dinamics.Predator;
import org.example.parts.Coordinates;
import org.example.parts.Entities;
import org.example.statics.Grass;
import org.example.statics.WorldMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TurnAction extends Actions {
    private final WorldMap worldMap;
    private final WorldMapUtils worldMapUtils;

    public TurnAction(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.worldMapUtils = new WorldMapUtils(worldMap);
    }

    public void turnActions() {
        this.makeMoveAllCreatures();
    }

    private void makeMoveAllCreatures() {
        List<Creature> creatures = findAllCreatures();
        HashSet<Creature> creaturesMadeMove = new HashSet<>();
        boolean isAllCreaturesMadeMove = false;

        while (!isAllCreaturesMadeMove) {
            isAllCreaturesMadeMove = true;
            for (Creature c : creatures) {
                if (!creaturesMadeMove.contains(c)) {
                    c.makeMove(worldMap);
                    creaturesMadeMove.add(c);
                    isAllCreaturesMadeMove = false;
                    break;
                }
            }
            creatures = findAllCreatures();
        }
    }

    private List<Creature> findAllCreatures() {
        List<Creature> foundCreatures = new ArrayList<>();
        for (int y = 0; y < worldMap.getY(); y++) {
            for (int x = 0; x < worldMap.getX(); x++) {
                if (worldMap.getEntity(new Coordinates(y, x)) instanceof Creature creature) {
                    foundCreatures.add(creature);
                }
            }
        }
        return foundCreatures;
    }

    public void checkAndAddEntities() {
        Map<Entities, Integer> pool = worldMapUtils.getPoolObjectsForMap();
        Map<Entities, Integer> entitiesOnMap;
        boolean isAddedEntities = false;

        while (!isAddedEntities) {
            isAddedEntities = true;
            entitiesOnMap = worldMapUtils.getQuantityEntitiesOnMap();
            if (entitiesOnMap.get(Entities.GRASS) < pool.get(Entities.GRASS)) {
                Coordinates cell = worldMapUtils.getRandomEmptyCell();
                worldMap.putEntity(cell, new Grass());
                isAddedEntities = false;
            }
            if (entitiesOnMap.get(Entities.HERBIVORE) < pool.get(Entities.HERBIVORE)) {
                Coordinates cell = worldMapUtils.getRandomEmptyCell();
                worldMap.putEntity(cell, new Herbivore());
                isAddedEntities = false;
            }
            if (entitiesOnMap.get(Entities.PREDATOR) < pool.get(Entities.PREDATOR)) {
                Coordinates cell = worldMapUtils.getRandomEmptyCell();
                worldMap.putEntity(cell, new Predator());
                isAddedEntities = false;
            }
        }
    }
}


