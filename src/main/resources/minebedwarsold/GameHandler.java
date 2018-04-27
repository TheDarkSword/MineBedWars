package minebedwarsold;

import minebedwarsold.enums.GamePhases;
import minebedwarsold.objects.Resource;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
public class GameHandler {
    private Map map = new Map();
    private GamePhases phase;

    private ArrayList<Player> shoutTimeout = new ArrayList<>();

    public GameHandler() {
        this.phase = GamePhases.BOOTING;
    }

    public void setPhase(GamePhases phase) {
        this.phase = phase;
        phaseChanged();
    }

    public Map getMap() {
        return map;
    }

    public GamePhases getPhase() {
        return phase;
    }

    private void phaseChanged() {
        if(phase.equals(GamePhases.WAITING)) {
            waiting();
        }
        else if(phase.equals(GamePhases.STARTING)) {
            starting();
        }
        else if(phase.equals(GamePhases.RUNNING)) {
            running();
        }
        else if(phase.equals(GamePhases.ENDING)) {
            ending();
        }
        else if(phase.equals(GamePhases.REBOOTING)) {
            rebooting();
        }
        else if(phase.equals(GamePhases.CONFIGURING)) {
            configuring();
        }
    }

    private void waiting() {
        for(Resource resource : map.getResources()) {
            resource.spawn();
        }
    }

    private void starting() {

    }

    private void running() {

    }

    private void ending() {

    }

    private void rebooting() {

    }

    private void configuring() {

    }
}