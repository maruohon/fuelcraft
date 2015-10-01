package iceman11a.fuelcraft.fcBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import iceman11a.fuelcraft.fuelcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;


public class fcBlocks extends Block{
	
	
	public static Block CorbamiteOre = new CorbamiteOre(Material.iron);
		

	protected fcBlocks(Material material) {
		super(material.iron);			
		
	}

	
	public void RegisterBlocks(){
	
	GameRegistry.registerBlock(CorbamiteOre, "CorbamiteOre");
	
	}
}
