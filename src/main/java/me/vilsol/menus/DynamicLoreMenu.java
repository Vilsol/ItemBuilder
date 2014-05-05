package me.vilsol.menus;

import java.util.Arrays;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.enums.InventorySize;
import me.vilsol.menuitems.AddLoreLine;
import me.vilsol.menuitems.EditLoreLine;
import me.vilsol.menuitems.switchitems.ToMainMenu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DynamicLoreMenu extends DynamicMenuModel {

	@Override
	public void addItems(Inventory i, Player plr) {
		ItemStack item = plr.getItemInHand();
		ItemMeta m = item.getItemMeta();
		
		int x = 0;
		if(m.getLore() != null){
			for(x = 0; x < m.getLore().size(); x++){
				addItemDynamic(EditLoreLine.class, i, x + 1, Arrays.asList(x, m.getLore().get(x)));
			}
		}
		
		addItemDynamic(AddLoreLine.class, i, x + 1);
		addItemDynamic(ToMainMenu.class, i, 0);
	}

	@Override
	public InventorySize getSize(Player plr) {
		ItemStack item = plr.getItemInHand();
		ItemMeta m = item.getItemMeta();
		if(m.getLore() != null){
			setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lore Line Count: " + m.getLore().size());
			return InventorySize.getMinAfter(m.getLore().size() + 1);
		}
		setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lore Editor");
		return InventorySize.S_9;
	}

	@Override
	public boolean canPlaceItem(Inventory i, Player plr, int slot, ItemStack item) {
		return false;
	}

	@Override
	public void onPickupItem(Inventory i, ItemStack item, int slot) {
		return;
	}

	@Override
	public void onPlaceItem(Inventory i, ItemStack item, int slot) {
		return;
	}

}
