package iceman11a.fuelcraft.proxys;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import iceman11a.fuelcraft.client.renderer.tileentity.TileEntityRendererCrossingGate;
import iceman11a.fuelcraft.gui.GuiCrossingGates;
import iceman11a.fuelcraft.reference.ReferenceGuiIds;
import iceman11a.fuelcraft.reference.ReferenceReflection;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ClientProxy extends CommonProxy {

	@Override
	public void openGui(int guiId)
	{
		switch (guiId)
		{
			case ReferenceGuiIds.GUI_ID_CROSSING_GATES:
				Minecraft.getMinecraft().displayGuiScreen(new GuiCrossingGates());
				break;
		}
	}

	@Override
	public void registerKeyBindings() { }

	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrossingGate.class, new TileEntityRendererCrossingGate());
	}

	@Override
	public void setupReflection()
	{
		ReferenceReflection.fieldGuiContainer_theSlot = ReflectionHelper.findField(GuiContainer.class, "u", "field_147006_u", "theSlot");
	}
}
