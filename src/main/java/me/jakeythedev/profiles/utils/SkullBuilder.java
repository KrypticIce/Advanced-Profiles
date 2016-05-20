package me.jakeythedev.profiles.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class SkullBuilder
{
	private ItemStack _itemStack;
	private SkullMeta _itemMeta;

	public SkullBuilder()
	{
		_itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		_itemMeta = (SkullMeta) _itemStack.getItemMeta();
	}

	public SkullBuilder setName(String name)
	{
		_itemMeta.setDisplayName(name);
		return this;
	}

	public SkullBuilder setOwner(String name)
	{
		_itemMeta.setOwner(name);
		return this;
	}
	
	public SkullBuilder setLore(String... lore)
	{
		_itemMeta.setLore(Arrays.asList(lore));
		return this;
	}

	public SkullBuilder setAmount(int amount)
	{
		_itemStack.setAmount(amount);
		return this;
	}

	public SkullBuilder addEnchant(Enchantment enchant, int level)
	{
		_itemMeta.addEnchant(enchant, level, true);
		return this;
	}

	public SkullBuilder setCustomSkull(String urlString)
	{
		if (urlString == null || urlString.equals(""))
			return this;

		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", urlString));
		Field field;
		try
		{
			field = _itemMeta.getClass().getDeclaredField("profile");
			field.setAccessible(true);
			field.set(_itemMeta, profile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return this;
	}

	public ItemStack build()
	{
		_itemStack.setItemMeta(_itemMeta);
		return _itemStack;
	}
}
