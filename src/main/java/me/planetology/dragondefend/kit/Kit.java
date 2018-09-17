package me.planetology.dragondefend.kit;

import org.bukkit.inventory.ItemStack;

public interface Kit {

    ItemStack[] getItems();
    ItemStack getHelmet();
    ItemStack getChestPlate();
    ItemStack getLeggings();
    ItemStack getBoots();
}
