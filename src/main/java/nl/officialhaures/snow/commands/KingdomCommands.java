package nl.officialhaures.snow.commands;

import me.mattstudios.mf.annotations.Alias;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.base.CommandBase;
import nl.officialhaures.snow.SnowPix;
import org.bukkit.command.CommandSender;

@Command("kingdom")
@Alias("k")

public class KingdomCommands extends CommandBase {

    private SnowPix plugin;

    public KingdomCommands(SnowPix plugin){
        this.plugin = plugin;
    }

    @Default
    public void onDefaultChat(final CommandSender sender){
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&l-------------------------"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6Kingdom Commands:"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom create <name>"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom list - Get all the kingdom names"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom info <kingdom> - Get information on a specific kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom join <kingdom> - Join a kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom leave - Leave your current kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom kick - Kick a player out of the kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom togglepvp - Toggled friendlyfire in the kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom enemy <kingdom> - Declare war on another kingdom"));
        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&6/kingdom ally <kingdom> - Make peace with another kingdom"));

        sender.sendMessage(plugin.getUtilManager().getColor().addColor("&l-------------------------"));
    }
}
