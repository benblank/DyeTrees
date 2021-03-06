/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DyeTrees.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.Registry.ReikaDyeHelper;
import Reika.DragonAPI.Libraries.Registry.ReikaItemHelper;
import Reika.DragonAPI.Libraries.Registry.ReikaPlantHelper;
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper;
import Reika.DyeTrees.DyeTrees;
import Reika.DyeTrees.Registry.DyeBlocks;
import Reika.DyeTrees.World.TreeShaper;

public class BlockDyeSapling extends BlockSapling {

	private Icon icon;

	private static Random r = new Random();

	public BlockDyeSapling(int par1) {
		super(par1);
		this.setCreativeTab(DyeTrees.dyeTreeTab);
		this.setStepSound(Block.soundGrassFootstep);
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random r) {
		if (this.canGrowAt(world, x, y, z))
			this.growTree(world, x, y, z, this.getGrowthHeight());
	}

	private int getGrowthHeight() {
		return 5+r.nextInt(3);
	}

	public void growTree(World world, int x, int y, int z, int h)
	{
		if (world.isRemote)
			return;
		int meta = world.getBlockMetadata(x, y, z);
		TreeShaper.getInstance().generateRandomWeightedTree(world, x, y, z, ReikaDyeHelper.dyes[meta], true);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int p6, float a, float b, float c) {
		ItemStack is = ep.getCurrentEquippedItem();
		if (is == null)
			return false;
		if (!ReikaItemHelper.matchStacks(is, ReikaDyeHelper.WHITE.getStackOf()))
			return false;
		int color = world.getBlockMetadata(x, y, z);
		if (this.canGrowAt(world, x, y, z))
			this.growTree(world, x, y, z, ep.isSneaking() ? 7 : this.getGrowthHeight());
		else
			world.spawnParticle("happyVillager", x+r.nextDouble(), y+r.nextDouble(), z+r.nextDouble(), 0, 0, 0);
		if (!ep.capabilities.isCreativeMode)
			is.stackSize--;
		return true;
	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return icon;
	}

	@Override
	public void registerIcons(IconRegister ico)
	{
		icon = ico.registerIcon("DyeTrees:sapling");
	}

	@Override
	public int getRenderColor(int dmg)
	{
		return ReikaDyeHelper.dyes[dmg].getColor();
	}

	@Override
	public int colorMultiplier(IBlockAccess iba, int x, int y, int z)
	{
		int dmg = iba.getBlockMetadata(x, y, z);
		return ReikaDyeHelper.dyes[dmg].getJavaColor().brighter().getRGB();
	}

	public static boolean canGrowAt(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		Block b = Block.blocksList[id];
		if (!ReikaPlantHelper.SAPLING.canPlantAt(world, x, y, z))
			return false;
		if (b instanceof BlockFluid)
			return false;
		for (int i = 0; i < 6; i++) {
			if (!ReikaWorldHelper.softBlocks(world, x, y+i, z) && !(i == 0 && id == DyeBlocks.SAPLING.getBlockID()))
				return false;
		}
		return world.getBlockLightValue(x, y, z) >= 9;
	}

	@Override
	public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		this.growTree(par1World, par2, par3, par4, par5Random);
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}

}
