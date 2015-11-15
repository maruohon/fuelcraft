package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerOilProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.tileentity.TileEntityFluidProcessor;
import iceman11a.fuelcraft.tileentity.TileEntityOilProducer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;

public class GuiOilProducer extends GuiFluidProcessor
{
	protected ContainerOilProducer containerOilProducer;

	public GuiOilProducer(ContainerOilProducer container, TileEntityOilProducer te) {
		super(container, te, FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_TAPOIL), FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL));
		this.containerOilProducer = container;
		this.xFluidInputSlot = 34;
		this.xInputTank = 55;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(gameTicks, mouseX, mouseY);

		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		// Empty blaze powder slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityOilProducer.SLOT_BLAZEPOWDER).getStack() == null)
		{
			this.drawTexturedModalRect(x + 87, y + 24, 224, 0, 16, 16);
		}

		// Draw the temperature gauge
		int height = 28;
		int renderHeight = height * this.containerOilProducer.temperature / TileEntityOilProducer.MAX_TEMPERATURE;
		int yPos = height - renderHeight;
		this.drawTexturedModalRect(x + 116, y + 57 + yPos, 186, 68 + yPos, 5, renderHeight);

		// Draw the required-temperature marker
		yPos = 28 * (TileEntityOilProducer.MAX_TEMPERATURE - TileEntityOilProducer.REQUIRED_TEMPERATURE) / TileEntityOilProducer.MAX_TEMPERATURE;
		this.drawTexturedModalRect(x + 116, y + 55 + yPos, 196, 67, 12, 5);
	}

	@Override
	protected void drawTooltips(int mouseX, int mouseY)
	{
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		List<String> list = new ArrayList<String>();
		// Hovering over the temperature meter
		if (mouseX >= x + 116 && mouseY >= y + 57 && mouseX < x + 116 + 5 && mouseY < y + 57 + 28)
		{
			list.add(this.containerOilProducer.temperature + " \u00b0" + "C");
			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		else
		{
			super.drawTooltips(mouseX, mouseY);
		}
	}

	@Override
	protected void addSlotHoveringText(Slot slot, List<String> list) {
		// Hovering over an empty fuel slot
		if (slot == this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_FUEL))
		{
			list.add(I18n.format("fuelcraft.gui.label.fuel", new Object[0]));
			list.add(I18n.format("fuelcraft.gui.label.fuel.insertinfo", new Object[0]));
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
		// Hovering over an empty blaze powder slot
		else if (slot == this.inventorySlots.getSlot(TileEntityOilProducer.SLOT_BLAZEPOWDER))
		{
			list.add(I18n.format("fuelcraft.gui.label.blazepowder", new Object[0]));
		}
	}
}
