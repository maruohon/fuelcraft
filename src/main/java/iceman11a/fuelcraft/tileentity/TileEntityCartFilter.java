package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.gui.GuiCartFilter;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerCartFilter;
import iceman11a.fuelcraft.reference.ReferenceNames;

import java.util.List;

import mods.railcraft.common.carts.CartUtils;
import mods.railcraft.common.carts.EntityCartFiltered;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.carts.ICartType;
import mods.railcraft.common.util.crafting.CartFilterRecipe;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCartFilter extends TileEntityFuelCraftInventory
{
    public static final int SLOT_CART_FILTER = 0;
    public static final int SLOT_FILTER_ITEM = 1;

    protected boolean redstoneState;

    public TileEntityCartFilter()
    {
        super(ReferenceNames.NAME_TILE_CART_FILTER);
        this.itemStacks = new ItemStack[2];
    }

    @Override
    public void updateEntity()
    {
        if (this.worldObj.isRemote == false && this.redstoneState == true)
        {
            this.triggerAction();
        }
    }

    @Override
    public void onBlockNeighbourChange()
    {
        this.redstoneState = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
    }

    @SuppressWarnings("unchecked")
    public boolean triggerAction()
    {
        if (this.itemStacks[SLOT_CART_FILTER] != null)
        {
            // The cart type filter item is not a valid cart
            if (EnumCart.getCartType(this.itemStacks[SLOT_CART_FILTER]) == null)
            {
                return false;
            }
        }

        ForgeDirection dir = ForgeDirection.getOrientation(this.getRotation());
        int x = this.xCoord + dir.offsetX;
        int y = this.yCoord;
        int z = this.zCoord + dir.offsetZ;

        boolean ret = false;
        double r = 1.0d;
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x, y, z, x + r, y + r, z + r);
        List<EntityCartFiltered> list = this.worldObj.getEntitiesWithinAABB(EntityCartFiltered.class, aabb);

        for (EntityCartFiltered cart : list)
        {
            if (this.itemStacks[SLOT_CART_FILTER] == null || CartUtils.doesCartMatchFilter(this.itemStacks[SLOT_CART_FILTER], cart) == true)
            {
                CartFilterRecipe.FilterType filterType = CartFilterRecipe.FilterType.fromCartType(cart.getCartType());

                if (this.itemStacks[SLOT_FILTER_ITEM] == null ||
                   (filterType != null && filterType.isAllowedFilterItem(this.itemStacks[SLOT_FILTER_ITEM])))
                {
                    //System.out.println("setting filter");
                    cart.setFilter(this.itemStacks[SLOT_FILTER_ITEM]);
                    ret = true;
                }
            }
        }

        return ret;
    }

    @Override
    public boolean isItemValidForSlot(int slotNum, ItemStack stack)
    {
        if (stack == null)
        {
            return true;
        }

        if (slotNum == SLOT_CART_FILTER)
        {
            ICartType cartType = EnumCart.getCartType(stack);
            return EnumCart.CARGO.equals(cartType) || EnumCart.TANK.equals(cartType);
        }

        // Filter item slots
        if (this.itemStacks[SLOT_CART_FILTER] != null)
        {
            CartFilterRecipe.FilterType filterType = CartFilterRecipe.FilterType.fromCartType(EnumCart.getCartType(this.itemStacks[SLOT_CART_FILTER]));
            return filterType != null && filterType.isAllowedFilterItem(stack);
        }

        return true;
    }

    @Override
    public ContainerCartFilter getContainer(InventoryPlayer inventoryPlayer)
    {
        return new ContainerCartFilter(inventoryPlayer, this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer)
    {
        return new GuiCartFilter(this.getContainer(inventoryPlayer), this);
    }
}
