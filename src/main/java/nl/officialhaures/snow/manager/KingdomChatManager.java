package nl.officialhaures.snow.manager;

import nl.officialhaures.snow.utils.ChatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KingdomChatManager {
    public static final String PUBLIC_PREFIX = "§7[§6Public§7] §r";
    public static final String ROLEPLAY_PREFIX = "§7[§5Roleplay§7] §r";
    public static final String DEFAULT_PREFIX = "§7[§aKD§7] §r";


    public static void sendMessage(Player player, ChatType chatType, String message){
        String prefix;
        switch(chatType){
            case PUBLIC:
                prefix = PUBLIC_PREFIX;
                break;
            case ROLEPLAY:
                prefix = ROLEPLAY_PREFIX;
                break;
            default:
                prefix = DEFAULT_PREFIX;
                break;
        }

        String formattedMessage = prefix + player.getDisplayName() + ": " + message;
        Bukkit.broadcastMessage(formattedMessage);

    }


}
