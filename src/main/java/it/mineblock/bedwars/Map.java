package it.mineblock.bedwars;

import it.mineblock.bedwars.enums.GamePhases;
import it.mineblock.bedwars.objects.*;
import it.mineblock.mbcore.spigot.config.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            config = Main.configuration.loadConfig(map);
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

    public void setWorld(World world) {
        this.world = world;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    private void load() {
        world = Bukkit.getWorld(config.getString("world"));
        center = new Location(
                world,
                config.getDouble("center.x"),
                config.getDouble("center.y"),
                config.getDouble("center.z")
        );
        radius = config.getLong("radius");
        minPlayers = config.getInt("min-players");
        maxPlayers = config.getInt("max-players");
        teamSize = config.getInt("team-size");

        for(String section : config.getSection("teams").getKeys()) {
            Team team = new Team(section);
            team.setSpawn(new Location(
                    world,
                    config.getDouble("teams." + section + ".spawn.x"),
                    config.getDouble("teams." + section + ".spawn.y"),
                    config.getDouble("teams." + section + ".spawn.z"),
                    config.getFloat("teams." + section + ".spawn.yaw"),
                    config.getFloat ("teams." + section + ".spawn.pitch")
            ));

            team.setBed1(new Location(
                    world,
                    config.getDouble("teams." + section + ".bed.b1.x"),
                    config.getDouble("teams." + section + ".bed.b1.y"),
                    config.getDouble("teams." + section + ".bed.b1.z")));
            team.setBed2(new Location(
                    world,
                    config.getDouble("teams." + section + ".bed.b2.x"),
                    config.getDouble("teams." + section + ".bed.b2.y"),
                    config.getDouble("teams." + section + ".bed.b2.z")));

            teams.add(team);
        }

        for(String section : config.getSection("resources").getKeys()) {
            String material = config.getString("resources." + section + ".material");
            int id;

            try {
                id = Integer.parseInt(section);
            } catch (NumberFormatException ignored) {
                new NumberFormatException("This resource id must be an Integer, check your map config!").printStackTrace();
                Main.plugin.onDisable();
                return;
            }

            Resource resource = new Resource(id, material);
            resource.setLocation(new Location(
                    world,
                    config.getDouble("resources." + section + ".location.x"),
                    config.getDouble("resources." + section + ".location.y") + 0.5,
                    config.getDouble("resources." + section + ".location.z")
            ));
            resource.setTeam("resources." + section + ".team");

            resources.add(resource);
        }

        Main.gameHandler.setPhase(GamePhases.WAITING);
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
            config.set("resources." + resource.getId() + ".material", resource.getResource());

            config.set("resources." + resource.getId() + ".location.x", resource.getLocation().getX());
            config.set("resources." + resource.getId() + ".location.y", resource.getLocation().getY());
            config.set("resources." + resource.getId() + ".location.z", resource.getLocation().getZ());

            config.set("resources." + resource.getId() + ".team", resource.getTeam() == null ? "" : resource.getTeam().name());
        }

        Main.configuration.saveConfig(config, new File(Main.plugin.getDataFolder(), Main.MAP));
        Main.gameHandler.setPhase(GamePhases.WAITING);
    }
}
