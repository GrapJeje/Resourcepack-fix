package eu.camonetwork.resourcepackfix.Infrastructure.Events;

import eu.camonetwork.resourcepackfix.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ReloadEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static void call() {
        Bukkit.getPluginManager().callEvent(new OnEnableEvent());
    }

    public Main getPlugin() {
        return Main.instance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
