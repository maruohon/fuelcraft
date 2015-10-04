package iceman11a.fuelcraft.gui;

	
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import iceman11a.fuelcraft.gui.ContainerTileEntityInventory;
import iceman11a.fuelcraft.tileentity.TileEntityFuelCraftInventory;
import iceman11a.fuelcraft.machines.ReferenceTextures;

public class GuiDieselProducer extends GuiContainer
{
    
	protected TileEntityFuelCraftInventory te;
    protected ResourceLocation guiTexture;

    public GuiDieselProducer(ContainerTileEntityInventory container, TileEntityFuelCraftInventory te)
    {
        super(container);
        this.te = te;
        this.guiTexture = ReferenceTextures.getGuiTexture("gui.container." + te.getTEName());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks)
    {
        super.drawScreen(mouseX, mouseY, gameTicks);
        this.drawTooltips(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.bindTexture(this.guiTexture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    protected void drawTooltips(int mouseX, int mouseY)
    {
    }

    protected void bindTexture(ResourceLocation rl)
    {
        this.mc.renderEngine.bindTexture(rl);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        String s = this.te.hasCustomInventoryName() ? this.te.getInventoryName() : I18n.format(this.te.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 5, 0x404025);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, 84, 0x404025);
    }


}


