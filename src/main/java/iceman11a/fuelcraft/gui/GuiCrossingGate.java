package iceman11a.fuelcraft.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import iceman11a.fuelcraft.network.MessageCrossingGateSetArea;
import iceman11a.fuelcraft.network.PacketHandler;
import iceman11a.fuelcraft.reference.ReferenceTextures;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiCrossingGate extends GuiScreen
{
    protected TileEntityCrossingGate te;
    protected ResourceLocation guiTexture;
    protected int xSize = 176;
    protected int ySize = 96;
    protected int dWheel;
    protected int eventX = 0;
    protected int eventY = 0;

    public GuiCrossingGate(TileEntityCrossingGate te)
    {
        this.te = te;
        this.guiTexture = ReferenceTextures.getGuiTexture("gui.crossinggate");
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        int posX = x + 80;
        int posY = y +  6;
        this.buttonList.clear();
        this.buttonList.add(new GuiButtonIcon(0, posX, posY +  0, 14, 14, 176, 0, this.guiTexture));
        this.buttonList.add(new GuiButtonIcon(1, posX, posY + 14, 14, 14, 176, 0, this.guiTexture));
        this.buttonList.add(new GuiButtonIcon(2, posX, posY + 28, 14, 14, 176, 0, this.guiTexture));
        this.buttonList.add(new GuiButtonIcon(3, posX, posY + 42, 14, 14, 176, 0, this.guiTexture));
        this.buttonList.add(new GuiButtonIcon(4, posX, posY + 56, 14, 14, 176, 0, this.guiTexture));
        this.buttonList.add(new GuiButtonIcon(5, posX, posY + 70, 14, 14, 176, 0, this.guiTexture));
        posX = x + 110;
        this.buttonList.add(new GuiButton(8, posX, posY + 14, 60, 20, StatCollector.translateToLocal("fuelcraft.gui.label.showarea")));
        this.buttonList.add(new GuiButton(7, posX, posY + 36, 40, 20, StatCollector.translateToLocal("fuelcraft.gui.label.save")));
        this.buttonList.add(new GuiButton(6, posX, posY + 58, 40, 20, StatCollector.translateToLocal("fuelcraft.gui.label.reset")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f)
    {
        this.bindTexture(this.guiTexture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        super.drawScreen(mouseX, mouseY, f);

        int posX = x + 16;
        int posY = y + 10;
        AxisAlignedBB bb = this.te.getEditArea();
        if (bb == null)
        {
            bb = this.te.getRelativeArea();
        }

        this.fontRendererObj.drawString(String.format("min X: %4d", (int)bb.minX), posX, posY +   0, 0xff404040);
        this.fontRendererObj.drawString(String.format("min Y: %4d", (int)bb.minY), posX, posY +  14, 0xff404040);
        this.fontRendererObj.drawString(String.format("min Z: %4d", (int)bb.minZ), posX, posY +  28, 0xff404040);
        this.fontRendererObj.drawString(String.format("max X: %4d", (int)bb.maxX), posX, posY +  42, 0xff404040);
        this.fontRendererObj.drawString(String.format("max Y: %4d", (int)bb.maxY), posX, posY +  56, 0xff404040);
        this.fontRendererObj.drawString(String.format("max Z: %4d", (int)bb.maxZ), posX, posY +  70, 0xff404040);

        this.drawTooltips(mouseX, mouseY);
    }

    /**
     * Used to draw tooltips when hovering the mouse over some areas.
     */
    protected void drawTooltips(int mouseX, int mouseY)
    {
        List<String> list = new ArrayList<String>();

        int x = mouseX - (this.width - this.xSize) / 2;
        int y = mouseY - (this.height - this.ySize) / 2;
        if (x >= 157 && x <= 170 && y >= 4 && y <= 16)
        {
            list.add(StatCollector.translateToLocal("fuelcraft.gui.label.clickscrolltip.0"));
            list.add(StatCollector.translateToLocal("fuelcraft.gui.label.clickscrolltip.1"));
            list.add(StatCollector.translateToLocal("fuelcraft.gui.label.clickscrolltip.2"));
        }

        this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
    }

    @Override
    public void keyTyped(char keyChar, int keyID)
    {
        if (keyID == Keyboard.KEY_ESCAPE || keyID == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            super.keyTyped(keyChar, Keyboard.KEY_ESCAPE);
        }
    }

    @Override
    public void handleMouseInput()
    {
        super.handleMouseInput();

        this.dWheel = Mouse.getEventDWheel();
        if (this.dWheel != 0)
        {
            this.dWheel /= 120;
            this.eventX = Mouse.getEventX() * this.width / this.mc.displayWidth;
            this.eventY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
            this.mouseScrolled(this.eventX, this.eventY, this.dWheel);
        }
    }

    public void mouseScrolled(int mouseX, int mouseY, int button)
    {
        GuiButton guiButton;

        for (int i = 0; i < this.buttonList.size(); ++i)
        {
            guiButton = (GuiButton)this.buttonList.get(i);

            if (guiButton.mousePressed(this.mc, mouseX, mouseY))
            {
                this.actionPerformed(guiButton, button > 0 ? 0 : 1);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        for (int i = 0; i < this.buttonList.size(); i++)
        {
            GuiButton guiButton = (GuiButton)this.buttonList.get(i);

            if (guiButton.mousePressed(this.mc, mouseX, mouseY))
            {
                guiButton.func_146113_a(this.mc.getSoundHandler());
                this.actionPerformed(guiButton, button);
            }
        }
    }

    protected void actionPerformed(GuiButton button, int mouseButton)
    {
        double change = mouseButton == 0 ? 1.0d : -1.0d;
        // AABB area edge edit buttons
        if (button.id >= 0 && button.id <= 5)
        {
            if (this.te.getEditArea() == null)
            {
                this.te.setEditArea(this.te.getRelativeArea().copy());
            }

            this.moveAABBEdge(this.te.getEditArea(), button.id, change);
        }
        // Reset the edit area
        else if (button.id == 6)
        {
            this.te.setEditArea(null);
        }
        // Save the edit area
        else if (button.id == 7)
        {
            PacketHandler.INSTANCE.sendToServer(new MessageCrossingGateSetArea(this.te.getWorldObj().provider.dimensionId,
                    this.te.xCoord, this.te.yCoord, this.te.zCoord, this.te.getAreaAsArray(this.te.getEditArea())));

            this.te.setEditArea(null);
        }
        // Toggle AABB rendering
        else if (button.id == 8)
        {
            this.te.renderArea = ! this.te.renderArea;
        }
    }

    public void moveAABBEdge(AxisAlignedBB aabb, int edge, double change)
    {
        if (edge == 0) aabb.minX += change;
        if (edge == 1) aabb.minY += change;
        if (edge == 2) aabb.minZ += change;
        if (edge == 3) aabb.maxX += change;
        if (edge == 4) aabb.maxY += change;
        if (edge == 5) aabb.maxZ += change;
    }

    protected void bindTexture(ResourceLocation rl)
    {
        this.mc.renderEngine.bindTexture(rl);
    }
}
