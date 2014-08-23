package me.vilsol;

import java.net.URLEncoder;
import java.util.HashMap;

import me.vilsol.jsonlib.JSONMessage;
import me.vilsol.jsonlib.MessageBlock;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
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
		JSONMessage j = new JSONMessage();
		MessageBlock c = new MessageBlock().setText(", ");
		j.addBlock(new MessageBlock().setColor(ChatColor.BLACK).setText("&0").setInsertion("&0")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_BLUE).setText("&1").setInsertion("&1")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_GREEN).setText("&2").setInsertion("&2")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_AQUA).setText("&3").setInsertion("&3")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_RED).setText("&4").setInsertion("&4")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_PURPLE).setText("&5").setInsertion("&5")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.GOLD).setText("&6").setInsertion("&6")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.GRAY).setText("&7").setInsertion("&7")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.DARK_GRAY).setText("&8").setInsertion("&8")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.BLUE).setText("&9").setInsertion("&9")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.GREEN).setText("&a").setInsertion("&a")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.AQUA).setText("&b").setInsertion("&b")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.RED).setText("&c").setInsertion("&c")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.LIGHT_PURPLE).setText("&d").setInsertion("&d")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.YELLOW).setText("&e").setInsertion("&e")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.WHITE).setText("&f").setInsertion("&f")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.MAGIC).setText("&k").setInsertion("&k")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.BOLD).setText("&l").setInsertion("&l")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.STRIKETHROUGH).setText("&m").setInsertion("&m")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.UNDERLINE).setText("&n").setInsertion("&n")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.ITALIC).setText("&o").setInsertion("&o")); j.addBlock(c);
		j.addBlock(new MessageBlock().setColor(ChatColor.RESET).setText("&r").setInsertion("&r"));
		j.sendToPlayer(plr); // TODO Wait for 1.8...
	}
	
	@SuppressWarnings("deprecation")
	public static void sendLinkOfItem(ItemStack item, Player plr) {
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
	
	public static HashMap<Integer, ItemStack> stackTogether(HashMap<Integer, ItemStack> items) {
		
		return items;
	}
	
}
