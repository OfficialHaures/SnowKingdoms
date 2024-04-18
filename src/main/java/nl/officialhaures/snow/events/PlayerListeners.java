package nl.officialhaures.snow.events;

import nl.officialhaures.snow.SnowPix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {
    private SnowPix plugin;

    public PlayerListeners(SnowPix plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPlayedBefore()){
            player.sendMessage(plugin.getUtilManager().getColor().addColor("&a&lWelcome back &f" + player.getDisplayName()));

        }
    }
}
