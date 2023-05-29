package me.foxyg3n.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

/**
 * Utils for sending formated text to players
 */
public abstract class Messenger {

    private static String prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "Fox" + ChatColor.WHITE + "Lib" + ChatColor.GRAY + "]";
    private static ChatColor messageColor = ChatColor.WHITE;
    private static ChatColor infoColor = ChatColor.BLUE;
    private static ChatColor warningColor = ChatColor.RED;
    private static ChatColor confirmColor = ChatColor.GREEN;

    /**
     * Sets the prefix that will be sent to players at the start of the message
     * Example: ChatColor.GOLD + "Fox" + ChatColor.WHITE + "Lib"
     *
     * @param prefix plugin prefix
     */
    public static void setPrefix(String prefix) {
        Messenger.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
    }

    /**
     * Sets the color that the text of message() will be sent
     *
     ** @param color Chosed color
     */
    public static void setMessageColor(ChatColor color) {
        Messenger.messageColor = color;
    }

    /**
     * Sets the color that the text of info() will be sent
     *
     * @param color Chosed color
     */
    public static void setInfoColor(ChatColor color) {
        Messenger.infoColor = color;
    }

    /**
     * Sets the color that the text of warning() will be sent
     *
     * @param color Chosed color
     */
    public static void setWarningColor(ChatColor color) {
        Messenger.warningColor = color;
    }

    /**
     * Sets the color that the text of confirm() will be sent
     *
     * @param color Chosed color
     */
    public static void setConfirmColor(ChatColor color) {
        Messenger.confirmColor = color;
    }

    /**
     * Sends formated message to the player
     *
     * @param player Player to send the message to
     * @param text The message
     */
    public static void message(Player player, String text) {
        player.sendMessage(prefix + " " + messageColor + text);
    }

    /**
     * Sends formated information message to the player
     *
     * @param player Player to send the message to
     * @param text The message
     */
    public static void info(Player player, String text) {
        player.sendMessage(prefix + " " + infoColor + text);
    }

    /**
     * Sends formated warning message to the player
     *
     * @param player Player to send the message to
     * @param text The message
     */
    public static void warning(Player player, String text) {
        player.sendMessage(prefix + " " + warningColor + text);
    }

    /**
     * Sends formated confirm message to the player
     *
     * @param player Player to send the message to
     * @param text The message
     */
    public static void confirm(Player player, String text) {
        player.sendMessage(prefix + " " + confirmColor + text);
    }

}