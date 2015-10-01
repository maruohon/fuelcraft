package iceman11a.fuelcraft.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceman11a.fuelcraft.fuelcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockDieselProducer extends Block {
	
	
	 	public String blockName;
	    @SideOnly(Side.CLIENT)
	    protected IIcon[] icons;

	    public BlockDieselProducer(String name, float hardness)
	    {
	        this(name, hardness, Material.rock);
	        this.setStepSound(soundTypeStone);
	    }

	    public BlockDieselProducer(String name, float hardness, Material material)
	    {
	        super(material);
	        this.setHardness(hardness);
	        this.setCreativeTab(fuelcraft.tabFuelcraft);
	        this.setBlockName(name);
	    }


	    @Override
	    public Block setBlockName(String name)
	    {
	        this.blockName = name;
	        return super.setBlockName(ReferenceNames.getPrefixedName(name));
	    }

	    @Override
	    public int damageDropped(int meta)
	    {
	        return meta;
	    }

	    @SideOnly(Side.CLIENT)
	    @Override
	    public IIcon getIcon(int side, int meta)
	    {
	        // These are for the rendering in ItemBlock form in inventories etc.

	        if (side == 0 || side == 1)
	        {
	            return this.icons[1]; // top
	        }
	        if (side == 3)
	        {
	            return this.icons[2]; // front
	        }

	        return this.icons[4]; // side (left)
	    }

	    @SideOnly(Side.CLIENT)
	    @Override
	    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	    {
	        // FIXME we should get the proper side (left, right, back) textures based on the TE rotation and the side argument
	        if (side == 0 || side == 1)
	        {
	            return this.icons[side];
	        }

	        /*TileEntity te = blockAccess.getTileEntity(x, y, z);
	        // Here we should get the rotation of the block to decide when to return the front texture, and when the sides
	        if (te instanceof TileEntityFluidCraft && side == ((TileEntityFluidCraft)te).getRotation())
	        {
	            return this.icons[2]; // front
	        }*/

	        return this.icons[4];
	    }

	    @SideOnly(Side.CLIENT)
	    @Override
	    public void registerBlockIcons(IIconRegister iconRegister)
	    {
	        this.icons = new IIcon[6];
	        this.icons[0] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".side");
	        this.icons[1] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".side");
	        this.icons[2] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".front");
	        this.icons[3] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".side");
	        this.icons[4] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".side");
	        this.icons[5] = iconRegister.registerIcon(ReferenceTextures.getTileName(this.blockName) + ".side");
	    }
	    
}
