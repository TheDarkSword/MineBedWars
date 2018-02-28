package it.mineblock.minebedwars.objects;

import it.mineblock.minebedwars.Main;
import it.mineblock.minebedwars.enums.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2018 by Lorenzo Magni
 * This file is part of MineBedwars.
 * <p>
 * MineBedwars is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * MineBedwars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with MineBedwars.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Resource {
    public List<BukkitTask> tasks = new ArrayList<>();
    private int id;
    private String[] resources;
    private Material[] materials;
    private Location location;
    private int tier;
    private Teams team;

    public Resource(int id, String[] resources) {
        this.id = id;
        this.resources = resources;
        this.tier = 0;

        setMaterials();
    }

    public Resource(int id, String resource) {
        this.id = id;
        this.resources = new String[] {resource};
        this.tier = 0;

        setMaterials();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTier() {
        tier++;

        for(BukkitTask task : tasks) {
            task.cancel();
        }

        spawn();
    }

    private void setMaterials() {
        List<Material> materials = new ArrayList<>();

        for(String resource : resources) {
            switch(resource) {
                case "iron":
                    materials.add(Material.IRON_INGOT);
                    break;
                case "gold":
                    materials.add(Material.GOLD_INGOT);
                    break;
                case "diamond":
                    materials.add(Material.DIAMOND);
                    break;
                case "emerald":
                    materials.add(Material.EMERALD);
                    break;
            }
        }

        this.materials = new Material[materials.size()];
        short index = 0;
        for(Material material : materials) {
            this.materials[index] = material;
            index++;
        }
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public String[] getResources() {
        return resources;
    }

    public Location getLocation() {
        return location;
    }

    public Teams getTeam() {
        return team;
    }

    public void spawn() {
        short index = 0;
        for(String resource : resources) {
            long delay = Main.config.getInt("game.tier-" + tier + "." + resource + ".delay");
            int amount = Main.config.getInt("game.tier-" + tier + "." + resource + ".amount");

            ItemStack item = new ItemStack(materials[index], amount);
            World world = location.getWorld();

            tasks.add(Bukkit.getScheduler().runTaskTimer(Main.plugin, () -> world.dropItem(location, item), 0L, delay));
        }
    }
}
