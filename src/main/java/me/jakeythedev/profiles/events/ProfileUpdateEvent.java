package me.jakeythedev.profiles.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.jakeythedev.profiles.Profiles;
import me.jakeythedev.profiles.repository.PlayerData;
import me.jakeythedev.profiles.utils.MoodUtil;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class ProfileUpdateEvent extends Event
{
	
	/*/
	 * TODO Intergrate some sort of API using ProfileUpdateEvent
	 */
	
	private Profiles _profiles;
    private Player _player;

    public ProfileUpdateEvent(Profiles profiles, Player player)
    {
    	this._profiles = profiles;
        this._player = player;
    }
    
    public Profiles getProfiles()
    {
    	return _profiles;
    }

    public Player getPlayer()
    {
        return _player;
    }

    public int getAge()
    {
        return PlayerData.players.get(_player).getAge();
    }

    public MoodUtil getMood()
    {
        return PlayerData.players.get(_player).getMood();
    }

    public String getMedia()
    {
        return PlayerData.players.get(_player).getMedia();
    }

    public String getBio()
    {
        return PlayerData.players.get(_player).getBio();
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}

