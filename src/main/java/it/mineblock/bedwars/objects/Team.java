package it.mineblock.bedwars.objects;

import it.mineblock.bedwars.enums.Teams;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private Teams name;
    private Location spawn;
    private Location bed1;
    private Location bed2;
    private ArrayList<Player> members = new ArrayList<>();

    public Team(Teams name) {
        this.name = name;
    }

    public Teams getTeam() {
        return name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getBed1() {
        return bed1;
    }

    public Location getBed2() {
        return bed2;
    }
}
