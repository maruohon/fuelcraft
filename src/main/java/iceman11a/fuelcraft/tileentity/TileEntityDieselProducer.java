package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.energy.FuelcraftEnergyStorage;
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
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.Interface(iface = "cofh.api.energy.IEnergyReceiver", modid = "CoFHCore") // TODO Which mod should provide the API?
public class TileEntityDieselProducer extends TileEntityFuelCraftInventory implements IFluidHandler, IEnergyReceiver {

	public static final int SLOT_FUEL              = 0;
	public static final int SLOT_OIL_BUCKET_IN     = 1;
	public static final int SLOT_OIL_BUCKET_OUT    = 2;
	public static final int SLOT_DIESEL_BUCKET_IN  = 3;
	public static final int SLOT_DIESEL_BUCKET_OUT = 4;

	public static int capacityOil    =  16000;
	public static int capacityDiesel =  16000;
	public static int capacityEnergy = 100000;

	private Fluid fluidInput;
	private Fluid fluidOutput;

	private FluidTankFuelcraft tankInput;
	private FluidTankFuelcraft tankOutput;

	private FuelcraftEnergyStorage energyStorage;

	private int counter;

	public TileEntityDieselProducer() {
		super(ReferenceNames.NAME_TILE_DIESEL_PRODUCER);
		this.itemStacks = new ItemStack[5];
		this.fluidInput = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);
		this.fluidOutput = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_DIESEL);
		this.tankInput = new FluidTankFuelcraft(this, null, capacityOil);
		this.tankOutput = new FluidTankFuelcraft(this, null, capacityDiesel);
		this.energyStorage = new FuelcraftEnergyStorage(capacityEnergy);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.energyStorage.setStoredEnergy(nbt.getInteger("StoredEnergy"));

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

		nbt.setInteger("StoredEnergy", this.energyStorage.getEnergyStored());

		if (this.tankInput.getFluid() != null) {
			nbt.setTag("FluidInput", this.tankInput.getFluid().writeToNBT(new NBTTagCompound()));
		}

		if (this.tankOutput.getFluid() != null) {
			nbt.setTag("FluidOutput", this.tankOutput.getFluid().writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public void updateEntity() {
		if (++counter >= 10) {
			counter = 0;

			//if (this.tankInput.getFluidAmount() == 16000) this.tankInput.drain(1000, true); // FIXME debug
			//if (this.tankOutput.getFluidAmount() == 0) this.tankOutput.fill(new FluidStack(this.fluidOutput, 16000), true); // FIXME debug

			if (this.worldObj.isRemote == false) {
				this.consumeInputFluidItem();
				this.fillOutputFluidItem();
			}
		}
	}

	/**
	 * Tries to take one fluid container item from the input slot and add the fluid from it to the input tank
	 * an then return the empty container item (if any) from it to the output slot.
	 */
	public boolean consumeInputFluidItem() {
		// Has input items, and output slot is empty or has space in the output slot
		if (this.itemStacks[SLOT_OIL_BUCKET_IN] != null) {
			if (this.itemHasValidInputFluid(this.itemStacks[SLOT_OIL_BUCKET_IN]) == false) {
				return false;
			}

			ItemStack stackIn = this.itemStacks[SLOT_OIL_BUCKET_IN].copy();
			stackIn.stackSize = 1;
			FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(stackIn);
			int amount = fluidStack != null ? fluidStack.amount : 0;
			ItemStack stackOut = FluidContainerRegistry.drainFluidContainer(stackIn);

			if (fluidStack == null) {
				return false;
			}

			// Can add the fluid from one input container to the input tank
			if (this.tankInput.fill(fluidStack, false) == amount) {
				// Has an empty container item as output
				if (stackOut != null) {
					if (this.itemStacks[SLOT_OIL_BUCKET_OUT] == null) {
						this.tankInput.fill(fluidStack, true);
						this.decrStackSize(SLOT_OIL_BUCKET_IN, 1);
						this.itemStacks[SLOT_OIL_BUCKET_OUT] = stackOut;
						return true;
					}
					else if (this.itemStacks[SLOT_OIL_BUCKET_OUT].stackSize < this.itemStacks[SLOT_OIL_BUCKET_OUT].getMaxStackSize() &&
							 stackOut.isItemEqual(this.itemStacks[SLOT_OIL_BUCKET_OUT])) {
						this.tankInput.fill(fluidStack, true);
						this.decrStackSize(SLOT_OIL_BUCKET_IN, 1);
						this.itemStacks[SLOT_OIL_BUCKET_OUT].stackSize++;
						return true;
					}
				}
				// No empty container item as a result of emptying a fluid container, just transfer the fluid
				else {
					this.tankInput.fill(fluidStack, true);
					this.decrStackSize(SLOT_OIL_BUCKET_IN, 1);
					return true;
				}
			}
		}

		return false;
	}

	public boolean fillOutputFluidItem() {
		// Has input items, and output slot is empty or has space in the output slot
		if (this.itemStacks[SLOT_DIESEL_BUCKET_IN] != null) {
			if (this.itemIsValidContainerForOutputFluid(this.itemStacks[SLOT_DIESEL_BUCKET_IN]) == false) {
				return false;
			}

			ItemStack stackIn = this.itemStacks[SLOT_DIESEL_BUCKET_IN].copy();
			stackIn.stackSize = 1;
			int capacity = FluidContainerRegistry.getContainerCapacity(new FluidStack(this.fluidOutput, 0), stackIn);
			FluidStack fluidStack = this.tankOutput.drain(capacity, false);
			if (fluidStack == null || fluidStack.amount < capacity) {
				return false;
			}

			ItemStack stackOut = FluidContainerRegistry.fillFluidContainer(fluidStack, stackIn);
			if (stackOut == null) {
				return false;
			}

			if (this.itemStacks[SLOT_DIESEL_BUCKET_OUT] == null) {
				this.tankOutput.drain(capacity, true);
				this.itemStacks[SLOT_DIESEL_BUCKET_OUT] = stackOut;
				this.decrStackSize(SLOT_DIESEL_BUCKET_IN, 1);
				return true;
			}
			else if (this.itemStacks[SLOT_DIESEL_BUCKET_OUT].stackSize < this.itemStacks[SLOT_DIESEL_BUCKET_OUT].getMaxStackSize() &&
					 stackOut.isItemEqual(this.itemStacks[SLOT_DIESEL_BUCKET_OUT])) {
				this.tankOutput.drain(capacity, true);
				this.itemStacks[SLOT_DIESEL_BUCKET_OUT].stackSize++;
				this.decrStackSize(SLOT_DIESEL_BUCKET_IN, 1);
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns true, if the given ItemStack is a filled fluid container, which contains
	 * a valid fluid for the input tank of this machine.
	 */
	public boolean itemHasValidInputFluid(ItemStack stack) {
		Fluid fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);

		return fluid != null && FluidContainerRegistry.isFilledContainer(stack) &&
			   FluidContainerRegistry.containsFluid(stack, new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME));
	}

	public boolean itemIsValidContainerForOutputFluid(ItemStack stack) {
		FluidStack fluidStack = new FluidStack(this.fluidOutput, FluidContainerRegistry.BUCKET_VOLUME);
		//return FluidContainerRegistry.getContainerCapacity(fluidStack, stack) > 0;
		return FluidContainerRegistry.fillFluidContainer(fluidStack, stack) != null;
	}

	/**
	 * Check if the given item works as a fuel source in this machine
	 */
	public boolean isItemFuel(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slotNum, ItemStack stack) {
		if (slotNum == SLOT_OIL_BUCKET_OUT || slotNum == SLOT_DIESEL_BUCKET_OUT) {
			return false;
		}

		if (slotNum == SLOT_FUEL) {
			return this.isItemFuel(stack);
		}

		if (slotNum == SLOT_DIESEL_BUCKET_IN) {
			return this.itemIsValidContainerForOutputFluid(stack);
		}

		if (slotNum == SLOT_OIL_BUCKET_IN) {
			return this.itemHasValidInputFluid(stack);
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

	@Optional.Method(modid = "CoFHCore")
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return this.energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getEnergyStored(ForgeDirection from) {
		return this.energyStorage.getEnergyStored();
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return this.energyStorage.getMaxEnergyStored();
	}
}