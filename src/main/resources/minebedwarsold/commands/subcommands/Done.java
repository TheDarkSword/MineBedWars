package minebedwarsold.commands.subcommands;

import minebedwarsold.Main;
import minebedwarsold.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
public class Done {

    public static void execute(Player player) {

        Map map = Main.gameHandler.getMap();

        /*if (map.getWorld() != null) {
            //TODO Send error message
            return;
        }
        if (map.getCenter() != null) {
            //TODO Send error message
            return;
        }
        if (map.getRadius() != null) {
            //TODO Send error message
            return;
        }
        if (map.getMinPlayes() != null) {
            //TODO Send error message
            return;
        }
        if (map.getMaxPlayers() != null) {
            //TODO Send error message
            return;
        }
        if (map.getTeamSize() != null) {
            //TODO Send error message
            return;
        }*/

        Main.gameHandler.getMap().saveToConfig();

        //TODO Send help message send logger

        Bukkit.getScheduler().runTaskLater(Main.plugin, () -> Bukkit.getServer().shutdown(), 400L);
    }
}
