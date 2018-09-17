package me.planetology.dragondefend.listeners.entity;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

    public EntityDamageByEntity() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player taker = (Player) event.getEntity();

            if (DragonDefend.getInstance().getGameManager().getGameState().equals(GameState.JOIN)) event.setCancelled(true);

            if (DragonDefend.getInstance().getGameManager().getSpectators().contains(damager)){
                event.setCancelled(true);

                return;
            }

            if (DragonDefend.getInstance().getTeamManager().getTeam(damager).getName().equals(DragonDefend.getInstance().getTeamManager().getTeam(taker).getName())) {
                event.setCancelled(true);

                return;
            }
        }

        if (event.getDamager() instanceof Arrow && event.getEntity() instanceof Player){
            Player taker = (Player) event.getEntity();
            Arrow arrow = (Arrow) event.getDamager();

            if (DragonDefend.getInstance().getTeamManager().getTeam((Player) arrow.getShooter()).getName() == DragonDefend.getInstance().getTeamManager().getTeam(taker).getName()){
                event.setCancelled(true);
            }
        }
    }
}
