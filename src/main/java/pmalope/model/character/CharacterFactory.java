package pmalope.model.character;

import pmalope.model.character.*;
import pmalope.model.character.heros.Deadpool;
import pmalope.model.character.heros.Hero;
import pmalope.model.character.heros.Thor;
import pmalope.model.character.heros.Wolverine;
import pmalope.model.character.villian.Magneto;
import pmalope.model.character.villian.Ultron;

public abstract class CharacterFactory {
    private static Hero newHero;
    private static Hero newEnemy;

    public static Hero newHero(String name, CharacterType type) {
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

    public static Hero newEnemy(Hero hero, CharacterType type) {
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
