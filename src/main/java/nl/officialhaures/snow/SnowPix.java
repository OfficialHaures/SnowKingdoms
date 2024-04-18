package nl.officialhaures.snow;

import nl.officialhaures.snow.commands.KingdomCommands;
import nl.officialhaures.snow.events.KingdomChatListener;
import nl.officialhaures.snow.events.PlayerListeners;
import nl.officialhaures.snow.manager.UtilManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SnowPix extends JavaPlugin {

    private UtilManager utilManager;
    private SnowPix instance;

    @Override
    public void onEnable() {
        instance = this;
        utilManager = new UtilManager();
        getServer().getPluginManager().registerEvents(new PlayerListeners(instance), this);
        getServer().getPluginManager().registerEvents(new KingdomChatListener(), this);
        getCommand("kingdom").setExecutor(new KingdomCommands(instance));
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public UtilManager getUtilManager() {
        return utilManager;
    }
}