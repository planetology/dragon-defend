package me.planetology.dragondefend.runnables.threads;

import lombok.Getter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameScoreboardUpdateEvent;
import me.planetology.dragondefend.runnables.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FinishedThread implements GameRunnable {

    private @Getter int seconds, runnableInt;

    public FinishedThread() {
        seconds = 10;
    }

    @Override
    public void start() {
        runnableInt = Bukkit.getScheduler().scheduleSyncRepeatingTask(DragonDefend.getInstance(), () -> {
            if (seconds != 0) {
                seconds--;

                DragonDefend.getInstance().getGameManager().setScoreboardTitle(ChatColor.GOLD + "Game Over!");

                GameScoreboardUpdateEvent event = new GameScoreboardUpdateEvent();

                Bukkit.getServer().getPluginManager().callEvent(event);
            } else {
                //Bukkit.reload();

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
