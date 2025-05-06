package org.example.dinamics;

import org.example.statics.Entity;

import java.util.Objects;

public class Predator extends Creature {
    public Herbivore target;

    public Predator() {
        super(new Herbivore());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Predator that = (Predator) obj;

        if (this.hashCode() != that.hashCode()) return false;
        if (hp != that.hp) return false;
        return this.target == that.target;
    }
}
