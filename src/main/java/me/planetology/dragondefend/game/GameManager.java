package me.planetology.dragondefend.game;

import lombok.Getter;
import lombok.Setter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.runnables.threads.EggThread;
import me.planetology.dragondefend.runnables.threads.FinishedThread;
import me.planetology.dragondefend.runnables.threads.LobbyThread;
import me.planetology.dragondefend.runnables.threads.StartedThread;
import me.planetology.dragondefend.utils.ItemUtil;
import me.planetology.dragondefend.utils.TimeUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private @Getter @Setter GameState gameState;
    private @Getter @Setter int eggLives;
    private @Getter @Setter String scoreboardTitle;
    private @Getter List<String> spectators;

    private @Getter StartedThread startedThread;

    // Utils
    private @Getter ItemUtil itemUtil;
    private @Getter TimeUtil timeUtil;

    public GameManager() {
        gameState = GameState.JOIN;
        eggLives = 30;
        spectators = new ArrayList<>();

        itemUtil = new ItemUtil();
        timeUtil = new TimeUtil();

        new BukkitRunnable() {
            @Override
            public void run() {
                new LobbyThread().start();
            }
        }.runTaskLater(DragonDefend.getInstance(), 20L);
    }

    public void startGame() {
        startedThread = new StartedThread();

        startedThread.start();
        new EggThread().start();
    }

    public void endGame() {
        new FinishedThread().start();

        // Stop game thread
        startedThread.stop();
    }
}
