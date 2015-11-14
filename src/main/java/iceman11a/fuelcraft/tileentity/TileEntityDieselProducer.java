package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.config.Configs;
import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityDieselProducer extends TileEntityFluidProcessor
{
	public static int outputFluidProductionRate = 3; // mB per tick; 3 * 20 = 60 mB per second

	public TileEntityDieselProducer() {
		super(ReferenceNames.NAME_TILE_DIESEL_PRODUCER, FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_DIESEL));
		this.itemStacks = new ItemStack[5];
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (this.worldObj.isRemote == false) {
			this.processFluids(Configs.dieselProducerEnergyPerDieselBucket, Configs.dieselProducerOilPerDieselBucket, outputFluidProductionRate);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiDieselProducer(this.getContainer(inventoryPlayer), this);
	}
}
