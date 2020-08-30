package com.swingy.model.hero;

public class Ultron extends Enemy {

    public Ultron(int level) {
        super(level);
        this.name = "Lord Geff";
        this.type = "Ultron";
        this.attack = level + 5;
        this.defense = level + 2;
        this.hitPoints = level + 12;
    }
}
