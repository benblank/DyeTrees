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

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import Reika.DragonAPI.Libraries.IO.ReikaColorAPI;
import Reika.DragonAPI.Libraries.Registry.ReikaDyeHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeRainbowForest extends BiomeGenBase {

	Random rand = new Random();

	public BiomeRainbowForest(int id) {
		super(id);
		this.setColor(16711935);
		this.setBiomeName("Rainbow Forest");
		this.func_76733_a(5159473); //not used in 1.5.2
		this.setTemperatureRainfall(0.7F, 0.8F);
		//spawnableWaterCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 4, 4));

		//topBlock = (byte)DyeBlocks.GRASS.getBlockID();
	}

	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return new RainbowForestDecorator(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getModdedBiomeGrassColor(int original)
	{
		Color c = ReikaDyeHelper.dyes[rand.nextInt(16)].getJavaColor();
		double bias = 0.0005;
		double d = 1D-bias;
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int r2 = ReikaColorAPI.getRedFromInteger(original);
		int g2 = ReikaColorAPI.getGreenFromInteger(original);
		int b2 = ReikaColorAPI.getBlueFromInteger(original);
		return new Color((int)(r2*d+bias*r), (int)(g2*d+bias*g), (int)(b2*d+bias*b)).getRGB();
	}

	@Override
	public int getBiomeGrassColor() //use dyeGrass block, with metadata
	{
		int original = BiomeGenBase.forest.getBiomeGrassColor();
		int x = 0;
		int y = 2;
		int z = 0;
		Color c = ReikaDyeHelper.dyes[(Math.abs(x/16)+y+Math.abs(z/16))%16].getJavaColor().brighter();
		double bias = 0.00095;
		double d = 1D-bias;
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int r2 = ReikaColorAPI.getRedFromInteger(original);
		int g2 = ReikaColorAPI.getGreenFromInteger(original);
		int b2 = ReikaColorAPI.getBlueFromInteger(original);
		return new Color((int)(r2*d+bias*r), (int)(g2*d+bias*g), (int)(b2*d+bias*b)).getRGB();
	}

	@Override
	public float getSpawningChance()
	{
		return 0.6F;
	}

}
