package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerDieselProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TileEntityDieselProducer extends TileEntityFuelCraftInventory {

	public TileEntityDieselProducer() {
		super(ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER);
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
		return false; // itemContainsFluidFuel(stack) || getItemBurnTime(stack) > 0;
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