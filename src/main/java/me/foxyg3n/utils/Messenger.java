package me.foxyg3n.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utils for sending formated text to players
 */
public abstract class Messenger {

    private final String prefix;
    private ChatColor messageColor = ChatColor.WHITE;
    private ChatColor infoColor = ChatColor.BLUE;
    private ChatColor warningColor = ChatColor.RED;
    private ChatColor confirmColor = ChatColor.GREEN;

    public Messenger(String pluginNamePrefix, String pluginNameSuffix) {
        this.prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + pluginNamePrefix + ChatColor.WHITE + pluginNameSuffix + ChatColor.GRAY + "]";
    }

    public Messenger(String prefix) {
        this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
    }

    /**
     * Sets the color that the text of message() will be sent
     *
     ** @param color Chosed color
     */
    public void setMessageColor(ChatColor color) {
        this.messageColor = color;
    }

    /**
     * Sets the color that the text of info() will be sent
     *
     * @param color Chosed color
     */
    public void setInfoColor(ChatColor color) {
        this.infoColor = color;
    }

    /**
     * Sets the color that the text of warning() will be sent
     *
     * @param color Chosed color
     */
    public void setWarningColor(ChatColor color) {
        this.warningColor = color;
    }

    /**
     * Sets the color that the text of confirm() will be sent
     *
     * @param color Chosed color
     */
    public void setConfirmColor(ChatColor color) {
        this.confirmColor = color;
    }

    /**
     * Sends formated message to the player
     *
     * @param recipent Recipent of the message
     * @param text The message
     */
    public void message(CommandSender recipent, String text) {
        recipent.sendMessage(prefix + " " + messageColor + text);
    }

    /**
     * Sends formated information message to the player
     *
     * @param recipent Recipent of the message
     * @param text The message
     */
    public void info(CommandSender recipent, String text) {
        recipent.sendMessage(prefix + " " + infoColor + text);
    }

    /**
     * Sends formated warning message to the player
     *
     * @param recipent Recipent of the message
     * @param text The message
     */
    public void warning(CommandSender recipent, String text) {
        recipent.sendMessage(prefix + " " + warningColor + text);
    }

    /**
     * Sends formated confirm message to the player
     *
     * @param recipent Recipent of the message
     * @param text The message
     */
    public void confirm(CommandSender recipent, String text) {
        recipent.sendMessage(prefix + " " + confirmColor + text);
    }

}