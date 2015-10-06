package iceman11a.fuelcraft.block;

import iceman11a.fuelcraft.block.machine.BlockDieselProducer;
import iceman11a.fuelcraft.block.ore.BlockCorCoalOre;
import iceman11a.fuelcraft.block.ore.BlockCorbamiteOre;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelcraftBlocks {
	
	public static Block lightStone, lightForestGrass, lightForestDirt, lightLog, lightLeaves, lightSapling, effectLeaves;
	public static BlockModPortal lightPortal;
	public static BlockModFire lightFire;

	public static final Block blockCorbamiteOre = new BlockCorbamiteOre("CorbamiteOre", 0.5F, Material.rock);
	public static final Block blockCorCoalOre = new BlockCorCoalOre("CorCoalOre", 0.5F, Material.rock);

	public static final Block blockDieselProducer = new BlockDieselProducer(ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER, 1.0f, Material.iron);
	
	/**
	 * Load Blocks.
	 */
	public static void loadBlocks(){
		lightForestDirt = new BlockBasic(Material.rock, "lightForestDirt", "lightForestDirt", Block.soundTypeGrass);
		lightForestGrass = new BlockModLightGrass(Material.rock, "lightForestGrass", Block.soundTypeGrass);
		lightStone = new BlockBasic(Material.rock, "lightStone", "lightStone", Block.soundTypeStone);
		lightLog = new BlockModLightLog("lightLog", "log");
		lightLeaves = new BlockModLightLeaves("lightLeaves", "light");
		//Testing for my tree particle effect
		effectLeaves = new BlockModEffectLeaves("effectLeaves", "effect");
		lightSapling = new BlockModLightSapling("lightSapling", "light");
		lightPortal = new BlockModPortal("lightPortal");
		lightFire  = new BlockModFire("lightFire");

		registerBlocks();
	}

	/**
	 * Register Blocks.
	 */
	private static void registerBlocks(){
	    // Terrain blocks
		GameRegistry.registerBlock(lightForestDirt, "lightForestDirt");
		GameRegistry.registerBlock(lightForestGrass, "lightForestGrass");
		GameRegistry.registerBlock(lightStone, "lightStone");
		GameRegistry.registerBlock(lightPortal, "lightPortal");
		GameRegistry.registerBlock(lightFire, "lightFire");
		GameRegistry.registerBlock(lightLog, "lightLog");
		GameRegistry.registerBlock(lightLeaves, "lightLeaves");
		GameRegistry.registerBlock(effectLeaves, "effectLeaves");
		GameRegistry.registerBlock(lightSapling, "lightSapling");

		// Ores
		GameRegistry.registerBlock(blockCorbamiteOre, "BlockCorbamiteOre");
		GameRegistry.registerBlock(blockCorCoalOre, "BlockCorCoalOre");

		// Machines
		GameRegistry.registerBlock(blockDieselProducer, ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER);
	}
}
