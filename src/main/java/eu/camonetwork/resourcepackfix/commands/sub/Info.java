package eu.camonetwork.resourcepackfix.commands.sub;

import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import eu.camonetwork.resourcepackfix.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Info implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("");
            sender.sendMessage(Text.colorize("&d&lResourcepackFix &8> &7Versie " + Main.instance.getDescription().getVersion()));
            sender.sendMessage(Text.colorize("&b&lAuthor &8> &7GrapJeje"));
            sender.sendMessage("");
            sender.sendMessage(Text.colorize("&6This sub-plugin is custom made for &3CamoNetwork"));
            sender.sendMessage("");

            return true;
        }
        return false;
    }
}
