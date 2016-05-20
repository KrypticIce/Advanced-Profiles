package me.jakeythedev.profiles.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.ui.ProfileUI;
import me.jakeythedev.profiles.utils.MessageUtil;
import me.jakeythedev.profiles.utils.MoodUtil;

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
					else if (args[1].equalsIgnoreCase("bio"))
					{
						StringBuilder bio = new StringBuilder();
						
						for (int i = 2; i < args.length; i++)
						{
							bio.append(args[i]).append(" ");
						}
						
						playerData.setBio(bio.toString());
						MessageUtil.sendMessage(player, "Updater", "Updated your bio to " + bio.toString());
					}
					else if (args[1].equalsIgnoreCase("media"))
					{
						playerData.setMedia(args[2]);
						MessageUtil.sendMessage(player, "Updater", "Updated your YouTube username to " + args[2].toString());
					}
					else if (args[1].equalsIgnoreCase("mood"))
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
				}
			}
			else
			{ //profile update media facebook <username
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
		return false;
	}

}
