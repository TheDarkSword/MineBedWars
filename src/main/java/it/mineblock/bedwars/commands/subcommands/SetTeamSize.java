package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;

public class SetTeamSize {
    public static void execute(String rawSize) {
        int size;

        try {
            size = Integer.parseInt(rawSize);
        } catch (NumberFormatException ignored) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawSize + " &cis not a valid number! Must be an integer > 0. Please run this command again with a valid number."));
            return;
        }

        if(size <= 0) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cI'm sorry but &e" + rawSize + " &cis not a valid number! Must be an integer > 0. Please run this command again with a valid number."));
            return;
        }

        if(Main.gameHandler.getMap().getMaxPlayers() != null && Main.gameHandler.getMap().getMaxPlayers() > size) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cThe size of the teams can't be bigger than the maximum amount of players. Pleas run this command again with a number smaller than " + Main.gameHandler.getMap().getMaxPlayers() + "&e."));
            return;
        }

        if(Main.gameHandler.getMap().getMaxPlayers() != null && Main.gameHandler.getMap().getMaxPlayers() % size != 0) {
            Bukkit.broadcastMessage(Chat.getTranslated("&cThe size of the teams must be a divisor of the maximum amount of players. Pleas run this command again with a different size."));
            return;
        }


        Main.gameHandler.getMap().setTeamSize(size);

        Bukkit.broadcastMessage(Chat.getTranslated("&eEasy, isn't it? Now i know everything i need to start the game. To confirm your inputs run the command &c/mbw done"));
    }
}
