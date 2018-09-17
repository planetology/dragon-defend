package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private final DragonDefend instance;

    public PlayerDeath() {
        instance = DragonDefend.getInstance();

        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (event.getEntity().getKiller() instanceof Player) {
            Team playerTeam = instance.getTeamManager().getTeam(player), killerTeam = instance.getTeamManager().getTeam(killer);

            event.setDeathMessage(instance.getPrefix() + playerTeam.getColor() + player.getName() + ChatColor.GOLD + " was killed by " + killerTeam.getColor() + killer.getName() + ChatColor.GOLD + "!");
        } else {
            Team playerTeam = instance.getTeamManager().getTeam(player);

            event.setDeathMessage(instance.getPrefix() + playerTeam.getColor() + player.getName() + ChatColor.GOLD + " died.");
        }

        event.getDrops().clear();
    }
}
