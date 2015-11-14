package iceman11a.fuelcraft.inventory;

import iceman11a.fuelcraft.tileentity.TileEntityOilProducer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerOilProducer extends ContainerFluidProcessor
{
    public TileEntityOilProducer tileEntityOilProducer;
    public int temperature;

    public ContainerOilProducer(InventoryPlayer inventoryPlayer, TileEntityOilProducer te) {
        super(inventoryPlayer, te);
        this.tileEntityOilProducer = te;
        this.addPlayerInventorySlots(8, 94);
    }

    @Override
    protected void addCustomInventorySlots() {
        this.addSlotToContainer(new SlotGeneric(this.inventory, 0,   8, 55)); // Fuel
        this.addSlotToContainer(new SlotGeneric(this.inventory, 1,  34, 24)); // Input fluid buckets in
        this.addSlotToContainer(new SlotGeneric(this.inventory, 2,  34, 55)); // Input fluid buckets out
        this.addSlotToContainer(new SlotGeneric(this.inventory, 3, 152, 24)); // Output fluid buckets in
        this.addSlotToContainer(new SlotGeneric(this.inventory, 4, 152, 55)); // Output fluid buckets out
        this.addSlotToContainer(new SlotGeneric(this.inventory, 5,  87, 24)); // Blaze Powder
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            // The values need to fit into a short, where these get truncated to in non-local SMP

            if (this.temperature != this.tileEntityOilProducer.getTemperature()) {
                this.temperature = this.tileEntityOilProducer.getTemperature();
                icrafting.sendProgressBarUpdate(this, 5, this.temperature);
            }
        }
    }

    @Override
    public void updateProgressBar(int var, int val)
    {
        super.updateProgressBar(var, val);

        switch (var) {
            case 5:
                this.temperature = val;
                break;
        }
    }
}
