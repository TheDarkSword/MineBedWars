package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;

public class SetMaxPlayers {
    public static void execute(String rawPlayers) {
        int players;

        try {
            players = Integer.parseInt(rawPlayers);
        } catch (NumberFormatException ignored) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawPlayers + " &cis not a valid number! Must be an integer > 0. Please run this command again with a valid number."));
            return;
        }

        if(players <= 0) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawPlayers + " &cis not a valid number! Must be an integer > 0. Please run this command again with a valid number."));
            return;
        }

        if(Main.gameHandler.getMap().getMinPlayers() != null && Main.gameHandler.getMap().getMinPlayers() >= players) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cThis value must be bigger than the minimum player amount (maxPlayers > " + Main.gameHandler.getMap().getMinPlayers() + ")."));
            return;
        }


        Main.gameHandler.getMap().setMaxPlayers(players);

        Bukkit.broadcastMessage(Chat.getTranslated("&ePerfect!"));
        Bukkit.broadcastMessage(Chat.getTranslated("Now i need the minimum amount of players required to start the game. Use the command &c/mbw setminplayers&e."));
    }
}
