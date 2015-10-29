package chuckl0r;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OfflinePlayerCheck
{
	Player otherPlayer = null;
	
	@Deprecated
	public boolean OfflinePlayerCheck(CommandSender sender, String theOtherOne)
	{
		
		//OFFLINE PLAYER CHECK
		OfflinePlayer other = Bukkit.getServer().getOfflinePlayer(theOtherOne);
		if (other.isOnline())
		{
			otherPlayer = (Player) other;
			return true;
		}
		else
		{
			otherPlayer = null;
			return true;
		}
	}
	
}
