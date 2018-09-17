package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener {

    public PlayerDropItem() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if (!event.getPlayer().isOp()) event.setCancelled(true);
    }
}
