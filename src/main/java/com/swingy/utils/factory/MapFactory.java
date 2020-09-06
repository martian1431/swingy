package com.swingy.utils.factory;

import com.swingy.model.character.heros.Hero;
import com.swingy.utils.Map;

// TODO abstarct class
public class MapFactory {

    public static Map generateMap(Hero hero) {
        int mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        if (mapSize >= 25) {
            mapSize = 25;
        }
        Map map = new Map(mapSize);
        map.registerHero(hero);
        map.spreadEnemies();
        return (map);
    }
}
