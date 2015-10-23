package iceman11a.fuelcraft.block.fluid;

import iceman11a.fuelcraft.Fuelcraft;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.reference.ReferenceTextures;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidTapoil extends BlockFluidBaseFuelcraft
{
	@SideOnly(Side.CLIENT)
	public IIcon iconFlow;

	public BlockFluidTapoil(Fluid fluid)
	{
		super(ReferenceNames.NAME_FLUID_TAPOIL, fluid);
		this.setCreativeTab(Fuelcraft.tabFuelcraft);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}

		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}

		return super.displaceIfPossible(world, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(ReferenceTextures.getFluidName(this.blockName + "_still"));
		this.iconFlow = iconRegister.registerIcon(ReferenceTextures.getFluidName(this.blockName + "_flow")); // TODO
		this.fluid.setIcons(this.blockIcon, this.iconFlow);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0 || side == 1)
		{
			return this.blockIcon;
		}

		return this.iconFlow;
	}
}
