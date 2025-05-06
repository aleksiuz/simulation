package org.example.dinamics;

import org.example.statics.Entity;
import org.example.statics.Grass;

import java.util.Objects;

public class Herbivore extends Creature {
    public Grass target;

    public Herbivore() {
        super(new Grass());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Herbivore that = (Herbivore) obj;

        if(this.hashCode() != that.hashCode()) return false;
        if( hp != that.hp) return false;
        return this.target == that.target;
    }
}
