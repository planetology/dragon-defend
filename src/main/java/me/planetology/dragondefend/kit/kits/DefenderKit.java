package me.planetology.dragondefend.kit.kits;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.kit.Kit;
import me.planetology.dragondefend.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DefenderKit implements Kit {

    private ItemUtil itemUtil;

    public DefenderKit() {
        itemUtil = DragonDefend.getInstance().getGameManager().getItemUtil();
    }

    @Override
    public ItemStack[] getItems() {
        HashMap<Enchantment, Integer> swordEnchantments = new HashMap<>();

        swordEnchantments.put(Enchantment.DAMAGE_ALL, 1);

        ItemStack sword = itemUtil.setMaterial(Material.IRON_SWORD).setAmount(1).setName(ChatColor.GOLD + "Dragon Breath").setEnchantments(swordEnchantments).setUnbreakable(true).build();

        return new ItemStack[]{sword};
    }

    @Override
    public ItemStack getHelmet() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.IRON_HELMET).setAmount(1).setEnchantments(enchantments).build();
    }

    @Override
    public ItemStack getChestPlate() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.IRON_CHESTPLATE).setAmount(1).setEnchantments(enchantments).build();
    }

    @Override
    public ItemStack getLeggings() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.IRON_LEGGINGS).setAmount(1).setEnchantments(enchantments).build();
    }

    @Override
    public ItemStack getBoots() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.IRON_BOOTS).setAmount(1).setEnchantments(enchantments).build();
    }
}
