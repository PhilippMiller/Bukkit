package com.gamer_point.chuckl0r.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExecutor {
	
	public static Map<String, String> commands = new HashMap<String, String>();
	static {
		commands.put("help", "Zeigt diese Liste mit allen befehlen an.");
	}
	
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (!(sender instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
			return true;
		}
		
		/*
		 * Command /help
		 */
		if (command.getName().equalsIgnoreCase("help")) {
			player.sendMessage(ChatColor.GOLD + "Admin Commands | HELP");
			for (String key : commands.keySet()) {
				player.sendMessage(ChatColor.GRAY + "/" + key + ChatColor.WHITE + " " + commands.get(key));
			}
			player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "powered by www.gamer-point.com");
		}
		
		return true;
	}

}
