package me.planetology.dragondefend;

import me.planetology.dragondefend.commands.SetEggCommand;
import me.planetology.dragondefend.commands.SetSpawnCommand;
import me.planetology.dragondefend.commands.TeamCommand;
import me.planetology.dragondefend.kit.kits.AttackerKit;
import me.planetology.dragondefend.kit.kits.DefenderKit;
import me.planetology.dragondefend.listeners.block.BlockBreak;
import me.planetology.dragondefend.listeners.block.BlockFromTo;
import me.planetology.dragondefend.listeners.entity.EntityDamage;
import me.planetology.dragondefend.listeners.entity.EntityDamageByEntity;
import me.planetology.dragondefend.listeners.game.GameEggDamage;
import me.planetology.dragondefend.listeners.game.GameEggDestroy;
import me.planetology.dragondefend.listeners.game.GameScoreboardUpdate;
import me.planetology.dragondefend.listeners.game.GameStart;
import me.planetology.dragondefend.listeners.player.*;
import me.planetology.dragondefend.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.Material;

class Initializer {

    void init() {
        registerCommands();
        registerListeners();
        registerKits();
        registerTeams();
    }

    private void registerCommands() {
        new SetEggCommand();
        new SetSpawnCommand();
        new TeamCommand();
    }

    private void registerListeners() {
        new BlockBreak();
        new BlockFromTo();

        new EntityDamage();
        new EntityDamageByEntity();

        new GameEggDamage();
        new GameEggDestroy();
        new GameScoreboardUpdate();
        new GameStart();

        new FoodLevelChange();
        new PlayerDeath();
        new PlayerDropItem();
        new PlayerExpChange();
        new PlayerGameJoin();
        new PlayerInteract();
        new PlayerJoin();
        new PlayerQuit();
        new PlayerRespawn();
        new PlayerTeamSelect();
    }

    private void registerKits() {
        DragonDefend.getInstance().getKitManager().getKits().add(new AttackerKit());
        DragonDefend.getInstance().getKitManager().getKits().add(new DefenderKit());
    }

    private void registerTeams() {
        new Team("Attackers", ChatColor.GREEN, DragonDefend.getInstance().getConfigManager().getPlayerLocation("attackers"),
                Material.IRON_SWORD, 8, new AttackerKit());
        new Team("Defenders", ChatColor.RED, DragonDefend.getInstance().getConfigManager().getPlayerLocation("defenders"),
                Material.BLAZE_POWDER, 4, new DefenderKit());
    }
}
