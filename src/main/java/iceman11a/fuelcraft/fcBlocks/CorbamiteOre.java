package iceman11a.fuelcraft.fcBlocks;

import iceman11a.fuelcraft.fuelcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CorbamiteOre extends Block {

	public CorbamiteOre(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
		
		this.setBlockName("CorbamiteOre");
		this.setCreativeTab(fuelcraft.tabFuelcraft);
		this.setBlockTextureName("fc:CorbamireOre");
	}

}
