package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerTileEntityInventory;
import iceman11a.fuelcraft.tileentity.TileEntityCartPainter;
import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import iceman11a.fuelcraft.tileentity.TileEntityFuelCraftInventory;
import net.minecraft.util.ResourceLocation;

public class GuiCartPainter extends GuiFuelCraftInventory {

	protected TileEntityFuelCraftInventory te;
	protected ResourceLocation guiTexture;

	public GuiCartPainter(ContainerTileEntityInventory container, TileEntityCartPainter te) {
		super(container, te);
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(gameTicks, mouseX, mouseY);
	}
}