package iceman11a.fuelcraft.fcBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import iceman11a.fuelcraft.fuelcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import iceman11a.fuelcraft.fcBlocks.BlockCorbamiteOre;
import iceman11a.fuelcraft.machines.ReferenceNames;
import iceman11a.fuelcraft.fcBlocks.BlockCorCoalOre;


public class fcBlocks extends Block{
	
	
	public static Block BlockCorbamiteOre = new BlockCorbamiteOre(ReferenceNames.NAME_TILE_CORBAMITORE, 1.0f, Material.iron);
	public static Block BlockCorCoalOre = new BlockCorCoalOre(ReferenceNames.NAME_TILE_CORCOALORE, 1.0f, Material.iron); 
		
	protected fcBlocks(Material material) {
		super(material);			
		
	}
	
	public void RegisterBlocks(){
	
	GameRegistry.registerBlock(BlockCorbamiteOre, "BlockCorbamiteOre");
	GameRegistry.registerBlock(BlockCorCoalOre, "BlockCorCoalOre");
	
	}
}
