package com.swingy.utils;

import com.swingy.model.artifact.Artifact;
import com.swingy.model.character.Villian;
import com.swingy.model.character.Hero;

public class StaticGlobal {
    private StaticGlobal() {

    }

    public static boolean DISPLAY_LOGO = true;
    public static boolean CONSOLE_MODE = true;

    public static boolean ARTIFACT_DROPPED;
    public static boolean GOAL_REACHED;
    public static boolean HERO_RAN;


    public static Hero hero;
    public static Map map;
    public static Artifact artifact;
    public static Villian villian;
}
