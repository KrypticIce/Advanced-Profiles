package me.jakeythedev.profiles;

import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.jakeythedev.profiles.commands.ProfileCommand;
import me.jakeythedev.profiles.listeners.ConnectionListeners;
import me.jakeythedev.profiles.listeners.GlobalListeners;
import me.jakeythedev.profiles.ui.ProfileUI;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class Manager
{

    private Profiles _plugin;

    public Manager(Profiles plugin)
    {
        this._plugin = plugin;

        System.out.println("Manager registering began..");

        register();
        databaseSetup();

        System.out.println("Manager registering finished..");
    }

    public void databaseSetup()
    {
        try
        {
        	_plugin.db.createTable("profiledata", "uuid varchar(45), age int, mood varchar(45), youtube varchar(45), bio varchar(45)");
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void register()
    {
    	Bukkit.getPluginManager().registerEvents(new ConnectionListeners(_plugin), _plugin);
    	Bukkit.getPluginManager().registerEvents(new GlobalListeners(_plugin), _plugin);
        Bukkit.getPluginManager().registerEvents(new ProfileUI(_plugin), _plugin);
        
        _plugin.getCommand("profile").setExecutor(new ProfileCommand());
       
    }
}
