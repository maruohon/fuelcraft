package iceman11a.fuelcraft.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiDieselEngine extends GuiContainer{

	public GuiDieselEngine(Container p_i1072_1_) {
		super(p_i1072_1_);
		// TODO Auto-generated constructor stub
	}



	//List listA = new ArrayList();
	List buttonList = new ArrayList();
	
	public final int posX = 256;
	public final int posY = 256;
	
	boolean canMove = false; // Can the engine move.
	
	public void initGui() {
				
		this.buttonList.add(new GuiButton(0, 256, 256 * 40, 100, 20, "Start Engine"));
		this.buttonList.add(new GuiButton(1, 256, 256 * 40, 100, 20, "Slow Speed"));
		this.buttonList.add(new GuiButton(2, 256, 256 * 40, 100, 20, "1/2 Speed"));
		this.buttonList.add(new GuiButton(3, 256, 256 * 40, 100, 20, "Full Speed"));
		this.buttonList.add(new GuiButton(4, 256, 256 * 40, 100, 20, "Shutdown"));
		
	}
	
	
	public void actionPreformed(GuiButton button) {
		switch(button.id)
		{
		case 0:
			Minecraft.getMinecraft().thePlayer.sendChatMessage("Engine Stated");
			
			//Start the diesel engine here
			//Sound for engine idle.
			//Generate heat for progressbar and any thing else.
			
		break;
		case 1:
			//Runs in slow speed
			//Sound for engine running
		break;
		case 2:
		    //Runs in 1/2 speed
			//Sound for engine running
		break;
		case 3:
			//runs at full speed
			//Sound for engine running
		break;
		case 4:
			//Shutsdown engine
			//Turn Sound off for engine
		break;
			default:
		}
	}



	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
