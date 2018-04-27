package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;

public class SetMinPlayers {
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

        if(Main.gameHandler.getMap().getMaxPlayers() != null && Main.gameHandler.getMap().getMaxPlayers() <= players) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cThis value must be smaller than the maximum player amount (minPlayers < " + Main.gameHandler.getMap().getMaxPlayers() + ")."));
            return;
        }


        Main.gameHandler.getMap().setMinPlayers(players);

        Bukkit.broadcastMessage(Chat.getTranslated("&eGood, almost done!"));
        Bukkit.broadcastMessage(Chat.getTranslated("The last thing i need to know is the size of each team. Use the command &c/mbw setteamsize <size>&e."));
    }
}
