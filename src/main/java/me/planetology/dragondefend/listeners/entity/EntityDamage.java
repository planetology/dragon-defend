package me.planetology.dragondefend.listeners.entity;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    public EntityDamage() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (DragonDefend.getInstance().getGameManager().getGameState().equals(GameState.JOIN)) event.setCancelled(true);
    }
}
