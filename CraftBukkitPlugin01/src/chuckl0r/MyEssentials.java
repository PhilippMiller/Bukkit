package chuckl0r;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.SetIdleTimeoutCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.*;

public class MyEssentials extends JavaPlugin
{
	
	@Override
	public void onEnable()
	{
		this.getLogger().info(ChatColor.YELLOW + "MyEssentials wurde aktiviert!");
	}
	
	public void onDisable()
	{
		this.getLogger().info(ChatColor.YELLOW + "MyEssentials wurde deaktiviert!");
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		MyEssentialsCommandExecutor commands = new MyEssentialsCommandExecutor(this);
		
		commands.doCommand(sender, cmd, label, args);
		
		return false;
	}

}
