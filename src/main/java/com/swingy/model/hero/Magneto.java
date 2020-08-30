package com.swingy.model.hero;

public class Magneto extends Enemy {

    public Magneto(int level) {
        super(level);
        this.name = "The Kid Next Door";
        this.type = "Magneto";
        this.attack = level + 6;
        this.defense = level + 2;
        this.hitPoints = level + 15;
    }
}
