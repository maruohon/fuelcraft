package iceman11a.fuelcraft.reference;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

	public static ResourceLocation getResourceLocation(String modId, String path) {
		return new ResourceLocation(modId, path);
	}

	public static ResourceLocation getResourceLocation(String path) {
		return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
	}
}
