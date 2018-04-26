package it.mineblock.minebedwarsold.objects;

import it.mineblock.mbcore.Chat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

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
public class Bed {
    private Location loc;
    private Location loc2;
    private Team team;

    public Bed(Location loc) {
        this.loc = loc;

        Block bed = loc.getBlock();

        Block rel = bed.getRelative(BlockFace.EAST);
        if(rel.getType().equals(Material.BED_BLOCK)) {
            loc2 = rel.getLocation();
            return;
        }

        rel = bed.getRelative(BlockFace.SOUTH);
        if(rel.getType().equals(Material.BED_BLOCK)) {
            loc2 = rel.getLocation();
            return;
        }

        rel = bed.getRelative(BlockFace.WEST);
        if(rel.getType().equals(Material.BED_BLOCK)) {
            loc2 = rel.getLocation();
            return;
        }

        rel = bed.getRelative(BlockFace.NORTH);
        if(rel.getType().equals(Material.BED_BLOCK)) {
            loc2 = rel.getLocation();
            return;
        }

        Chat.getLogger("Bed error! Missing second block.", "warning");
    }

    public Bed(Location loc, Location loc2) {
        this.loc = loc;
        this.loc2 = loc2;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Location getLocation() {
        return loc;
    }

    public Location getLocation2() {
        return loc2;
    }

    public Boolean isThis(Location location) {
        return loc.equals(location) || loc2.equals(location);
    }

    @Override
    public String toString() {
        return "{Block1: [wolrd:" +
                loc.getWorld().getName() +
                ", x: " +
                loc.getX() +
                ", y: " +
                loc.getY() +
                ", z: " +
                loc.getZ() +
                "], Block2: [world: " +
                loc2.getWorld().getName() +
                ", x: " +
                loc2.getX() +
                ", y: " +
                loc2.getY() +
                ", z: " +
                loc2.getZ() +
                "]}";
    }
}
