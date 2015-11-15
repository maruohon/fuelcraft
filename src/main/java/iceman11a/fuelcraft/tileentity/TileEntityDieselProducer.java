package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.config.Configs;
import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
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

	/**
	 * Converts input fluid into output fluid, if there is enough energy available,
	 * enough space in the output tank, and input fluid in the input tank.
	 */
	public boolean processFluids(int energyPerBucket, int inputFluidPerOutput, int productionRate) {

		if (this.fluidOutput == null) {
			return false;
		}

		int energyRequired = energyPerBucket * productionRate / 1000;
		int inputFluidRequired = inputFluidPerOutput * productionRate / 1000;
		int producedAmount = productionRate;

		// Handle the remaining bits in the tank that are below the regular production rate
		if (this.energyStorage.getEnergyStored() < energyRequired || this.tankInput.getFluidAmount() < inputFluidRequired) {
			producedAmount = 1;
			energyRequired = energyPerBucket / 1000;
			inputFluidRequired = inputFluidPerOutput / 1000;
		}

		if (this.energyStorage.getEnergyStored() >= energyRequired && this.tankInput.getFluidAmount() >= inputFluidRequired) {
			FluidStack fluidStack = new FluidStack(this.fluidOutput, producedAmount);
			// Enough room to store the produced fluid amount
			if (this.tankOutput.fill(fluidStack, false) == producedAmount) {
				this.energyStorage.extractEnergy(energyRequired, false);
				this.tankInput.drain(inputFluidRequired, true);
				this.tankOutput.fill(fluidStack, true);
				return true;
			}
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiDieselProducer(this.getContainer(inventoryPlayer), this);
	}
}
