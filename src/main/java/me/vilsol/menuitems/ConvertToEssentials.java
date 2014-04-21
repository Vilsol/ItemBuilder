package me.vilsol.menuitems;

import me.vilsol.Utils;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.enums.ClickType;
import me.vilsol.menuengine.utils.Builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConvertToEssentials implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	@Override
	public void execute(Player plr, ClickType click) {
		plr.closeInventory();
		if(plr.getItemInHand() == null) return;
		Utils.sendLinkOfItem(plr.getItemInHand(), plr);
	}

	@Override
	public ItemStack getItem() {
		ItemStack i = new Builder(Material.ANVIL).setName(ChatColor.GOLD + "Get Essentials Spawn Code").getItem();
		return i;
	}
	
}
