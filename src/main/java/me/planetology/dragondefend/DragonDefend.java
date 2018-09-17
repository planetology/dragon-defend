package me.planetology.dragondefend;

import lombok.Getter;
import me.planetology.dragondefend.configuration.ConfigManager;
import me.planetology.dragondefend.game.GameManager;
import me.planetology.dragondefend.kit.KitManager;
import me.planetology.dragondefend.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author: Planetology
 * @since: 9/10/2018
 */
public final class DragonDefend extends JavaPlugin {

    private static @Getter DragonDefend instance;
    private @Getter Initializer initializer;

    // Managers
    private @Getter ConfigManager configManager;
    private @Getter GameManager gameManager;
    private @Getter TeamManager teamManager;
    private @Getter KitManager kitManager;

    private @Getter String prefix;

    @Override
    public void onEnable() {
        instance = this;
        initializer = new Initializer();

        // Set up needed configuration data
        configManager = new ConfigManager();
        configManager.setup();

        gameManager = new GameManager();
        teamManager = new TeamManager();
        kitManager = new KitManager();

        initializer.init();

        prefix = ChatColor.GOLD + "DragonDefend " + ChatColor.DARK_GRAY + "» " + ChatColor.RESET;
    }

    @Override
    public void onDisable() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.kickPlayer(ChatColor.RED + "Rebooting.");
        }
    }
}
