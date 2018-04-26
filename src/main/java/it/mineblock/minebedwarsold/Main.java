package it.mineblock.minebedwarsold;

import it.mineblock.mbcore.Chat;
import it.mineblock.mbcore.spigot.MBConfig;
import it.mineblock.mbcore.spigot.config.Configuration;
import it.mineblock.minebedwarsold.commands.Mbw;
import it.mineblock.minebedwarsold.enums.*;
import it.mineblock.minebedwarsold.utls.PlayerUtls;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
public class Main extends JavaPlugin {
    public static final String PLUGIN = "MineBedwars";
    public static final String MAP = "map.yml";

    private static final String CONFIG = "config.yml";
    private static final String MESSAGES = "messages.yml";

    public static Plugin plugin;
    public static Configuration config;
    public static Configuration msgConfig;
    public static GameHandler gameHandler = new GameHandler();
    public static MBConfig configuration = new MBConfig();

    public void onEnable() {
        plugin = this;

        configLoader();
        //TODO Re-enable this Socket.init();
        Menu.init();

        registerListeners();
        registerCommands();

        mapLoader();
    }

    public void onDisable() {
        unregisterListeners();
    }

    private void configLoader() {
        config = configuration.autoloadConfig(plugin, PLUGIN, getResource(CONFIG), new File(getDataFolder(), CONFIG), CONFIG);
        msgConfig = configuration.autoloadSecondaryConfig(getDataFolder(), PLUGIN, getResource(MESSAGES), new File(getDataFolder(), MESSAGES), MESSAGES);
    }

    private void mapLoader() {
        if(configuration.configExists(new File(getDataFolder(), MAP))) {
            gameHandler.getMap().loadFromConfig();
            gameHandler.setPhase(GamePhases.WAITING);
        } else {
            gameHandler.setPhase(GamePhases.CONFIGURING);
            Chat.getLogger(Message.MAP_CONFIGURATION.get(), "warning");
            getServer().broadcastMessage(Message.MAP_CONFIGURATION.get());
        }
    }

    private void registerListeners() {

    }

    private void registerCommands() {
        getCommand("mbw").setExecutor(new Mbw());
    }

    private void unregisterListeners() {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("mbwreload")) {
            if(!PlayerUtls.hasPermission(sender, Permissions.RELOAD.get())) {
                Chat.send(Message.INSUFFICIENT_PERM.get(), sender);
                return true;
            }

            onDisable();
            onEnable();
        }

        return true;
    }
}
