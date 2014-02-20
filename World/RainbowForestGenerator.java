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

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Libraries.Java.ReikaRandomHelper;
import Reika.DragonAPI.Libraries.Registry.ReikaDyeHelper;
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper;
import Reika.DragonAPI.ModInteract.ThaumBlockHandler;
import Reika.DyeTrees.Blocks.BlockDyeSapling;
import Reika.DyeTrees.Registry.DyeOptions;

public class RainbowForestGenerator extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (ColorTreeGenerator.canGenerateTree(world, x, z) && BlockDyeSapling.canGrowAt(world, x, y, z)) {
			//ColorTreeGenerator.growTree(world, x, y, z, 5+random.nextInt(3), random, this.getColor(x, y, z));
			//TreeShaper.getInstance().generateTallTree(world, x, y, z);
			ReikaDyeHelper color = this.getColor(x, y, z);
			if (random.nextInt(10) == 0) {
				if (RainbowTreeGenerator.getInstance().checkRainbowTreeSpace(world, x, y, z)) {
					RainbowTreeGenerator.getInstance().generateRainbowTree(world, x, y, z);
					if (ModList.THAUMCRAFT.isLoaded() && DyeOptions.ETHEREAL.getState()) {
						for (int i = 0; i < 8; i++) {
							int dx = ReikaRandomHelper.getRandomPlusMinus(x, 6);
							int dz = ReikaRandomHelper.getRandomPlusMinus(z, 6);
							int dy = world.getTopSolidOrLiquidBlock(dx, dz);
							if (ReikaWorldHelper.softBlocks(world, dx, dy, dz)) {
								int id = ThaumBlockHandler.getInstance().plantID;
								int meta = ThaumBlockHandler.getInstance().etherealMeta;
								world.setBlock(dx, dy, dz, id, meta, 3);
								world.updateAllLightTypes(dx, dy, dz);
								world.markBlockForRenderUpdate(dx, dy, dz);
							}
						}
					}
				}
				else {
					TreeShaper.getInstance().generateRandomWeightedTree(world, x, y, z, color, false);
				}
			}
			else {
				TreeShaper.getInstance().generateRandomWeightedTree(world, x, y, z, color, false);
			}
			return true;
		}
		return false;
	}

	public static ReikaDyeHelper getColor(int x, int y, int z) {
		double[] color = new double[3];
		int sc = 32;
		int vsc = 64;
		int hexcolor;
		return ReikaDyeHelper.dyes[(Math.abs(x/16)+y+Math.abs(z/16))%16];
	}

}
