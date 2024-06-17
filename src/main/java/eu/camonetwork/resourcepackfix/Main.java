package eu.camonetwork.resourcepackfix;

import eu.camonetwork.resourcepackfix.commands.ResourcepackfixCommand;
import eu.camonetwork.resourcepackfix.commands.ResourcepackfixTabCompleter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main instance;

    public void registerCommand(String cmdName, CommandExecutor executor, TabCompleter tabCompleter) {
        PluginCommand command = getServer().getPluginCommand(cmdName);
        if (command != null) {
            command.setExecutor(executor);
            if (tabCompleter != null) {
                command.setTabCompleter(tabCompleter);
            }
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        registerCommand("resourcepackfix", new ResourcepackfixCommand(), new ResourcepackfixTabCompleter());

    }

}
