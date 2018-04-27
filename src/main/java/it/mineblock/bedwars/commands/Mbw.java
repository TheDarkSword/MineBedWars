package it.mineblock.bedwars.commands;

import it.mineblock.bedwars.Main;
import it.mineblock.bedwars.commands.subcommands.Create;
import it.mineblock.bedwars.enums.GamePhases;
import it.mineblock.bedwars.enums.Messages;
import it.mineblock.bedwars.enums.Permissions;
import it.mineblock.mbcore.spigot.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mbw implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(!(sender instanceof Player)) {
            Chat.send(Messages.NOT_PLAYER.get(), sender);
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            sendHelp();
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                Create.execute(player);
                break;
            case "setradius":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                break;
            case "setmaxplayers":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                break;
            case "setminplayers":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                break;
            case "setteamsize":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                break;
            case "done":
                if(!Main.gameHandler.getPhase().equals(GamePhases.CONFIGURATION)) {
                    Chat.send(Messages.NOT_NOW.get(), player);
                    return true;
                }

                if(!player.hasPermission(Permissions.EDIT.get())) {
                    Chat.send(Messages.INSUFFICIENT_PERM.get(), player);
                    return true;
                }

                break;
            default:
                sendHelp();
                break;
        }
        return true;
    }

    private void sendHelp() {
    }
}
