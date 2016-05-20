package me.jakeythedev.profiles.listeners;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.repository.Database;
import me.jakeythedev.profiles.repository.PlayerData;
import net.md_5.bungee.api.ChatColor;

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
		try
		{
			if (_profiles.db.getConnection().isClosed() || _profiles.db.getConnection() == null)
			{
				try
				{
					_profiles.db = new Database(
							_profiles.getConfig().getString("Settings.IP"),
							_profiles.getConfig().getString("Settings.PORT"),
							_profiles.getConfig().getString("Settings.DATABASE"),
							_profiles.getConfig().getString("Settings.USERNAME"),
							_profiles.getConfig().getString("Settings.PASSWORD"));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		PlayerData.players.put(event.getPlayer(), new PlayerData(_profiles, event.getPlayer()));
	}
	
	@EventHandler
	public void onConnect(PlayerJoinEvent event)
	{
		
		Player player = event.getPlayer();
		
		try
		{
			if (_profiles.db.getConnection().isClosed() || _profiles.db.getConnection() == null)
			{
				player.kickPlayer(ChatColor.BLUE + "PROFILES" + "\n" + ChatColor.WHITE + 
						"Your profile data has not been loaded." + "\n" + "Or a secure connection is not present.");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event)
	{
		
		PlayerData.players.get(event.getPlayer()).saveData(event.getPlayer());
	}
}
