package me.planetology.dragondefend.event.game;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameFinishEvent extends Event implements Cancellable {

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
