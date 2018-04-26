package it.mineblock.bedwars.objects;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.enums.Teams;
import it.mineblock.mbcore.spigot.Chat;
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

    public Team(String name) {
        for(Teams team : Teams.values()) {
            if(name.equalsIgnoreCase(team.name())) {
                this.name = team;
                return;
            }
        }
        new IllegalArgumentException("This team has an invalid name, check your map config!").printStackTrace();
        Main.plugin.onDisable();
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

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setBed1(Location bed1) {
        this.bed1 = bed1;
    }

    public void setBed2(Location bed2) {
        this.bed2 = bed2;
    }
}
