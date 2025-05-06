package org.example.statics;

public class Grass extends Entity {
    private int hp = 3;

    public Grass() {
    }

    public void lossHp() {
        this.hp--;
    }

    public int getHp() {
        return hp;
    }
}

