package eu.camonetwork.resourcepackfix.commands.sub;

import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.MessagesConfig;
import eu.camonetwork.resourcepackfix.Infrastructure.Events.ReloadEvent;
import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import eu.camonetwork.resourcepackfix.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    private final MessagesConfig messagesConfig = Main.messagesConfig;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rpf.reload")) {
            sender.sendMessage(Text.colorize(messagesConfig.noPermissions()));
            return true;
        }

        Main.ReloadConfigs();

        Bukkit.getPluginManager().callEvent(new ReloadEvent());
        sender.sendMessage(Text.colorize(messagesConfig.configReloaded()));
        return true;
    }
}
