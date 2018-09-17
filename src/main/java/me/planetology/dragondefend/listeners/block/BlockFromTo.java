package me.planetology.dragondefend.listeners.block;

import me.planetology.dragondefend.DragonDefend;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromTo implements Listener {

    public BlockFromTo() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.getBlock().getType().equals(Material.DRAGON_EGG)) {
            event.setCancelled(true);
        }
    }
}
