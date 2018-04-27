package minebedwarsold.socket;

import it.mineblock.mbcore.Chat;
import it.mineblock.mbcore.socket.Packet;
import it.mineblock.mbcore.socket.SocketClient;
import it.mineblock.mbcore.socket.SocketCommandException;

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
public class SendToLobby extends SocketClient {

    public SendToLobby(String host, int port, boolean verbose) {
        super(host, port, verbose);
    }

    @Override
    public void execute(Packet input) {
        if(input.getData().equals("exception")) {
            SocketCommandException exception = (SocketCommandException) input.getData();
            Chat.getLogger("6c" + exception.getMessage() + " - &4" + exception.getCause(), "warning");
        }
    }
}
