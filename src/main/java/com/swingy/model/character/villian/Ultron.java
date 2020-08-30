package com.swingy.model.character.villian;

import com.swingy.model.character.Villian;

public class Ultron extends Villian {

    public Ultron(int level) {
        super(level);
        this.name = "Lord Geff";
        this.type = "Ultron";
        this.attack = level + 5;
        this.defense = level + 2;
        this.hitPoints = level + 12;
    }
}
