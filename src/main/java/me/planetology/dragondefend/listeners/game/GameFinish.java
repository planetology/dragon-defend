package me.planetology.dragondefend.listeners.game;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameFinishEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameFinish implements Listener {

    private final DragonDefend instance;

    public GameFinish() {
        instance = DragonDefend.getInstance();

        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onGameFinish(GameFinishEvent event) {
        int eggLives = instance.getGameManager().getEggLives();

        if (eggLives > 1) {
            instance.getGameManager().setEggLives(eggLives - 1);
        } else {

        }
    }
}
