package me.planetology.dragondefend.listeners.block;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.event.game.GameEggDamageEvent;
import me.planetology.dragondefend.event.game.GameEggDestroyEvent;
import me.planetology.dragondefend.event.game.GameFinishEvent;
import me.planetology.dragondefend.game.GameState;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private final DragonDefend instance;

    public BlockBreak() {
        instance = DragonDefend.getInstance();

        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!player.isOp()) event.setCancelled(true);

        if (DragonDefend.getInstance().getGameManager().getGameState().equals(GameState.STARTED)) {
            if (block.getType().equals(Material.DRAGON_EGG)) {
                if (DragonDefend.getInstance().getTeamManager().getTeam(player).equals("Defenders")) return;

                int eggLives = instance.getGameManager().getEggLives();

                if (eggLives > 1) {
                    instance.getGameManager().setEggLives(eggLives - 1);

                    GameEggDamageEvent gameEggDamageEvent = new GameEggDamageEvent(player);

                    Bukkit.getPluginManager().callEvent(gameEggDamageEvent);
                } else {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.spawnParticle(Particle.EXPLOSION_HUGE, event.getBlock().getLocation(), 1, 0, 0, 0);
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1.0f, 1.0f);
                    }

                    Bukkit.broadcastMessage(DragonDefend.getInstance().getPrefix() + ChatColor.GOLD + "Game over, " + ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.GOLD +  " has destroyed Glaurung's egg!");

                    block.setType(Material.AIR);

                    instance.getGameManager().setGameState(GameState.FINISHED);
                    instance.getGameManager().endGame();
                }
            }
        }
    }
}
