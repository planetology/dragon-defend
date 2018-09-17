package me.planetology.dragondefend.commands;

import me.planetology.dragondefend.DragonDefend;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final DragonDefend instance;

    public SetSpawnCommand() {
        instance = DragonDefend.getInstance();

        instance.getCommand("setspawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.isOp()) return true;

        if (args.length == 1) {
            player.sendMessage(instance.getPrefix() + ChatColor.GREEN + "Saved location: " + args[0]);
            instance.getConfigManager().setPlayerLocation(args[0], player.getLocation());
        } else {
            player.sendMessage(instance.getPrefix() + ChatColor.RED + "Usage: /setspawn <name>");

            return true;
        }

        return false;
    }
}
