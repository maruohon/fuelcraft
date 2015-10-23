package iceman11a.fuelcraft.item;

import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelcraftItems {

	public static final Item bucketDieselFuel = new ItemDieselBucket();
	public static final Item itemOreResource = new ItemOreResource();
	public static final Item token = new ItemToken();

	public static void registerItems() {
		GameRegistry.registerItem(bucketDieselFuel, ReferenceNames.NAME_ITEM_DIESEL_BUCKET);
		GameRegistry.registerItem(itemOreResource, ReferenceNames.NAME_ITEM_ORE_RESOURCE);
		GameRegistry.registerItem(token, ReferenceNames.NAME_ITEM_TOKEN);
	}
}
