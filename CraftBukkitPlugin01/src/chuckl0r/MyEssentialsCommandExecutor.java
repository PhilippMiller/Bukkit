package chuckl0r;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MyEssentialsCommandExecutor {
	private OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();

	// COMMAND AUFRUFER
	public boolean doCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// PLAYER CHECK
		Player player = (Player) sender;

		if (!(sender instanceof Player)) {
			player.sendMessage(ChatColor.RED + "Du musst ein Spieler sein!");
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("wispi")) {
			this.wispi(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("god")) {
			this.godMode(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("gmode")) {
			this.gmode(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("tm")) {
			this.timeManager(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("" + MyEssentials.pluginName + "")) {
			this.MyEssentails(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("g")) {
			this.give(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("w")) {
			this.wetter(sender, cmd, label, args);
		} else if (cmd.getName().equalsIgnoreCase("ws")) {
			if (args.length == 1) {
				String args0Inhalt = args[0];
				args = new String[2];
				args[1] = args0Inhalt;
			}
			if (args.length == 0) {
				args = new String[1];
			}
			args[0] = "sonne";
			this.wetter(sender, cmd, label, args);
		} else if (cmd.getName().equalsIgnoreCase("wr")) {
			if (args.length == 1) {
				String args0Inhalt = args[0];
				args = new String[2];
				args[1] = args0Inhalt;
			}
			if (args.length == 0) {
				args = new String[1];
			}
			args[0] = "regen";
			this.wetter(sender, cmd, label, args);
		} else if (cmd.getName().equalsIgnoreCase("wst")) {
			if (args.length == 1) {
				String args0Inhalt = args[0];
				args = new String[2];
				args[1] = args0Inhalt;
			}
			if (args.length == 0) {
				args = new String[1];
			}
			args[0] = "sturm";
			this.wetter(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("tel")) {
			this.teleport(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("h")) {
			this.heilen(sender, cmd, label, args);
		} else if (cmd.getName().equalsIgnoreCase("crazy")) {
			this.crazyName(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("repair")) {
			this.repair(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("glow")) {
			this.glowEffect(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("mob")) {
			this.mobSpawner(sender, cmd, label, args);
		}

		else if (cmd.getName().equalsIgnoreCase("fly")) {
			this.fly(sender, cmd, label, args);
		}

		// HIER WEITERE COMMANDS!

		return false;
	}

	// COMMANDS
	public boolean wispi(CommandSender sender, Command cmd, String label, String[] args) {

		// ############################
		// ######COMMAND "/wispi"######
		// ############################
		Player player = (Player) sender;
		OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();

		offlinePlayer.OfflinePlayerChecker(player, args[0]);
		Player otherPlayer = offlinePlayer.getOtherPlayer();

		if ((args.length >= 2) && (otherPlayer == null)) {
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "Der Spieler "
					+ ChatColor.DARK_RED + otherPlayer + ChatColor.RED + " ist nicht online!");
		}
		if (args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/wispi [name] [text]");
			return true;
		}

		if (otherPlayer != null) {
			String text = "";

			for (int i = 1; i < args.length; i++) {
				text = text + args[i] + " ";
			}

			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.AQUA + "WHISPER an "
					+ ChatColor.DARK_GRAY + args[0] + ": " + text);
			otherPlayer.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.AQUA
					+ "WHISPER von " + ChatColor.DARK_GRAY + player.getName() + ": " + text);
			return true;
		}

		return false;
	}

	public boolean godMode(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/god"############
		// ##################################
		Player player = (Player) sender;
		World world = player.getWorld();

		if (player.isInvulnerable()) {
			world.strikeLightningEffect(player.getLocation());
			player.setInvulnerable(false);
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Du bist nun "
					+ ChatColor.DARK_GREEN + "Sterblich" + ChatColor.GREEN + "!");
			return true;
		} else {
			player.setInvulnerable(true);
			world.strikeLightningEffect(player.getLocation());
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Du bist nun "
					+ ChatColor.DARK_GREEN + "Unverwundbar" + ChatColor.GREEN + "!");
			return true;
		}
	}

	public boolean gmode(CommandSender sender, Command cmd, String label, String[] args) {

		// ###########################
		// ######COMMAND "/gmode"#####
		// ###########################
		Player player = (Player) sender;

		if (args.length == 0) {
			GameMode gm = player.getGameMode();
			if (gm == GameMode.CREATIVE) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Du bist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
			} else {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Du bist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
			}
			return true;
		}

		if (args.length == 1) {
			GameMode gm = player.getGameMode();
			if (args[0].equalsIgnoreCase("creative") == true) {
				if (gm == GameMode.CREATIVE) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Du bist bereits im Creative Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Du bist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("survival") == true) {
				if (gm == GameMode.SURVIVAL) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Du bist bereits im Survival Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Du bist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("spectator")) {
				if (gm == GameMode.SPECTATOR) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Du bist bereits im Spectator Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Du bist nun im " + ChatColor.DARK_GREEN + "Spectator" + ChatColor.GREEN + " Modus!");
				}
			}

			OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
			offlinePlayer.OfflinePlayerChecker(sender, args[0]);
			Player otherPlayer = offlinePlayer.getOtherPlayer();
			if (otherPlayer != null) {
				GameMode gm_other = otherPlayer.getGameMode();
				if (gm_other == GameMode.CREATIVE) {
					otherPlayer.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.DARK_GREEN
							+ otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN
							+ "Survival" + ChatColor.GREEN + " Modus!");
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.DARK_GREEN
							+ otherPlayer.getName() + ChatColor.GREEN + " ist nun im " + ChatColor.DARK_GREEN
							+ "Creative" + ChatColor.GREEN + " Modus!");
				}
				return true;
			}

			if ((args[0].equalsIgnoreCase("survival") == false) && (args[0].equalsIgnoreCase("creative") == false)) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Mögliche GameModes: " + ChatColor.DARK_RED + "Creative , Survival");
				return true;
			}

		}

		OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
		offlinePlayer.OfflinePlayerChecker(sender, args[0]);
		Player otherPlayer = offlinePlayer.getOtherPlayer();

		if ((args.length >= 2) && (otherPlayer != null)) {
			GameMode gm = player.getGameMode();
			if (args[1].equalsIgnoreCase("creative") == true) {
				if (gm == GameMode.CREATIVE) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Der Spieler " + ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED
							+ " ist bereits im " + ChatColor.DARK_RED + "Creative" + ChatColor.RED + " Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Der Spieler " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN
							+ " ist nun im " + ChatColor.DARK_GREEN + "Creative" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if (args[1].equalsIgnoreCase("survival") == true) {
				if (gm == GameMode.SURVIVAL) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Der Spieler " + ChatColor.DARK_RED + otherPlayer.getName() + ChatColor.RED
							+ " ist bereits im " + ChatColor.DARK_RED + "Survival" + ChatColor.RED + " Modus!");
					return true;
				} else {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Der Spieler " + ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN
							+ " ist nun im " + ChatColor.DARK_GREEN + "Survival" + ChatColor.GREEN + " Modus!");
					return true;
				}
			}
			if ((args[1].equalsIgnoreCase("survival") == false) && (args[1].equalsIgnoreCase("creative") == false)) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Mögliche GameModes: " + ChatColor.DARK_RED + "Creative , Survival");
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
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/tm [time]");
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Für [time] kann " + ChatColor.DARK_RED + "\"tag\", \"nacht\" oder ein Ganzzahliger Wert "
					+ ChatColor.RED + "eingegeben werden.");
			return true;
		}
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("tag")) {
				world.setTime(1000);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Es ist "
						+ ChatColor.DARK_GREEN + "Tag!");
				return true;
			}
			if (args[0].equalsIgnoreCase("nacht")) {
				world.setTime(13000);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Es ist "
						+ ChatColor.DARK_GREEN + "Nacht!");
				return true;
			}

			int wert = 0;
			try {
				wert = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "Bitte "
						+ ChatColor.DARK_RED + "\"tag\",\"nacht\" oder einen Ganzzahligen Wert eingeben!");
				return true;
			}

			world.setTime(wert);
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
					+ "Die Zeit wurde auf " + ChatColor.DARK_GREEN + wert + ChatColor.GREEN + " gesetzt!");
			return true;
		}

		return false;

	}

	public boolean MyEssentails(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/" + MyEssentials.pluginName + ""#####
		// ##################################
		Player player = (Player) sender;

		ArrayList<String> list = new ArrayList<String>();
		list.add(Bukkit.getServer().getPluginManager().getPlugin("" + MyEssentials.pluginName + "").getDescription()
				.getName());
		list.addAll(Bukkit.getServer().getPluginManager().getPlugin("" + MyEssentials.pluginName + "").getDescription()
				.getAuthors());
		list.add(Bukkit.getServer().getPluginManager().getPlugin("" + MyEssentials.pluginName + "").getDescription()
				.getVersion());

		player.sendMessage(
				ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.YELLOW + "Plugin Information");
		for (String s : list) {
			player.sendMessage("=> " + s);
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean give(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/g"################
		// ##################################
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
					+ "Du musst eine ItemID angeben.");
		} else if (args.length == 1 || args.length == 2) {
			try {
				int itemId = Integer.parseInt(args[0]);
				int anzahl = 1;

				if (args.length >= 2) {
					anzahl = Integer.parseInt(args[1]);
				}

				player.getInventory().addItem(new ItemStack(itemId, anzahl));
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.GREEN
						+ "Deinem Inventar wurde " + ChatColor.DARK_GREEN + anzahl + "x " + itemId + ChatColor.GREEN
						+ " hinzugefügt!");

				return true;

			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
						+ "Diese ID kenne ich nicht!");
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
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
					+ "Bitte Wetter eingeben: /w [Wetter]");
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
					+ "Wetter: \"sonne\", \"regen\", \"sturm\"");
			return true;
		} else if (args.length >= 1) {
			World world = player.getWorld();
			int zeitraum = 10000;
			if (args.length == 2) {
				try {
					zeitraum = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
							+ "Diesen Zeitraum kenne ich nicht!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("sonne")) {
				world.setThundering(false);
				world.setStorm(false);
				world.setWeatherDuration(zeitraum);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.GREEN
						+ "Jetzt kommt " + ChatColor.DARK_GREEN + "Sonne" + ChatColor.GREEN + ".");
				return true;
			} else if (args[0].equalsIgnoreCase("regen")) {
				world.setThundering(false);
				world.setStorm(true);
				world.setWeatherDuration(zeitraum);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.GREEN
						+ "Jetzt kommt " + ChatColor.DARK_GREEN + "Regen" + ChatColor.GREEN + ".");
				return true;
			} else if (args[0].equalsIgnoreCase("sturm")) {
				world.setThundering(true);
				world.setStorm(true);
				world.setWeatherDuration(zeitraum);
				int sturmZeit = (int) (zeitraum * 0.8);
				world.setThunderDuration(sturmZeit);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.GREEN
						+ "Jetzt kommt ein " + ChatColor.DARK_GREEN + "Gewitter" + ChatColor.GREEN + ".");
				return true;
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
					+ "Bitte Wetter eingeben: /w [Wetter] <Zeitraum>");
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "] " + ChatColor.RED
					+ "Wetter: \"sonne\", \"regen\", \"sturm\"");
		}
		return false;
	}

	public boolean teleport(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/tel"##############
		// ##################################
		Player player = (Player) sender;
		OfflinePlayerCheck offlinePlayer = new OfflinePlayerCheck();
		if (args.length == 1) {
			offlinePlayer.OfflinePlayerChecker(sender, args[0]);
			Player otherPlayer = offlinePlayer.getOtherPlayer();
			if (otherPlayer != null) {
				float blockX = otherPlayer.getLocation().getBlockX();
				float blockY = otherPlayer.getLocation().getBlockY();
				float blockZ = otherPlayer.getLocation().getBlockZ();
				float blockPitch = otherPlayer.getLocation().getPitch();
				float blockYaw = otherPlayer.getLocation().getYaw();
				World w = otherPlayer.getWorld();

				Location loc = new Location(w, blockX, blockY, blockZ);
				loc.setPitch(blockPitch);
				loc.setYaw(blockYaw);

				player.teleport(loc);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Erfolgreich zur Position von " + ChatColor.DARK_GREEN + otherPlayer.getName()
						+ ChatColor.GREEN + " teleportiert." + ChatColor.GRAY + " @ X:" + (int) blockX + " Y:"
						+ (int) blockY + " Z:" + (int) blockZ);
				return true;
			} else {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Dieser Spieler ist leider nicht online!");
				return true;
			}
		} else if (args.length == 3) {
			try {
				float blockX = Float.parseFloat(args[0]);
				float blockY = Float.parseFloat(args[1]);
				float blockZ = Float.parseFloat(args[2]);
				float blockPitch = player.getLocation().getPitch();
				float blockYaw = player.getLocation().getYaw();
				World w = player.getWorld();

				Location loc = new Location(w, blockX, blockY, blockZ);
				loc.setPitch(blockPitch);
				loc.setYaw(blockYaw);

				player.teleport(loc);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Erfolgreich zur Position teleportiert." + ChatColor.GRAY + " @ X:" + (int) blockX + " Y:"
						+ (int) blockY + " Z:" + (int) blockZ);
				return true;
			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Dies sind keine gültigen Koordinaten!");
				player.sendMessage(
						ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "/tel [X] [Y] [Z]");
				return true;
			}
		} else if (args.length == 4) {
			offlinePlayer.OfflinePlayerChecker(sender, args[0]);
			Player otherPlayer = offlinePlayer.getOtherPlayer();
			if (otherPlayer != null) {

				try {
					float blockX = Float.parseFloat(args[1]);
					float blockY = Float.parseFloat(args[2]);
					float blockZ = Float.parseFloat(args[3]);
					float blockPitch = player.getLocation().getPitch();
					float blockYaw = player.getLocation().getYaw();
					World w = player.getWorld();

					Location loc = new Location(w, blockX, blockY, blockZ);
					loc.setPitch(blockPitch);
					loc.setYaw(blockYaw);

					player.teleport(loc);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Erfolgreich zur Position von " + ChatColor.DARK_GREEN + otherPlayer.getName()
							+ ChatColor.GREEN + " teleportiert." + ChatColor.GRAY + " @ X:" + (int) blockX + " Y:"
							+ (int) blockY + " Z:" + (int) blockZ);
					return true;
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Dies sind keine gültigen Koordinaten!");
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "/tel [User] [X] [Y] [Z]");
					return true;
				}
			} else {

			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Dieser Spieler ist leider nicht online!");
			return true;
		}

		return false;
	}

	public boolean heilen(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/h"################
		// ##################################
		Player player = (Player) sender;

		if (args.length == 0) {
			player.setHealth(20);
			player.setFoodLevel(20);
			player.sendMessage(
					ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Du hast dich geheilt.");
			return true;
		} else if (args.length >= 1) {
			offlinePlayer.OfflinePlayerChecker(sender, args[0]);
			Player otherPlayer = offlinePlayer.getOtherPlayer();

			if (otherPlayer != null) {
				otherPlayer.setHealth(20);
				otherPlayer.setFoodLevel(20);
				otherPlayer.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.DARK_GREEN
						+ player.getName() + ChatColor.GREEN + " hat dich geheilt!");
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Du hast "
						+ ChatColor.DARK_GREEN + otherPlayer.getName() + ChatColor.GREEN + " geheilt.");
				return true;
			} else {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Dieser Spieler ist leider nicht online!");
				return true;
			}
		}

		return false;
	}

	public boolean crazyName(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/crazy"############
		// ##################################
		Player player = (Player) sender;
		if (args.length == 0) {
			player.sendMessage(
					ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "/crazy [on | off]");
		} else if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("off")) {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Willkommen zurück " + ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN + ".");
				player.setDisplayName(player.getName());
				player.setPlayerListName(player.getName());
			} else if (args[0].equalsIgnoreCase("on")) {
				player.setDisplayName(ChatColor.MAGIC + player.getName() + ChatColor.RESET);
				player.setPlayerListName(ChatColor.MAGIC + player.getName() + ChatColor.RESET);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Bis bald " + ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN + ".");
			} else {
				player.sendMessage(
						ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "/crazy [on | off]");
			}
		}

		return false;
	}

	public boolean repair(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/repair"###########
		// ##################################
		Player player = (Player) sender;
		player.getInventory().getItemInMainHand().setDurability((short) 0);
		player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
				+ "Dein Item wurde erfolgreich " + ChatColor.DARK_GREEN + "repariert" + ChatColor.GREEN + "!");
		return true;
	}

	public boolean glowEffect(CommandSender sender, Command cmd, String label, String[] args) {
		// ##################################
		// ######COMMAND "/glow"###########
		// ##################################
		Player player = (Player) sender;
		if (args.length < 1) {
			if (player.hasPotionEffect(PotionEffectType.GLOWING)) {
					player.removePotionEffect(PotionEffectType.GLOWING);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
							+ "Glowing wurde " + ChatColor.DARK_GREEN + "deaktiviert" + ChatColor.GREEN + "!");
			} else {
				player.addPotionEffect(
						new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, Integer.MAX_VALUE));
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Glowing wurde " + ChatColor.DARK_GREEN + "aktiviert" + ChatColor.GREEN + "!");
			}
		} else {
			if (offlinePlayer.OfflinePlayerChecker(sender, args[0])) {
				Player otherPlayer = offlinePlayer.getOtherPlayer();
				if (otherPlayer.hasPotionEffect(PotionEffectType.GLOWING)) {
					otherPlayer.removePotionEffect(PotionEffectType.GLOWING);
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN
							+ " hat den Glowing effekt bei dir " + ChatColor.DARK_GREEN + "deaktiviert" + ChatColor.GREEN + "!");
				} else {
					otherPlayer.addPotionEffect(
							new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, Integer.MAX_VALUE));
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN
							+ " hat den Glowing effekt bei dir " + ChatColor.DARK_GREEN + "aktiviert" + ChatColor.GREEN + "!");
				}
			} else {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Der spieler " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "ist nicht online!");
				player.sendMessage(
						ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "/glow ([Spieler])");
			}
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean mobSpawner(CommandSender sender, Command cdm, String label, String[] args) {
		// ############################
		// ######COMMAND "/mob"######
		// ############################
		Player player = (Player) sender;
		Block block = player.getTargetBlock((HashSet<Byte>) null, 100);

		if (args.length < 1) {
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/mob <MOB NAME> [Anzahl]");
			player.sendMessage(ChatColor.AQUA + "Passive Mobs:");
			player.sendMessage("Bat, Chicken, Cow, Mushroom_Cow, Pig, Rabbit, Sheep, Squid, Villager");
			player.sendMessage("");
			player.sendMessage(ChatColor.GRAY + "Neutrale Mobs:");
			player.sendMessage("Cave_Spider, Enderman, Spider, Zombie_Pigman");
			player.sendMessage("");
			player.sendMessage(ChatColor.RED + "Agressive Mobs:");
			player.sendMessage(
					"Blaze, Creeper, Endermite, Ghast, Guardian, Magma_Cube, Shulker, Silverfish, Skeleton, Slime, Witch, Zombie");
			player.sendMessage("");
			player.sendMessage(ChatColor.DARK_GREEN + "Tame-bare Mobs:");
			player.sendMessage("Horse, Ocelot, Wolf");
			player.sendMessage("");
			player.sendMessage(ChatColor.YELLOW + "Utility Mobs:");
			player.sendMessage("Iron_Golem, Snow_Golem");
			player.sendMessage("");
			player.sendMessage(ChatColor.DARK_RED + "BOSS Mobs:");
			player.sendMessage("Ender_Dragon, Wither");
			player.sendMessage("");
			player.sendMessage(ChatColor.WHITE + "Ungenutze Mobs:");
			player.sendMessage("Giant");

			return false;
		}

		World world = player.getWorld();
		String entityName = args[0].toLowerCase();
		int count = 1;

		if (args.length > 1) {
			if (!args[1].equals("")) {
				try {
					count = Integer.parseInt(args[1]);
				} catch (Exception ex) {
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Die Zahl " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " kenne ich nicht!");
					player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
							+ "Der richtige Befehl lautet: " + ChatColor.DARK_RED + "/mob <MOB NAME> [Anzahl]");
					count = 1;
				}
			}
		}

		switch (entityName) {
		// Passive Mobs (Bat, Chicken, Cow, Mushroom_Cow, Pig, Rabbit, Sheep,
		// Squid, Villager)
		case "bat":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.BAT);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "chicken":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.CHICKEN);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "cow":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.COW);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "mushroom_cow":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.MUSHROOM_COW);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "pig":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.PIG);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "rabbit":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.RABBIT);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "sheep":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SHEEP);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "squid":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SQUID);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "villager":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.VILLAGER);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// Neutrale Mobs (Cave_Spider, Enderman, Spider, Zombie_Pigman)
		case "cave_spider":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.CAVE_SPIDER);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "enderman":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.ENDERMAN);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "spider":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SPIDER);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "zombie_pigman":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.PIG_ZOMBIE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// Agressive Mobs (Blaze, Creeper, Endermite, Ghast, Guardian,
		// Magma_Cube, Shulker, Silverfish, Skeleton, Slime, Witch, Zombie)
		case "blaze":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.BLAZE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "creeper":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.CREEPER);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "endermite":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.ENDERMITE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "ghast":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.GHAST);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "guardian":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.GUARDIAN);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "magma_cube":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.MAGMA_CUBE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "shulker":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.MAGMA_CUBE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "silverfish":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SILVERFISH);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "skeleton":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SKELETON);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "slime":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SLIME);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "witch":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.WITCH);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "zombie":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.ZOMBIE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// Tame-bare Mobs (Horse, Ocelot, Wolf)
		case "horse":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.HORSE);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "ocelot":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.OCELOT);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "wolf":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.WOLF);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// Utility Mobs (Iron_Golem, Snow_Golem)
		case "iron_golem":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.IRON_GOLEM);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "snow_golem":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.SNOWMAN);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// BOSS Mobs (Ender_Dragon, Wither)
		case "ender_dragon":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.ENDER_DRAGON);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		case "wither":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.WITHER);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// Ungenutze Mobs (Giant)
		case "giant":
			for (int i = 0; i < count; i++) {
				world.spawnEntity(block.getLocation(), EntityType.GIANT);
			}
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Ich rufe: "
					+ ChatColor.DARK_GREEN + count + "x " + entityName + ChatColor.GREEN + "!");
			break;
		// DEFAULT IF THE TYPE IS UNKNOWN!
		default:
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
					+ "Dieses Wesen ist mir " + ChatColor.DARK_RED + "unbekannt" + ChatColor.RED + "!");
			break;
		}

		return true;
	}

	public boolean fly(CommandSender sender, Command cmd, String label, String[] args) {
		// ############################
		// ######COMMAND "/fly"######
		// ############################
		Player player = (Player) sender;

		if (args.length < 1) {
			if (!player.getAllowFlight()) {
				player.setAllowFlight(true);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Du darfst nun " + ChatColor.DARK_GREEN + "fliegen" + ChatColor.GREEN + "!");
			} else {
				player.setFlying(false);
				player.setAllowFlight(false);
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
						+ "Du darfst jetzt " + ChatColor.UNDERLINE + "nicht" + ChatColor.RESET + ChatColor.GREEN
						+ " mehr " + ChatColor.DARK_GREEN + "fliegen" + ChatColor.GREEN + "!");
			}
		} else {
			if (offlinePlayer.OfflinePlayerChecker(sender, args[0])) {
				Player otherPlayer = offlinePlayer.getOtherPlayer();
				if (!otherPlayer.getAllowFlight()) {
					otherPlayer.setAllowFlight(true);
					otherPlayer.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: "
							+ ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN + " erlaubt dir nun zu "
							+ ChatColor.DARK_GREEN + "fliegen" + ChatColor.GREEN + "!");
				} else {
					otherPlayer.setFlying(false);
					otherPlayer.setAllowFlight(false);
					otherPlayer.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: "
							+ ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN + " erlaubt dir nun "
							+ ChatColor.UNDERLINE + "nicht" + ChatColor.RESET + ChatColor.GREEN + " mehr zu "
							+ ChatColor.DARK_GREEN + "fliegen" + ChatColor.GREEN + "!");
				}
			} else {
				player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED
						+ "Der spieler " + ChatColor.DARK_RED + args[0] + ChatColor.RED + "ist nicht online!");
				player.sendMessage(
						ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.RED + "/fly ([Spieler])");
			}
		}

		return true;
	}
}
