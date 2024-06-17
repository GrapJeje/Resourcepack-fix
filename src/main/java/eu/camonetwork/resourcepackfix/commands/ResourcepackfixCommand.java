package eu.camonetwork.resourcepackfix.commands;

import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import eu.camonetwork.resourcepackfix.Main;
import eu.camonetwork.resourcepackfix.commands.sub.Info;
import eu.camonetwork.resourcepackfix.commands.sub.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResourcepackfixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("");
            sender.sendMessage(Text.colorize("&3&lCamoMines &8> &7Versie " + Main.instance.getDescription().getVersion()));
            sender.sendMessage(Text.colorize("&b&lAuthor &8> &7GrapJeje"));
            sender.sendMessage("");
            sender.sendMessage(Text.colorize("&6This sub-plugin is custom made for &3CamoNetwork"));
            sender.sendMessage("");

            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Reload reloadCommand = new Reload();
            return reloadCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("info")) {
            Info infoCommand = new Info();
            return infoCommand.onCommand(sender, command, label, args);
        }
        else
            sender.sendMessage(Text.colorize("&cDit is een ongeldig subcommand"));

        return false;
    }
}
