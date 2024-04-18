package nl.officialhaures.snow.events;

import nl.officialhaures.snow.manager.KingdomChatManager;
import nl.officialhaures.snow.utils.ChatType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class KingdomChatListener implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.startsWith("!")) {
            event.setCancelled(true);
            KingdomChatManager.sendMessage(player, ChatType.PUBLIC, message.substring(1));
        } else if (message.startsWith("#")) {
            event.setCancelled(true);
            KingdomChatManager.sendMessage(player, ChatType.ROLEPLAY, message.substring(1));
        } else {
            KingdomChatManager.sendMessage(player, ChatType.DEFAULT, message);
        }
    }
}