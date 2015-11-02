package chuckl0r;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OfflinePlayerCheck
{
	Player otherPlayer = null;
	
	@Deprecated
	public boolean OfflinePlayerChecker(CommandSender sender, String theOtherOne)
	{
		
		//OFFLINE PLAYER CHECK
		
		UUID anOther = Bukkit.getPlayer(theOtherOne).getUniqueId();
		
		OfflinePlayer other = Bukkit.getServer().getOfflinePlayer(anOther);
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
