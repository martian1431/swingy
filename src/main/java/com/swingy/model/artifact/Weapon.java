package com.swingy.model.artifact;

import lombok.Getter;

@Getter
public class Weapon extends Artifact {
    private int attack;

    public Weapon(String name, int attack) {
        super(name);
        this.type = ArtifactEnum.WEAPON;
        this.attack = attack;
    }

    //   TODO use lombok for getters and setters
//    public int getAttack() {
//        return attack;
//    }
}
