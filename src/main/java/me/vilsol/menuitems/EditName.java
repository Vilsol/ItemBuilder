package me.vilsol.menuitems;

import me.vilsol.Utils;
import me.vilsol.jsonlib.ClickAction;
import me.vilsol.jsonlib.JSONMessage;
import me.vilsol.jsonlib.MessageBlock;
import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.menuengine.utils.Builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditName implements MenuItem, ChatCallback {

	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	public void execute(Player plr, ClickType click) {
		ChatCallback.locked_players.put(plr, this);
		plr.closeInventory();
		plr.sendMessage("Enter a new name for the item or '-' to cancel.");
		Utils.sendEditorMessage(plr);
		if(plr.getItemInHand().getItemMeta().getDisplayName() == null) return;
		String con = plr.getItemInHand().getItemMeta().getDisplayName();
		con = con.replace('§', '&');
		new JSONMessage().addBlock(new MessageBlock().setClickEvent(ClickAction.SUGGEST_COMMAND, con).setText("Current name (click): " + plr.getItemInHand().getItemMeta().getDisplayName())).sendToPlayer(plr);
	}

	public ItemStack getItem() {
		ItemStack i = new Builder(Material.NAME_TAG).setName(ChatColor.GOLD + "Rename Item").getItem();
		return i;
	}

	public void onChatMessage(AsyncPlayerChatEvent e) {
		MenuModel.openLastMenu(e.getPlayer());
		ChatCallback.locked_players.remove(e.getPlayer());
		if(e.getMessage().equalsIgnoreCase("-")) return;
		if(e.getPlayer().getItemInHand().getType() == Material.AIR) return;
		ItemMeta m = e.getPlayer().getItemInHand().getItemMeta();
		m.setDisplayName(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		e.getPlayer().getItemInHand().setItemMeta(m);
	}

}
