package eu.camonetwork.resourcepackfix.Infrastructure.Helpers;

import org.bukkit.ChatColor;

public class Text {
    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
