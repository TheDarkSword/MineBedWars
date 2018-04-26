package it.mineblock.bedwars.enums;

import org.bukkit.DyeColor;

import java.util.HashMap;

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
