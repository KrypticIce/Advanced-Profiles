package me.jakeythedev.profiles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.jakeythedev.profiles.repository.Database;
import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.ui.ProfileUI;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class Profiles extends JavaPlugin implements Listener
{

	public Database db;

	@Override
	public void onEnable()
	{


		getConfig().options().copyDefaults(true);
		saveConfig();
		
		try
		{
			db = new Database(
					getConfig().getString("Settings.IP"),
					getConfig().getString("Settings.PORT"),
					getConfig().getString("Settings.DATABASE"),
					getConfig().getString("Settings.USERNAME"),
					getConfig().getString("Settings.PASSWORD"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		new Manager(this);
		
		for (Player all : Bukkit.getOnlinePlayers())
			PlayerData.players.put(all, new PlayerData(this, all));

		System.out.println("Advanced Profiles by JakeyTheDev Enabled!");
	}

	@Override
	public void onDisable()
	{
		for (Player all : Bukkit.getOnlinePlayers())
			PlayerData.players.get(all).saveData(all);
		
		System.out.println("Advanced Profiles by JakeyTheDev Disabled!");
	}
}
