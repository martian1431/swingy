package com.swingy.utils.factory;

import com.swingy.model.character.heros.Hero;
import com.swingy.utils.Grid;

// TODO abstarct class
public class GridFactory {

    public static Grid generateMap(Hero hero) {
        int mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        if (mapSize >= 25) {
            mapSize = 25;
        }
        Grid grid = new Grid(mapSize);
        grid.registerHero(hero);
        grid.spreadEnemies();
        return (grid);
    }
}
