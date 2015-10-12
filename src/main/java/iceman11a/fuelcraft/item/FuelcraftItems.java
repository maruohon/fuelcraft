package iceman11a.fuelcraft.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelcraftItems {

	public static final Item corbamite = new ItemCorbamite();
	public static final Item bucketDieselFuel = new ItemBucketDiesel();
	public static final Item corCoal = new ItemCorCoal();
	public static final Item token = new ItemToken();

	public static void registerItems()
	{
		GameRegistry.registerItem(corbamite, "Corbamite");
		GameRegistry.registerItem(bucketDieselFuel, "BucketDieselFuel");
		GameRegistry.registerItem(corCoal, "CorCoal");
		GameRegistry.registerItem(token, "Token");
	}
}
