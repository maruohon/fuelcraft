package iceman11a.fuelcraft.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import iceman11a.fuelcraft.client.renderer.tileentity.TileEntityRendererCrossingGate;
import iceman11a.fuelcraft.gui.GuiCrossingGate;
import iceman11a.fuelcraft.reference.ReferenceGuiIds;
import iceman11a.fuelcraft.reference.ReferenceReflection;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.tileentity.TileEntity;

public class ClientProxy extends CommonProxy
{

    @Override
    public void openGui(int guiId, TileEntity te)
    {
        switch (guiId)
        {
            case ReferenceGuiIds.GUI_ID_CROSSING_GATES:
                if (te instanceof TileEntityCrossingGate)
                {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiCrossingGate((TileEntityCrossingGate)te));
                }
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
