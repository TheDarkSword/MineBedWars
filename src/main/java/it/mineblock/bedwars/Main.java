package it.mineblock.bedwars;

import it.mineblock.mbcore.spigot.MBConfig;
import it.mineblock.mbcore.spigot.config.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static final String MAP = "map.yml";

    private static final String PLUGIN = "MineBedwars";
    private static final String CONFIG = "config.yml";
    private static final String MESSAGES = "messages.yml";

    public static Plugin plugin;
    public static Configuration config;
    public static Configuration messages;
    public static GameHandler gameHandler = new GameHandler();

    static MBConfig configuration = new MBConfig();


    public void onEnable() {

    }

    public void onDisable() {
        
    }
}
