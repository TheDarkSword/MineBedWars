package it.mineblock.minebedwars.enums;

import org.bukkit.DyeColor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright © 2018 by Lorenzo Magni
 * This file is part of MineBedwars.
 * <p>
 * MineBedwars is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * MineBedwars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with MineBedwars.  If not, see <http://www.gnu.org/licenses/>.
 */
public enum Teams {
    BLACK(DyeColor.BLACK),
    BLUE(DyeColor.BLUE),
    CYAN(DyeColor.CYAN),
    GRAY(DyeColor.GRAY),
    GREEN(DyeColor.GREEN),
    LIGHT_BLUE(DyeColor.LIGHT_BLUE),
    LIME(DyeColor.LIME),
    MAGENTA(DyeColor.MAGENTA),
    PINK(DyeColor.PINK),
    RED(DyeColor.RED),
    WHITE(DyeColor.WHITE),
    YELLOW(DyeColor.YELLOW);

    private DyeColor color;

    Teams(DyeColor color) {
        this.color = color;
    }

    public DyeColor getColor() {
        return color;
    }

    public static Teams getByMeta(int meta) {
        HashMap<Integer, Teams> teams = new HashMap<>();
        teams.put(15, BLACK);
        teams.put(11, BLUE);
        teams.put(9, CYAN);
        teams.put(7, GRAY);
        teams.put(13, GREEN);
        teams.put(3, LIGHT_BLUE);
        teams.put(5, LIME);
        teams.put(2, MAGENTA);
        teams.put(6, PINK);
        teams.put(14, RED);
        teams.put(0, WHITE);
        teams.put(4, YELLOW);

        return teams.get(meta);
    }
}
