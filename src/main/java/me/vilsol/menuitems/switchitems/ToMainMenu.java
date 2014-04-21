package me.vilsol.menuitems.switchitems;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.menuengine.enums.ClickType;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.menus.MainMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToMainMenu implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	@Override
	public void execute(Player plr, ClickType click) {
		MenuModel.menus.get(MainMenu.class).showToPlayer(plr);
	}

	@Override
	public ItemStack getItem() {
		ItemStack i = new Builder(Material.SLIME_BALL).setName(ChatColor.GOLD + "Back To Main Menu").getItem();
		return i;
	}
	
}
