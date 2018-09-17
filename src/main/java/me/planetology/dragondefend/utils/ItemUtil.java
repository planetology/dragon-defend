package me.planetology.dragondefend.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUtil {

    private Material material;
    private Integer amount;
    private String name;
    private List<String> lore;
    private boolean unbreakable = false;
    private HashMap<Enchantment, Integer> enchantments;

    public ItemUtil setMaterial(Material material) {
        this.material = material;

        return this;
    }

    public ItemUtil setAmount(Integer amount) {
        this.amount = amount;

        return this;
    }

    public ItemUtil setName(String name) {
        this.name = name;

        return this;
    }

    public ItemUtil setLore(List<String> lore) {
        this.lore = lore;

        return this;
    }

    public ItemUtil setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;

        return this;
    }

    public ItemUtil setEnchantments(HashMap<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;

        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (amount != null) item.setAmount(amount);
        if (name != null) itemMeta.setDisplayName(name);
        if (lore != null) itemMeta.setLore(lore);
        if (unbreakable) itemMeta.setUnbreakable(true);

        if (enchantments != null) {
            for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                itemMeta.addEnchant(enchantment.getKey(), enchantment.getValue(), false);
            }
        }

        item.setItemMeta(itemMeta);

        return item;
    }
}
