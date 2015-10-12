package iceman11a.fuelcraft.block;

import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFuelcraft extends ItemBlock {

	public ItemBlockFuelcraft(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
			case 0: // Diesel Producer
				return "tile." + ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_DIESEL_PRODUCER);
			case 1: // Cart Painter
				return "tile." + ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_CART_PAINTER);
			case 2: // Token Controller
				return "tile." + ReferenceNames.getPrefixedName(ReferenceNames.NAME_TILE_TOKEN_CONTROLLER);
		}

		return super.getUnlocalizedName(stack);
	}
}
