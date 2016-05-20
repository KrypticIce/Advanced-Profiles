package me.jakeythedev.profiles.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public class ItemStackBuilder
{
	private ItemStack _itemStack;
	private ItemMeta _itemMeta;

	public ItemStackBuilder(Material material)
	{
		_itemStack = new ItemStack(material);
		_itemMeta = _itemStack.getItemMeta();
	}

	public ItemStackBuilder(Material material, byte data)
	{
		_itemStack = new ItemStack(material, 1, data);
		_itemMeta = _itemStack.getItemMeta();
	}

	public ItemStackBuilder setName(String name)
	{
		_itemMeta.setDisplayName(name);
		return this;
	}

	public ItemStackBuilder setLore(String... lore)
	{
		_itemMeta.setLore(Arrays.asList(lore));
		return this;
	}

	public ItemStackBuilder setAmount(int amount)
	{
		_itemStack.setAmount(amount);
		return this;
	}

	public ItemStack build()
	{
		_itemStack.setItemMeta(_itemMeta);
		return _itemStack;
	}
}
