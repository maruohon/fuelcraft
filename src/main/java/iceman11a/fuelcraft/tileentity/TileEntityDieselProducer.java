package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.fluid.FluidTankFuelcraft;
import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerDieselProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TileEntityDieselProducer extends TileEntityFuelCraftInventory implements IFluidHandler {

	public static final int SLOT_FUEL              = 0;
	public static final int SLOT_OIL_BUCKET_IN     = 1;
	public static final int SLOT_OIL_BUCKET_OUT    = 2;
	public static final int SLOT_DIESEL_BUCKET_IN  = 3;
	public static final int SLOT_DIESEL_BUCKET_OUT = 4;

	public static int capacityOil = 16000;
	public static int capacityDiesel = 16000;
	public static int capacityEnergy = 100000;

	public int storedEnergy;

	private Fluid fluidInput;
	//private Fluid fluidOutput;

	private FluidTankFuelcraft tankInput;
	private FluidTankFuelcraft tankOutput;

	@SideOnly(Side.CLIENT)
	public int fluidAmountOil;
	@SideOnly(Side.CLIENT)
	public int fluidAmountDiesel;

	public TileEntityDieselProducer() {
		super(ReferenceNames.NAME_TILE_DIESEL_PRODUCER);
		this.itemStacks = new ItemStack[5];
		this.fluidInput = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);
		//this.fluidOutput = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_DIESEL);
		this.tankInput = new FluidTankFuelcraft(this, null, capacityOil);
		this.tankOutput = new FluidTankFuelcraft(this, null, capacityDiesel);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.storedEnergy = nbt.getInteger("StoredEnergy");

		if (nbt.hasKey("FluidInput", Constants.NBT.TAG_COMPOUND) == true) {
			this.tankInput.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("FluidInput")));
		}

		if (nbt.hasKey("FluidOutput", Constants.NBT.TAG_COMPOUND) == true) {
			this.tankOutput.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("FluidOutput")));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("StoredEnergy", this.storedEnergy);

		if (this.tankInput.getFluid() != null) {
			nbt.setTag("FluidInput", this.tankInput.getFluid().writeToNBT(new NBTTagCompound()));
		}

		if (this.tankOutput.getFluid() != null) {
			nbt.setTag("FluidOutput", this.tankOutput.getFluid().writeToNBT(new NBTTagCompound()));
		}
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

	public int getOilAmount() {
		return this.tankInput.getFluidAmount();
	}

	public int getDieselAmount() {
		return this.tankOutput.getFluidAmount();
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		// Only allow the input fluid (Oil) into the input tank
		if (resource == null || resource.getFluid() != this.fluidInput) {
			return 0;
		}

		return this.tankInput.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null) {
			return null;
		}

		if (resource.isFluidEqual(this.tankInput.getFluid()) == true) {
			return this.tankInput.drain(resource.amount, doDrain);
		}

		if (resource.isFluidEqual(this.tankOutput.getFluid())) {
			return this.tankOutput.drain(resource.amount, doDrain);
		}

		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tankOutput.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return this.fluidInput != null && this.fluidInput == fluid;
		//return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		//return this.fluidOutput != null && this.fluidOutput == fluid;
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.tankInput.getInfo(), this.tankOutput.getInfo() };
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