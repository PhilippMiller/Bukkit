package chuckl0r;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.*;

public class MyEssentials extends JavaPlugin {
	
	public static final String pluginName = "GP-E";

	public void onEnable() {
		new EventListener(this);
		this.getLogger().info(pluginName + " wurde aktiviert!");
	}

	public void onDisable() {
		this.getLogger().info(pluginName + " wurde deaktiviert!");
	}

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		MyEssentialsCommandExecutor commands = new MyEssentialsCommandExecutor();

		commands.doCommand(sender, cmd, label, args);

		return true;
	}

}
