package it.mineblock.bedwars;

import it.mineblock.bedwars.enums.GamePhases;
import it.mineblock.bedwars.objects.*;
import it.mineblock.mbcore.spigot.config.Configuration;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private Configuration config;
    private World world;
    private Location center;
    private Long radius;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer teamSize;
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    public void init() {
        File map = new File(Main.plugin.getDataFolder(), Main.MAP);

        if(Main.configuration.configExists(map)) {
            load();
        } else {
            try {
                map.createNewFile();
                Main.gameHandler.setPhase(GamePhases.CONFIGURATION);
                config = Main.configuration.loadConfig(map);
            } catch (IOException e) {
                e.printStackTrace();
                Main.plugin.onDisable();
            }
        }
    }

    private void load() {

    }

    public void save() {
        config.set("world", world.getName());
        config.set("center.x", center.getX());
        config.set("center.y", center.getY());
        config.set("center.z", center.getZ());
        config.set("radius", radius);
        config.set("min-players", minPlayers);
        config.set("max-players", maxPlayers);
        config.set("team-size", teamSize);

        for(Team team : teams) {
            config.set("teams." + team.getTeam().name() + ".spawn.x", team.getSpawn().getX());
            config.set("teams." + team.getTeam().name() + ".spawn.y", team.getSpawn().getY());
            config.set("teams." + team.getTeam().name() + ".spawn.z", team.getSpawn().getZ());
            config.set("teams." + team.getTeam().name() + ".spawn.yaw", team.getSpawn().getYaw());
            config.set("teams." + team.getTeam().name() + ".spawn.pitch", team.getSpawn().getPitch());

            config.set("teams." + team.getTeam().name() + ".bed.b1.x", team.getBed1().getX());
            config.set("teams." + team.getTeam().name() + ".bed.b1.y", team.getBed1().getY());
            config.set("teams." + team.getTeam().name() + ".bed.b1.z", team.getBed1().getZ());

            config.set("teams." + team.getTeam().name() + ".bed.b2.x", team.getBed2().getX());
            config.set("teams." + team.getTeam().name() + ".bed.b2.y", team.getBed2().getY());
            config.set("teams." + team.getTeam().name() + ".bed.b2.z", team.getBed2().getZ());
        }

        for(Resource resource : resources) {
            config.set("resources." + resource.getId() + ".materials", resource.getResources());

            config.set("resources." + resource.getId() + ".location.x", resource.getLocation().getX());
            config.set("resources." + resource.getId() + ".location.y", resource.getLocation().getY());
            config.set("resources." + resource.getId() + ".location.z", resource.getLocation().getZ());

            config.set("resources." + resource.getId() + ".team", resource.getTeam() == null ? "" : resource.getTeam().name());
        }

        Main.configuration.saveConfig(config, new File(Main.plugin.getDataFolder(), Main.MAP));
    }
}
