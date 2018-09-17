package me.planetology.dragondefend.configuration;

import lombok.Getter;
import me.planetology.dragondefend.DragonDefend;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private final DragonDefend instance;

    public @Getter File settings;
    public @Getter FileConfiguration config, settingsConfig;

    public ConfigManager() {
        instance = DragonDefend.getInstance();

        config = instance.getConfig();
    }

    public void setup() {
        instance.saveDefaultConfig();

        if (!config.contains("locations.players.lobby")) System.out.println("Lobby spawn not found! Please set it up ASAP!");
        if (!config.contains("locations.players.spectators")) System.out.println("Spectator spawn not found! Please set it up ASAP!");
        if (!config.contains("locations.players.attackers")) System.out.println("Attacker spawn not found! Please set it up ASAP!");
        if (!config.contains("locations.players.defenders")) System.out.println("Defender spawn not found! Please set it up ASAP!");

        if (!config.contains("locations.blocks.egg")) System.out.println("Dragon egg location not found! Please set it up ASAP!");
    }

    public void setPlayerLocation(String locationType, Location location) {
        String path = "locations.players." + locationType;

        config.set(path + ".x", location.getBlockX());
        config.set(path + ".y", location.getBlockY());
        config.set(path + ".z", location.getBlockZ());
        config.set(path + ".pitch", location.getPitch());
        config.set(path + ".yaw", location.getYaw());

        instance.saveConfig();
    }

    public void setBlockLocation(String locationType, Location location) {
        String path = "locations.blocks." + locationType;

        config.set(path + ".x", location.getBlockX());
        config.set(path + ".y", location.getBlockY());
        config.set(path + ".z", location.getBlockZ());

        instance.saveConfig();
    }

    public Location getPlayerLocation(String locationType) {
        String path = "locations.players." + locationType;

        int x = config.getInt(path + ".x");
        int y = config.getInt(path + ".y");
        int z = config.getInt(path + ".z");
        float pitch = (float) config.getDouble(path + ".pitch");
        float yaw = (float) config.getDouble(path + ".yaw");

        return new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
    }

    public Location getBlockLocation(String locationType) {
        String path = "locations.blocks." + locationType;

        int x = config.getInt(path + ".x");
        int y = config.getInt(path + ".y");
        int z = config.getInt(path + ".z");

        return new Location(Bukkit.getWorld("world"), x, y, z);
    }
}
