package it.mineblock.minebedwars.objects;

import it.mineblock.minebedwars.enums.Teams;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

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
public class Team {
    private Teams name;
    private Location spawn;
    private Bed bed;
    private ArrayList<Player> members;

    public Team(Teams name) {
        this.name = name;
    }

    public Team(String name) {
        for(Teams team : Teams.values()) {
            if(name.equalsIgnoreCase(team.name())) {
                this.name = team;
            }
        }
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Teams getName() {
        return name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public int getSize() {
        return members.size();
    }
}
