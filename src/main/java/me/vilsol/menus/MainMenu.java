package me.vilsol.menus;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.menuitems.ConvertToEssentials;
import me.vilsol.menuitems.EditAmount;
import me.vilsol.menuitems.EditDurability;
import me.vilsol.menuitems.EditName;
import me.vilsol.menuitems.switchitems.ToDynamicLoreMenu;

import org.bukkit.ChatColor;

public class MainMenu extends MenuModel {

	public MainMenu() {
		super(9, ChatColor.GOLD + "" + ChatColor.BOLD + "ItemBuilder" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + " 1.0");
		addItem(EditName.class, 0);
		addItem(EditAmount.class, 1);
		addItem(EditDurability.class, 2);
		addItem(ToDynamicLoreMenu.class, 3);
		
		addItem(ConvertToEssentials.class, 8);
	}
	
}
