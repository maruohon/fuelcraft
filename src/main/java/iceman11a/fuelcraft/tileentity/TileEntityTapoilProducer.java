package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.config.Configs;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.gui.GuiTapoilProducer;
import iceman11a.fuelcraft.inventory.ContainerTapoilProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTapoilProducer extends TileEntityFluidProcessor
{
    public static final int SLOT_SAPLING = 5;
    public static final int SLOT_COAL = 6;

    public static int outputFluidProductionRate = 3; // mB per tick; 3 * 20 = 60 mB per second

    protected int temperature;

	public TileEntityTapoilProducer() {
		super(ReferenceNames.NAME_TILE_TAPOIL_PRODUCER, FluidRegistry.getFluid("water"), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL));
		this.itemStacks = new ItemStack[7];
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

	    if (slotNum == SLOT_SAPLING) {
	        for (int id : OreDictionary.getOreIDs(stack)) {
	            if (OreDictionary.getOreName(id).equals("treeSapling")) {
	                return true;
	            }
	        }
	    }

	    if (slotNum == SLOT_COAL) {
	        return stack != null && stack.getItem() == Items.coal;
	    }

	    return false;
	}

    @Override
    public ContainerTapoilProducer getContainer(InventoryPlayer inventoryPlayer) {
        return new ContainerTapoilProducer(inventoryPlayer, this);
    }

    @SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiTapoilProducer(this.getContainer(inventoryPlayer), this);
	}
}
