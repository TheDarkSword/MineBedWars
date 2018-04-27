package minebedwarsold.utls;

import minebedwarsold.enums.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright © 2018 by Lorenzo Magni and Michele Giacalone
 * This file is part of MineBedWars.
 * <p>
 * MineBedWars is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * MineBedWars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with MineBedWars.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PlayerUtls {
    public static boolean hasPermission(Player player, String perm){
        return player.hasPermission(Permissions.FULL.get()) || player.hasPermission(perm);
    }

    public static boolean hasPermission(CommandSender sender, String perm) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            return player.hasPermission(Permissions.FULL.get()) || player.hasPermission(perm);
        } else {
            return true;
        }
    }
}
