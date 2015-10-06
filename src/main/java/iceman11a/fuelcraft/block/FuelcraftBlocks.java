package iceman11a.fuelcraft.block;

import iceman11a.fuelcraft.block.machine.BlockDieselProducer;
import iceman11a.fuelcraft.block.ore.BlockCorCoalOre;
import iceman11a.fuelcraft.block.ore.BlockCorbamiteOre;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelcraftBlocks {

	public static final Block effectLeaves = new BlockModEffectLeaves("effectLeaves", "effect");
	public static final BlockModFire lightFire = new BlockModFire("lightFire");
	public static final Block lightForestDirt = new BlockBasic(Material.rock, "lightForestDirt", "lightForestDirt", Block.soundTypeGrass);
	public static final Block lightForestGrass = new BlockModLightGrass(Material.rock, "lightForestGrass", Block.soundTypeGrass);
	public static final Block lightLeaves = new BlockModLightLeaves("lightLeaves", "light");
	public static final Block lightLog = new BlockModLightLog("lightLog", "log");
	public static final BlockModPortal lightPortal = new BlockModPortal("lightPortal");
	public static final Block lightSapling = new BlockModLightSapling("lightSapling", "light");
	public static final Block lightStone = new BlockBasic(Material.rock, "lightStone", "lightStone", Block.soundTypeStone);

	public static final Block blockCorbamiteOre = new BlockCorbamiteOre("CorbamiteOre", 0.5F, Material.rock);
	public static final Block blockCorCoalOre = new BlockCorCoalOre("CorCoalOre", 0.5F, Material.rock);

	public static final Block blockDieselProducer = new BlockDieselProducer(ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER, 1.0f, Material.iron);
	
	/**
	 * Register Blocks.
	 */
	public static void registerBlocks(){
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
