package iceman11a.fuelcraft.config;

import iceman11a.fuelcraft.Util.GameLogger;
import iceman11a.fuelcraft.world.DimensionIDs;
import iceman11a.fuelcraft.world.biomes.BiomeIDs;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfig extends Configuration {

	//TADO: Config needs testing to see if it still uses ids if there changed or if it replaces the config file ?.

	/**
	 * Path for .minecraft folder
	 */
	private static String dir;
	private static Configuration config;

	/**
	 * Creates Config file in custom folder in .minecraft folder.
	 */
	public static void createTutConfig() {
		// My custom folder directory and config file name for the Config file.
		dir = GameLogger.getFilePath() + "Tutorial/Configs/";
		File file = new File(dir + "ConfiurationFile.cfg");
		config = new Configuration(file);

		// Load and add ids to config then save when finished.
		config.load();

		addDimensionIDs();
		addBiomeIDs();
		config.save();
	}

	/**
	 * Add Biome IDs to config file.
	 */
	private static void addBiomeIDs() {
		BiomeIDs.LIGHT = config.get(ConfigTypes.BIOMES, "lightForestBiome", 50).getInt();
		BiomeIDs.BLUE = config.get(ConfigTypes.BIOMES, "blueForestBiome", 52).getInt();
		BiomeIDs.DARK = config.get(ConfigTypes.BIOMES, "darkForestBiome", 51).getInt();
	}

	/**
	 * Add Dimension IDs to config file.
	 */
	private static void addDimensionIDs() {
		DimensionIDs.LIGHTFORESTDIMENSION = config.get(ConfigTypes.DIMENSION, "lightDimension", 35).getInt();
	}

	/**
	 * Gets the Configuration file
	 * @return config
	 */
	public static Configuration getConfig() {
		return config;
	}
}
