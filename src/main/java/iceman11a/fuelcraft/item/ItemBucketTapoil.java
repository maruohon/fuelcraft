package iceman11a.fuelcraft.item;

import net.minecraft.item.Item;

import iceman11a.fuelcraft.Fuelcraft;
import iceman11a.fuelcraft.block.fluid.FuelcraftFluids;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.reference.ReferenceTextures;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemBucketTapoil extends ItemBucket {

	public ItemBucketTapoil() {
		this(FuelcraftFluids.blockFluidDiesel);
	}

	public ItemBucketTapoil(Block fluidBlock) {
		super(fluidBlock);
		setMaxStackSize(1);
		setContainerItem(Items.bucket);
		setCreativeTab(Fuelcraft.tabFuelcraft);
		this.setUnlocalizedName(ReferenceNames.getPrefixedName("BucketTapoil"));
		this.setTextureName(ReferenceTextures.getItemTextureName("BucketTapoil"));
	}
}
