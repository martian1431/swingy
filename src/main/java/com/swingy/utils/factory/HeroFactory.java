package com.swingy.utils.factory;

import com.swingy.model.hero.*;

public abstract class HeroFactory {
    private static Hero newHero;
    private static Hero newEnemy;

    public static Hero newHero(String name, HeroEnum type) {
        switch (type) {
            case DEADPOOL:
                newHero = new Deadpool(name);
                break;
            case THOR:
                newHero = new Thor(name);
                break;
            case WOLVERINE:
                newHero = new Wolverine(name);
                break;
            default:
                break;
        }
        return newHero;
    }

    public static Hero newEnemy(Hero hero, HeroEnum type) {
        switch (type) {
            case MAGNETO:
                newEnemy = new Magneto(hero.getLevel());
                break;
            case ULTRON:
                newEnemy = new Ultron(hero.getLevel());
                break;
            default:
                break;
        }
        return newEnemy;
    }
}
