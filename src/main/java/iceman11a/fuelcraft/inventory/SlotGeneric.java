package iceman11a.fuelcraft.inventory;

import net.minecraft.inventory.Slot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;


public class SlotGeneric extends Slot {
	
	public SlotGeneric(IInventory inventory, int slot, int posX, int posY)
    {
        super(inventory, slot, posX, posY);
    }

    /**
     * This is for the fuel slot, This lets players add, Corbemite, coal and Charcoal and
     * Other fuels to the Producer
     */
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return this.inventory.isItemValidForSlot(this.slotNumber, stack);
    }

}
