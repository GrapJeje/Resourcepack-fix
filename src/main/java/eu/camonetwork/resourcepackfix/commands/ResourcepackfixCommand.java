package eu.camonetwork.resourcepackfix.commands;

import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.MessagesConfig;
import eu.camonetwork.resourcepackfix.Manager;
import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import eu.camonetwork.resourcepackfix.commands.sub.Info;
import eu.camonetwork.resourcepackfix.commands.sub.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResourcepackfixCommand implements CommandExecutor {

    private final Manager manager;
    private final MessagesConfig messagesConfig;

    public ResourcepackfixCommand(Manager manager, MessagesConfig messagesConfig) {
        this.manager = manager;
        this.messagesConfig = messagesConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            manager.checkAndApplyItemConfig(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Reload reloadCommand = new Reload();
            return reloadCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("info")) {
            Info infoCommand = new Info();
            return infoCommand.onCommand(sender, command, label, args);
        } else {
            sender.sendMessage(Text.colorize(messagesConfig.foundNonSubCommands()));
            return false;
        }
    }
}
