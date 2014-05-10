package me.vilsol;

import me.vilsol.commands.CommandEdit;
import me.vilsol.menuitems.AddLoreLine;
import me.vilsol.menuitems.ConvertToEssentials;
import me.vilsol.menuitems.EditAmount;
import me.vilsol.menuitems.EditDurability;
import me.vilsol.menuitems.EditLoreLine;
import me.vilsol.menuitems.EditName;
import me.vilsol.menuitems.switchitems.ToDynamicLoreMenu;
import me.vilsol.menuitems.switchitems.ToMainMenu;
import me.vilsol.menus.DynamicLoreMenu;
import me.vilsol.menus.MainMenu;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilder extends JavaPlugin {
	
	public static ItemBuilder plugin;
	
	public void onEnable(){
		plugin = this;
		
		// Register commands
		getCommand("edit").setExecutor(new CommandEdit());
		
		// Register items
		new EditName().registerItem();
		new EditDurability().registerItem();
		new EditLoreLine().registerItem();
		new EditAmount().registerItem();
		new ToMainMenu().registerItem();
		new ConvertToEssentials().registerItem();
		new ToDynamicLoreMenu().registerItem();
		new AddLoreLine().registerItem();
		
		// Register menus
		new MainMenu();
		new DynamicLoreMenu();
	}
	
}
