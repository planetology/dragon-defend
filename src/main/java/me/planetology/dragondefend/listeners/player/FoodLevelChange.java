package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {

    public FoodLevelChange() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
