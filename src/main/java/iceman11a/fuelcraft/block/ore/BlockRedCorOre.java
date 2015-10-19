package iceman11a.fuelcraft.block.ore;

import java.util.Random;

import iceman11a.fuelcraft.block.BlockFuelcraftBase;
import iceman11a.fuelcraft.item.FuelcraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;



public class BlockRedCorOre extends BlockFuelcraftBase {


	public BlockRedCorOre(String name, float hardness, Material material) {		
		super(name, hardness, material);
		this.setLightLevel(0.2F);		
		// TODO Auto-generated constructor stub
	}	

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
	return FuelcraftItems.RedCor;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 3 + rand.nextInt(3);
	}
}