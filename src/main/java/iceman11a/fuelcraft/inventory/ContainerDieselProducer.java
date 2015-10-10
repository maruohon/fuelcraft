package iceman11a.fuelcraft.inventory;

import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerDieselProducer extends ContainerTileEntityInventory {

	private TileEntityDieselProducer tileEntityDieselProducer;
	public int lastFluidAmountOil;
	public int lastFluidAmountDiesel;
	public int lastStoredEnergy;

	public ContainerDieselProducer(InventoryPlayer inventoryPlayer, TileEntityDieselProducer te) {
		super(inventoryPlayer, te);
		this.tileEntityDieselProducer = te;
		this.addPlayerInventorySlots(8, 94);
	}

	@Override
	protected void addCustomInventorySlots() {
		this.addSlotToContainer(new SlotGeneric(this.inventory, 0,   8, 64));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 1,  76, 24));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 2,  76, 55));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 3, 154, 24));
		this.addSlotToContainer(new SlotGeneric(this.inventory, 4, 154, 55));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			// The values need to fit into a short, where these get truncated to in non-local SMP

			if (this.lastStoredEnergy != this.tileEntityDieselProducer.storedEnergy) {
				icrafting.sendProgressBarUpdate(this, 0, this.tileEntityDieselProducer.storedEnergy / 10);
				this.lastStoredEnergy = this.tileEntityDieselProducer.storedEnergy;
			}

			if (this.lastFluidAmountOil != this.tileEntityDieselProducer.getOilAmount()) {
				icrafting.sendProgressBarUpdate(this, 1, this.tileEntityDieselProducer.getOilAmount());
				this.lastFluidAmountOil = this.tileEntityDieselProducer.getOilAmount();
			}

			if (this.lastFluidAmountDiesel != this.tileEntityDieselProducer.getDieselAmount()) {
				icrafting.sendProgressBarUpdate(this, 2, this.tileEntityDieselProducer.getDieselAmount());
				this.lastFluidAmountDiesel = this.tileEntityDieselProducer.getDieselAmount();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int var, int val) {
		switch (var) {
			case 0:
				this.tileEntityDieselProducer.storedEnergy = val * 10;
				break;
			case 1:
				this.tileEntityDieselProducer.fluidAmountOil = val;
				break;
			case 2:
				this.tileEntityDieselProducer.fluidAmountDiesel = val;
				break;
			default:
		}
	}
}
