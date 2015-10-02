package iceman11a.fuelcraft.fcBlocks;

import java.util.Random;

import iceman11a.fuelcraft.fuelcraft;
import iceman11a.fuelcraft.fcItems.fcItems;
import iceman11a.fuelcraft.machines.ReferenceNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import iceman11a.fuelcraft.fcBlocks.fcBlocks;

public class BlockCorCoalOre extends Block {
	
	public String blockName;

	protected BlockCorCoalOre(String name, float hardness, Material material) {
				
		super(material);
        this.setHardness(hardness);
        this.setCreativeTab(fuelcraft.tabFuelcraft);
        this.setBlockName(name);
        this.setBlockTextureName("fc:" + name);
			
		//this.setBlockName("BlockCorCoalOre");		
		//this.setCreativeTab(fuelcraft.tabFuelcraft);
		//this.setBlockTextureName("fc:BlockCorCoalOre");
		//this.setHardness(0.5F);
		//this.setLightLevel(0.2F);
	}

    @Override
    public Block setBlockName(String name)
    {
        this.blockName = name;
        return super.setBlockName(ReferenceNames.getPrefixedName(name));
    }

	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return fcItems.CorCoal;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 3 + rand.nextInt(3);
	}
}
