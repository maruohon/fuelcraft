package iceman11a.fuelcraft.block.ore;

import iceman11a.fuelcraft.block.BlockFuelcraftBase;
import iceman11a.fuelcraft.item.FuelcraftItems;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCorbamiteOre extends BlockFuelcraftBase
{

	public BlockCorbamiteOre(String name, float hardness, Material material) {
		super(name, hardness, material);
		this.setLightLevel(0.2F);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return FuelcraftItems.corbamite;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 3 + rand.nextInt(3);
	}
}
