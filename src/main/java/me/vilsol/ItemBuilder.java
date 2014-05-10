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
import net.minecraft.server.v1_7_R2.NBTTagCompound;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilder extends JavaPlugin implements Listener {
	
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
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e){
		if(e.getPlayer().getItemInHand().getType() == Material.AIR) return;
		if(e.getMessage().startsWith("copy")){
			ItemStack copy = e.getPlayer().getItemInHand().clone();
			e.getPlayer().getInventory().setItem(8, copy);;
			e.getPlayer().sendMessage("Copied");
		}else if(e.getMessage().startsWith("add")){
			ItemStack copy = e.getPlayer().getItemInHand().clone();
			net.minecraft.server.v1_7_R2.ItemStack x = CraftItemStack.asNMSCopy(copy);
			NBTTagCompound s = x.getTag();
			if(s == null) s = new NBTTagCompound();
			s.setString("DUPED", "DUPED");
			try {
				x.getClass().getDeclaredField("tag").setAccessible(true);
				x.getClass().getDeclaredField("tag").set(x, s);
			} catch(SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			ItemStack newStack = CraftItemStack.asCraftMirror(x);
			System.out.println(CraftItemStack.asNMSCopy(newStack).getTag());
			e.getPlayer().getInventory().setItemInHand(newStack);
			e.getPlayer().sendMessage("Added");
		}else if(e.getMessage().startsWith("read")){
			ItemStack copy = e.getPlayer().getItemInHand();
			System.out.println(CraftItemStack.asNMSCopy(e.getPlayer().getItemInHand().clone()).getTag());
			System.out.println(CraftItemStack.asNMSCopy(e.getPlayer().getItemInHand()).getTag());
			net.minecraft.server.v1_7_R2.ItemStack x = CraftItemStack.asNMSCopy(copy);
			if(x.getTag().hasKey("DUPED")){
				Bukkit.broadcastMessage("SUCCESS!!");
			}else{
				e.getPlayer().sendMessage("Failure");
			}
		}
	}
	
}
