package it.mineblock.minebedwars.commands;

import it.mineblock.mbcore.Chat;
import it.mineblock.minebedwars.Main;
import it.mineblock.minebedwars.commands.subcommands.*;
import it.mineblock.minebedwars.enums.GamePhases;
import it.mineblock.minebedwars.enums.Message;
import it.mineblock.minebedwars.enums.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

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
public class Mbw implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            Chat.send(Message.NOT_PLAYER.get(), sender);
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            sendHelp(player);
            return true;
        }

        if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURING)) {
            Chat.send(Message.NOT_NOW.get(), player);
            return true;
        }

        String arg = args[0].toLowerCase();

        switch(arg) {
            case "create":
                if(args.length != 1) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                Create.execute(player);
                break;
            case "setradius":
                if(args.length != 2) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                SetRadius.execute(player, args[1]);
                break;
            case "setmaxplayers":
                if(args.length != 2) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                SetMaxPlayers.execute(player, args[1]);
                break;
            case "setminplayers":
                if(args.length != 2) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                SetMinPlayers.execute(player, args[1]);
                break;
            case "setteamsize":
                if(args.length != 2) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                SetTeamSize.execute(player, args[1]);
                break;
            case "done":
                if(args.length != 1) {
                    sendHelp(player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Message.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                Done.execute(player);
                break;
            default:
                sendHelp(player);
                break;
        }
        return true;
    }

    private void sendHelp(Player player) {
        List<String> help = Main.msgConfig.getStringList("help");
        for(String msg : help) {
            Chat.send(msg, player, true);
        }
    }
}
