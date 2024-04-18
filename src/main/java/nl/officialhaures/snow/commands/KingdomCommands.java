package nl.officialhaures.snow.commands;

import nl.officialhaures.snow.SnowPix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomCommands implements CommandExecutor {

    private SnowPix plugin;

    public KingdomCommands(SnowPix plugin) {
        plugin = this.plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(plugin.getUtilManager().getColor().addColor("&4 You may only use this command as a player!"));
            return false;
        }

        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("kingdom")) {
            if (strings.length == 0) {
                player.sendMessage(plugin.getUtilManager().getColor().addColor("&6&lKingdom Commands:"));
                player.sendMessage(plugin.getUtilManager().getColor().addColor("&e/kingdom"));
            }
        }
        return true;
    }
}
