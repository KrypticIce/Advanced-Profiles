package me.jakeythedev.profiles.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class MessageUtil
{

    public static void sendMessage(Player player, String prefix, String message)
    {
        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.WHITE + ": " + ChatColor.RESET + message);
    }

    public static void sendMessage(Player player, String message)
    {
        player.sendMessage(ChatColor.RESET + message);
    }
}
