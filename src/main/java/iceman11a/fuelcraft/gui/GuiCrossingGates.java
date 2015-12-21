package iceman11a.fuelcraft.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiButton;

public class GuiCrossingGates extends GuiFuelCraft {

    protected int dWheel;
    protected int eventX = 0;
    protected int eventY = 0;

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        //drawDefaultBackground();    // The default dark background
        super.drawScreen(i, j, f);

        /*String s = I18n.format("multishot.gui.label.settings");
        int textWidth = this.fontRendererObj.getStringWidth(s);
        int x = (this.width / 2);
        int y = (this.height / 2);
        this.fontRendererObj.drawString(s, x - (textWidth / 2), y - 115, 0xffffffff);
        s = " v" + Reference.VERSION;
        this.fontRendererObj.drawString(s, x - 130, y - 115, 0xffb0b0b0);*/
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void keyTyped(char keyChar, int keyID)
    {
        if (keyID == Keyboard.KEY_ESCAPE)
        {
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
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

    public void mouseScrolled(int x, int y, int value)
    {
        /*GuiButton guiButton;

        for (int i = 0; i < this.buttonList.size(); ++i)
        {
            guiButton = (GuiButton)this.buttonList.get(i);

            if (guiButton.mousePressed(this.mc, x, y))
            {
                if (isConfigButton(guiButton))
                {
                    int mode = this.getButtonModifier(); // 0..3 for 1/10/100/1000 at a time
                    // value is the number of "notches" the wheel was scrolled, positive for up, negative for down
                    //Configs.getConfig().changeValue(guiButton.id, mode, 0, value);
                    //this.updateGuiButton(guiButton, guiButton.id);
                }
                break;
            }
        }*/
    }

    protected int getButtonModifier()
    {
        if (isCtrlKeyDown() && isShiftKeyDown()) { return 3; }
        else if(isShiftKeyDown()) { return 2; }
        else if (isCtrlKeyDown()) { return 1; }
        return 0;
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        for (int l = 0; l < this.buttonList.size(); ++l)
        {
            GuiButton guiButton = (GuiButton)this.buttonList.get(l);

            if (guiButton.mousePressed(this.mc, par1, par2))
            {
                guiButton.func_146113_a(this.mc.getSoundHandler());

                /*if (par3 == 0) // Left click
                {
                    this.actionPerformedLeft(guiButton);
                }
                else if (par3 == 1) // Right click
                {
                    this.actionPerformedRight(guiButton);
                }
                else if (par3 == 2) // Middle click
                {
                    this.actionPerformedMiddle(guiButton);
                }*/
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        /*if (par1GuiButton.id == Constants.GUI_BUTTON_ID_BACK_TO_GAME)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        else if (this.isMenuScreenButton(par1GuiButton))
        {
            this.changeActiveScreen(par1GuiButton);
        }
        else if (par1GuiButton.id == Constants.GUI_BUTTON_ID_LOAD_DEFAULTS)
        {
        }
        else if (isConfigButton(par1GuiButton))
        {
            int mode = this.getButtonModifier(); // 0..4 for 1/10/100/1000/10000 at a time
            //Configs.getConfig().changeValue(par1GuiButton.id, mode, 0);
        }*/
    }
}
