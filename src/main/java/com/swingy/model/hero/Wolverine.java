package com.swingy.model.hero;

public class Wolverine extends Hero{

    public Wolverine() { super(); }

    public Wolverine(String name) {
        super(name);
        this.type = "Octopus";
        this.attack += 10;
        this.defense += 3;
        this.hitPoints += 75;
    }
}
