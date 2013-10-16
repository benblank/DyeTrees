/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DyeTrees.World;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.Registry.ReikaDyeHelper;
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper;
import Reika.DyeTrees.Registry.DyeBlocks;

public class TreeShaper {

	public static final int LUMPY_CHANCE = 10;
	public static final int TALL_CHANCE = 30;
	public static final int NORMAL_CHANCE = 60;

	private static final int leafID = DyeBlocks.DECAY.getBlockID();

	private static final TreeShaper instance = new TreeShaper();

	private final Random rand = new Random();

	private TreeShaper() {
		this.initialize();
	}

	public static TreeShaper getInstance() {
		return instance;
	}

	public void initialize() {

	}

	public void generateRandomWeightedTree(World world, int x, int y, int z, ReikaDyeHelper color) {
		int val = rand.nextInt(100);
		int chance = LUMPY_CHANCE;
		if (val < chance) {
			this.generateLumpyTree(world, x, y, z, color);
			return;
		}
		chance += TALL_CHANCE;
		if (val < chance) {
			this.generateTallTree(world, x, y, z, color);
			return;
		}
		this.generateNormalTree(world, x, y, z, color);
	}

	public void generateNormalTree(World world, int x, int y, int z, ReikaDyeHelper color) {
		if (ColorTreeGenerator.canGenerateTree(world, x, z)) {
			int meta = color.ordinal();
			int log = rand.nextInt(4);
			int w = 2;
			int h = 5+rand.nextInt(3);

			for (int i = 0; i < h; i++) {
				world.setBlock(x, y+i, z, Block.wood.blockID, log, 3);
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-3, z+j)) {
						world.setBlock(x+i, y+h-3, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-2, z+j)) {
						world.setBlock(x+i, y+h-2, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-1, z+j)) {
						world.setBlock(x+i, y+h-1, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i*j == 0) {
						if (this.canGenerateLeavesAt(world, x+i, y+h, z+j)) {
							world.setBlock(x+i, y+h, z+j, leafID, meta, 3);
						}
					}
				}
			}
		}
	}

	public void generateTallTree(World world, int x, int y, int z, ReikaDyeHelper color) {
		if (ColorTreeGenerator.canGenerateTree(world, x, z)) {
			int h = 10+rand.nextInt(3);
			int log = rand.nextInt(4);
			int meta = color.ordinal();
			int w = 2;

			for (int i = 0; i < h; i++) {
				world.setBlock(x, y+i, z, Block.wood.blockID, log, 3);
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i*j == 0)
						if (this.canGenerateLeavesAt(world, x+i, y+h-8, z+j)) {
							world.setBlock(x+i, y+h-8, z+j, leafID, meta, 3);
						}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-7, z+j)) {
						world.setBlock(x+i, y+h-7, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (i*j != w*w && i*j != -w*w)
						if (this.canGenerateLeavesAt(world, x+i, y+h-6, z+j)) {
							world.setBlock(x+i, y+h-6, z+j, leafID, meta, 3);
						}
				}
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (i*j != w*w && i*j != -w*w)
						if (this.canGenerateLeavesAt(world, x+i, y+h-5, z+j)) {
							world.setBlock(x+i, y+h-5, z+j, leafID, meta, 3);
						}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-4, z+j)) {
						world.setBlock(x+i, y+h-4, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (i*j != w*w && i*j != -w*w)
						if (this.canGenerateLeavesAt(world, x+i, y+h-3, z+j)) {
							world.setBlock(x+i, y+h-3, z+j, leafID, meta, 3);
						}
				}
			}
			for (int i = -w; i <= w; i++) {
				for (int j = -w; j <= w; j++) {
					if (i*j != w*w && i*j != -w*w)
						if (this.canGenerateLeavesAt(world, x+i, y+h-2, z+j)) {
							world.setBlock(x+i, y+h-2, z+j, leafID, meta, 3);
						}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (this.canGenerateLeavesAt(world, x+i, y+h-1, z+j)) {
						world.setBlock(x+i, y+h-1, z+j, leafID, meta, 3);
					}
				}
			}
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i*j == 0) {
						if (this.canGenerateLeavesAt(world, x+i, y+h, z+j)) {
							world.setBlock(x+i, y+h, z+j, leafID, meta, 3);
						}
					}
				}
			}
		}
	}

	public void generateLumpyTree(World world, int x, int y, int z, ReikaDyeHelper color) {
		if (ColorTreeGenerator.canGenerateTree(world, x, z)) {
			int h = 8+rand.nextInt(4);
			int log = rand.nextInt(4);
			int meta = color.ordinal();

			for (int i = 0; i < h; i++) {
				world.setBlock(x, y+i, z, Block.wood.blockID, log, 3);
			}

			for (int i = 1; i < 2; i++) {
				int dx = x+i;
				int dy = y+h-2;
				int dz = z;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x-i;
				dz = z;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x;
				dz = z-i;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x;
				dz = z+i;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x+i;
				dy = y+h-6;
				dz = z;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x-i;
				dz = z;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x;
				dz = z-i;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}

				dx = x;
				dz = z+i;
				world.setBlock(dx, dy, dz, Block.wood.blockID, log, 3);
				for (int j = -1; j <= 1; j++) {
					for (int k = -1; k <= 1; k++) {
						for (int m = -1; m <= 1; m++) {
							if (j*k*m == 0 && this.canGenerateLeavesAt(world, dx+j, dy+k, dz+m)) {
								world.setBlock(dx+j, dy+k, dz+m, leafID, meta, 3);
							}
						}
					}
				}
			}

			int dy = y+h-4;
			for (int i = 1; i < 2; i++) {
				if (this.canGenerateLeavesAt(world, x+i, dy, z)) {
					world.setBlock(x+i, dy, z, leafID, meta, 3);
				}
				if (this.canGenerateLeavesAt(world, x-i, dy, z)) {
					world.setBlock(x-i, dy, z, leafID, meta, 3);
				}
				if (this.canGenerateLeavesAt(world, x, dy, z+i)) {
					world.setBlock(x, dy, z+i, leafID, meta, 3);
				}
				if (this.canGenerateLeavesAt(world, x, dy, z-i)) {
					world.setBlock(x, dy, z-i, leafID, meta, 3);
				}
			}

			dy = y+h;
			for (int k = -1; k <= 1; k++) {
				for (int m = -1; m <= 1; m++) {
					if (k*m == 0 && this.canGenerateLeavesAt(world, x+k, dy, z+m)) {
						world.setBlock(x+k, dy, z+m, leafID, meta, 3);
					}
				}
			}
		}
	}

	private boolean canGenerateLeavesAt(World world, int x, int y, int z) {
		boolean soft = ReikaWorldHelper.softBlocks(world, x, y, z);
		boolean leaves = world.getBlockId(x, y, z) == Block.leaves.blockID;
		//ReikaJavaLibrary.pConsole(x+", "+y+", "+z, !soft && !leaves && world.getBlockId(x, y, z) != Block.wood.blockID);
		return soft || leaves;
	}

}