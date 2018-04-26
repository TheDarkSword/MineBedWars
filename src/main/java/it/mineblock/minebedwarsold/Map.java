package it.mineblock.minebedwarsold;

import it.mineblock.mbcore.Chat;
import it.mineblock.mbcore.spigot.config.Configuration;
import it.mineblock.minebedwarsold.objects.Bed;
import it.mineblock.minebedwarsold.objects.Resource;
import it.mineblock.minebedwarsold.objects.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class Map {
    private World world;
    private Location center;
    private Long radius;
    private Integer minPlayes;
    private Integer maxPlayers;
    private Integer teamSize;
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Bed> beds = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    public void setWorld(World world) {
        this.world = world;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public void setMinPlayes(int minPlayes) {
        this.minPlayes = minPlayes;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public World getWorld() {
        return world;
    }

    public Location getCenter() {
        return center;
    }

    public Long getRadius() {
        return radius;
    }

    public Integer getMinPlayes() {
        return minPlayes;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void setTeam(Team team, int index) {
        teams.add(index, team);
    }

    public void addBed(Bed bed) {
        beds.add(bed);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void loadFromConfig() {
        Configuration map = Main.configuration.loadConfig(new File(Main.plugin.getDataFolder(), Main.MAP));

        world = Bukkit.getWorld(map.getString("world"));
        center = new Location(
                world,
                map.getDouble("center.x"),
                map.getDouble("center.y"),
                map.getDouble("center.z")
        );
        radius = map.getLong("radius");
        minPlayes = map.getInt("min-players");
        maxPlayers = map.getInt("max-players");
        teamSize = map.getInt("team-size");

        for(String section : map.getSection("teams").getKeys()) {
            Team team = new Team(section);
            team.setSpawn(new Location(
                    world,
                    map.getDouble("teams." + section + ".spawn.x"),
                    map.getDouble("teams." + section + ".spawn.y"),
                    map.getDouble("teams." + section + ".spawn.z"),
                    map.getFloat("teams." + section + ".spawn.yaw"),
                    map.getFloat ("teams." + section + ".spawn.pitch")
            ));

            Bed bed = new Bed(new Location(
                    world,
                    map.getDouble("teams." + section + ".bed.b1.x"),
                    map.getDouble("teams." + section + ".bed.b1.y"),
                    map.getDouble("teams." + section + ".bed.b1.z")
            ), new Location(
                    world,
                    map.getDouble("teams." + section + ".bed.b2.x"),
                    map.getDouble("teams." + section + ".bed.b2.y"),
                    map.getDouble("teams." + section + ".bed.b2.z")
            ));

            team.setBed(bed);

            Main.gameHandler.getMap().addBed(bed);
            Main.gameHandler.getMap().addTeam(team);
        }

        for(String section : map.getSection("resources").getKeys()) {
            List<String> mList = map.getStringList("resources." + section + ".materials");
            String[] materials = mList.toArray(new String[mList.size()]);

            Resource resource = new Resource(Integer.parseInt(section), materials);

            resource.setLocation(new Location(
                    Main.gameHandler.getMap().getWorld(),
                    map.getDouble("resources." + section + ".location.x"),
                    map.getDouble("resources." + section + ".location.y") + 0.5,
                    map.getDouble("resources." + section + ".location.z")
            ));

            Main.gameHandler.getMap().addResource(resource);
        }
    }

    public void saveToConfig() {
        File mapFile = new File(Main.plugin.getDataFolder(), Main.MAP);

        if(!Main.configuration.configExists(mapFile)) {
            try {
                if(!new File(Main.plugin.getDataFolder(), Main.MAP).createNewFile()) {
                    Chat.getLogger("Map creation failed","severe");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Configuration map = Main.configuration.loadConfig(new File(Main.plugin.getDataFolder(), Main.MAP));

        map.set("world", world.getName());
        map.set("center.x", center.getX());
        map.set("center.y", center.getY());
        map.set("center.z", center.getZ());
        map.set("radius", radius);
        map.set("min-players", minPlayes);
        map.set("max-players", maxPlayers);
        map.set("team-size", teamSize);

        for(Team team : teams) {
            map.set("teams." + team.getName().name() + ".spawn.x", team.getSpawn().getX());
            map.set("teams." + team.getName().name() + ".spawn.y", team.getSpawn().getY());
            map.set("teams." + team.getName().name() + ".spawn.z", team.getSpawn().getZ());
            map.set("teams." + team.getName().name() + ".spawn.yaw", team.getSpawn().getYaw());
            map.set("teams." + team.getName().name() + ".spawn.pitch", team.getSpawn().getPitch());

            map.set("teams." + team.getName().name() + ".bed.b1.x", team.getBed().getLocation().getX());
            map.set("teams." + team.getName().name() + ".bed.b1.y", team.getBed().getLocation().getY());
            map.set("teams." + team.getName().name() + ".bed.b1.z", team.getBed().getLocation().getZ());

            map.set("teams." + team.getName().name() + ".bed.b2.x", team.getBed().getLocation2().getX());
            map.set("teams." + team.getName().name() + ".bed.b2.y", team.getBed().getLocation2().getY());
            map.set("teams." + team.getName().name() + ".bed.b2.z", team.getBed().getLocation2().getZ());
        }

        for(Resource resource : resources) {
            map.set("resources." + resource.getId() + ".materials", resource.getResources());

            map.set("resources." + resource.getId() + ".location.x", resource.getLocation().getX());
            map.set("resources." + resource.getId() + ".location.y", resource.getLocation().getY());
            map.set("resources." + resource.getId() + ".location.z", resource.getLocation().getZ());

            map.set("resources." + resource.getId() + ".team", resource.getTeam() == null ? "" : resource.getTeam().name());
        }

        Main.configuration.saveConfig(map, new File(Main.plugin.getDataFolder(), Main.MAP));
    }
}
