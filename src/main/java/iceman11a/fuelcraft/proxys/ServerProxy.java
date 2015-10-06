package iceman11a.fuelcraft.proxys;

import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.tileentity.TileEntityDieselProducer;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy{
	
	public void registerKeyBindings() { }
	public void registerRenderers() { }

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityDieselProducer.class, ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER));
	}
}
