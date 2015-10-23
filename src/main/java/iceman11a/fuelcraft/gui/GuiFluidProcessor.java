package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerFluidProcessor;
import iceman11a.fuelcraft.reference.ReferenceReflection;
import iceman11a.fuelcraft.tileentity.TileEntityFluidProcessor;
import iceman11a.fuelcraft.util.FuelcraftStringUtils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public abstract class GuiFluidProcessor extends GuiFuelCraftInventory {

	protected ContainerFluidProcessor containerFluidProcessor;

	protected FluidStack fluidInput;
	protected FluidStack fluidOutput;

	public GuiFluidProcessor(ContainerFluidProcessor container, TileEntityFluidProcessor te, Fluid fluidInput, Fluid fluidOutput) {
		super(container, te);

		this.containerFluidProcessor = container;
		this.fluidInput = new FluidStack(fluidInput, 0);
		this.fluidOutput = new FluidStack(fluidOutput, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(gameTicks, mouseX, mouseY);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		// Empty fuel input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_FUEL).getStack() == null)
		{
			this.drawTexturedModalRect(x + 8, y + 64, 192, 0, 16, 16);
		}

		// Draw the burn time flame
		if (this.containerFluidProcessor.burnTime > 0 && this.containerFluidProcessor.burnTimeFresh != 0) {
			int height = this.containerFluidProcessor.burnTime * 13 / this.containerFluidProcessor.burnTimeFresh;
			this.drawTexturedModalRect(x + 28, y + 65 + 13 - height, 208, 14 - height, 16, height);
		}

		// Empty Oil Bucket input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_INPUT_FLUID_BUCKET_IN).getStack() == null)
		{
			this.drawTexturedModalRect(x + 76, y + 24, 176, 0, 16, 16);
		}

		// Empty Diesel Bucket input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityFluidProcessor.SLOT_OUTPUT_FLUID_BUCKET_IN).getStack() == null)
		{
			this.drawTexturedModalRect(x + 154, y + 24, 176, 0, 16, 16);
		}

		// Some energy stored
		if (this.containerFluidProcessor.energyStored > 0)
		{
			int h = 36;
			int t = this.containerFluidProcessor.energyStored * h / TileEntityFluidProcessor.capacityEnergy;
			this.drawTexturedModalRect(x + 9, y + 23 + h - t, 178, 69 + h - t, 6, t);
		}

		// Some oil stored in the tank
		if (this.containerFluidProcessor.fluidAmountInput > 0 && this.fluidInput != null)
		{
			this.fluidInput.amount = this.containerFluidProcessor.fluidAmountInput;
			this.drawFluid(this.fluidInput, x + 97, y + 24, 14, 47, TileEntityFluidProcessor.capacityFluidInput);
			/*int h = 47;
			int t = this.tileEntityDieselProducer.fluidAmountOil * h / TileEntityDieselProducer.capacityOil;
			this.drawTexturedModalRect(x + 97, y + 24 + h - t, 177, 17 + h - t, 14, t);*/
		}

		// Draw the input tank gauge
		this.bindTexture(this.guiTexture);
		this.drawTexturedModalRect(x + 97, y + 24, 209, 17, 14, 47);

		// Some diesel stored in the tank
		if (this.containerFluidProcessor.fluidAmountOutput > 0 && this.fluidOutput != null)
		{
			this.fluidOutput.amount = this.containerFluidProcessor.fluidAmountOutput;
			this.drawFluid(this.fluidOutput, x + 133, y + 24, 14, 47, TileEntityFluidProcessor.capacityFluidOutput);
			/*int h = 47;
			int t = this.tileEntityDieselProducer.fluidAmountDiesel * h / TileEntityDieselProducer.capacityDiesel;
			this.drawTexturedModalRect(x + 133, y + 24 + h - t, 193, 17 + h - t, 14, t);*/
		}

		// Draw the output tank gauge
		this.bindTexture(this.guiTexture);
		this.drawTexturedModalRect(x + 133, y + 24, 225, 17, 14, 47);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		String str = this.te.hasCustomInventoryName() ? this.te.getInventoryName() : I18n.format(this.te.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(str, this.xSize / 2 - this.fontRendererObj.getStringWidth(str) / 2, 5, 0x404025);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, 84, 0x404025);
	}

	@Override
	protected void drawTooltips(int mouseX, int mouseY)
	{
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		List<String> list = new ArrayList<String>();
		// Hovering over the energy meter
		if (mouseX >= x + 7 && mouseY >= y + 21 && mouseX <= x + 15 && mouseY <= y + 60)
		{
			int energyAmount = this.containerFluidProcessor.energyStored;
			int energyCapacity = TileEntityFluidProcessor.capacityEnergy;
			list.add(FuelcraftStringUtils.formatNumberWithKSeparators(energyAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(energyCapacity) + " RF");
			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		// Hovering over the input tank
		else if (mouseX >= x + 96 && mouseY >= y + 23 && mouseX <= x + 111 && mouseY <= y + 71)
		{
			int inputFluidAmount = this.containerFluidProcessor.fluidAmountInput;
			if (inputFluidAmount > 0) {
				if (this.fluidInput != null) {
					list.add(this.fluidInput.getLocalizedName());
				}

				list.add(FuelcraftStringUtils.formatNumberWithKSeparators(inputFluidAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(TileEntityFluidProcessor.capacityFluidInput) + " mB");
			}
			else {
				list.add(I18n.format("fuelcraft.gui.label.empty", new Object[0]));
			}

			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		// Hovering over the output tank
		else if (mouseX >= x + 132 && mouseY >= y + 23 && mouseX <= x + 147 && mouseY <= y + 71)
		{
			int outputFluidAmount = this.containerFluidProcessor.fluidAmountOutput;
			if (outputFluidAmount > 0) {
				if (this.fluidOutput != null) {
					list.add(this.fluidOutput.getLocalizedName());
				}

				list.add(FuelcraftStringUtils.formatNumberWithKSeparators(outputFluidAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(TileEntityFluidProcessor.capacityFluidOutput) + " mB");
			}
			else {
				list.add(I18n.format("fuelcraft.gui.label.empty", new Object[0]));
			}

			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		// Hovering over an empty slot
		else
		{
			Slot slot = null;

			try {
				slot = (Slot)ReferenceReflection.fieldGuiContainer_theSlot.get(this);
			}
			catch (IllegalAccessException e) {
				return;
			}

			if (slot != null && slot.getHasStack() == false)
			{
				this.addSlotHoveringText(slot, list);

				this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
			}
		}
	}

	protected abstract void addSlotHoveringText(Slot slot, List<String> list);

}
