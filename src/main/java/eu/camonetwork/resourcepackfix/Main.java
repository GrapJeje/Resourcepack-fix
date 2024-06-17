package eu.camonetwork.resourcepackfix;

import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.ConfigManager;
import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.DefaultConfig;
import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.MessagesConfig;
import eu.camonetwork.resourcepackfix.commands.ResourcepackfixCommand;
import eu.camonetwork.resourcepackfix.commands.ResourcepackfixTabCompleter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    public static Main instance;
    public static Manager manager;
    public static DefaultConfig defaultConfig;
    public static MessagesConfig messagesConfig;
    public static List<ConfigManager> configList = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        // Initialize Configs
        saveDefaultConfig();
        defaultConfig = new DefaultConfig();
        messagesConfig = new MessagesConfig();

        ReloadConfigs();

        configList.add(defaultConfig);
        configList.add(messagesConfig);

        // Register Commands
        registerCommand("resourcepackfix", new ResourcepackfixCommand(), new ResourcepackfixTabCompleter());

        manager = new Manager(defaultConfig, messagesConfig);
    }

    public void registerCommand(String cmdName, CommandExecutor executor, TabCompleter tabCompleter) {
        PluginCommand command = getServer().getPluginCommand(cmdName);
        if (command != null) {
            command.setExecutor(executor);
            if (tabCompleter != null) {
                command.setTabCompleter(tabCompleter);
            }
        }
    }

    public static void ReloadConfigs() {
        defaultConfig.reload();
        messagesConfig.reload();
    }
}
