package it.mineblock.bedwars.listeners;

import it.mineblock.bedwars.Main;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerLogin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(Main.gameHandler.isBroadcastWaiting) {
            Bukkit.broadcastMessage(Chat.getTranslated("&eWelcome to &6MineBedwars &emap configuration system!"));
            Bukkit.broadcastMessage(Chat.getTranslated("&eTo start the creation of the map position yourself in the world you want to configure and enter the command &c/mbw create&e."));
        }
    }
}
