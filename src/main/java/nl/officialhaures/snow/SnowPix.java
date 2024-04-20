package nl.officialhaures.snow;

import me.mattstudios.mf.base.CommandManager;
import nl.officialhaures.snow.commands.KingdomCommands;
import nl.officialhaures.snow.events.KingdomChatListener;
import nl.officialhaures.snow.events.PlayerListeners;
import nl.officialhaures.snow.manager.Database;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;


public final class SnowPix extends JavaPlugin {

    private SnowPix instance;
    CommandManager commandManager;
    Database database;

    @Override
    public void onEnable() {
        instance = this;
        database = new Database();
        database.getConnection();
//        database.initialize();
        commandManager = new CommandManager(instance);
        commandManager.register(new KingdomCommands(instance));
        getServer().getPluginManager().registerEvents(new PlayerListeners(instance), this);
        getServer().getPluginManager().registerEvents(new KingdomChatListener(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("TETEWATS");
        database.closeConnection();
    }

    public Database getDatabase() {
        return database;
    }
}
