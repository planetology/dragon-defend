package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.game.GameState;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    public PlayerQuit() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Team team = DragonDefend.getInstance().getTeamManager().getTeam(player);

        if (team != null){
            team.removePlayer(player);
        }

        if (DragonDefend.getInstance().getGameManager().getGameState().equals(GameState.JOIN)) {
            event.setQuitMessage(ChatColor.DARK_GRAY + "« " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " left the game! (" + Bukkit.getOnlinePlayers().size() + "/12)");
        } else {
            event.setQuitMessage(null);
        }
    }
}
