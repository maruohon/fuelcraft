package iceman11a.fuelcraft.inventory;

import iceman11a.fuelcraft.tileentity.TileEntityCartPainter;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ContainerCartPainter extends ContainerTileEntityInventory {

	private TileEntityCartPainter tileEntityCartPainter;

	public ContainerCartPainter(InventoryPlayer inventoryPlayer, TileEntityCartPainter te) {
		super(inventoryPlayer, te);
		this.tileEntityCartPainter = te;
		this.addPlayerInventorySlots(8, 94);
	}

	@Override
	protected void addCustomInventorySlots() {
		this.addSlotToContainer(new SlotGeneric(this.inventory, 0,   8, 70)); //Engine filter
		this.addSlotToContainer(new SlotGeneric(this.inventory, 1,  76, 24)); //Color 1
		this.addSlotToContainer(new SlotGeneric(this.inventory, 2,  76, 55)); //Color 2
		this.addSlotToContainer(new SlotGeneric(this.inventory, 3, 154, 24)); //Cart 2/ Tank or Chest
		this.addSlotToContainer(new SlotGeneric(this.inventory, 4, 154, 55)); //Single filter
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int var, int val) {
	}
}
