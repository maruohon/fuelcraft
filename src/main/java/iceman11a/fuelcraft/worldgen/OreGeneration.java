package iceman11a.fuelcraft.worldgen;

import iceman11a.fuelcraft.block.FuelcraftBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;


public class OreGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId)
		{
			case 0:
				generateOverworld(world, random, chunkX * 16, chunkZ * 16);
				break;
			case -1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
			case -99:
				generateLight(world, random, chunkX, chunkZ);
				break;
			default:
		}
	}

	public void generateEnd(World world, Random rand, int x, int z) {
		this.generateOre(FuelcraftBlocks.blockCorbamiteOre, world, rand, x, z, 16, 16, 6 + rand.nextInt(16), 25, 38, 100);
	}

	public void generateOverworld(World world, Random rand, int x, int z) {
		this.generateOre(FuelcraftBlocks.blockCorbamiteOre, world, rand, x, z, 16, 16, 6 + rand.nextInt(16), 25, 38, 100);
	}

	public void generateNether(World world, Random rand, int x, int z) {
		this.generateOre(FuelcraftBlocks.blockCorbamiteOre, world, rand, x, z, 16, 16, 6 + rand.nextInt(16), 25, 38, 100);
	}

	public void generateLight(World world, Random rand, int x, int z) {
		this.generateOre(FuelcraftBlocks.blockCorbamiteOre, world, rand, x, z, 16, 16, 6 + rand.nextInt(16), 25, 38, 100);
		this.generateOre(FuelcraftBlocks.blockCorCoalOre, world, rand, x, z, 16, 16, 6 + rand.nextInt(16), 25, 38, 100);
	}

	public void generateOre(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVienSize, int chanceToSpawn, int minY, int maxY) {

		for(int i = 0; i < chanceToSpawn; i++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);

			(new WorldGenMinable(block, maxVienSize)).generate(world, random, posX, posY, posZ);
		}
	}
}
