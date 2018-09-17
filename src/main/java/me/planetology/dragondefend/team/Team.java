package me.planetology.dragondefend.team;

import lombok.Getter;
import lombok.Setter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.player.PlayerTeamSelectEvent;
import me.planetology.dragondefend.kit.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private @Getter @Setter String name;
    private @Getter @Setter List<String> players;
    private @Getter @Setter ChatColor color;
    private @Getter @Setter int maxPlayers;
    private @Getter Location location;
    private @Getter @Setter Material material;
    private @Getter Kit kit;

    public Team(String name, ChatColor color, Location location, Material material, int maxPlayers, Kit kit) {
        this.name = name;
        this.color = color;
        this.players = new ArrayList<>();
        this.maxPlayers = maxPlayers;
        this.location = location;
        this.material = material;
        this.kit = kit;

        DragonDefend.getInstance().getTeamManager().getTeams().add(this);
    }

    public boolean containsPlayer(Player player) {
        return players.contains(player.getName());
    }

    public void addPlayer(Player player) {
        PlayerTeamSelectEvent event = new PlayerTeamSelectEvent(player, this);

        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (event.getTeam().containsPlayer(player)) return;

            event.getTeam().players.add(player.getName());
        }
    }

    public void removePlayer(Player player){
        if (!containsPlayer(player)) return;

        players.remove(player.getName());
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(color + name + ChatColor.GRAY + " (Right-click)");
        item.setItemMeta(itemMeta);

        return item;
    }

    public boolean isFull() {
        return players.size() >= maxPlayers;
    }
}
