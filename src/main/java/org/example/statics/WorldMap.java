package org.example.statics;

import org.example.parts.Coordinates;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int Y;
    private final int X;
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public WorldMap(int size) {
        this.Y = size;
        this.X = size;
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }
}

