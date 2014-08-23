package me.vilsol.jsonlib;

import java.util.ArrayList;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class JSONMessage {
	
	private JSONObject object = new JSONObject();
	private JSONArray extra = new JSONArray();
	private ArrayList<MessageBlock> blocks = new ArrayList<MessageBlock>();
	
	public JSONMessage(){
		object.put("extra", extra);
		object.put("text", "");
	}
	
	public JSONMessage addBlock(MessageBlock block){
		extra.add(block.getObject());
		return this;
	}
	
	public JSONMessage swapBlocks(int one, int two){
		if(blocks.size() <= one || blocks.size() <= two) return this;
		MessageBlock temp = blocks.get(one);
		blocks.set(one, blocks.get(two));
		blocks.set(two, temp);
		return this;
	}
	
	@Override
	public String toString(){
		return object.toJSONString();
	}
	
	public void sendToPlayer(Player p){
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(object.toJSONString()), true));
	}
	
}
