package me.vilsol.jsonlib;

public enum BlockType {
	
	URL("open_url"), COMMAND("run_command"), TEXT, INSERTION, SUGGESTION("suggest_command"), ITEM("show_item");
	
	public String action;
	
	private BlockType(){
	}

	private BlockType(String action){
		this.action = action;
	}
	
}
