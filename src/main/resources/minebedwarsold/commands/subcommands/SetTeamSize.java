package minebedwarsold.commands.subcommands;

import it.mineblock.mbcore.Chat;
import minebedwarsold.Main;
import minebedwarsold.enums.Message;
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
public class SetTeamSize {
    public static void execute(Player player, String rawNumber) {

        int number;

        try {
            number = Integer.parseInt(rawNumber);
        } catch (NumberFormatException ignored) {
            Chat.send(Message.INCORRECT_ARGUMENT.get(), player);
            return;
        }

        Integer max = Main.gameHandler.getMap().getMaxPlayers();

        if(max == null) {
            //TODO Send error (configure before min and max)
            return;
        }

        if((max % number) != 0) {
            //TODO Send error
            return;
        }

        Main.gameHandler.getMap().setTeamSize(number);

        //TODO Send help message
    }
}
