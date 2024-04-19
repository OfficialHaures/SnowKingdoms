package nl.officialhaures.snow.utils;

import org.bukkit.ChatColor;

public class Color {
    public static String addColor(String string){

        ChatColor.translateAlternateColorCodes('&', string);

        return string;
    }
}
