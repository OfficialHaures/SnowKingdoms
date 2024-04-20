package nl.officialhaures.snow.commands;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import nl.officialhaures.snow.SnowPix;
import nl.officialhaures.snow.manager.Database;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Command("kingdom")
@Alias("k")

public class KingdomCommands extends CommandBase {

    private SnowPix plugin;

    public KingdomCommands(SnowPix plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onDefaultChat(final CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&l-------------------------")));

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6Kingdom Commands:")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom create <name>")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom list - Get all the kingdom names")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom info <kingdom> - Get information on a specific kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom join <kingdom> - Join a kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom invite <name> - Invite someone to a kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom leave - Leave your current kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom kick - Kick a player out of the kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom togglepvp - Toggled friendlyfire in the kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom enemy <kingdom> - Declare war on another kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom ally <kingdom> - Make peace with another kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom disband - Disband the kingdom")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6/kingdom setrank <name> <rank> - Gives a kingdommember a new rank")));

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&l-------------------------")));
    }

    @SubCommand("create")
    @Permission("kingdom.create")
    public void onKingdomCreate(final CommandSender sender, String[] args) {
        if (!sender.hasPermission("kingdom.create")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    ("&cYou do not have permission to create a kingdom.")));
            return;
        }

        String kdname = args[1];

        List<String> existingKingdoms = plugin.getDatabase().getKingdoms();
        if (existingKingdoms.contains(kdname)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    ("&cA kingdom with that name already exists.")));
            return;
        }

        String query = "INSERT INTO `kingdoms`(`Name`) VALUES ('" + kdname + "')";
        plugin.getDatabase().executeQuery(query);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&aKingdom &e" + kdname + "&a has been created.")));

    }

    @SubCommand("list")
    public void onListCommand(final CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                ("&6Kingdom List:")));
        for (String kingdom : plugin.getDatabase().getKingdoms()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    ("&e- " + kingdom)));
        }
    }

    @SubCommand("info")
    public void onKingdomInfo(final CommandSender sender, final String kingdomName) {
        List<String> kingdoms = plugin.getDatabase().getKingdoms();
        Integer members = plugin.getDatabase().getMembers();

        if (kingdoms.contains(kingdomName)) {
            sender.sendMessage(ChatColor.GOLD + "Information about the kingdom");
            sender.sendMessage(ChatColor.GOLD + "Name: " + ChatColor.YELLOW + kingdomName);
            sender.sendMessage(ChatColor.GOLD + "King: " + ChatColor.YELLOW + "Functie in onderhoud");
            sender.sendMessage(ChatColor.GOLD + "Members: " + ChatColor.YELLOW + members.toString());

        } else {
            sender.sendMessage("Kingdom: " + kingdomName + " not found.");
        }
    }


    @SubCommand("join")
    public void onKingdomJoin(final CommandSender sender, final String kingdom) {

    }

    @SubCommand("invite")
    public void onInviteKingdom(final CommandSender sender, final String name) {

    }

    @SubCommand("leave")
    public void onLeaveKingdom(final CommandSender sender) {

    }

    @SubCommand("kick")
    public void onKickPlayer(final CommandSender sender, final String name) {

    }

    @SubCommand("togglepvp")
    public void onTogglePvp(final CommandSender sender) {

    }

    @SubCommand("enemy")
    public void onEnemyKingdom(final CommandSender sender, final String kingdom) {

    }

    @SubCommand("ally")
    public void onAllyKingdom(final CommandSender sender, final String kingdom) {

    }

    //Not sure if disband works
    @SubCommand("disband")
    @Permission("kingdom.king")
    public void onDisbandKingdom(final CommandSender sender) {
        // Controleer of de afzender een speler is
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can disband a kingdom.");
            return;
        }

        Player player = (Player) sender;
        String playerName = player.getName();

        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT kingdom FROM players WHERE name = ?");
            stmt.setString(1, playerName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String kingdomName = rs.getString("kingdom");

                // Ontbind het koninkrijk in de database
                plugin.getDatabase().executeQuery("DELETE FROM kingdoms WHERE name = '" + kingdomName + "'");
                plugin.getDatabase().executeQuery("UPDATE players SET kingdom = NULL WHERE kingdom = '" + kingdomName + "'");

                player.sendMessage("The kingdom " + kingdomName + " is disband.");
            } else {
                player.sendMessage("You are not longer a king of a kingdom, so you cannot disband it.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }

    @SubCommand("setrank")
    @Permission("kingdom.setrank")
    public void onSetRank(final CommandSender sender, final String playerName, final String newRank) {
        String query = "UPDATE players SET rank = '" + newRank + "' WHERE name = '" + playerName + "'";
        plugin.getDatabase().executeQuery(query);
        sender.sendMessage("The rank of " + playerName + " is updated to " + newRank);
    }
}
