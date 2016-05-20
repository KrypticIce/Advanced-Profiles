package me.jakeythedev.profiles.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.utils.MessageUtil;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class GlobalListeners implements Listener
{
	private Profiles _profiles;
	
	public GlobalListeners(Profiles profile)
	{
		this._profiles = profile;
	}
	
	@EventHandler
	public void onUIInteract(InventoryClickEvent event)
	{
		final Player player = (Player) event.getWhoClicked();

		if (event.getInventory().getTitle().contains(ChatColor.WHITE + "Profile"))
		{
			event.setCancelled(true);

			if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)
				return;

			if (event.getCurrentItem().getType() == Material.SKULL_ITEM && event.getCurrentItem().getItemMeta().getDisplayName().contains("Profile stats"))
			{
				
				if (_profiles.getConfig().getBoolean("Settings.chatstats"))
				{
					MessageUtil.sendMessage(player, "Profiles", "Generating statistics (chat version)");
					
					new BukkitRunnable()
					{
						
						public void run()
						{
							MessageUtil.sendMessage(player, ChatColor.LIGHT_PURPLE.toString() + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
							MessageUtil.sendMessage(player, "Profiles", " - ");
							MessageUtil.sendMessage(player, "Username: " + player.getName());
							MessageUtil.sendMessage(player, "UUID: " + player.getUniqueId().toString());
							MessageUtil.sendMessage(player, "ADDRESS: " + player.getAddress().getAddress() + ":" + player.getAddress().getPort());
							MessageUtil.sendMessage(player, ChatColor.LIGHT_PURPLE.toString() + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
							MessageUtil.sendMessage(player, "AGE: " + PlayerData.players.get(player).getAge());
							MessageUtil.sendMessage(player, "BIO: " + PlayerData.players.get(player).getBio());
							MessageUtil.sendMessage(player, "SOCIAL MEDIA: " + PlayerData.players.get(player).getMedia());
							MessageUtil.sendMessage(player, ChatColor.LIGHT_PURPLE.toString() + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
						}
					}.runTaskLater(_profiles, 3*20);
				}
			}
		}
	}
}

