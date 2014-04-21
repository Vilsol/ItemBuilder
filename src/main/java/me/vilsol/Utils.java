package me.vilsol;

import java.net.URLEncoder;
import java.util.HashMap;

import me.vilsol.jsonlib.JSONMessage;
import net.minecraft.server.v1_7_R2.ChatSerializer;
import net.minecraft.server.v1_7_R2.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Joiner;

public class Utils {

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(Exception e) {}
		return false;
	}

	public static boolean isShort(String s) {
		try {
			Short.parseShort(s);
			return true;
		} catch(Exception e) {}
		return false;
	}
	
	public static void sendEditorMessage(Player plr){
		JSONMessage j = new JSONMessage("", ChatColor.WHITE);
		j.addInsertionText("&0", ChatColor.BLACK, "&0"); j.addText(", ");
		j.addInsertionText("&1", ChatColor.DARK_BLUE, "&1"); j.addText(", ");
		j.addInsertionText("&2", ChatColor.DARK_GREEN, "&2"); j.addText(", ");
		j.addInsertionText("&3", ChatColor.DARK_AQUA, "&3"); j.addText(", ");
		j.addInsertionText("&4", ChatColor.DARK_RED, "&4"); j.addText(", ");
		j.addInsertionText("&5", ChatColor.DARK_PURPLE, "&5"); j.addText(", ");
		j.addInsertionText("&6", ChatColor.GOLD, "&6"); j.addText(", ");
		j.addInsertionText("&7", ChatColor.GRAY, "&7"); j.addText(", ");
		j.addInsertionText("&8", ChatColor.DARK_GRAY, "&8"); j.addText(", ");
		j.addInsertionText("&9", ChatColor.BLUE, "&9"); j.addText(", ");
		j.addInsertionText("&a", ChatColor.GREEN, "&a"); j.addText(", ");
		j.addInsertionText("&b", ChatColor.AQUA, "&b"); j.addText(", ");
		j.addInsertionText("&c", ChatColor.RED, "&c"); j.addText(", ");
		j.addInsertionText("&d", ChatColor.LIGHT_PURPLE, "&d"); j.addText(", ");
		j.addInsertionText("&e", ChatColor.YELLOW, "&e"); j.addText(", ");
		j.addInsertionText("&f", ChatColor.WHITE, "&f"); j.addText(", ");
		j.addInsertionText("&k", ChatColor.MAGIC, "&k"); j.addText(", ");
		j.addInsertionText("&l", ChatColor.BOLD, "&l"); j.addText(", ");
		j.addInsertionText("&m", ChatColor.STRIKETHROUGH, "&m"); j.addText(", ");
		j.addInsertionText("&n", ChatColor.UNDERLINE, "&n"); j.addText(", ");
		j.addInsertionText("&o", ChatColor.ITALIC, "&o"); j.addText(", ");
		j.addInsertionText("&r", ChatColor.RESET, "&r");
		j.sendToPlayer(plr); // TODO Wait for 1.8...
	}
	
	@SuppressWarnings("deprecation")
	public static void sendLinkOfItem(ItemStack item, Player plr){
		String i = Utils.itemToEssentialsSpawn(item);
		i = i.replace('§', '&');
		String url = "{color:\"white\",extra:[{clickEvent:{action:open_url,value:\"http://vilsol.me/?page=payload&a1=" + URLEncoder.encode(i) + "\"},color:\"underline\",text:\"LINK\"}],text:\"Click here for data: \"}";
		((CraftPlayer) plr).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(url), true));
	}

	@SuppressWarnings("deprecation")
	public static String itemToEssentialsSpawn(ItemStack i) {
		String x = "/i " + i.getTypeId();
		
		if(i.getDurability() != 0) x += ":" + i.getDurability();
		x += " " + i.getAmount();
		
		ItemMeta m = i.getItemMeta();
		if(m.getDisplayName() != null) x += " name:" + m.getDisplayName().replace(' ', '_');
		if(m.getLore() != null) x += " lore:" + Joiner.on('|').join(m.getLore()).replace(' ', '_');
		return x;
	}
	
	public static HashMap<Integer, ItemStack> stackTogether(HashMap<Integer, ItemStack> items){
		
		return items;
	}
	
}
