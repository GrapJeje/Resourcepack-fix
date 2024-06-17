package eu.camonetwork.resourcepackfix.commands.sub;

import eu.camonetwork.resourcepackfix.Infrastructure.Events.ReloadEvent;
import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("camomines.reload")) {
            sender.sendMessage(Text.colorize("§cJe hebt geen toestemming om dit subcommando uit te voeren."));
            return true;
        }

        Bukkit.getPluginManager().callEvent(new ReloadEvent());
        sender.sendMessage(Text.colorize("&cConfig's successfully reloaded"));
        return true;
    }
}
