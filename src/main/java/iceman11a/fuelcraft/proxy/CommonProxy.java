package iceman11a.fuelcraft.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.tileentity.TileEntityCartFilter;
import iceman11a.fuelcraft.tileentity.TileEntityCartPainter;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;
import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import iceman11a.fuelcraft.tileentity.TileEntityOilProducer;
import iceman11a.fuelcraft.tileentity.TileEntityTapoilProducer;
import iceman11a.fuelcraft.tileentity.TileEntityTokenController;
import net.minecraft.tileentity.TileEntity;

public class CommonProxy
{

    public void openGui(int guiId, TileEntity te) { }
    public void registerKeyBindings() { }
    public void registerRenderers() { }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityDieselProducer.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_DIESEL_PRODUCER));
        GameRegistry.registerTileEntity(TileEntityOilProducer.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_OIL_PRODUCER));
        GameRegistry.registerTileEntity(TileEntityTapoilProducer.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_TAPOIL_PRODUCER));
        GameRegistry.registerTileEntity(TileEntityCartPainter.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_CART_PAINTER));
        GameRegistry.registerTileEntity(TileEntityCartFilter.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_CART_FILTER));
        GameRegistry.registerTileEntity(TileEntityTokenController.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_TOKEN_CONTROLLER));
        GameRegistry.registerTileEntity(TileEntityCrossingGate.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_CROSSING_GATE));
    }

    public void setupReflection() { }
}
