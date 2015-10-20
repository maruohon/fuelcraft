package iceman11a.fuelcraft.inventory;

import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerDieselProducer extends ContainerTileEntityInventory {

	private TileEntityDieselProducer tileEntityDieselProducer;
	public int fluidAmountOil;
	public int fluidAmountDiesel;
	public int energyStored;

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

			if (this.energyStored != this.tileEntityDieselProducer.getEnergyStored(ForgeDirection.UP)) {
				icrafting.sendProgressBarUpdate(this, 0, this.tileEntityDieselProducer.getEnergyStored(ForgeDirection.UP) / 10);
				this.energyStored = this.tileEntityDieselProducer.getEnergyStored(ForgeDirection.UP);
			}

			if (this.fluidAmountOil != this.tileEntityDieselProducer.getOilAmount()) {
				icrafting.sendProgressBarUpdate(this, 1, this.tileEntityDieselProducer.getOilAmount());
				this.fluidAmountOil = this.tileEntityDieselProducer.getOilAmount();
			}

			if (this.fluidAmountDiesel != this.tileEntityDieselProducer.getDieselAmount()) {
				icrafting.sendProgressBarUpdate(this, 2, this.tileEntityDieselProducer.getDieselAmount());
				this.fluidAmountDiesel = this.tileEntityDieselProducer.getDieselAmount();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int var, int val) {
		switch (var) {
			case 0:
				this.energyStored = val * 10;
				break;
			case 1:
				this.fluidAmountOil = val;
				break;
			case 2:
				this.fluidAmountDiesel = val;
				break;
			default:
		}
	}
}
