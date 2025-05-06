package org.example.dinamics;

import org.example.parts.Coordinates;
import org.example.services.BFS;
import org.example.statics.Entity;
import org.example.statics.Grass;
import org.example.statics.WorldMap;

import java.util.List;

public abstract class Creature extends Entity {
    private static final int MAX_HP = 5;
    private static final int HUNGER_LIMIT = 6;
    private List<Coordinates> path;
    private boolean hungry = false;
    private int hungerLevel = 0;
    protected int hp = MAX_HP;
    protected Entity target;

    public Creature(Entity target) {
        this.target = target;
    }

    public void makeMove(WorldMap map) {
        Coordinates coordinatesFrom = this.getCoordinates();
        Coordinates coordinatesTo = this.findPath(map, coordinatesFrom);

        if (path.size() > 1) {
            this.step(map, coordinatesFrom, coordinatesTo);
        } else if (path.size() == 1) {
            this.attack(map, coordinatesTo);
        } else {
            this.hungerLevel++;
        }
        checkHunger(map);
    }

    private Coordinates findPath(WorldMap map, Coordinates from) {
        BFS bfs = new BFS(map);
        bfs.initMatrix(from, target);
        this.path = bfs.start(from, target);
        return (!this.path.isEmpty()) ? this.path.getFirst() : from;
    }

    private void step(WorldMap map, Coordinates from, Coordinates to) {
        if (map.isEmpty(to)) {
            Entity e = map.getEntity(from);
            map.removeEntity(from);
            map.putEntity(to, e);
        }
        this.hungerLevel++;
    }

    private void attack(WorldMap map, Coordinates to) {
        Entity target = map.getEntity(to);
        if (target instanceof Herbivore) {
            if (((Herbivore) target).getHp() > 1) {
                ((Herbivore) target).lossHp();
                this.regenerationHp();
            } else if (((Herbivore) target).getHp() == 1) {
                map.removeEntity(to);
            }
        } else if (target instanceof Grass) {
            if (((Grass) target).getHp() > 1) {
                ((Grass) target).lossHp();
                this.regenerationHp();
            } else if (((Grass) target).getHp() == 1) {
                map.removeEntity(to);
            }
        }
        this.hungerLevel = 0;
        this.hungry = false;
    }

    public void checkHunger(WorldMap map) {
        if (hungerLevel >= HUNGER_LIMIT) {
            this.lossHp();
            this.hungry = true;
        }
        if (this.hp == 0) {
            map.removeEntity(this.getCoordinates());
        }
    }

    public boolean isHungry() {
        return hungry;
    }

    public void lossHp() {
        this.hp--;
    }

    public void regenerationHp() {
        if (this.hp < MAX_HP) {
            this.hp++;
        }
    }

    public int getHp() {
        return hp;
    }
}
