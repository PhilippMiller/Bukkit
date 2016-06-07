package chuckl0r;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OfflinePlayerCheck
{
	private Player otherPlayer = null;
	
	
	public boolean OfflinePlayerChecker(CommandSender sender, String theOtherOne)
	{
		
		//OFFLINE PLAYER CHECK
		@SuppressWarnings ("deprecation")
		Player theOterhPlayer = Bukkit.getServer().getPlayer(theOtherOne);
		if (theOterhPlayer.isOnline()) {
			otherPlayer = theOterhPlayer;
			return true;
		} else {
			otherPlayer = null;
			return false;
		}
	}
	
	public Player getOtherPlayer() {
		return this.otherPlayer;
	}
	
}
