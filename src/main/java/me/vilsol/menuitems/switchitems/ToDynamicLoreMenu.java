package me.vilsol.menuitems.switchitems;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.enums.ClickType;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.menus.DynamicLoreMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToDynamicLoreMenu implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	@Override
	public void execute(Player plr, ClickType click) {
		DynamicMenuModel.menus.get(DynamicLoreMenu.class).showToPlayer(plr);
	}

	@Override
	public ItemStack getItem() {
		ItemStack i = new Builder(Material.CACTUS).setName(ChatColor.GOLD + "Change Lore").getItem();
		return i;
	}
	
}
