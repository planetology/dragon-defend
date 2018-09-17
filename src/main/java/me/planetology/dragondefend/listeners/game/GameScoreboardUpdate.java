package me.planetology.dragondefend.listeners.game;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameScoreboardUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

public class GameScoreboardUpdate implements Listener {

    public GameScoreboardUpdate() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onGameScoreboardUpdate(GameScoreboardUpdateEvent event) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(DragonDefend.getInstance().getGameManager().getScoreboardTitle());

            Score playerScore = onlinePlayer.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Players: ");
            Score eggScore = onlinePlayer.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Dragon Egg: ");

            playerScore.setScore(Bukkit.getOnlinePlayers().size());
            eggScore.setScore(DragonDefend.getInstance().getGameManager().getEggLives());
        }
    }
}
