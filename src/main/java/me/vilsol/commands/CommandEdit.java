package me.vilsol.commands;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.menus.MainMenu;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEdit implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Check if it is a player
		if(!(sender instanceof Player)){
			sender.sendMessage("Only players can execute this command.");
			return true;
		}
		
		Player p = (Player) sender;
		
		// Check if player is holding an item
		if(p.getItemInHand().getType() == Material.AIR){
			p.sendMessage("You must be holding an item.");
			return true;
		}
		
		// Show the menu
		MenuModel.menus.get(MainMenu.class).showToPlayer(p);
		
		return true;
	}
	
}
