package org.example.services;

import org.example.dinamics.Herbivore;
import org.example.dinamics.Predator;
import org.example.parts.Coordinates;
import org.example.parts.Symbols;
import org.example.statics.*;

public class Renderer {
    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render() {
        for (int y = 0; y < worldMap.getY(); y++) {
            for (int x = 0; x < worldMap.getX(); x++) {
                System.out.print(getSpriteEntity(worldMap.getEntity(new Coordinates(y, x))));
            }
            System.out.println();
        }
    }

    private String getSpriteEntity(Entity entity) {
        if (entity instanceof Predator predator) {
            if (predator.isHungry()) {
                return Symbols.HUNGRY_PREDATOR;
            } else {
                return Symbols.PREDATOR;
            }
        }
        if (entity instanceof Herbivore herbivore) {
            if (herbivore.isHungry()) {
                return Symbols.HUNGRY_HERBIVORE;
            } else {
                return Symbols.HERBIVORE;
            }
        }
        if (entity instanceof Grass) {
            return Symbols.GRASS;
        }
        if (entity instanceof Rock) {
            return Symbols.ROCK;
        }
        if (entity instanceof Tree) {
            return Symbols.TREE;
        }
        if (entity == null) {
            return Symbols.EMPTY_CELL;
        }
        return Symbols.DEFAULT;
    }
}
