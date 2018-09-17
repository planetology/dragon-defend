package me.planetology.dragondefend.event.player;

import lombok.Getter;
import lombok.Setter;
import me.planetology.dragondefend.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTeamSelectEvent extends Event implements Cancellable {

    private @Getter @Setter Player player;
    private @Getter @Setter Team team;

    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();

    public PlayerTeamSelectEvent(Player player, Team team) {
        this.player = player;
        this.team = team;

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
