package it.mineblock.minebedwarsold.commands.subcommands;

import it.mineblock.mbcore.Chat;
import it.mineblock.minebedwarsold.Main;
import it.mineblock.minebedwarsold.enums.Teams;
import it.mineblock.minebedwarsold.objects.Bed;
import it.mineblock.minebedwarsold.objects.Resource;
import it.mineblock.minebedwarsold.objects.Team;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

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
public class Create {
    @SuppressWarnings("deprecation")
    public static void execute(Player player) {
        Main.gameHandler.getMap().setWorld(player.getWorld());

        for(Chunk chunk : Main.gameHandler.getMap().getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Block glass = beacon.getLocation().add(0D, 1D, 0D).getBlock();

                    if(glass.getType().equals(Material.STAINED_GLASS)) {
                        Chat.getLogger("&dFound team: " + Teams.getByMeta(glass.getData()), "info");
                        Team team = new Team(Teams.getByMeta(glass.getData()));
                        team.setSpawn(beacon.getLocation());

                        beacon.getBlock().setType(Material.AIR);
                        glass.setType(Material.AIR);

                        Main.gameHandler.getMap().addTeam(team);
                        Chat.getLogger("&2" + team.toString(), "info");
                    }
                }
            }
        }

        for(Chunk chunk : Main.gameHandler.getMap().getWorld().getLoadedChunks()) {
            outerLoop:
            for(BlockState bedBlock : chunk.getTileEntities()) {
                if(bedBlock instanceof org.bukkit.block.Bed) {
                    Chat.getLogger("&5Found bed", "info");
                    Block wool = bedBlock.getLocation().subtract(0D, 1D, 0D).getBlock();

                    Chat.getLogger("&6Wool color: " + wool.getData() + " " + Teams.getByMeta(wool.getData()).name(), "info");

                    if(wool.getType().equals(Material.WOOL)) {
                        Bed bed = new Bed(bedBlock.getLocation());
                        for(Bed b : Main.gameHandler.getMap().getBeds()) {
                            if(b.isThis(bed.getLocation())) {
                                continue outerLoop;
                            }
                        }
                        
                        Team team = null;
                        int index = 0;

                        for(int i = 0; i < Main.gameHandler.getMap().getTeams().size(); i++) {
                            if(Main.gameHandler.getMap().getTeams().get(i).getName().equals(Teams.getByMeta(wool.getData()))) {
                                team = Main.gameHandler.getMap().getTeams().get(i);
                                index = i;
                                break;
                            }
                        }

                        Chat.getLogger(index + team.toString(), "info");

                        bed.setTeam(team);
                        Chat.getLogger("&4" + bed.toString(), "info");
                        team.setBed(bed);
                        Main.gameHandler.getMap().setTeam(team, index);
                        Main.gameHandler.getMap().addBed(bed);
                    }
                }
            }
        }

        int index = 0;
        for(Chunk chunk : Main.gameHandler.getMap().getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Location bLocation = beacon.getLocation();
                    beacon.getBlock().setType(Material.AIR);

                    Block block = bLocation.subtract(0D, 1D, 0D).getBlock();
                    Resource resource;

                    if(block.getType().equals(Material.EMERALD_BLOCK)) {
                        resource = new Resource(index, "emerald");
                        resource.setLocation(block.getLocation());
                    }
                    else if(block.getType().equals(Material.DIAMOND_BLOCK)) {
                        resource = new Resource(index, "diamond");
                        resource.setLocation(block.getLocation());
                    }
                    else if(block.getType().equals(Material.GOLD_BLOCK)) {
                        resource = new Resource(index, new String[] {"iron", "gold"});
                        Location rLocation;

                        if(block.getRelative(BlockFace.NORTH_EAST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, -0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_EAST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_WEST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.NORTH_WEST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, -0.5D);
                        } else {
                            continue;
                        }

                        resource.setLocation(rLocation);
                    }
                    else if(block.getType().equals(Material.IRON_BLOCK)) {
                        resource = new Resource(index, new String[] {"iron", "gold"});
                        Location rLocation;

                        if(block.getRelative(BlockFace.NORTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, -0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.NORTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, -0.5D);
                        } else {
                            continue;
                        }

                        resource.setLocation(rLocation);
                    } else {
                        continue;
                    }

                    Block wool = bLocation.add(0D, 1D, 0D).getBlock();

                    if(wool.getType().equals(Material.WOOL)) {
                        resource.setTeam(Teams.getByMeta(wool.getData()));
                        wool.setType(Material.AIR);
                    }

                    index++;
                    Main.gameHandler.getMap().addResource(resource);
                    Chat.getLogger("&e" + resource.toString(), "info");
                }
            }
        }

        //TODO Send guide message
    }

    /*@SuppressWarnings("deprecation")
    public static void execute(Player player) {
        Main.gameHandler.getMap().setWorld(player.getWorld());

        for(Chunk chunk : Main.gameHandler.getMap().getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Block glass = beacon.getLocation().add(0D, 1D, 0D).getBlock();

                    if(glass.getType().equals(Material.STAINED_GLASS)) {
                        Chat.getLogger("&dFound team: " + Teams.getByMeta(glass.getData()), "info");
                        Team team = new Team(Teams.getByMeta(glass.getData()));
                        team.setSpawn(beacon.getLocation());

                        beacon.getBlock().setType(Material.AIR);
                        glass.setType(Material.AIR);

                        outerLoop:
                        for(BlockState bedBlock : chunk.getTileEntities()) {
                            if(bedBlock instanceof org.bukkit.block.Bed) {
                                Chat.getLogger("&5Found bed", "info");
                                Block wool = bedBlock.getLocation().subtract(0D, 1D, 0D).getBlock();

                                Chat.getLogger("&6Wool color: " + wool.getData() + " " + Teams.getByMeta(wool.getData()).name(), "info");

                                if(wool.getType().equals(Material.WOOL) && Teams.getByMeta(wool.getData()).equals(team.getName())) {
                                    Bed bed = new Bed(bedBlock.getLocation());
                                    for(Bed b : Main.gameHandler.getMap().getBeds()) {
                                        if(b.isThis(bed.getLocation())) {
                                            break outerLoop;
                                        }
                                    }
                                    bed.setTeam(team);
                                    Chat.getLogger("&4" + bed.toString(), "info");
                                    team.setBed(bed);
                                    Main.gameHandler.getMap().addBed(bed);
                                    break;
                                }
                            }
                        }

                        Main.gameHandler.getMap().addTeam(team);
                        Chat.getLogger("&2" + team.toString(), "info");
                    }
                }
            }
        }

        int index = 0;
        for(Chunk chunk : Main.gameHandler.getMap().getWorld().getLoadedChunks()) {
            for(BlockState beacon : chunk.getTileEntities()) {
                if(beacon instanceof Beacon) {
                    Location bLocation = beacon.getLocation();
                    beacon.getBlock().setType(Material.AIR);

                    Block block = bLocation.subtract(0D, 1D, 0D).getBlock();
                    Resource resource;

                    if(block.getType().equals(Material.EMERALD_BLOCK)) {
                        resource = new Resource(index, "emerald");
                        resource.setLocation(block.getLocation());
                    }
                    else if(block.getType().equals(Material.DIAMOND_BLOCK)) {
                        resource = new Resource(index, "diamond");
                        resource.setLocation(block.getLocation());
                    }
                    else if(block.getType().equals(Material.GOLD_BLOCK)) {
                        resource = new Resource(index, new String[] {"iron", "gold"});
                        Location rLocation;

                        if(block.getRelative(BlockFace.NORTH_EAST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, -0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_EAST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_WEST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.NORTH_WEST).getType().equals(Material.GOLD_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, -0.5D);
                        } else {
                            continue;
                        }

                        resource.setLocation(rLocation);
                    }
                    else if(block.getType().equals(Material.IRON_BLOCK)) {
                        resource = new Resource(index, new String[] {"iron", "gold"});
                        Location rLocation;

                        if(block.getRelative(BlockFace.NORTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, -0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_EAST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.SOUTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, 0.5D);
                        }
                        else if(block.getRelative(BlockFace.NORTH_WEST).getType().equals(Material.IRON_BLOCK)) {
                            rLocation = bLocation.add(-0.5D, 0D, -0.5D);
                        } else {
                            continue;
                        }

                        resource.setLocation(rLocation);
                    } else {
                        continue;
                    }

                    Block wool = bLocation.add(0D, 1D, 0D).getBlock();

                    if(wool.getType().equals(Material.WOOL)) {
                        resource.setTeam(Teams.getByMeta(wool.getData()));
                        wool.setType(Material.AIR);
                    }

                    index++;
                    Main.gameHandler.getMap().addResource(resource);
                    Chat.getLogger("&e" + resource.toString(), "info");
                }
            }
        }

        //TODO Send guide message
    }*/
}
