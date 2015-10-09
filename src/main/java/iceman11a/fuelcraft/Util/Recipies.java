package iceman11a.fuelcraft.Util;

import iceman11a.fuelcraft.block.FuelcraftBlocks;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.block.machine.BlockDieselProducer;
import iceman11a.fuelcraft.item.FuelcraftItems;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
		//GameRegistry.addRecipe(new ItemStack(Items.coal), "C C", " C ", "   ", 'C', newItemStack(Material.Charcoal));
		
		//Machines
		//2 pcs of redstone, One pc of stone, and 3 pcs of glass
		GameRegistry.addRecipe(new ItemStack(FuelcraftBlocks.blockDieselProducer, 1), "R R", " S ", "GGG", 'R', new ItemStack(Items.redstone), 'S', new ItemStack(Blocks.stone), 'G', new ItemStack(Blocks.glass));
		
		//Items
		GameRegistry.addRecipe(new ItemStack(FuelcraftItems.corbamite, 4), "C C", "   ", " S ", 'C', new ItemStack(Items.coal), 'S', new ItemStack(Blocks.stone));
		
		
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