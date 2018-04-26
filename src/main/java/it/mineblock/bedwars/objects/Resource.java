package it.mineblock.bedwars.objects;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class Resource {
    BukkitTask task;
    private int id;
    private String resource;
    private Material material;
    private Location location;
    private int tier;
    private Teams team;

    public Resource(int id, String resource) {
        this.id = id;
        this.resource = resource;
        this.tier = 0;

        switch(resource) {
            case "iron":
                material = Material.IRON_INGOT;
                break;
            case "gold":
                material = Material.GOLD_INGOT;
                break;
            case "diamond":
                material = Material.DIAMOND;
                break;
            case "emerald":
                material = Material.EMERALD;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }

    public Location getLocation() {
        return location;
    }

    public Teams getTeam() {
        return team;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTeam(String name) {
        if(name.isEmpty()) {
            this.team = null;
        } else {
            for (Teams team : Teams.values()) {
                if (name.equalsIgnoreCase(team.name())) {
                    this.team = team;
                    return;
                }
            }
            this.team = null;
            //TODO Change behaviour based on situation
        }
    }

    private boolean upgrade() {
        if(tier++ > (Main.config.getSection("resources").getKeys().size() - 1)) {
            return false;
        }

        tier++;
        if(task != null) {
            task.cancel();
            spawn();
        }
        return true;
    }

    public void spawn() {
        long delay = Main.config.getLong("game.resources.tier-" + tier + "." + resource + ".delay") * 20;
        int amount = Main.config.getInt("game.resources.tier-" + tier + "." + resource + ".amount");
        ItemStack item = new ItemStack(material, amount);
        World world = location.getWorld();

        task = Bukkit.getScheduler().runTaskTimer(Main.plugin, () -> {
            world.dropItem(location, item);
            //TODO Change behaviour
        }, 0L, delay);
    }
}
