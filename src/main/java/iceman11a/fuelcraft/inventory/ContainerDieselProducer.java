package iceman11a.fuelcraft.inventory;

import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerDieselProducer extends ContainerTileEntityInventory {

	private TileEntityDieselProducer tileEntityDieselProducer;

	public ContainerDieselProducer(InventoryPlayer inventoryPlayer, TileEntityDieselProducer te) {
		super(inventoryPlayer, te);
		this.tileEntityDieselProducer = te;
		this.addPlayerInventorySlots(8, 94);
	}

	@Override
	protected void addCustomInventorySlots() {
		this.addSlotToContainer(new SlotGeneric(this.inventory, 0,   8, 70));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 1,  76, 24));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 2,  76, 55));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 3, 154, 24));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 4, 154, 55));
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
