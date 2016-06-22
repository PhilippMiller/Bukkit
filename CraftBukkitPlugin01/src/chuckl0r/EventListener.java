package chuckl0r;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.Directional;

public class EventListener implements Listener {

	public EventListener(MyEssentials plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onLoseFoode(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		if (player.isInvulnerable())
			event.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractWithRod(PlayerInteractEvent e) {
		Player player = (Player) e.getPlayer();
		if (player.isInvulnerable()) {
			if (player.getItemInHand().getType() == Material.BLAZE_ROD) {
				Block block = player.getTargetBlock((HashSet<Byte>) null, 100);
				player.getWorld().strikeLightningEffect(block.getLocation());
	
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (player.getLastPlayed() == 0) {
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN
					+ "Willkommen auf " + player.getServer().getServerName() + ChatColor.DARK_GREEN + player.getName());
		} else {
			SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.YYYY");
			SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
			player.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GREEN + "Willkommen "
					+ ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN + ", dein letzter Besuch war am "
					+ sdf_date.format(player.getLastPlayed()) + " um " + sdf_time.format(player.getLastPlayed()));
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignEdit(SignChangeEvent e) {
		Bukkit.broadcastMessage(e.getLine(0));
		if (e.getLine(0).equalsIgnoreCase("[Redstone]")) {
			e.setLine(0, ChatColor.DARK_RED + "[RedStone]");

			String xyz = e.getLine(1).trim();
			String splittedXYZ[] = xyz.split(",");
			String x = splittedXYZ[0];
			String y = splittedXYZ[1];
			String z = splittedXYZ[2];
			try {
				if (x.equals("") || y.equals("") || z.equals(""))
					throw new NumberFormatException();

				int CorX = Integer.parseInt(x);
				int CorY = Integer.parseInt(y);
				int CorZ = Integer.parseInt(z);

				World world = e.getBlock().getWorld();
				Block block = world.getBlockAt(CorX, CorY, CorZ);

				if (block.getType() != Material.AIR && block.getType() != Material.REDSTONE_TORCH_ON) {
					e.setLine(1, x + "," + y + "," + z);
					e.setLine(3, ChatColor.RED + "[OFF] " + ChatColor.DARK_GRAY + "Blocked!");
				} else {
					e.setLine(1, x + "," + y + "," + z);
					e.setLine(3, ChatColor.RED + "[OFF]");
				}

				e.getPlayer()
						.sendMessage(ChatColor.GOLD + "[" + MyEssentials.pluginName + "]: " + ChatColor.GRAY
								+ "Erfolgreich " + ChatColor.DARK_RED + "[RedStone]" + ChatColor.GRAY + " Schild für "
								+ ChatColor.DARK_RED + "X:" + x + ChatColor.GRAY + "," + ChatColor.DARK_RED + " Y:" + y
								+ ChatColor.GRAY + "," + ChatColor.DARK_RED + " Z:" + z + ChatColor.RESET
								+ ChatColor.GRAY + " erstellt.");
			} catch (NumberFormatException ex) {
				e.setLine(1, "X,Y,Z");
				e.setLine(2, "");
				e.setLine(3, ChatColor.RED + "FAIL");
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onRedstone(BlockRedstoneEvent e) {
		Block b = e.getBlock();
		ArrayList<Block> blocksAround = new ArrayList<Block>();
		blocksAround.add(b);

		ArrayList<BlockFace> blockFaces = new ArrayList<BlockFace>();
		blockFaces.add(BlockFace.UP);
		blockFaces.add(BlockFace.NORTH);
		blockFaces.add(BlockFace.EAST);
		blockFaces.add(BlockFace.SOUTH);
		blockFaces.add(BlockFace.WEST);

		ArrayList<Sign> signList = new ArrayList<Sign>();

		for (Block block : blocksAround) {
			for (BlockFace face : blockFaces) {
				Block meanedBlock = block.getRelative(face);
				if (meanedBlock.getType() == Material.SIGN || meanedBlock.getType() == Material.SIGN_POST
						|| meanedBlock.getType() == Material.WALL_SIGN) {
					signList.add((Sign) meanedBlock.getState());
				}
			}
		}

		if (!signList.isEmpty()) {
			for (Sign sign : signList) {
				String signLine0 = sign.getLine(0);
				if (signLine0.equalsIgnoreCase("[Gate]")) {
					gateFunktionality(e);
				} else if (signLine0.equalsIgnoreCase("[Redstone]")
						|| signLine0.equalsIgnoreCase(ChatColor.DARK_RED + "[Redstone]")) {
					redStoneAtXYZFunktionality(e, sign);
				}
			}
		}
	}

	private void redStoneAtXYZFunktionality(BlockRedstoneEvent e, Sign sign) {
		String xyz = sign.getLine(1).trim();
		String splittedXYZ[] = xyz.split(",");
		String x = splittedXYZ[0];
		String y = splittedXYZ[1];
		String z = splittedXYZ[2];
		try {
			if (x.equals("") || y.equals("") || z.equals(""))
				throw new NumberFormatException();

			int CorX = Integer.parseInt(x);
			int CorY = Integer.parseInt(y);
			int CorZ = Integer.parseInt(z);

			World world = e.getBlock().getWorld();
			Block block = world.getBlockAt(CorX, CorY, CorZ);

			if (e.getNewCurrent() >= 1 && e.getOldCurrent() == 0) {
				if (block.getType() != Material.AIR && block.getType() != Material.REDSTONE_TORCH_ON) {
					sign.setLine(0, ChatColor.DARK_RED + "[RedStone]");
					sign.setLine(1, x + "," + y + "," + z);
					sign.setLine(3, ChatColor.RED + "OFF" + ChatColor.DARK_GRAY + "Block im Weg!");
				} else {
					block.setType(Material.REDSTONE_TORCH_ON);
					block.getState().update();

					if (!sign.getLine(0).equalsIgnoreCase(ChatColor.DARK_RED + "[RedStone]"))
						sign.setLine(0, ChatColor.DARK_RED + "[RedStone]");
					sign.setLine(3, ChatColor.GREEN + "[ON]");
				}
			} else {
				if (block.getType() == Material.REDSTONE_TORCH_ON) {
					block.setType(Material.AIR);
					block.getState().update();

					sign.setLine(3, ChatColor.RED + "[OFF]");
				}
			}
			sign.update();
		} catch (NumberFormatException ex) {
			sign.setLine(1, "X,Y,Z");
			sign.setLine(2, "");
			sign.setLine(3, ChatColor.RED + "FAIL");
			sign.update();
		}
	}

	private void gateFunktionality(BlockRedstoneEvent e) {
		Block b = e.getBlock();
		ArrayList<Block> blockHitList = new ArrayList<Block>();

		ArrayList<BlockFace> blockFaces = new ArrayList<BlockFace>();
		blockFaces.add(BlockFace.UP);
		blockFaces.add(BlockFace.DOWN);
		blockFaces.add(BlockFace.NORTH);
		blockFaces.add(BlockFace.EAST);
		blockFaces.add(BlockFace.SOUTH);
		blockFaces.add(BlockFace.WEST);

		ArrayList<BlockFace> blockFaces_NESW = new ArrayList<BlockFace>();
		blockFaces_NESW.add(BlockFace.NORTH);
		blockFaces_NESW.add(BlockFace.EAST);
		blockFaces_NESW.add(BlockFace.SOUTH);
		blockFaces_NESW.add(BlockFace.WEST);

		for (BlockFace face : blockFaces) {
			if (b.getRelative(face).getType() == Material.SIGN || b.getRelative(face).getType() == Material.SIGN_POST
					|| b.getRelative(face).getType() == Material.WALL_SIGN) {
				blockHitList.add(b.getRelative(face));
			}
		}

		for (Block block : blockHitList) {
			Sign sign = (Sign) block.getState();
			if (sign.getType() == Material.SIGN || sign.getType() == Material.SIGN_POST
					|| sign.getType() == Material.WALL_SIGN) {
				if (sign.getLine(0).equalsIgnoreCase("[GATE]")) {
					BlockFace blockFacing = getBlockFace(block);
					BlockFace newBlockFacing;
					switch (blockFacing) {
					case NORTH:
						newBlockFacing = BlockFace.SOUTH;
						break;
					case EAST:
						newBlockFacing = BlockFace.WEST;
						break;
					case SOUTH:
						newBlockFacing = BlockFace.NORTH;
						break;
					case WEST:
						newBlockFacing = BlockFace.EAST;
						break;
					default:
						newBlockFacing = BlockFace.NORTH;
					}

					ArrayList<Block> blocksAround = new ArrayList<Block>();

					if (block.getType() == Material.WALL_SIGN) {
						Block backBlock = block.getRelative(newBlockFacing);
						if (backBlock.getType() == Material.FENCE) {
							for (BlockFace face : blockFaces_NESW) {
								if (backBlock.getRelative(face).getType() == Material.FENCE) {
									blocksAround.add(backBlock.getRelative(face));
								}
							}
							ArrayList<Block> moreBlocksAround = new ArrayList<Block>();
							for (Block anotherBlock : blocksAround) {
								moreBlocksAround.add(anotherBlock);
							}

							for (Block anotherBlock : moreBlocksAround) {
								for (BlockFace face : blockFaces_NESW) {

									if (anotherBlock.getRelative(face).getType() == Material.FENCE) {
										blocksAround.add(anotherBlock.getRelative(face));
										Block nextBlock = anotherBlock.getRelative(face);
										while (nextBlock.getType() == Material.FENCE) {
											blocksAround.add(nextBlock.getRelative(face));
											nextBlock = nextBlock.getRelative(face);
										}
									}
								}
							}
						} else if (backBlock.getRelative(BlockFace.DOWN).getType() == Material.FENCE) {
							backBlock = backBlock.getRelative(BlockFace.DOWN);
							for (BlockFace face : blockFaces_NESW) {
								if (backBlock.getRelative(face).getType() == Material.FENCE) {
									blocksAround.add(backBlock.getRelative(face));
								}
							}
							ArrayList<Block> moreBlocksAround = new ArrayList<Block>();
							for (Block anotherBlock : blocksAround) {
								moreBlocksAround.add(anotherBlock);
							}

							for (Block anotherBlock : moreBlocksAround) {
								for (BlockFace face : blockFaces_NESW) {
									if (anotherBlock.getRelative(face).getType() == Material.FENCE) {
										blocksAround.add(anotherBlock.getRelative(face));
										Block nextBlock = anotherBlock.getRelative(face);
										while (nextBlock.getType() == Material.FENCE) {
											blocksAround.add(nextBlock.getRelative(face));
											nextBlock = nextBlock.getRelative(face);
										}
									}
								}
							}
						}
					} else if (block.getType() == Material.SIGN_POST) {
						if (block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.FENCE) {
							Block theFoundBlock = block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
							blocksAround.add(theFoundBlock);
							for (BlockFace face : blockFaces_NESW) {
								if (theFoundBlock.getRelative(face).getType() == Material.FENCE) {
									blocksAround.add(theFoundBlock.getRelative(face));
								}
							}
							ArrayList<Block> moreBlocksAround = new ArrayList<Block>();
							for (Block anotherBlock : blocksAround) {
								moreBlocksAround.add(anotherBlock);
							}

							for (Block anotherBlock : moreBlocksAround) {
								for (BlockFace face : blockFaces_NESW) {
									if (anotherBlock.getRelative(face).getType() == Material.FENCE) {
										blocksAround.add(anotherBlock.getRelative(face));
										Block nextBlock = anotherBlock.getRelative(face);
										while (nextBlock.getType() == Material.FENCE) {
											blocksAround.add(nextBlock.getRelative(face));
											nextBlock = nextBlock.getRelative(face);
										}
									}
								}
							}

						}
					}

					/*
					 * Check Redstone Value 1 = On | 0 = Off
					 */
					if (e.getNewCurrent() >= 1 && e.getOldCurrent() == 0) {
						if (blocksAround.size() > 0) {
							for (Block aBlock : blocksAround) {
								Block deeperBlock = aBlock;
								for (int i = 0; i < 10; i++) {
									if (deeperBlock.getRelative(BlockFace.DOWN).getType() == Material.AIR) {
										deeperBlock.getRelative(BlockFace.DOWN).setType(Material.FENCE);
										deeperBlock.getRelative(BlockFace.DOWN).getState().update();

										deeperBlock = deeperBlock.getRelative(BlockFace.DOWN);
									} else if (deeperBlock.getRelative(BlockFace.DOWN).getType() == Material.FENCE) {
										deeperBlock = deeperBlock.getRelative(BlockFace.DOWN);
									} else {
										break;
									}
								}
							}
						}
					} else if (e.getNewCurrent() == 0 && e.getOldCurrent() >= 1) {
						if (blocksAround.size() > 0) {

							int yMax = Integer.MIN_VALUE;
							int yMin = Integer.MAX_VALUE;

							for (Block aBlock : blocksAround) {
								Block deeperBlock = aBlock;
								while (deeperBlock.getRelative(BlockFace.DOWN).getType() == Material.FENCE) {
									if (deeperBlock.getY() > yMax)
										yMax = deeperBlock.getY() - 1;
									else
										yMin = deeperBlock.getY() - 1;

									deeperBlock = deeperBlock.getRelative(BlockFace.DOWN);
								}
							}

							for (int i = yMin; i <= yMax; i++) {
								for (Block aBlock : blocksAround) {
									Block deeperBlock = aBlock;
									while (deeperBlock.getRelative(BlockFace.DOWN).getType() == Material.FENCE) {
										deeperBlock = deeperBlock.getRelative(BlockFace.DOWN);
									}
									if (deeperBlock.getY() == i) {
										deeperBlock.setType(Material.AIR);
										deeperBlock.getState().update();
									} else {
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private BlockFace getBlockFace(Block b) {
		return ((Directional) b.getType().getNewData(b.getData())).getFacing();
	}

}
