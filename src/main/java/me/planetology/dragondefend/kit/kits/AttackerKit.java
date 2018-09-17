package me.planetology.dragondefend.kit.kits;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.kit.Kit;
import me.planetology.dragondefend.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class AttackerKit implements Kit {

    private ItemUtil itemUtil;

    public AttackerKit() {
        itemUtil = DragonDefend.getInstance().getGameManager().getItemUtil();
    }

    @Override
    public ItemStack[] getItems() {
        HashMap<Enchantment, Integer> swordEnchantments = new HashMap<>();
        HashMap<Enchantment, Integer> bowEnchantments = new HashMap<>();
        HashMap<Enchantment, Integer> pickAxeEnchantments = new HashMap<>();

        swordEnchantments.put(Enchantment.KNOCKBACK, 2);

        ItemStack sword = itemUtil.setMaterial(Material.IRON_SWORD).setAmount(1).setName(ChatColor.GOLD + "Dragon Slayer").setEnchantments(swordEnchantments).setUnbreakable(true).build();

        bowEnchantments.put(Enchantment.ARROW_INFINITE, 1);
        bowEnchantments.put(Enchantment.ARROW_KNOCKBACK, 2);

        ItemStack bow = itemUtil.setMaterial(Material.BOW).setAmount(1).setName(ChatColor.GOLD + "Elven Bow").setEnchantments(bowEnchantments).setUnbreakable(true).build();
        ItemStack arrow = itemUtil.setMaterial(Material.ARROW).setAmount(1).setName(ChatColor.GOLD + "Elven Arrow").setEnchantments(null).build();

        pickAxeEnchantments.put(Enchantment.DIG_SPEED, 2);

        ItemStack pickAxe = itemUtil.setMaterial(Material.DIAMOND_PICKAXE).setAmount(1).setName(ChatColor.GOLD + "Egg Destroyer").setEnchantments(pickAxeEnchantments).build();

        return new ItemStack[]{sword, bow, pickAxe, arrow};
    }

    @Override
    public ItemStack getHelmet() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.LEATHER_HELMET).setAmount(1).setEnchantments(enchantments).build();
    }

    @Override
    public ItemStack getChestPlate() {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        return itemUtil.setMaterial(Material.LEATHER_CHESTPLATE).setAmount(1).setEnchantments(enchantments).build();
    }

    @Override
    public ItemStack getLeggings() {
        return new ItemStack(Material.DIAMOND_LEGGINGS);
    }

    @Override
    public ItemStack getBoots() {
        return new ItemStack(Material.DIAMOND_BOOTS);
    }
}
