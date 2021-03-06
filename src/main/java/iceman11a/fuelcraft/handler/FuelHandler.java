package iceman11a.fuelcraft.handler;

import cpw.mods.fml.common.IFuelHandler;
import iceman11a.fuelcraft.item.FuelcraftItems;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == FuelcraftItems.corbamite) {
			return 900;
		}
		else if(fuel.getItem() == FuelcraftItems.corCoal) {
			return 400;
		}
		else
			return 0;
	}
}
