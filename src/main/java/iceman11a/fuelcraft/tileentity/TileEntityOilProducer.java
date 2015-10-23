package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.config.Configs;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.gui.GuiOilProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityOilProducer extends TileEntityFluidProcessor
{
	public static int outputFluidProductionRate = 3; // mB per tick; 3 * 20 = 60 mB per second

	public TileEntityOilProducer() {
		super(ReferenceNames.NAME_TILE_OIL_PRODUCER, FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL));
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (this.worldObj.isRemote == false) {
			this.processFluids(Configs.oilProducerEnergyPerOilBucket, Configs.oilProducerTapoilPerOilBucket, outputFluidProductionRate);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiOilProducer(this.getContainer(inventoryPlayer), this);
	}
}
