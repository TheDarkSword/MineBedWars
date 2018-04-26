package it.mineblock.bedwars.objects;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Resource {
    private ArrayList<BukkitTask> tasks = new ArrayList<>();
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
        this(id, new String[] {resource});
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

    private void setMaterials() {
        Material[] materials = new Material[2];

        int index = 0;
        for(String resource : resources) {
            switch(resource) {
                case "iron":
                    materials[index] = Material.IRON_INGOT;
                    break;
                case "gold":
                    materials[index] = Material.GOLD_INGOT;
                    break;
                case "diamond":
                    materials[index] = Material.DIAMOND;
                    break;
                case "emerald":
                    materials[index] = Material.EMERALD;
                    break;
            }
        }

        this.materials = materials;
    }

    public void spawn() {
        short index = 0;
        for(String resource : resources) {
            long delay = Main.config.getInt("game.spawner.tier-" + tier + "." + resource + ".delay") * 20;
            int amount = Main.config.getInt("game.spawner.tier-" + tier + "." + resource + ".amount");

            ItemStack item = new ItemStack(materials[index], amount);
            World world = location.getWorld();

            tasks.add(Bukkit.getScheduler().runTaskTimer(Main.plugin, () -> {
                world.dropItem(location, item);
            }, 0L, delay));
        }
    }
}
