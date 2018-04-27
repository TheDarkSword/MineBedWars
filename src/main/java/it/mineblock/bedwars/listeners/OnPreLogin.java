package it.mineblock.bedwars.listeners;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.GamePhases;
import it.mineblock.bedwars.enums.Messages;
import it.mineblock.bedwars.enums.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;


public class OnPreLogin implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getUniqueId());
        Player player = offlinePlayer.getPlayer();

        if(Main.gameHandler == null) {
            return;
        }

        if(Main.gameHandler.getPhase().equals(GamePhases.BOOTING)) {
            if(player.hasPermission(Permissions.STAFF.get())) {
                return;
            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Messages.KICK_BOOTING.get());
        }
        else if(Main.gameHandler.getPhase().equals(GamePhases.RUNNING)) {
            if(player.hasPermission(Permissions.STAFF.get())) {
                return;
            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Messages.KICK_RUNNING.get());
        }
        else if(Main.gameHandler.getPhase().equals(GamePhases.ENDING)) {
            if(player.hasPermission(Permissions.STAFF.get())) {
                return;
            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Messages.KICK_BOOTING.get());
        }
        else if(Main.gameHandler.getPhase().equals(GamePhases.REBOOTING)) {
            if(player.hasPermission(Permissions.STAFF.get())) {
                return;
            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Messages.KICK_BOOTING.get());
        }
        else if(Main.gameHandler.getPhase().equals(GamePhases.WAITING) || Main.gameHandler.getPhase().equals(GamePhases.STARTING)) {
            if(Main.gameHandler.getMap().getMaxPlayers() <= Bukkit.getOnlinePlayers().size()) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Messages.KICK_FULL.get());
            }
        }
    }
}
