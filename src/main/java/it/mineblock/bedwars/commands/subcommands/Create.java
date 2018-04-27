package it.mineblock.bedwars.commands.subcommands;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.Teams;
import it.mineblock.bedwars.objects.Resource;
import it.mineblock.bedwars.objects.Team;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;

public class Create {

    @SuppressWarnings("deprecation")
    public static void execute(Player player) {
        Main.gameHandler.getMap().setWorld(player.getWorld());

        /* Set team spawns */
        for(Chunk chunk : player.getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Block glass = beacon.getLocation().add(0, 1, 0).getBlock();

                    if(glass.getType().equals(Material.STAINED_GLASS)) {
                        byte data = glass.getData();

                        if(Main.debug) {
                            Chat.getLogger("&dFound team: " + Teams.getByMeta(data), "info");
                        }

                        Team team = new Team(Teams.getByMeta(data));
                        team.setSpawn(beacon.getLocation());

                        beacon.getBlock().setType(Material.AIR);
                        glass.setType(Material.AIR);

                        /* Set beds */
                        for(Chunk chunk2 : player.getWorld().getLoadedChunks()) {
                            for(BlockState bed : chunk2.getTileEntities()) {
                                if(bed instanceof Bed) {
                                    Block wool = bed.getLocation().subtract(0, 1, 0).getBlock();

                                    if(wool.getType().equals(Material.WOOL) && wool.getData() == data) {
                                        if(Main.debug) {
                                            Chat.getLogger("&5Found bed: " + Teams.getByMeta(data), "info");
                                        }

                                        team.setBeds(bed.getLocation());
                                    }
                                }
                            }
                        }
                        /* Set beds */

                        Main.gameHandler.getMap().addTeam(team);
                    }
                }
            }
        }
        /* Set team spawns */

        /* Set resources */
        int index = 0;
        for(Chunk chunk : player.getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Location location = beacon.getLocation();
                    beacon.setType(Material.AIR);
                    Block block = location.subtract(0, 1, 0).getBlock();
                    Resource resource;
                    Resource resource2 = null;

                    if(block.getType().equals(Material.EMERALD_BLOCK)) {
                        resource = new Resource(index, "emerald");
                        resource.setLocation(location);
                    }
                    else if(block.getType().equals(Material.DIAMOND_BLOCK)) {
                        resource = new Resource(index, "diamond");
                        resource.setLocation(location);
                    }
                    else if(block.getType().equals(Material.IRON_BLOCK)) {
                        resource = new Resource(index, "iron");
                        index++;
                        resource2 = new Resource(index, "gold");
                        Location rLocation;

                        if(block.getRelative(BlockFace.NORTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = location.add(0.5D, 0D, -0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = location.add(0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = location.add(-0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.NORTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = location.add(-0.5D, 0D, -0.5D);
                        } else {
                            continue;
                        }
                        resource.setLocation(rLocation);
                        resource2.setLocation(rLocation);

                        Block wool = location.add(0, 1, 0).getBlock();

                        if(wool.getType().equals(Material.WOOL)) {
                            resource.setTeam(Teams.getByMeta(wool.getData()));
                            resource2.setTeam(Teams.getByMeta(wool.getData()));

                            wool.setType(Material.AIR);
                        }
                    } else {
                        continue;
                    }

                    index++;
                    Main.gameHandler.getMap().addResource(resource);
                    if(resource2 != null) Main.gameHandler.getMap().addResource(resource2);
                }
            }
        }
        /* Set resources */
    }
}
