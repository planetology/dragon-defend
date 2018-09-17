package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawn implements Listener {

    private final DragonDefend instance;

    public PlayerRespawn() {
        instance = DragonDefend.getInstance();

        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Team team = instance.getTeamManager().getTeam(event.getPlayer());

        event.getPlayer().teleport(team.getLocation());

        player.setAllowFlight(true);
        player.setFlying(true);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.hidePlayer(instance, player);
        }

        new BukkitRunnable() {
            int seconds = 8;

            @Override
            public void run() {
                seconds--;

                player.setLevel(seconds);

                if (seconds == 0) {
                    cancel();

                    Team team = instance.getTeamManager().getTeam(player);

                    player.teleport(team.getLocation());
                    player.setFlying(false);
                    player.setAllowFlight(false);

                    instance.getKitManager().giveKit(player);

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showPlayer(instance, player);
                    }
                }
            }

        }.runTaskTimer(instance, 1L, 20L);
    }
}
