package me.jakeythedev.profiles.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.ui.ProfileUI;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class ConnectionListeners implements Listener	
{
	private Profiles _profiles;
	
	public ConnectionListeners(Profiles profile)
	{
		this._profiles = profile;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event)
	{
		PlayerData.players.put(event.getPlayer(), new PlayerData(_profiles, event.getPlayer()));
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event)
	{
		
		PlayerData.players.get(event.getPlayer()).saveData(event.getPlayer());
	}
}
