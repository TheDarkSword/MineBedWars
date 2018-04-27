package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.GamePhases;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Done {
    public static void execute() {
        ArrayList<String> missing = new ArrayList<>();

        if(Main.gameHandler.getMap().getWorld() == null) {
            missing.add("World");
        }
        if(Main.gameHandler.getMap().getCenter() == null) {
            missing.add("Center");
        }
        if(Main.gameHandler.getMap().getRadius() == null) {
            missing.add("Radius");
        }
        if(Main.gameHandler.getMap().getMaxPlayers() == null) {
            missing.add("Max Players");
        }
        if(Main.gameHandler.getMap().getMinPlayers() == null) {
            missing.add("Min Players");
        }
        if(Main.gameHandler.getMap().getTeamSize() == null) {
            missing.add("Team Size");
        }
        if(Main.gameHandler.getMap().getTeams().size() == 0) {
            missing.add("Teams");
        }
        if(Main.gameHandler.getMap().getResources().size() == 0) {
            missing.add("Resources");
        }

        if(!missing.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("&cOps. Something went wrong! You forgot to give me some parameters: &e");
            for(int i = 0; i < missing.size(); i++) {
                builder.append(missing.get(i));

                if(i != missing.size() - 1) {
                    builder.append("&c, &e");
                }
            }
            builder.append("&c. Run again some of the previous commands.");
            Bukkit.broadcastMessage(Chat.getTranslated(builder.toString()));
            return;
        }

        Bukkit.broadcastMessage(Chat.getTranslated("&eMy job is now done! The map is completely configured and you can now play your first &cBedwars&e. This is a goodbye, good game!"));
        Bukkit.broadcastMessage(Chat.getTranslated("&eOh, i almost forgot, you will be teleported in 15 seconds to the world spawn and the game will start soon. Bye!"));

        Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
            Location spawn = Main.gameHandler.getMap().getWorld().getSpawnLocation();
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.teleport(spawn);
                player.setGameMode(GameMode.SURVIVAL);
                player.setInvulnerable(false);
                player.setFlying(false);
            }

            Main.gameHandler.setPhase(GamePhases.WAITING);
        }, 300);
    }
}
