package me.planetology.dragondefend.listeners.game;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameStartEvent;
import me.planetology.dragondefend.team.TeamManager;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStart implements Listener {

    public GameStart() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        TeamManager teamManager = DragonDefend.getInstance().getTeamManager();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.getInventory().clear();
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

            DragonDefend.getInstance().getKitManager().giveKit(onlinePlayer);

            Team playerTeam = teamManager.getTeam(onlinePlayer);

            if (playerTeam == null) {
                Team smallestTeam = teamManager.getSmallestTeam();

                if (smallestTeam != null) {
                    smallestTeam.addPlayer(onlinePlayer);
                }
            }

            Team finalPlayerTeam = teamManager.getTeam(onlinePlayer);

            onlinePlayer.teleport(finalPlayerTeam.getLocation());
        }

        Bukkit.broadcastMessage(DragonDefend.getInstance().getPrefix() + ChatColor.GOLD + "Glaurung's egg has spawned, the game has started!");
    }
}
