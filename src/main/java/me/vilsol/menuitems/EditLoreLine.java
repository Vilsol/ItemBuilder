package me.vilsol.menuitems;

import java.util.ArrayList;
import java.util.List;

import me.vilsol.Utils;
import me.vilsol.jsonlib.JSONMessage;
import me.vilsol.menuengine.engine.BonusItem;
import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.menuengine.enums.ClickType;
import me.vilsol.menuengine.utils.Builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditLoreLine implements MenuItem, ChatCallback, BonusItem {

	private int line;
	
	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	public void execute(final Player plr, ClickType click) {
		if(click == ClickType.RIGHT){
			if(plr.getItemInHand().getType() == Material.AIR) return;
			ItemMeta m = plr.getItemInHand().getItemMeta();
			List<String> lore = m.getLore();
			if(lore == null) return;
			lore.remove(line);
			m.setLore(lore);
			plr.getItemInHand().setItemMeta(m);
			MenuModel.openLastMenu(plr);
		}else{
			ChatCallback.locked_players.put(plr, this);
			plr.closeInventory();
			plr.sendMessage("Enter durability for the item, '-' to cancel.");
			Utils.sendEditorMessage(plr);
			if(plr.getItemInHand().getItemMeta().getLore() == null) return;
			if(plr.getItemInHand().getItemMeta().getLore().size() - 1 >= line){
				JSONMessage m = new JSONMessage("", ChatColor.WHITE);
				String con = plr.getItemInHand().getItemMeta().getLore().get(line);
				con = con.replace('§', '&');
				m.addSuggestCommand("Current line (click): " + plr.getItemInHand().getItemMeta().getLore().get(line), ChatColor.WHITE, con);
				m.sendToPlayer(plr);
			}
		}
	}

	public ItemStack getItem() {
		ItemStack i = new Builder(Material.BOOK_AND_QUILL).setName(ChatColor.GOLD + "Change Lore Line " + (line + 1) + " (RightClick to delete)").getItem();
		return i;
	}

	public void onChatMessage(AsyncPlayerChatEvent e) {
		if(!e.getMessage().equalsIgnoreCase("-") && e.getPlayer().getItemInHand().getType() != Material.AIR) {
			ItemMeta m = e.getPlayer().getItemInHand().getItemMeta();
			List<String> lore = m.getLore();
			if(lore == null) lore = new ArrayList<String>();
			while(lore.size() <= line){
				lore.add("");
			}
			lore.set(line, ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			m.setLore(lore);
			e.getPlayer().getItemInHand().setItemMeta(m);
		}

		MenuModel.openLastMenu(e.getPlayer());
		ChatCallback.locked_players.remove(e.getPlayer());
	}

	@Override
	public void setBonusData(Object o) {
		line = (int) o;
	}
	
}
