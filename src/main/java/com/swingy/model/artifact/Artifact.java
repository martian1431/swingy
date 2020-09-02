package com.swingy.model.artifact;

import java.io.Serializable;

public abstract class Artifact implements Serializable {
    private static final long serialVersionUID = 2776303584447042497L;

    String name;
    ArtifactEnum type;

    Artifact(String name) {

        this.name = name;
    }

//   TODO use lombok for getters and setters
    public String getName() {
        return name;
    }

    public ArtifactEnum getType() {
        return type;
    }
}
