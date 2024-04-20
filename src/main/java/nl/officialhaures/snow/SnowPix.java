package nl.officialhaures.snow;

import me.mattstudios.mf.base.CommandManager;
import nl.officialhaures.snow.commands.KingdomCommands;
import nl.officialhaures.snow.events.KingdomChatListener;
import nl.officialhaures.snow.events.PlayerListeners;
import nl.officialhaures.snow.manager.Database;
import nl.officialhaures.snow.manager.UtilManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SnowPix extends JavaPlugin {

    private UtilManager utilManager;
    private SnowPix instance;
    CommandManager commandManager;
    Database database;

    @Override
    public void onEnable() {
        instance = this;
        database = new Database();
        database.initialize();
        commandManager.register(new KingdomCommands(instance));
        utilManager = new UtilManager();
        getServer().getPluginManager().registerEvents(new PlayerListeners(instance), this);
        getServer().getPluginManager().registerEvents(new KingdomChatListener(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info(getUtilManager().getColor().addColor(""));
    }

    public UtilManager getUtilManager() {
        return utilManager;
    }

    public Database getDatabase() {
        return database;
    }
}
