package eu.camonetwork.resourcepackfix.Infrastructure.Helpers;

import org.bukkit.ChatColor;

public class Text {
    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String stripColors(String string) {
        return ChatColor.stripColor(string);
    }

    public static boolean equalsIgnoreColorAndFormatting(String string1, String string2) {
        return stripColors(string1).equalsIgnoreCase(stripColors(string2));
    }

}
