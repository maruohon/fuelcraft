package iceman11a.fuelcraft.gui;

import iceman11a.fuelcraft.inventory.ContainerTileEntityInventory;
import iceman11a.fuelcraft.tileentity.TileEntityCartPainter;

public class GuiCartPainter extends GuiFuelCraftInventory {

	public GuiCartPainter(ContainerTileEntityInventory container, TileEntityCartPainter te) {
		super(container, te);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(gameTicks, mouseX, mouseY);
	}
}