package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.player.PlayerTeamSelectEvent;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerTeamSelect implements Listener {

    public PlayerTeamSelect() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerTeamSelect(PlayerTeamSelectEvent event) {
        Player player = event.getPlayer();
        Team team = event.getTeam();

        if (team.containsPlayer(player)) {
            player.sendMessage(DragonDefend.getInstance().getPrefix() + ChatColor.RED + "You are already on this team!");
            event.setCancelled(true);

            return;
        }

        if (team.getPlayers().size() >= team.getMaxPlayers()) {
            player.sendMessage(DragonDefend.getInstance().getPrefix() + ChatColor.RED + "This team is already full!");
            event.setCancelled(true);

            return;
        }

        Team playerTeam = DragonDefend.getInstance().getTeamManager().getTeam(player);

        if (playerTeam != null) playerTeam.removePlayer(player);

        DragonDefend.getInstance().getKitManager().setKit(player, team.getKit());

        // Show team color in tab-list
        player.setPlayerListName(team.getColor() + player.getName());
        player.sendTitle("", ChatColor.GOLD + "You joined the " + team.getColor() + team.getName(), 20, 3 * 20, 20);
    }
}
