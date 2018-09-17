package me.planetology.dragondefend.runnables.threads;

import lombok.Getter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameScoreboardUpdateEvent;
import me.planetology.dragondefend.game.GameState;
import me.planetology.dragondefend.runnables.GameRunnable;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class StartedThread implements GameRunnable {

    private final DragonDefend instance;

    private @Getter int seconds, runnableInt;

    public StartedThread() {
        instance = DragonDefend.getInstance();

        // Set game length to 10 minutes
        seconds = 10 * 60;
    }

    @Override
    public void start() {
        runnableInt = Bukkit.getScheduler().scheduleSyncRepeatingTask(DragonDefend.getInstance(), () -> {
            if (seconds != 0) {
                seconds--;

                instance.getGameManager().setScoreboardTitle(ChatColor.GOLD + "DragonDefend " + ChatColor.GRAY + "» " + ChatColor.WHITE +  instance.getGameManager().getTimeUtil().convertToClock(seconds));

                GameScoreboardUpdateEvent event = new GameScoreboardUpdateEvent();

                Bukkit.getServer().getPluginManager().callEvent(event);
            } else {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0f, 1.0f);
                }

                Bukkit.broadcastMessage(instance.getPrefix() + ChatColor.GOLD + "Game over, the " + instance.getTeamManager().getTeam("Defenders").getColor() + "Defenders" + ChatColor.GOLD + "have won!");

                instance.getGameManager().setGameState(GameState.FINISHED);
                instance.getGameManager().endGame();

                stop();
            }
        }, 1L, 20L);
    }

    @Override
    public void stop() {
        try {
            Bukkit.getScheduler().cancelTask(runnableInt);
        } catch (Exception e) {
            System.out.println("Tried to cancel a runnable with ID: " + runnableInt + " that is not running (yet?)!");
        }
    }
}
