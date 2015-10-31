package chuckl0r;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.material.MaterialData;

import net.minecraft.server.v1_8_R3.Material;

public class MyEssentialsCommandExecutor
{
	
	private MyEssentials plugin;
	
	public MyEssentialsCommandExecutor(MyEssentials plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean doCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		Player otherPlayer = null;
		
		//PLAYER CHECK
		if(!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Du musst ein Spieler sein!");
			return true;
		}
		Player player = (Player) sender;
		
		//############################
		//######COMMAND "/wispi"######
		//############################
		if (cmd.getName().equals("wispi"))
		{
			OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
			offlinePlayer.OfflinePlayerCheck(sender, args[0]);
			otherPlayer = offlinePlayer.otherPlayer;
			
			if ((args.length >= 2) && (otherPlayer == null))
			{
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler "+ ChatColor.DARK_RED + otherPlayer + ChatColor.RED + " ist nicht online!");
			}
			if (args.length < 2)
			{
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/wispi [name] [text]");
				return true;
			}
			
			if (otherPlayer != null)
			{
				String text = "";
				
				for (int i = 1; i < args.length; i++)
				{
					text = text + args[i] + " ";
				}
				
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.AQUA + "WHISPER an " + ChatColor.DARK_GRAY + args[0] + ": " + text);
				otherPlayer.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.AQUA + "WHISPER von " + ChatColor.DARK_GRAY + sender.getName() + ": " + text);
				return true;
			}
			
		}
		
		//###########################
		//######COMMAND "/g"#########
		//###########################
		else if (cmd.getName().equals("gmode"))
		{
			
			if (args.length == 0)
			{
				GameMode gm = player.getGameMode();
				if (gm == GameMode.CREATIVE)
				{
					player.setGameMode(GameMode.SURVIVAL);
					sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
				}
				else
				{
					player.setGameMode(GameMode.CREATIVE);
					sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
				}
				return true;
			}
			
			if (args.length == 1)
			{
				GameMode gm = player.getGameMode();
				if (args[0].equalsIgnoreCase("creative") == true)
				{
					if (gm == GameMode.CREATIVE)
					{
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Du bist bereits im Creative Modus!");
						return true;
					}
					else
					{
						player.setGameMode(GameMode.CREATIVE);	
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("survival") == true)
				{
					if (gm == GameMode.SURVIVAL)
					{
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Du bist bereits im Survival Modus!");
						return true;
					}
					else
					{
						player.setGameMode(GameMode.SURVIVAL);	
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
						return true;
					}
				}
				
				OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
				offlinePlayer.OfflinePlayerCheck(sender, args[0]);
				otherPlayer = offlinePlayer.otherPlayer;
				if (otherPlayer != null)
				{
					GameMode gm_other = otherPlayer.getGameMode();
					if (gm_other == GameMode.CREATIVE)
					{
						otherPlayer.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
					}
					else
					{
						player.setGameMode(GameMode.CREATIVE);
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
					}
					return true;
				}
				
				if ((args[0].equalsIgnoreCase("survival") == false) && (args[0].equalsIgnoreCase("creative") == false))
				{
					sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Mögliche GameModes: " + ChatColor.DARK_RED + "Creative , Survival");
					return true;
				}
				
				
			}
			
			
			OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
			offlinePlayer.OfflinePlayerCheck(sender, args[0]);
			otherPlayer = offlinePlayer.otherPlayer;
			
			if ((args.length >= 2) && (otherPlayer != null))
			{
				GameMode gm = player.getGameMode();
				if (args[1].equalsIgnoreCase("creative") == true)
				{
					if (gm == GameMode.CREATIVE)
					{
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED + " ist bereits im " + ChatColor.DARK_RED + "Creative" + ChatColor.RED + " Modus!");
						return true;
					}
					else
					{
						player.setGameMode(GameMode.CREATIVE);	
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Der Spieler " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im "+ ChatColor.DARK_GREEN +"Creative" + ChatColor.GREEN + " Modus!");
						return true;
					}
				}
				if (args[1].equalsIgnoreCase("survival") == true)
				{
					if (gm == GameMode.SURVIVAL)
					{
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED + " ist bereits im " + ChatColor.DARK_RED + "Survival" + ChatColor.RED + " Modus!");
						return true;
					}
					else
					{
						player.setGameMode(GameMode.SURVIVAL);	
						sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Der Spieler " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im "+ ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
						return true;
					}
				}
				if ((args[1].equalsIgnoreCase("survival") == false) && (args[1].equalsIgnoreCase("creative") == false))
				{
					sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Mögliche GameModes: " + ChatColor.DARK_RED + "Creative , Survival");
					return true;
				}
			}
			
		}
		
		//###########################
		//######COMMAND "/tm"########
		//###########################
		else if (cmd.getName().equals("tm"))
		{
			World world = player.getWorld();
			
			if (args.length == 0)
			{
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/tm [time]");
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Für [time] kann " + ChatColor.DARK_RED + "\"tag\", \"nacht\" oder ein Ganzzahliger Wert " + ChatColor.RED + "eingegeben werden.");
				return true;
			}
			
			if (args[0].equalsIgnoreCase("tag"))
			{
				world.setTime(1000);
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Es ist "+ ChatColor.DARK_GREEN + "Tag!");
				return true;
			}
			if (args[0].equalsIgnoreCase("nacht"))
			{
				world.setTime(13000);
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Es ist "+ ChatColor.DARK_GREEN + "Nacht!");
				return true;
			}
			
			int wert = 0;
			try 
			{
				wert = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e)
			{
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Bitte " + ChatColor.DARK_RED + "\"tag\",\"nacht\" oder einen Ganzzahligen Wert eingeben!");
				return true;
			}
			
			world.setTime(wert);
			sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Die Zeit wurde auf "+ ChatColor.DARK_GREEN + wert + ChatColor.GREEN + " gesetzt!");
			return true;
		}
		
		
		//##################################
		//######COMMAND "/MyEssentials"#####
		//##################################
		else if (cmd.getName().equals("MyEssentials"))
		{
			ArrayList<String> list = new ArrayList<String>();
			list.add(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getName());
			list.addAll(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getAuthors());
			list.add(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getVersion());
			
			
			String listString = "";

			sender.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.YELLOW + "Plugin Information");
			for (String s : list)
			{
				sender.sendMessage("=> " + s);
			}
			
			return true;
		}
		else if (cmd.getName().equals("g"))
		{
			int wert = 0;
			try 
			{
				wert = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e)
			{
				sender.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Bitte " + ChatColor.DARK_RED + "\"tag\",\"nacht\" oder einen Ganzzahligen Wert eingeben!");
				return true;
			}
			ItemStack item = new ItemStack(null, wert);
			player.getInventory().addItem();
			
			return true;
		}
		
		//HIER WEITERE COMMANDS!
		
		
		return false;
	}
	
	
	

}
