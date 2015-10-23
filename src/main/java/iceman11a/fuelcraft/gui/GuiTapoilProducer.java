package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerFluidProcessor;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.tileentity.TileEntityFluidProcessor;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;

public class GuiTapoilProducer extends GuiFluidProcessor
{
	public GuiTapoilProducer(ContainerFluidProcessor container, TileEntityFluidProcessor te) {
		super(container, te, FluidRegistry.getFluid("water"), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL));
	}

	@Override
	protected void addSlotHoveringText(Slot slot, List<String> list) {
		// Hovering over an empty CorCoal slot
		if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_FUEL))
		{
			list.add(I18n.format("fuelcraft.gui.label.fuel", new Object[0]));
		}
		// Hovering over an empty Water Bucket input slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_INPUT_FLUID_BUCKET_IN))
		{
			list.add(I18n.format("fuelcraft.gui.label.waterbucket.in.filled", new Object[0]));
		}
		// Hovering over an empty Bucket output slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_INPUT_FLUID_BUCKET_OUT))
		{
			list.add(I18n.format("fuelcraft.gui.label.waterbucket.out.empty", new Object[0]));
		}
		// Hovering over an empty Bucket input slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_OUTPUT_FLUID_BUCKET_IN))
		{
			list.add(I18n.format("fuelcraft.gui.label.tapoilbucket.in.empty", new Object[0]));
		}
		// Hovering over an empty Tapoil Bucket output slot
		else if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_OUTPUT_FLUID_BUCKET_OUT))
		{
			list.add(I18n.format("fuelcraft.gui.label.tapoilbucket.out.filled", new Object[0]));
		}
	}
}
