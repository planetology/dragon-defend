package me.planetology.dragondefend.runnables.threads;

import lombok.Getter;
import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.runnables.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EggThread implements GameRunnable {

    private @Getter int seconds, runnableInt;

    public EggThread() {
        seconds = 10;
    }

    @Override
    public void start() {
        Location eggLocation = DragonDefend.getInstance().getConfigManager().getBlockLocation("egg");

        if (eggLocation.getBlock().getType().equals(Material.AIR)) {
            eggLocation.getBlock().setType(Material.DRAGON_EGG);
        }

        runnableInt = Bukkit.getScheduler().scheduleSyncRepeatingTask(DragonDefend.getInstance(), () -> {
            if (seconds != 0) {
                seconds--;
            } else {
                seconds = 20;

                eggLocation.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, eggLocation, 5, .7, .7, .7);

                for (Entity entity : eggLocation.getWorld().getNearbyEntities(eggLocation, 5, 5, 5)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;

                        // Bounce back attacking players >:)
                        if (DragonDefend.getInstance().getTeamManager().getTeam("Attackers").containsPlayer(player)) {
                            player.setVelocity(player.getVelocity().setY(0.7).setZ(0.9));
                        }
                    }
                }
            }
        }, 1L, 20L);
    }

    @Override
    public void stop() {
        try {
            Bukkit.getScheduler().cancelTask(runnableInt);
        } catch (Exception e) {
            System.out.println("Tried to cancel a runnable with ID: " + runnableInt + " that is not running (yet?)!");
        }
    }
}
