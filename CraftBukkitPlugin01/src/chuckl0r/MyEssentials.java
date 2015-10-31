package chuckl0r;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.*;

public class MyEssentials extends JavaPlugin
{
	
	@Override
	public void onEnable()
	{
		this.getLogger().info("MyEssentials wurde aktiviert!");
	}
	
	public void onDisable()
	{
		this.getLogger().info("MyEssentials wurde deaktiviert!");
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		MyEssentialsCommandExecutor commands = new MyEssentialsCommandExecutor();
		
		commands.doCommand(sender, cmd, label, args);
		
		return true;
	}

}
