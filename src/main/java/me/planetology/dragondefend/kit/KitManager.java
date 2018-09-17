package me.planetology.dragondefend.kit;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;

public class KitManager {

    private @Getter List<Kit> kits;
    private Map<UUID, Kit> playerKits;

    public KitManager() {
        this.kits = new ArrayList<>();
        this.playerKits = new HashMap<>();
    }

    public void setKit(Player player, Kit kit) {
        playerKits.put(player.getUniqueId(), kit);
    }

    public void giveKit(Player player) {
        Kit kit = playerKits.get(player.getUniqueId());

        player.getInventory().setContents(kit.getItems());

        player.getInventory().setHelmet(kit.getHelmet());
        player.getInventory().setChestplate(kit.getChestPlate());
        player.getInventory().setLeggings(kit.getLeggings());
        player.getInventory().setBoots(kit.getBoots());
    }
}
