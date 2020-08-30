package com.swingy.model.artifact;

public class Armor extends Artifact {

    private int defense;

    public Armor(String name, int defense) {
        super(name);
        this.type = ArtifactEnum.ARMOR;
        this.defense = defense;
    }

    //   TODO use lombok for getters and setters
    public int getDefense() {
        return defense;
    }
}
