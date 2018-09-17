package me.planetology.dragondefend.team;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {

    private @Getter List<Team> teams;

    public TeamManager(){
        teams = new ArrayList<>();
    }

    public Team getTeam(Player player) {
        for (Team teamIterate : teams) if (teamIterate.containsPlayer(player)) return teamIterate;

        return null;
    }

    public Team getTeam(String team) {
        for (Team teamIterate : teams) {
            if (teamIterate.getName().equalsIgnoreCase(team)) return teamIterate;
        }

        return null;
    }

    public Team getTeam(ItemStack itemStack) {
        for (Team teamIterate : teams) {
            if (teamIterate.getItem().isSimilar(itemStack)) return teamIterate;
        }

        return null;
    }

    public Team getSmallestTeam() {
        for (Team teamIterate : teams) {
            if (!teamIterate.isFull()) {
                return teamIterate;
            }
        }

        return null;
    }
}
