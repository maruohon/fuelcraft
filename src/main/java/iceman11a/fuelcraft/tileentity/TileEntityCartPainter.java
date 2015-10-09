package iceman11a.fuelcraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceman11a.fuelcraft.gui.GuiCartPainter;
import iceman11a.fuelcraft.gui.GuiDieselProducer;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerCartPainter;
import iceman11a.fuelcraft.inventory.ContainerDieselProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityCartPainter extends TileEntityFuelCraftInventory {

	public TileEntityCartPainter(String name) {
		super(ReferenceNames.NAME_TILE_CART_PAINTER);
		this.itemStacks = new ItemStack[5];
		// TODO Auto-generated constructor stub
	}
	
	
	
		public static boolean isItemFuel(ItemStack stack) {
			return false; // itemContainsFluidFuel(stack) || getItemBurnTime(stack) > 0;
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
