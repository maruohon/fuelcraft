package iceman11a.fuelcraft.Util;

import iceman11a.fuelcraft.block.FuelcraftBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipies {

	/**
	 * Register Recipies with Game Registry.
	 */
	public static void registerRecipies() {
		addShapelessRecipies();
		addShapedRecipies();
	}

	/**
	 * Add Shaped Recpies.
	 */
	private static void addShapedRecipies() {
		//GameRegistry.addRecipe(new ItemStack(TutorialBlocks.darkStone), "   ", " X ", "   ", 'X', newItemStack(Blocks.stone));
	}

	/**
	 * Add Shapless Recpies.
	 */
	private static void addShapelessRecipies() {
		ItemStack dirt = new ItemStack(Blocks.dirt);
		ItemStack cobble = new ItemStack(Blocks.cobblestone);
		GameRegistry.addShapelessRecipe(new ItemStack(FuelcraftBlocks.lightStone, 1), dirt, dirt, cobble, cobble);
	}
}