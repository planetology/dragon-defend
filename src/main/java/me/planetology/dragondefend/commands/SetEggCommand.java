package me.planetology.dragondefend.commands;

import me.planetology.dragondefend.DragonDefend;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEggCommand implements CommandExecutor {

    private final DragonDefend instance;

    public SetEggCommand() {
        instance = DragonDefend.getInstance();

        instance.getCommand("setegg").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.isOp()) return true;

        player.sendMessage(instance.getPrefix() + ChatColor.GREEN + "Saved location: Dragon Egg");
        instance.getConfigManager().setBlockLocation("egg", player.getLocation());

        if (player.getLocation().getBlock().getType().equals(Material.AIR)) {
            player.getLocation().getBlock().setType(Material.DRAGON_EGG);
        }

        return false;
    }
}
