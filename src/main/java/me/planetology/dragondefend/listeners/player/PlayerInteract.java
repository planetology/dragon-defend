package me.planetology.dragondefend.listeners.player;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    public PlayerInteract() {
        Bukkit.getPluginManager().registerEvents(this, DragonDefend.getInstance());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                Team teamInHand = DragonDefend.getInstance().getTeamManager().getTeam(itemInHand);

                if (teamInHand == null) return;

                teamInHand.addPlayer(player);
            }
        }
    }
}
