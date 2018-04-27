package minebedwarsold;

import it.mineblock.minebedwarsold.socket.*;
import minebedwarsold.socket.SendToLobby;

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
public class Socket {

    public static SendToLobby sendToLobby;

    public static void init() {
        String host = Main.config.getString("socket.host");
        int port = Main.config.getInt("socket.port");
        boolean verbose = Main.config.getBoolean("socket.verbose");

        sendToLobby = new SendToLobby(host, port, verbose);
    }
}
