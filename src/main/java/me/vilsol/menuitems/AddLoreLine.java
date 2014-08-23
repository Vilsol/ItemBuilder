package me.vilsol.menuitems;

import java.util.ArrayList;
import java.util.List;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AddLoreLine implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	public void execute(final Player plr, ClickType click) {
		ItemMeta m = plr.getItemInHand().getItemMeta();
		List<String> lore = m.getLore();
		if(lore == null) lore = new ArrayList<String>();
		lore.add("");
		m.setLore(lore);
		plr.getItemInHand().setItemMeta(m);
		DynamicMenuModel.openLastMenu(plr);
	}

	public ItemStack getItem() {
		ItemStack i = new Builder(Material.PAPER).setName(ChatColor.GOLD + "Add a lore line").getItem();
		return i;
	}
	
}
