package me.vilsol.menuitems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.vilsol.Utils;
import me.vilsol.jsonlib.JSONMessage;
import me.vilsol.menuengine.engine.BonusItem;
import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.engine.MenuItem;
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
	private String lore;
	
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
			DynamicMenuModel.openLastMenu(plr);
		}else if(click == ClickType.LEFT){
			ChatCallback.locked_players.put(plr, this);
			plr.closeInventory();
			plr.sendMessage("Enter lore for the item, '-' to cancel.");
			Utils.sendEditorMessage(plr);
			if(plr.getItemInHand().getItemMeta().getLore() == null) return;
			if(plr.getItemInHand().getItemMeta().getLore().size() - 1 >= line){
				JSONMessage m = new JSONMessage("", ChatColor.WHITE);
				String con = plr.getItemInHand().getItemMeta().getLore().get(line);
				con = con.replace('§', '&');
				m.addSuggestCommand("Current line (click): " + plr.getItemInHand().getItemMeta().getLore().get(line), ChatColor.WHITE, con);
				m.sendToPlayer(plr);
			}
		}else if(click == ClickType.MIDDLE){
			if(plr.getItemInHand().getType() == Material.AIR) return;
			ItemMeta m = plr.getItemInHand().getItemMeta();
			List<String> lore = m.getLore();
			if(lore == null) return;
			
			lore.add("");
			for(int x = lore.size() - 2; x >= 0; x--){
				lore.set(x+1, lore.get(x));
			}
			
			lore.set(line, "");
			m.setLore(lore);
			plr.getItemInHand().setItemMeta(m);
			DynamicMenuModel.openLastMenu(plr);
		}
	}

	public ItemStack getItem() {
		ItemStack i = new Builder(Material.BOOK_AND_QUILL).setName(lore).setLore(Arrays.asList(ChatColor.GOLD + "LeftClick: Change Lore Line (" + (line + 1) + ")", ChatColor.GOLD + "RightClick: Delete Lore Line (" + (line + 1) + ")", ChatColor.GOLD + "MiddleClick: Insert Empty Line Before (" + (line + 1) + ")")).getItem();
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

		DynamicMenuModel.openLastMenu(e.getPlayer());
		ChatCallback.locked_players.remove(e.getPlayer());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBonusData(Object o) {
		List<Object> d = (List<Object>) o;
		line = (int) d.get(0);
		lore = (String) d.get(1);
	}
	
}
