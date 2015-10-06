package iceman11a.fuelcraft.block.fluid;
import iceman11a.fuelcraft.Fuelcraft;
import iceman11a.fuelcraft.reference.ReferenceNames;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidBaseFuelcraft extends BlockFluidClassic {

	protected Fluid fluid;
	protected String blockName;

	public BlockFluidBaseFuelcraft(String blockName, Fluid fluid)
	{
		this(blockName, fluid, Material.water);
	}

	public BlockFluidBaseFuelcraft(String blockName, Fluid fluid, Material material)
	{
		super(fluid, material);
		//verifyIsFluid(fluidName);
		this.fluid = fluid;
		this.setBlockName(blockName);
		this.setHardness(100.0f);
		this.setLightOpacity(3);
		this.setCreativeTab(Fuelcraft.tabFuelcraft);
	}

    @Override
    public Block setBlockName(String name)
    {
        this.blockName = name;
        return super.setBlockName(ReferenceNames.getPrefixedName(name));
    }

    /*
	private static Fluid verifyIsFluid(String fluidName)
	{
		Fluid fluid = FluidRegistry.getFluid(fluidName);

		if (fluid.canBePlacedInWorld() == true)
		{
			ReflectionHelper.setPrivateValue(Fluid.class, fluid, null, "block");
		}

		return fluid;
	}
*/
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.checkCanStay(world, x, y, z, world.rand);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		this.checkCanStay(world, x, y, z, rand);
		super.updateTick(world, x, y, z, rand);
	}

	@Override
	public String getUnlocalizedName()
	{
		return "fluid." + ReferenceNames.getPrefixedName(this.blockName);
	}

	protected void checkCanStay(World world, int x, int y, int z, Random rand)
	{
	}
}
