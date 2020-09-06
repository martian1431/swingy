package com.swingy.model.character.villian;

import com.swingy.model.character.heros.Hero;

public abstract class Villian extends Hero {
    public Villian(int level) {
        this.level = level;
    }
}