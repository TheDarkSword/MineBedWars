package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetRadius {
    public static void execute(Player player, String rawRadius) {
        long radius;

        try {
            radius = Long.parseLong(rawRadius);
        } catch (NumberFormatException ignored) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawRadius + " &cis not a valid number! Radius must be an integer > 0. Please run this command again with a valid radius."));
            return;
        }

        if(radius <= 0) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawRadius + " &cis not a valid number! Radius must be an integer > 0. Please run this command again with a valid radius."));
            return;
        }

        Main.gameHandler.getMap().setCenter(player.getLocation());
        Main.gameHandler.getMap().setRadius(radius);

        Bukkit.broadcastMessage(Chat.getTranslated("&ePerfect! So this is the center [x: &c" + Main.gameHandler.getMap().getCenter().getX() + "&e, z: &c" + Main.gameHandler.getMap().getCenter().getZ() +
                "&e] and the radius of the map is &c" + rawRadius + "&e."));
        Bukkit.broadcastMessage(Chat.getTranslated("Now i need the maximum amount of players that can take part to the game (Remember to leave some extra slot for the staff). Use the command &c/mbw setmaxplayers&e."));
    }
}
