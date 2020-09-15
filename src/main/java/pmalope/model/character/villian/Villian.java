package pmalope.model.character.villian;

import pmalope.model.character.heros.Hero;

public abstract class Villian extends Hero {
    public Villian(int level) {
        this.level = level;
    }
}