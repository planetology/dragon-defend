package me.planetology.dragondefend.runnables.threads;

import lombok.Getter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameScoreboardUpdateEvent;
import me.planetology.dragondefend.game.GameState;
import me.planetology.dragondefend.event.game.GameStartEvent;
import me.planetology.dragondefend.runnables.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyThread implements GameRunnable {

    private final DragonDefend instance;

    private @Getter int seconds, runnableInt;

    public LobbyThread() {
        instance = DragonDefend.getInstance();

        // Set lobby length to 1 minute
        seconds = 60;
    }

    @Override
    public void start() {
        runnableInt = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            if (seconds != 0) {
                seconds--;

                instance.getGameManager().setScoreboardTitle(ChatColor.GOLD + "DragonDefend " + ChatColor.GRAY + "» " + ChatColor.WHITE +  instance.getGameManager().getTimeUtil().convertToClock(seconds));

                GameScoreboardUpdateEvent event = new GameScoreboardUpdateEvent();

                Bukkit.getServer().getPluginManager().callEvent(event);

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setLevel(seconds);
                }

                if (seconds == 60 || seconds == 30 || seconds == 15 || (seconds <= 10 && seconds > 0)){
                    Bukkit.broadcastMessage(instance.getPrefix() + ChatColor.GRAY + "Game will start in " + ChatColor.YELLOW + seconds + (seconds == 1 ? ChatColor.GRAY + " second!" : ChatColor.GRAY + " seconds!"));
                }

                if (seconds <= 5 && seconds > 0) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, 1.0F, 1.0F);
                    }
                }
            } else {
                if (Bukkit.getOnlinePlayers().size() < 1) {
                    seconds = 61;

                    Bukkit.broadcastMessage(instance.getPrefix() + ChatColor.GRAY + "Not enough players, resetting timer...");
                } else {
                    GameStartEvent event = new GameStartEvent();

                    Bukkit.getServer().getPluginManager().callEvent(event);

                    if (!event.isCancelled()) {
                        instance.getGameManager().setGameState(GameState.STARTED);
                        instance.getGameManager().startGame();
                    }

                    stop();
                }
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
