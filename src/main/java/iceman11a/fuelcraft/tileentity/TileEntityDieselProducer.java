package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerDieselProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TileEntityDieselProducer extends TileEntityFuelCraftInventory {

	public static final int SLOT_FUEL              = 0;
	public static final int SLOT_OIL_BUCKET_IN     = 1;
	public static final int SLOT_OIL_BUCKET_OUT    = 2;
	public static final int SLOT_DIESEL_BUCKET_IN  = 3;
	public static final int SLOT_DIESEL_BUCKET_OUT = 4;

	public static int capacityOil = 16000;
	public static int capacityDiesel = 16000;
	public static int capacityEnergy = 100000;

	public int fluidAmountOil;
	public int fluidAmountDiesel;
	public int storedEnergy;

	public TileEntityDieselProducer() {
		super(ReferenceNames.NAME_TILE_DIESEL_PRODUCER);
		this.itemStacks = new ItemStack[5];
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}

	/**
	 * Check if the given item works as a fuel source in this machine
	 */
	public static boolean isItemFuel(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slotNum, ItemStack stack) {
		if (slotNum == SLOT_OIL_BUCKET_OUT || slotNum == SLOT_DIESEL_BUCKET_OUT) {
			return false;
		}

		if (slotNum == SLOT_FUEL) {
			return isItemFuel(stack);
		}

		if (slotNum == SLOT_DIESEL_BUCKET_IN) {
			return FluidContainerRegistry.isEmptyContainer(stack);
		}

		if (slotNum == SLOT_OIL_BUCKET_IN) {
			Fluid fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);
			if (fluid != null) {
				return FluidContainerRegistry.isFilledContainer(stack) &&
					   FluidContainerRegistry.containsFluid(stack, new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME));
			}

		}

		return false;
	}

	@Override
	public ContainerDieselProducer getContainer(InventoryPlayer inventoryPlayer) {
		return new ContainerDieselProducer(inventoryPlayer, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiDieselProducer(this.getContainer(inventoryPlayer), this);
	}
}