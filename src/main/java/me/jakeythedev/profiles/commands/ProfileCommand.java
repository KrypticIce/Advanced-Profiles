package me.jakeythedev.profiles.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.ui.ProfileUI;
import me.jakeythedev.profiles.utils.MessageUtil;
import me.jakeythedev.profiles.utils.MoodUtil;
import net.md_5.bungee.api.ChatColor;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class ProfileCommand implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if (sender instanceof Player)
		{
			Player player = (Player) sender;

			if (args.length == 0)
			{
				new ProfileUI(player, player);
				return false;
			}
			else if (args.length > 1)
			{
				PlayerData playerData = PlayerData.players.get(player);

				if (args[0].equalsIgnoreCase("update"))
				{
					if (args[1].equalsIgnoreCase("age"))
					{
						if (player.hasPermission("profiles.age"))
						{
							try
							{
								int age = Integer.parseInt(args[2]);

								playerData.setAge(age);
								MessageUtil.sendMessage(player, "Updater", "Updated your age to " + age);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						else
						{
							MessageUtil.sendMessage(player, "Permission", "You do not have the permission node: " + ChatColor.BLUE + "profiles.age");
						}
					}
					else if (args[1].equalsIgnoreCase("bio"))
					{
						if (player.hasPermission("profiles.bio"))
						{
							StringBuilder bio = new StringBuilder();

							for (int i = 2; i < args.length; i++)
							{
								bio.append(args[i]).append(" ");
							}

							playerData.setBio(bio.toString());
							MessageUtil.sendMessage(player, "Updater", "Updated your bio to " + bio.toString());
						}
						else
						{
							MessageUtil.sendMessage(player, "Permission", "You do not have the permission node: " + ChatColor.BLUE + "profiles.bio");
						}
					}
					else if (args[1].equalsIgnoreCase("media"))
					{
						if (player.hasPermission("profiles.media"))
						{
							playerData.setMedia(args[2]);
							MessageUtil.sendMessage(player, "Updater", "Updated your YouTube username to " + args[2].toString());
						}
						else
						{
							MessageUtil.sendMessage(player, "Permission", "You do not have the permission node: " + ChatColor.BLUE + "profiles.media");
						}
					}
					else if (args[1].equalsIgnoreCase("mood"))
					{
						if (player.hasPermission("profiles.media"))
						{
							try
							{
								MoodUtil mood = MoodUtil.valueOf(args[2]);

								playerData.setMood(mood);
								MessageUtil.sendMessage(player, "Updater", "Updated your mood to " + mood.name());
							}
							catch (Exception e)
							{
								MessageUtil.sendMessage(player, "Error", "That is an invalid mood!");

								for (MoodUtil moods : MoodUtil.values())
									MessageUtil.sendMessage(player, "Mood Types", moods.name());
							}
						}
						else
						{
							MessageUtil.sendMessage(player, "Permission", "You do not have the permission node: " + ChatColor.BLUE + "profiles.mood");
						}
					}
				}
				else
				{ 
					MessageUtil.sendMessage(player, "Syntax", "/profile update [age|bio|media]");
					MessageUtil.sendMessage(player, "Syntax", "/profile update age [integer]");
					MessageUtil.sendMessage(player, "Syntax", "/profile update bio [bio message]");
					MessageUtil.sendMessage(player, "Syntax", "/profile update media [youtube] [username]");
					MessageUtil.sendMessage(player, "Syntax", "/profile update mood [mood]");
					MessageUtil.sendMessage(player, "");

					for (MoodUtil moods : MoodUtil.values())
						MessageUtil.sendMessage(player, "Mood Types", moods.name());
				}
			}
		}
		return false;
	}
}
