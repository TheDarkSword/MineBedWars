package it.mineblock.bedwars;

import it.mineblock.bedwars.commands.Mbw;
import it.mineblock.bedwars.listeners.OnPreLogin;
import it.mineblock.mbcore.spigot.MBConfig;
import it.mineblock.mbcore.spigot.config.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    static final String MAP = "map.yml";

    private static final String PLUGIN = "MineBedwars";
    private static final String CONFIG = "config.yml";
    private static final String MESSAGES = "messages.yml";

    public static Plugin plugin;
    public static Configuration config;
    public static Configuration messages;
    public static GameHandler gameHandler;
    public static boolean debug = true;

    static MBConfig configuration = new MBConfig();


    public void onEnable() {
        plugin = this;

        config = configuration.autoloadConfig(plugin, PLUGIN, getResource(CONFIG), new File(getDataFolder(), CONFIG), CONFIG);
        messages = configuration.autoloadSecondaryConfig(getDataFolder(), PLUGIN, getResource(MESSAGES), new File(getDataFolder(), MESSAGES), MESSAGES);

        gameHandler = new GameHandler();

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnPreLogin(), this);
    }

    private void registerCommands() {
        getCommand("mbw").setExecutor(new Mbw());
    }
}
