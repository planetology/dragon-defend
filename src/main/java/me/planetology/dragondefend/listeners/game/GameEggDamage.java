package me.planetology.dragondefend.listeners.game;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameEggDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEggDamage implements Listener {

    public GameEggDamage() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onGameEggDamage(GameEggDamageEvent event) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }

        Bukkit.broadcastMessage(DragonDefend.getInstance().getPrefix() + ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.GOLD +  " has damaged the dragon egg!");
    }
}
