package me.jakeythedev.profiles.ui;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.events.ProfileUpdateEvent;
import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.utils.ItemStackBuilder;
import me.jakeythedev.profiles.utils.MessageUtil;
import me.jakeythedev.profiles.utils.MoodUtil;
import me.jakeythedev.profiles.utils.SkullBuilder;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class ProfileUI implements Listener
{

	private Profiles _profiles;

	public ProfileUI(Profiles profiles)
	{
		_profiles = profiles;
	}
	
	public ProfileUI(Player player, OfflinePlayer target)
	{
		build(player, target);
	}

	public void build(Player player, OfflinePlayer target)
	{

		Inventory ui = Bukkit.createInventory(null, 9 * 3, ChatColor.LIGHT_PURPLE + target.getName() + "'s " + ChatColor.WHITE + "Profile");

		if (player.hasPermission("profiles.updates"))
		{
			ui.setItem(15, new ItemStackBuilder(Material.REDSTONE_BLOCK).setName(ChatColor.RED + "Updates")
					.setLore(
							ChatColor.GRAY + "Recent Updates -",
							ChatColor.GRAY + "- Added Age, Bio, Social media (YT only) and moods",
							ChatColor.GRAY + "Next Update -",
							ChatColor.GRAY + "- Adding /profile <name> to let others see your profile.",
							ChatColor.GRAY + "- Adding more social medias. [Facebook and Twitter]"
							)
					.build());
		}
		else
		{
			ui.setItem(15, new ItemStackBuilder(Material.REDSTONE_BLOCK).setName(ChatColor.RED + "Coming soon!")
					.setLore(
							ChatColor.GRAY + "More updates are planned.."
							)
					.build());
		}
		
		if (target.isOnline())
		{
			
			ui.setItem(13, new SkullBuilder().setOwner(target.getName()).setName(ChatColor.GREEN + target.getName() + "'s Profile stats")
					.setLore(
							ChatColor.GRAY + "Age: " + PlayerData.players.get(target).getAge(),
							ChatColor.GRAY + "Bio: " + PlayerData.players.get(target).getBio(),
							ChatColor.GRAY + "Social Media [YouTube]: " + PlayerData.players.get(target).getMedia(),
							ChatColor.GRAY + "Current Address: " + target.getPlayer().getAddress().getAddress() + ":" + target.getPlayer().getAddress().getPort())
					.build());

			ui.setItem(11, new SkullBuilder().setCustomSkull(
					PlayerData.players.get(target).getMood().getSkull().getTextureID())
					.setName(ChatColor.AQUA + "Mood")
					.setLore(ChatColor.GRAY + "Current Mood: " + PlayerData.players.get(target).getMood().name().toLowerCase()).build());
			
		}

		player.openInventory(ui);
	}
}
