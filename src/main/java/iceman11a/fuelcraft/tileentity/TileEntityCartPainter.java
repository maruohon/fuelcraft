package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.gui.GuiCartPainter;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerCartPainter;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCartPainter extends TileEntityFuelCraftInventory {

	public TileEntityCartPainter() {
		super(ReferenceNames.NAME_TILE_CART_PAINTER);
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

	@Override
	public ContainerCartPainter getContainer(InventoryPlayer inventoryPlayer) {
		return new ContainerCartPainter(inventoryPlayer, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiCartPainter(this.getContainer(inventoryPlayer), this);
	}
}
