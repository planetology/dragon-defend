package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.player.PlayerGameJoinEvent;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

public class PlayerGameJoin implements Listener {

    public PlayerGameJoin() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerGameJoin(PlayerGameJoinEvent event) {
        Player player = event.getPlayer();

        // Clean up player
        player.setHealthScale(20.0);
        player.setHealth(20.0);
        player.setLevel(0);
        player.setExp(0.0F);
        player.setGameMode(GameMode.SURVIVAL);
        player.getActivePotionEffects().clear();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setHeldItemSlot(4);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("game", "dummy", ChatColor.GOLD + "DragonDefend");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score playerScore = objective.getScore("Players: ");
        Score eggScore = objective.getScore("Dragon Egg: ");

        playerScore.setScore(Bukkit.getOnlinePlayers().size());
        eggScore.setScore(DragonDefend.getInstance().getGameManager().getEggLives());

        player.setScoreboard(scoreboard);

        switch (DragonDefend.getInstance().getGameManager().getGameState()) {
            case JOIN:
                player.teleport(DragonDefend.getInstance().getConfigManager().getPlayerLocation("lobby"));

                player.sendMessage("");
                player.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GOLD + "Welcome to the land of the dragons " + player.getName());
                player.sendMessage(ChatColor.GRAY + " The dragon Glaurung will be born soon, but");
                player.sendMessage(ChatColor.GRAY + " he will be a danger for the people living");
                player.sendMessage(ChatColor.GRAY + " here. Help us slaying him, or defend his egg...");
                player.sendMessage("");

                // Give player team selection items
                for (Team team : DragonDefend.getInstance().getTeamManager().getTeams()){
                    player.getInventory().addItem(team.getItem());
                }

                break;
            case STARTED:
            case FINISHED:
                player.teleport(DragonDefend.getInstance().getConfigManager().getPlayerLocation("spectator"));
                player.setGameMode(GameMode.SPECTATOR);

                DragonDefend.getInstance().getGameManager().getSpectators().add(player.getName());
            default:
                break;
        }
    }
}
