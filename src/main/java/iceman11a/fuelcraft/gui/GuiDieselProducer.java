package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.Util.FuelcraftStringUtils;
import iceman11a.fuelcraft.inventory.ContainerTileEntityInventory;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.reference.ReferenceReflection;
import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiDieselProducer extends GuiFuelCraftInventory {

	protected TileEntityDieselProducer tileEntityDieselProducer;

	protected FluidStack fluidInput;
	protected FluidStack fluidOutput;

	public GuiDieselProducer(ContainerTileEntityInventory container, TileEntityDieselProducer te) {
		super(container, te);
		this.tileEntityDieselProducer = te;
		Fluid fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);
		if (fluid != null) {
			this.fluidInput = new FluidStack(fluid, 0);
		}
		fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_DIESEL);
		if (fluid != null) {
			this.fluidOutput = new FluidStack(fluid, 0);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(gameTicks, mouseX, mouseY);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		// Empty CorCoal input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_FUEL).getStack() == null)
		{
			this.drawTexturedModalRect(x + 8, y + 64, 192, 0, 16, 16);
		}

		// Empty Oil Bucket input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_OIL_BUCKET_IN).getStack() == null)
		{
			this.drawTexturedModalRect(x + 76, y + 24, 176, 0, 16, 16);
		}

		// Empty Diesel Bucket input slot, draw the slot background
		if (this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_DIESEL_BUCKET_IN).getStack() == null)
		{
			this.drawTexturedModalRect(x + 154, y + 24, 176, 0, 16, 16);
		}

		// Some energy stored
		if (this.tileEntityDieselProducer.storedEnergy > 0)
		{
			int h = 36;
			int t = this.tileEntityDieselProducer.storedEnergy * h / TileEntityDieselProducer.capacityEnergy;
			this.drawTexturedModalRect(x + 9, y + 23 + h - t, 178, 69 + h - t, 6, t);
		}

		// Some oil stored in the tank
		if (this.tileEntityDieselProducer.fluidAmountOil > 0 && this.fluidInput != null)
		{
			this.fluidInput.amount = this.tileEntityDieselProducer.fluidAmountOil;
			this.drawFluid(this.fluidInput, x + 97, y + 24, 14, 47, TileEntityDieselProducer.capacityOil);
			/*int h = 47;
			int t = this.tileEntityDieselProducer.fluidAmountOil * h / TileEntityDieselProducer.capacityOil;
			this.drawTexturedModalRect(x + 97, y + 24 + h - t, 177, 17 + h - t, 14, t);*/
		}

		// Draw the input tank gauge
		this.bindTexture(this.guiTexture);
		this.drawTexturedModalRect(x + 97, y + 24, 209, 17, 14, 47);

		// Some diesel stored in the tank
		if (this.tileEntityDieselProducer.fluidAmountOil > 0 && this.fluidOutput != null)
		{
			this.fluidOutput.amount = this.tileEntityDieselProducer.fluidAmountDiesel;
			this.drawFluid(this.fluidOutput, x + 133, y + 24, 14, 47, TileEntityDieselProducer.capacityDiesel);
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
			int energyAmount = this.tileEntityDieselProducer.storedEnergy;
			int energyCapacity = TileEntityDieselProducer.capacityEnergy;
			list.add(FuelcraftStringUtils.formatNumberWithKSeparators(energyAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(energyCapacity) + " RF");
			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		// Hovering over the oil tank
		else if (mouseX >= x + 96 && mouseY >= y + 23 && mouseX <= x + 111 && mouseY <= y + 71)
		{
			int oilAmount = this.tileEntityDieselProducer.fluidAmountOil;
			if (oilAmount > 0) {
				int oilCapacity = TileEntityDieselProducer.capacityOil;
				Fluid fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_OIL);
				if (fluid != null)
				{
					FluidStack fluidStack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
					list.add(fluidStack.getLocalizedName());
				}
				list.add(FuelcraftStringUtils.formatNumberWithKSeparators(oilAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(oilCapacity) + " mB");
			}
			else {
				list.add(I18n.format("fuelcraft.gui.label.empty", new Object[0]));
			}
			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
		// Hovering over the diesel tank
		else if (mouseX >= x + 132 && mouseY >= y + 23 && mouseX <= x + 147 && mouseY <= y + 71)
		{
			int dieselAmount = this.tileEntityDieselProducer.fluidAmountDiesel;
			if (dieselAmount > 0) {
				int dieselCapacity = TileEntityDieselProducer.capacityDiesel;
				Fluid fluid = FluidRegistry.getFluid(ReferenceNames.NAME_FLUID_DIESEL);
				if (fluid != null)
				{
					FluidStack fluidStack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
					list.add(fluidStack.getLocalizedName());
				}
				list.add(FuelcraftStringUtils.formatNumberWithKSeparators(dieselAmount) + " / " + FuelcraftStringUtils.formatNumberWithKSeparators(dieselCapacity) + " mB");
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
				// Hovering over an empty CorCoal slot
				if (slot == this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_FUEL))
				{
					list.add(I18n.format("fuelcraft.gui.label.fuel", new Object[0]));
				}
				// Hovering over an empty Oil Bucket input slot
				else if (slot == this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_OIL_BUCKET_IN))
				{
					list.add(I18n.format("fuelcraft.gui.label.oilbucket.in", new Object[0]));
				}
				// Hovering over an empty Oil Bucket output slot
				else if (slot == this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_OIL_BUCKET_OUT))
				{
					list.add(I18n.format("fuelcraft.gui.label.oilbucket.out", new Object[0]));
				}
				// Hovering over an empty Diesel Bucket input slot
				else if (slot == this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_DIESEL_BUCKET_IN))
				{
					list.add(I18n.format("fuelcraft.gui.label.dieselbucket.in", new Object[0]));
				}
				// Hovering over an empty Diesel Bucket output slot
				else if (slot == this.inventorySlots.getSlot(TileEntityDieselProducer.SLOT_DIESEL_BUCKET_OUT))
				{
					list.add(I18n.format("fuelcraft.gui.label.dieselbucket.out", new Object[0]));
				}

				this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
			}
		}
	}
}
