package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerFluidProcessor;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.tileentity.TileEntityFluidProcessor;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;

public class GuiOilProducer extends GuiFluidProcessor
{
	public GuiOilProducer(ContainerFluidProcessor container, TileEntityFluidProcessor te) {
		super(container, te, FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL));
	}

	@Override
	protected void addSlotHoveringText(Slot slot, List<String> list) {
		// Hovering over an empty fuel slot
		if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_FUEL))
		{
			list.add(I18n.format("fuelcraft.gui.label.fuel", new Object[0]));
		}
		// Hovering over an empty Tapoil Bucket input slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_INPUT_FLUID_BUCKET_IN))
		{
			list.add(I18n.format("fuelcraft.gui.label.tapoilbucket.in.filled", new Object[0]));
		}
		// Hovering over an empty Bucket output slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_INPUT_FLUID_BUCKET_OUT))
		{
			list.add(I18n.format("fuelcraft.gui.label.tapoilbucket.out.empty", new Object[0]));
		}
		// Hovering over an empty Bucket input slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_OUTPUT_FLUID_BUCKET_IN))
		{
			list.add(I18n.format("fuelcraft.gui.label.oilbucket.in.empty", new Object[0]));
		}
		// Hovering over an empty Oil Bucket output slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_OUTPUT_FLUID_BUCKET_OUT))
		{
			list.add(I18n.format("fuelcraft.gui.label.oilbucket.out.filled", new Object[0]));
		}
	}
}
