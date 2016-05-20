package me.jakeythedev.profiles.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.utils.MoodUtil;

/**
 * C R E A T E D B Y J A K E Y T H E D E V O N 14/05/2016
 */

public class PlayerData
{
	private Profiles plugin;

	private String _uuid;
	private MoodUtil _mood;
	private String _socialMedia, _bio;
	private int _age;

	public static HashMap<Player, PlayerData> players = new HashMap<Player, PlayerData>();

	public PlayerData(final Profiles plugin, final Player player)
	{
		this.plugin = plugin;
		_uuid = player.getUniqueId().toString();

		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable()
		{

			public void run()
			{

				ResultSet set = plugin.db.sendQuery("SELECT * FROM `" + "ProfileData" + "` WHERE UUID = '" + player.getUniqueId().toString() + "';");

				try
				{
					if (set.next())
					{

						_uuid = set.getString("uuid");
						_age = set.getInt("age");
						_mood = MoodUtil.valueOf(set.getString("mood"));
						_socialMedia = set.getString("youtube");
						_bio = set.getString("bio");

					}
					else
					{
						_uuid = player.getUniqueId().toString();
						_age = 1;
						_mood = MoodUtil.DEFAULT;
						_socialMedia = "None";
						_bio = "Default bio";

					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void saveData(Player player)
	{

		final String uuid = player.getUniqueId().toString();

		ResultSet set = plugin.db.sendQuery("SELECT * FROM `" + "ProfileData" + "` WHERE UUID = '" + uuid + "';");

		try
		{
			if (set.next())
			{

				plugin.db.sendUpdate("UPDATE `" + "ProfileData" + "` SET `uuid` = '" + this._uuid + "', `age` = '"
						+ this._age + "', `mood` = '" + this._mood + "', `youtube` = '" + this._socialMedia
						+ "', `bio` = '" + this._bio + "' WHERE `uuid` = '" + this._uuid + "';");

			}
			else
			{

				plugin.db.sendUpdate("INSERT INTO `" + "ProfileData" + "` (`uuid`, `age`, `mood`, `youtube`, `bio`) "
						+ "VALUES ('" + uuid + "', '" + _age + "', '" + this._mood + "', '" + this._socialMedia + "', '"
						+ this._bio + "');");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void saveDataAsync(final Player player)
	{
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable()
		{

			public void run()
			{
				saveData(player);
			}
		});
	}

	public String getUUID()
	{
		return _uuid;
	}

	public int getAge()
	{
		return _age;
	}

	public MoodUtil getMood()
	{
		return _mood;
	}

	public String getMedia()
	{
		return _socialMedia;
	}

	public String getBio()
	{
		return _bio;
	}

	public void setAge(int age)
	{
		_age = age;
	}

	public void setMood(MoodUtil mood)
	{
		_mood = mood;
	}

	public void setMedia(String username)
	{

		_socialMedia = username;
	}

	public void setBio(String bio)
	{
		_bio = bio;
	}
}
