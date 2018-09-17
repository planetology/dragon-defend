package me.planetology.dragondefend.listeners.game;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameEggDestroyEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEggDestroy implements Listener {

    public GameEggDestroy() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onGameEggDestory(GameEggDestroyEvent event) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.spawnParticle(Particle.EXPLOSION_HUGE, event.getBlock().getLocation(), 1, 0, 0, 0);
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1.0f, 1.0f);
        }

        Bukkit.broadcastMessage(DragonDefend.getInstance().getPrefix() + ChatColor.GOLD + "Game over, " + ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.GOLD +  " has destroyed Glaurung's egg!");
    }
}
