package me.vilsol.jsonlib;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class MessageBlock implements Cloneable {
	
	private JSONObject object = new JSONObject();
	
	public JSONObject getObject(){
		return object;
	}
	
	public MessageBlock setText(String text){
		if(text == null){
			object.remove("text");
		}else{
			object.put("text", text);
		}
		return this;
	}
	
	public MessageBlock setColor(ChatColor color){
		if(color == null){
			object.remove("color");
		}else{
			object.put("color", color.name().toLowerCase());
		}
		return this;
	}
	
	public MessageBlock setHoverEvent(HoverAction action, String value){
		JSONObject o = new JSONObject();
		o.put("action", action.name().toLowerCase());
		o.put("value", value);
		object.put("hoverEvent", o);
		return this;
	}
	
	public MessageBlock setClickEvent(ClickAction action, String value){
		JSONObject o = new JSONObject();
		o.put("action", action.name().toLowerCase());
		o.put("value", value);
		object.put("clickEvent", o);
		return this;
	}
	
	public MessageBlock setInsertion(String insertion){
		object.put("insertion", insertion);
		return this;
	}
	
	public MessageBlock setBold(boolean bold){
		if(bold){
			object.put("bold", true);
		}else{
			object.remove("bold");
		}
		return this;
	}
	
	public MessageBlock setItalic(boolean italic){
		if(italic){
			object.put("italic", true);
		}else{
			object.remove("italic");
		}
		return this;
	}
	
	public MessageBlock setUnderlined(boolean underlined){
		if(underlined){
			object.put("underlined", true);
		}else{
			object.remove("underlined");
		}
		return this;
	}
	
	public MessageBlock setStrikethrough(boolean strikethrough){
		if(strikethrough){
			object.put("strikethrough", true);
		}else{
			object.remove("strikethrough");
		}
		return this;
	}
	
	public MessageBlock setObfuscated(boolean obfuscated){
		if(obfuscated){
			object.put("obfuscated", true);
		}else{
			object.remove("obfuscated");
		}
		return this;
	}
	
	@Override
	public MessageBlock clone(){
		MessageBlock newBlock = new MessageBlock();

		if(object.containsKey("text")) newBlock.setText(object.get("text").toString());
		if(object.containsKey("color")) newBlock.setColor(ChatColor.valueOf(object.get("color").toString().toUpperCase()));
		
		newBlock.setBold(object.containsKey("bold"));
		newBlock.setObfuscated(object.containsKey("obfuscated"));
		newBlock.setStrikethrough(object.containsKey("strikethrough"));
		newBlock.setUnderlined(object.containsKey("underlined"));
		newBlock.setItalic(object.containsKey("italic"));

		if(object.containsKey("clickEvent")){
			String action = ((JSONObject) object.get("clickEvent")).get("action").toString();
			String value = ((JSONObject) object.get("clickEvent")).get("value").toString();
			newBlock.setClickEvent(ClickAction.valueOf(action), value);
		}
		
		if(object.containsKey("hoverEvent")){
			String action = ((JSONObject) object.get("hoverEvent")).get("action").toString();
			String value = ((JSONObject) object.get("hoverEvent")).get("value").toString();
			newBlock.setHoverEvent(HoverAction.valueOf(action), value);
		}
		
		return newBlock;
	}
	
}
