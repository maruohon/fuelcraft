package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.config.Configs;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.gui.GuiOilProducer;
import iceman11a.fuelcraft.inventory.ContainerOilProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityOilProducer extends TileEntityFluidProcessor
{
    public static final int SLOT_BLAZEPOWDER = 5;

    public static int outputFluidProductionRate = 3; // mB per tick; 3 * 20 = 60 mB per second

    protected int temperature;

    public TileEntityOilProducer() {
		super(ReferenceNames.NAME_TILE_OIL_PRODUCER, FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL));
		this.itemStacks = new ItemStack[6];
	}

    public int getTemperature() {
        return this.temperature;
    }

    @Override
	public void updateEntity() {
		super.updateEntity();

		if (this.worldObj.isRemote == false) {
			this.processFluids(Configs.oilProducerEnergyPerOilBucket, Configs.oilProducerTapoilPerOilBucket, outputFluidProductionRate);
		}
	}

    @Override
    public boolean isItemValidForSlot(int slotNum, ItemStack stack) {
        if (super.isItemValidForSlot(slotNum, stack) == true) {
            return true;
        }

        if (slotNum == SLOT_BLAZEPOWDER) {
            return stack != null && stack.getItem() == Items.blaze_powder;
        }

        return false;
    }

    @Override
    public ContainerOilProducer getContainer(InventoryPlayer inventoryPlayer) {
        return new ContainerOilProducer(inventoryPlayer, this);
    }

    @SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiOilProducer(this.getContainer(inventoryPlayer), this);
	}
}
