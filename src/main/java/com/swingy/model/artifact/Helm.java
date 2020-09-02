package com.swingy.model.artifact;

public class Helm extends Artifact {
    private int hitPoints;

    public Helm(String name, int hitPoints) {
        super(name);
        this.type = ArtifactEnum.HELM;
        this.hitPoints = hitPoints;
    }

//   TODO use lombok for getters and setters
    public int getHitPoints() {
        return hitPoints;
    }
}
