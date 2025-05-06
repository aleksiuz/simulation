package org.example.services;

import org.example.dinamics.Herbivore;
import org.example.dinamics.Predator;
import org.example.parts.Coordinates;
import org.example.parts.Entities;
import org.example.statics.Grass;
import org.example.statics.Rock;
import org.example.statics.Tree;
import org.example.statics.WorldMap;

import java.util.Map;

public class InitAction extends Actions {
    private final WorldMap worldMap;
    private final WorldMapUtils worldMapUtils;


    public InitAction(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.worldMapUtils = new WorldMapUtils(worldMap);
    }

    public void initActions() {
        this.spawn();
    }

    private void spawn() {
        Map<Entities, Integer> pool = worldMapUtils.getPoolObjectsForMap();
        for (Entities n : Entities.values()) {
            int limitEntities = getLimitEntities(pool, n.ordinal());
            for (int i = 0; i < limitEntities; i++) {
                Coordinates cell = worldMapUtils.getRandomEmptyCell();
                if (n == Entities.TREE) {
                    worldMap.putEntity(cell, new Tree());
                }
                if (n == Entities.ROCK) {
                    worldMap.putEntity(cell, new Rock());
                }
                if (n == Entities.GRASS) {
                    worldMap.putEntity(cell, new Grass());
                }
                if (n == Entities.HERBIVORE) {
                    worldMap.putEntity(cell, new Herbivore());
                }
                if (n == Entities.PREDATOR) {
                    worldMap.putEntity(cell, new Predator());
                }
            }
        }
    }

    public int getLimitEntities(Map<Entities, Integer> pool, int number) {
        if (number == Entities.TREE.ordinal()) {
            return pool.get(Entities.TREE);
        }
        if (number == Entities.ROCK.ordinal()) {
            return pool.get(Entities.ROCK);
        }
        if (number == Entities.GRASS.ordinal()) {
            return pool.get(Entities.GRASS);
        }
        if (number == Entities.HERBIVORE.ordinal()) {
            return pool.get(Entities.HERBIVORE);
        }
        if (number == Entities.PREDATOR.ordinal()) {
            return pool.get(Entities.PREDATOR);
        } else {
            return 0;
        }

    }
}
