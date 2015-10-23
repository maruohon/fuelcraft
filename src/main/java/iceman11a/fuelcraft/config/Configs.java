package iceman11a.fuelcraft.config;

import iceman11a.fuelcraft.Fuelcraft;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Configs {
	public static Property propBiomeIdBlue;
	public static Property propBiomeIdDark;
	public static Property propBiomeIdLight;
	public static Property propDimensionIdLightForest;

	public static Property propDieselProducerOilPerDieselBucket;
	public static Property propDieselProducerEnergyPerDieselBucket;
	public static Property propOilProducerTapoilPerOilBucket;
	public static Property propOilProducerEnergyPerOilBucket;

	public static int biomeIdBlue;
	public static int biomeIdDark;
	public static int biomeIdLight;
	public static int diomensionIdLightForest;

	public static int dieselProducerOilPerDieselBucket;
	public static int dieselProducerEnergyPerDieselBucket;
	public static int oilProducerTapoilPerOilBucket;
	public static int oilProducerEnergyPerOilBucket;

	public static void loadConfigs(File configFile) {
		Fuelcraft.logger.info("Loading configuration...");

		Configuration conf = new Configuration(configFile, null, true); // true: Use case sensitive category names
		conf.load();

		String category = "BiomeIDs";
		propBiomeIdBlue = conf.get(category, "blueForestBiome", 52).setRequiresMcRestart(false);
		biomeIdBlue = propBiomeIdBlue.getInt();

		propBiomeIdDark = conf.get(category, "darkForestBiome", 51).setRequiresMcRestart(false);
		biomeIdDark = propBiomeIdDark.getInt();

		propBiomeIdLight = conf.get(category, "lightForestBiome", 50).setRequiresMcRestart(false);
		biomeIdLight = propBiomeIdLight.getInt();

		category = "DimensionIDs";
		propDimensionIdLightForest = conf.get(category, "lightDimension", 35).setRequiresMcRestart(false);
		diomensionIdLightForest = propDimensionIdLightForest.getInt();

		category = "Generic";
		propDieselProducerOilPerDieselBucket = conf.get(category, "dieselProducerOilPerDieselBucket", 4000).setRequiresMcRestart(false);
		propDieselProducerOilPerDieselBucket.comment = "The amount of Oil required to make one bucket of Diesel, in millibuckets. Default: 4000 (= 4 buckets)";
		dieselProducerOilPerDieselBucket = propDieselProducerOilPerDieselBucket.getInt();

		propDieselProducerEnergyPerDieselBucket = conf.get(category, "dieselProducerEnergyPerDieselBucket", 100000).setRequiresMcRestart(false);
		propDieselProducerEnergyPerDieselBucket.comment = "The amount of RF energy required to make one bucket of Diesel. Default: 100000";
		dieselProducerEnergyPerDieselBucket = propDieselProducerEnergyPerDieselBucket.getInt();

		propOilProducerTapoilPerOilBucket = conf.get(category, "oilProducerTapoilPerOilBucket", 2000).setRequiresMcRestart(false);
		propOilProducerTapoilPerOilBucket.comment = "The amount of Tapoil required to make one bucket of Oil, in millibuckets. Default: 2000 (= 2 buckets)";
		oilProducerTapoilPerOilBucket = propOilProducerTapoilPerOilBucket.getInt();

		propOilProducerEnergyPerOilBucket = conf.get(category, "oilProducerEnergyPerOilBucket", 100000).setRequiresMcRestart(false);
		propOilProducerEnergyPerOilBucket.comment = "The amount of RF energy required to make one bucket of Oil. Default: 100000";
		oilProducerEnergyPerOilBucket = propOilProducerEnergyPerOilBucket.getInt();

		if (conf.hasChanged()) {
			conf.save();
		}
	}
}
