package me.foxyg3n.utils;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Simple interface which allows for quick listener implementation.
 * Example use:
 * <pre color="orange">
 * pluginManager.registerEvents((SimpleListener&#x3c;PlayerInteractEvent&#x3e;) event -> {
 *     event.setCancelled(true);
 * }, plugin);
 *  </pre>
 *
 * @param <T> Bukkit event which will be handled
 */
public interface SimpleListener<T extends Event> extends Listener {

    @EventHandler
    public abstract void onEvent(T event);

}
