package iceman11a.fuelcraft;

import iceman11a.fuelcraft.Util.GameLogger;
import iceman11a.fuelcraft.Util.Recipies;
import iceman11a.fuelcraft.block.FuelcraftBlocks;
import iceman11a.fuelcraft.block.fluid.FuelcraftFluids;
import iceman11a.fuelcraft.config.ModConfig;
import iceman11a.fuelcraft.events.EventHelper;
import iceman11a.fuelcraft.gui.GuiHandler;
import iceman11a.fuelcraft.handler.FuelHandler;
import iceman11a.fuelcraft.item.FuelcraftItems;
import iceman11a.fuelcraft.proxys.ServerProxy;
import iceman11a.fuelcraft.reference.Reference;
import iceman11a.fuelcraft.world.Dimension;
import iceman11a.fuelcraft.world.WorldTypesTutorial;
import iceman11a.fuelcraft.world.biomes.ModBiomes;
import iceman11a.fuelcraft.worldgen.OreGeneration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class Fuelcraft {
	public static OreGeneration eventWorldGen = new OreGeneration();

	@Instance(Reference.MOD_ID)
	public static Fuelcraft instance;
	public static Logger logger;

	@SidedProxy(clientSide="iceman11a.fuelcraft.proxys.ClientProxy", serverSide="iceman11a.fuelcraft.proxys.ServerProxy")
	private static ServerProxy proxy;	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		GameLogger.createFolders();
		ModConfig.createTutConfig();
		FuelcraftBlocks.registerBlocks();
		FuelcraftFluids.registerFluids();
		FuelcraftItems.registerItems();
		// The fluid container needs the bucket item to be registered, and that one needs the fluid block to be registered
		// So the registration order here needs to be fluids -> items -> fluid containers
		FuelcraftFluids.registerFluidContainers();

		proxy.registerTileEntities();
		proxy.setupReflection();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModBiomes.registerWithBiomeDictionary();
		Dimension.registerWorldProvider();
		Dimension.registerDimensions();
		WorldTypesTutorial.addCustomWorldTypes();
		EventHelper.registerEvents();

		proxy.registerRenderers(); // This is blank, Nothing in it

		GameRegistry.registerWorldGenerator(eventWorldGen,  0);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		GameRegistry.registerFuelHandler(new FuelHandler());
	}

	@EventHandler
	public static void postLoad(FMLPostInitializationEvent evt) {
		Recipies.registerRecipies();
	}

	public static CreativeTabs tabFuelcraft = new CreativeTabs("tabFuelcraft") {
		@Override
		public Item getTabIconItem(){
			return FuelcraftItems.itemOreResource;
		}
	};
}
