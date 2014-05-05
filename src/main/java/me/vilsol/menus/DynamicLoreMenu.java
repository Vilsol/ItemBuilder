package me.vilsol.menus;

import java.util.Arrays;

import me.vilsol.menuengine.engine.DynamicMenu;
import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.enums.InventorySize;
import me.vilsol.menuitems.AddLoreLine;
import me.vilsol.menuitems.EditLoreLine;
import me.vilsol.menuitems.switchitems.ToMainMenu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DynamicLoreMenu extends DynamicMenuModel {

	@Override
	public void addItems(DynamicMenu i, Player plr) {
		i.setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lore Editor");
		ItemStack item = plr.getItemInHand();
		ItemMeta m = item.getItemMeta();
		
		int x = 0;
		if(m.getLore() != null){
			for(x = 0; x < m.getLore().size(); x++){
				i.addItemDynamic(EditLoreLine.class, x + 1, Arrays.asList(x, m.getLore().get(x)));
			}
		}
		
		if(x < 53) i.addItemDynamic(AddLoreLine.class, x + 1);
		i.addItemDynamic(ToMainMenu.class, 0);
	}

	@Override
	public InventorySize getSize(Player plr) {
		ItemStack item = plr.getItemInHand();
		ItemMeta m = item.getItemMeta();
		if(m != null && m.getLore() != null){
			return InventorySize.getMinAfter(m.getLore().size() + 1);
		}
		return InventorySize.S_9;
	}

	@Override
	public boolean canPlaceItem(DynamicMenu i, Player plr, int slot, ItemStack item) {
		return false;
	}

	@Override
	public void onPickupItem(DynamicMenu i, ItemStack item, int slot) {
		return;
	}

	@Override
	public void onPlaceItem(DynamicMenu i, ItemStack item, int slot) {
		return;
	}

}
