package me.vilsol.menuitems;

import me.vilsol.Utils;
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

public class EditDurability implements MenuItem, ChatCallback {

	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	public void execute(Player plr, ClickType click) {
		ChatCallback.locked_players.put(plr, this);
		plr.closeInventory();
		plr.sendMessage("Enter durability for the item or 'cancel' to cancel.");
	}

	public ItemStack getItem() {
		ItemStack i = new Builder(Material.FIREBALL).setName(ChatColor.GOLD + "Change Durability").getItem();
		return i;
	}

	public void onChatMessage(AsyncPlayerChatEvent e) {
		if(!e.getMessage().equalsIgnoreCase("cancel")){
			if(e.getPlayer().getItemInHand().getType() != Material.AIR){
				if(Utils.isShort(e.getMessage())){
					e.getPlayer().getItemInHand().setDurability(Short.parseShort(e.getMessage()));
				}else{
					e.getPlayer().sendMessage("Durability must be a number and below " + Short.MAX_VALUE + "!");
					return;
				}
			}
		}
		
		MenuModel.openLastMenu(e.getPlayer());
		ChatCallback.locked_players.remove(e.getPlayer());
	}

}
