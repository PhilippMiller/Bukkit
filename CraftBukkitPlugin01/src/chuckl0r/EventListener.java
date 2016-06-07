package chuckl0r;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.Directional;

import net.minecraft.server.v1_9_R2.PlayerInteractManager;

public class EventListener implements Listener {

	public EventListener(MyEssentials plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
	public void onRedstone(BlockRedstoneEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) b.getState();
			String signLine0 = sign.getLine(0);
			
			if (signLine0.equalsIgnoreCase("[Gate]")) {
				gateFunktionality(e);
			} else if (signLine0.equalsIgnoreCase("[Redstone]")) {
				redStoneAtXYZFunktionality(e, sign);
			}
		}
	}

	private void redStoneAtXYZFunktionality(BlockRedstoneEvent e, Sign sign) {
		String xyz = sign.getLine(2).trim();
		String splittedXYZ[] = xyz.split(",");
		String x = splittedXYZ[0];
		String y = splittedXYZ[0];
		String z = splittedXYZ[0];
		
		try {
			int CorX = Integer.parseInt(x);
			int CorY = Integer.parseInt(y);
			int CorZ = Integer.parseInt(z);
			
			World world = e.getBlock().getWorld();
			Block block = world.getBlockAt(CorX, CorY, CorX);
			if (block.getType() != Material.AIR) {
				
			}
			sign.setLine(4, x + y + z);
			sign.update();
			
		} catch (NumberFormatException ex) {
			sign.setLine(2, "X,Y,Z");
			sign.setLine(4, "Y");
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
											Bukkit.broadcastMessage("(1) NextBlock @" + face + " X: " + nextBlock.getX()
													+ " Y: " + nextBlock.getY() + " Z: " + nextBlock.getZ());
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
											Bukkit.broadcastMessage("(2) NextBlock @" + face + " X: " + nextBlock.getX()
													+ " Y: " + nextBlock.getY() + " Z: " + nextBlock.getZ());
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
											Bukkit.broadcastMessage("(3) NextBlock @" + face + " X: " + nextBlock.getX()
													+ " Y: " + nextBlock.getY() + " Z: " + nextBlock.getZ());
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
