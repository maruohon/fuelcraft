package iceman11a.fuelcraft.block;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceman11a.fuelcraft.Fuelcraft;
import iceman11a.fuelcraft.reference.ReferenceGuiIds;
import iceman11a.fuelcraft.reference.ReferenceNames;
import iceman11a.fuelcraft.reference.ReferenceTextures;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;
import iceman11a.fuelcraft.tileentity.TileEntityFuelCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockCrossingGate extends BlockFuelcraftBase
{
    public static final byte YAW_TO_DIRECTION[] = {2, 5, 3, 4};

    public BlockCrossingGate(String name, float hardness, Material material)
    {
        super(name, hardness, material);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        switch (metadata)
        {
            case 0:
                return new TileEntityCrossingGate();
        }

        return null;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack stack)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null || (te instanceof TileEntityFuelCraft) == false)
        {
            return;
        }

        int yaw = MathHelper.floor_double((double)(livingBase.rotationYaw * 4.0f / 360.0f) + 0.5d) & 3;
        // Store the rotation to the TileEntity
        if (yaw < YAW_TO_DIRECTION.length)
        {
            ((TileEntityFuelCraft)te).setRotation(YAW_TO_DIRECTION[yaw]);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offsetX, float offsetY, float offsetZ)
    {
        PlayerInteractEvent e = new PlayerInteractEvent(player, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, x, y, z, side, world);
        if (MinecraftForge.EVENT_BUS.post(e) || e.getResult() == Result.DENY || e.useBlock == Result.DENY)
        {
            return false;
        }

        if (world.isRemote == true)
        {
            Fuelcraft.proxy.openGui(ReferenceGuiIds.GUI_ID_CROSSING_GATES);
        }

        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);

        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityFuelCraft)
        {
            ((TileEntityFuelCraft)te).onBlockNeighbourChange();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(this, 1, 0)); // Crossing Gate
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType() {
        return super.getRenderType();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        // These are for the rendering in ItemBlock form in inventories etc.

        int index = 0;
        int offset = meta * 6;

        if (side == 0 || side == 1)
        {
            index = offset + 1; // top
        }
        else if (side == 3)
        {
            index = offset + 2; // front
        }
        else
        {
            index = offset + 4; // side (left)
        }

        return index < this.icons.length ? this.icons[index] : this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        int meta = blockAccess.getBlockMetadata(x, y, z);
        int index = meta * 6 + side;

        return index < this.icons.length ? this.icons[index] : this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[6];
        this.blockIcon = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".top"); // Fallback

        // Crossing Gates
        this.icons[0] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".bottom");
        this.icons[1] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".top");
        this.icons[2] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".front");
        this.icons[3] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".back");
        this.icons[4] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".right");
        this.icons[5] = iconRegister.registerIcon(ReferenceTextures.getTileName(ReferenceNames.NAME_TILE_CROSSING_GATE) + ".left");
    }
}
