package chuckl0r;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MyEssentialsCommandExecutor {

	private Player otherPlayer = null;
	private OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();

	// COMMAND AUFRUFER
	public boolean doCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// PLAYER CHECK
		Player player = (Player) sender;
		
		if (!(sender instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Du musst ein Spieler sein!");
			return true;
		}

		if (cmd.getName().equals("wispi")) {
			this.wispi(sender, cmd, label, args);
		}

		else if (cmd.getName().equals("gmode")) {
			this.gmode(sender, cmd, label, args);
		}

		else if (cmd.getName().equals("tm")) {
			this.timeManager(sender, cmd, label, args);
		}

		else if (cmd.getName().equals("MyEssentials")) {
			this.MyEssentails(sender, cmd, label, args);
		}

		else if (cmd.getName().equals("g")) {
			this.give(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("w")) {
			this.wetter(sender, cmd, label, args);
		}
		else if (cmd.getName().equalsIgnoreCase("ws")){
			if (args.length == 2) args[1] = args[0];
			args[0] = "sonne";
			this.wetter(sender, cmd, label, args);
		}
		else if (cmd.getName().equalsIgnoreCase("wr")){
			if (args.length == 2) args[1] = args[0];
			args[0] = "regen";
			this.wetter(sender, cmd, label, args);
		}
		else if (cmd.getName().equalsIgnoreCase("wst")){
			if (args.length == 2) args[1] = args[0];
			args[0] = "sturm";
			this.wetter(sender, cmd, label, args);
		}

		// HIER WEITERE COMMANDS!

		return false;
	}

	// COMMANDS
	@Deprecated
	public boolean wispi(CommandSender sender, Command cmd, String label, String[] args) {

		// ############################
		// ######COMMAND "/wispi"######
		// ############################
		Player player = (Player) sender;

		offlinePlayer.OfflinePlayerChecker(player, args[0]);
		otherPlayer = offlinePlayer.otherPlayer;

		if ((args.length >= 2) && (otherPlayer == null)) {
			player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED
					+ otherPlayer + ChatColor.RED + " ist nicht online!");
		}
		if (args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der richtige Befehl lautet: "
					+ ChatColor.DARK_RED + "/wispi [name] [text]");
			return true;
		}

		if (otherPlayer != null) {
			String text = "";

			for (int i = 1; i < args.length; i++) {
				text = text + args[i] + " ";
			}

			player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.AQUA + "WHISPER an "
					+ ChatColor.DARK_GRAY + args[0] + ": " + text);
			otherPlayer.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.AQUA + "WHISPER von "
					+ ChatColor.DARK_GRAY + player.getName() + ": " + text);
			return true;
		}

		return false;
	}

	@Deprecated
	public boolean gmode(CommandSender sender, Command cmd, String label, String[] args) {

		// ###########################
		// ######COMMAND "/gmode"#####
		// ###########################
		Player player = (Player) sender;

		if (args.length == 0) {
			GameMode gm = player.getGameMode();
			if (gm == GameMode.CREATIVE) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im "
						+ ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
			} else {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im "
						+ ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
			}
			return true;
		}

		if (args.length == 1) {
			GameMode gm = player.getGameMode();
			if (args[0].equalsIgnoreCase("creative") == true) {
				if (gm == GameMode.CREATIVE) {
					player.sendMessage(
							ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Du bist bereits im Creative Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im "
							+ ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("survival") == true) {
				if (gm == GameMode.SURVIVAL) {
					player.sendMessage(
							ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Du bist bereits im Survival Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Du bist nun im "
							+ ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}

			OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
			offlinePlayer.OfflinePlayerChecker(sender, args[0]);
			otherPlayer = offlinePlayer.otherPlayer;
			if (otherPlayer != null) {
				GameMode gm_other = otherPlayer.getGameMode();
				if (gm_other == GameMode.CREATIVE) {
					otherPlayer.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.DARK_GREEN
							+ otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN
							+ "Survival" + ChatColor.GREEN + " Modus!");
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.DARK_GREEN
							+ otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN
							+ "Creative" + ChatColor.GREEN + " Modus!");
				}
				return true;
			}

			if ((args[0].equalsIgnoreCase("survival") == false) && (args[0].equalsIgnoreCase("creative") == false)) {
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Mögliche GameModes: "
						+ ChatColor.DARK_RED + "Creative , Survival");
				return true;
			}

		}

		OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
		offlinePlayer.OfflinePlayerChecker(sender, args[0]);
		otherPlayer = offlinePlayer.otherPlayer;

		if ((args.length >= 2) && (otherPlayer != null)) {
			GameMode gm = player.getGameMode();
			if (args[1].equalsIgnoreCase("creative") == true) {
				if (gm == GameMode.CREATIVE) {
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler "
							+ ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED + " ist bereits im "
							+ ChatColor.DARK_RED + "Creative" + ChatColor.RED + " Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Der Spieler "
							+ ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im "
							+ ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if (args[1].equalsIgnoreCase("survival") == true) {
				if (gm == GameMode.SURVIVAL) {
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der Spieler "
							+ ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED + " ist bereits im "
							+ ChatColor.DARK_RED + "Survival" + ChatColor.RED + " Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Der Spieler "
							+ ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " ist nun im "
							+ ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if ((args[1].equalsIgnoreCase("survival") == false) && (args[1].equalsIgnoreCase("creative") == false)) {
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Mögliche GameModes: "
						+ ChatColor.DARK_RED + "Creative , Survival");
				return true;
			}
		}

		return false;
	}

	public boolean timeManager(CommandSender sender, Command cmd, String label, String[] args) {

		// ###########################
		// ######COMMAND "/tm"########
		// ###########################
		Player player = (Player) sender;

		World world = player.getWorld();

		if (args.length == 0) {
			player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Der richtige Befehl lautet: "
					+ ChatColor.DARK_RED + "/tm [time]");
			player.sendMessage(
					ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Für [time] kann " + ChatColor.DARK_RED
							+ "\"tag\", \"nacht\" oder ein Ganzzahliger Wert " + ChatColor.RED + "eingegeben werden.");
			return true;
		}
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("tag")) {
				world.setTime(1000);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Es ist "
						+ ChatColor.DARK_GREEN + "Tag!");
				return true;
			}
			if (args[0].equalsIgnoreCase("nacht")) {
				world.setTime(13000);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Es ist "
						+ ChatColor.DARK_GREEN + "Nacht!");
				return true;
			}

			int wert = 0;
			try {
				wert = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.RED + "Bitte " + ChatColor.DARK_RED
						+ "\"tag\",\"nacht\" oder einen Ganzzahligen Wert eingeben!");
				return true;
			}

			world.setTime(wert);
			player.sendMessage(ChatColor.GOLD + "[MyEssentials]: " + ChatColor.GREEN + "Die Zeit wurde auf "
					+ ChatColor.DARK_GREEN + wert + ChatColor.GREEN + " gesetzt!");
			return true;
		}

		return false;

	}

	public boolean MyEssentails(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/MyEssentials"#####
		// ##################################
		Player player = (Player) sender;

		ArrayList<String> list = new ArrayList<String>();
		list.add(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getName());
		list.addAll(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getAuthors());
		list.add(Bukkit.getServer().getPluginManager().getPlugin("MyEssentials").getDescription().getVersion());

		player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.YELLOW + "Plugin Information");
		for (String s : list) {
			player.sendMessage("=> " + s);
		}

		return true;
	}

	@Deprecated
	public boolean give(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/g"################
		// ##################################
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Du musst eine ItemID angeben.");
		} else if (args.length == 1 || args.length == 2) {
			try {
				int itemId = Integer.parseInt(args[0]);
				int anzahl = 1;

				if (args.length >= 2) {
					anzahl = Integer.parseInt(args[1]);
				}

				player.getInventory().addItem(new ItemStack(itemId, anzahl));

				return true;

			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Diese ID kenne ich nicht!");
				return true;
			}

		}

		return false;
	}

	public boolean wetter(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/w"################
		// ##################################
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(
					ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Bitte Wetter eingeben: /w [Wetter]");
			player.sendMessage(
					ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Wetter: \"sonne\", \"regen\", \"sturm\"");
			return true;
		} else if (args.length >= 1) {
			World world = player.getWorld();
			int zeitraum = 10000; 
			if (args.length == 2)
			{
				try
				{
					zeitraum = Integer.parseInt(args[1]);
					return true;
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Diesen Zeitraum kenne ich nicht!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("sonne")) {
				world.setThundering(false);
				world.setStorm(false);
				world.setWeatherDuration(zeitraum);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.GREEN + "Jetzt kommt "
						+ ChatColor.DARK_GREEN + "Sonne" + ChatColor.GREEN + ".");
				return true;
			} else if (args[0].equalsIgnoreCase("regen")) {
				world.setThundering(false);
				world.setStorm(true);
				world.setWeatherDuration(zeitraum);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.GREEN + "Jetzt kommt "
						+ ChatColor.DARK_GREEN + "Regen" + ChatColor.GREEN + ".");
				return true;
			} else if (args[0].equalsIgnoreCase("sturm")) {
				world.setThundering(true);
				world.setStorm(true);
				world.setWeatherDuration(zeitraum);
				int sturmZeit = (int) (zeitraum*0.8);
				world.setThunderDuration(sturmZeit);
				player.sendMessage(ChatColor.GOLD + "[MyEssentials] " + ChatColor.GREEN + "Jetzt kommt ein "
						+ ChatColor.DARK_GREEN + "Gewitter" + ChatColor.GREEN + ".");
				return true;
			}
			player.sendMessage(
					ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Bitte Wetter eingeben: /w [Wetter]");
			player.sendMessage(
					ChatColor.GOLD + "[MyEssentials] " + ChatColor.RED + "Wetter: \"sonne\", \"regen\", \"sturm\"");
		}
		return false;
	}

}
