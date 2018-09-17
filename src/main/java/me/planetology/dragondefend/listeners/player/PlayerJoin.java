package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.player.PlayerGameJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private DragonDefend instance;

    public PlayerJoin() {
        instance = DragonDefend.getInstance();

        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerGameJoinEvent gameJoinEvent = new PlayerGameJoinEvent(player);

        Bukkit.getServer().getPluginManager().callEvent(gameJoinEvent);

        switch (DragonDefend.getInstance().getGameManager().getGameState()) {
            case JOIN:
                event.setJoinMessage(ChatColor.DARK_GRAY + "» " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " joined the game! (" + Bukkit.getOnlinePlayers().size() + "/12)");

                break;
            case STARTED:
                event.setJoinMessage(null);
            default:
                break;
        }
    }
}
