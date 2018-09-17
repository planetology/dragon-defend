package me.planetology.dragondefend.event.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameEggDamageEvent extends Event implements Cancellable {

    private @Getter @Setter Player player;

    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();

    public GameEggDamageEvent(Player player) {
        this.player = player;

        cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
