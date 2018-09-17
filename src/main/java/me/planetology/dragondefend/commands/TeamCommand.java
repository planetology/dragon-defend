package me.planetology.dragondefend.commands;

import me.planetology.dragondefend.DragonDefend;
import me.planetology.dragondefend.game.GameState;
import me.planetology.dragondefend.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor {

    private final DragonDefend instance;

    public TeamCommand() {
        instance = DragonDefend.getInstance();

        DragonDefend.getInstance().getCommand("team").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!instance.getGameManager().getGameState().equals(GameState.STARTED) || instance.getGameManager().getGameState().equals(GameState.FINISHED)) {
            if (args.length == 0) {
                StringBuilder stringBuilder = new StringBuilder();

                for (Team team : instance.getTeamManager().getTeams()) {
                    stringBuilder.append(team.getColor()).append(team.getName()).append(", ");
                }

                player.sendMessage(instance.getPrefix() + ChatColor.GRAY + "Available teams: " + stringBuilder.toString());
            } else if (args.length == 1) {
                if (instance.getTeamManager().getTeam(args[0]) != null) {
                    instance.getTeamManager().getTeam(args[0]).addPlayer(player);
                } else {
                    player.sendMessage(instance.getPrefix() + ChatColor.RED + "Invalid team, do /team to see all teams.");

                    return true;
                }
            } else {
                player.sendMessage(instance.getPrefix() + ChatColor.RED + "Usage: /team <team>");

                return true;
            }
        } else {
            player.sendMessage(instance.getPrefix() + ChatColor.RED + "Game has already started!");

            return true;
        }

        return false;
    }
}
