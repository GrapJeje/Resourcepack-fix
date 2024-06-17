package eu.camonetwork.resourcepackfix.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcepackfixTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        boolean ReloadPermissions = sender.hasPermission("rpf.reload");

        if (args.length == 1 && ReloadPermissions) {
            completions.addAll(Arrays.asList("reload"));
        }

        if (args.length == 1) {
            completions.addAll(Arrays.asList("info"));
        }

        if (args.length > 0) {
            String partialArg = args[args.length - 1].toLowerCase();
            completions = completions.stream()
                    .filter(completion -> completion.toLowerCase().startsWith(partialArg))
                    .collect(Collectors.toList());
        }

        return completions;
    }
}
