package com.gamer_point.chuckl0r.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminCommands extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getLogger().info("Admin Commands Enabled");
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Admin Commands Enabled");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandExecutor commandExec = new CommandExecutor();
		commandExec.execute(sender, command, label, args);
		return true;
	}
	

}
