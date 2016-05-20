package me.jakeythedev.profiles.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Material;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class Database
{

	private Connection _connection;

	public Database(String ip, String port, String db, String username, String password) throws SQLException
	{
		openConnection(ip, port, db, username, password);
	}

	public void createTable(String table, String columns) throws SQLException
	{

		final String CREATE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS " + table + "(" + columns + ");";
		
		PreparedStatement statement = null;

		try
		{
			statement = _connection.prepareStatement(CREATE_IF_NOT_EXISTS);
			statement.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error creating table named " + table + "!");
			return;
		}
		finally
		{
			if (statement != null)
				statement.close();
		}
	}

	public void openConnection(String ip, String port, String db, String username, String password)
	{
		try
		{
			_connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);
			System.out.println("Database connection established! Connected to " + ip + ":" + port
					+ " with the username: " + username);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error occured whilst trying to open connection!");
			return;
		}
	}

	public void closeConnection()
	{
		try
		{
			_connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Could not close connection. See stack trace for info!");
		}
	}
	
	public void sendUpdate(String query)
	{
		
		try
		{
			PreparedStatement statement = getConnection().prepareStatement(query);
			statement.executeUpdate();
			statement.close();
			
			System.out.println("Sent " + query + " to database!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Could not send update to database!");
		}
	}
	
	public ResultSet sendQuery(String query)
	{
		ResultSet set = null;
		
		try
		{
			PreparedStatement statment = getConnection().prepareStatement(query);
			set = statment.executeQuery();
			
			System.out.println("Sent " + query + " to database!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Could not send query to database!");
		}
		
		return set;
	}

	public Connection getConnection()
	{
		return _connection;
	}
}
