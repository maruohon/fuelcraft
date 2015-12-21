package iceman11a.fuelcraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCrossingGate extends TileEntityFuelCraft {

    public static final int STATE_NOT_MOVING =  0;
    public static final int STATE_OPEN       = -1;
    public static final int STATE_CLOSED     =  1;

    protected AxisAlignedBB area;
    protected boolean cartsPassing;

    @SideOnly(Side.CLIENT)
    public boolean renderArea;
    @SideOnly(Side.CLIENT)
    public int movingTo;
    @SideOnly(Side.CLIENT)
    public long timeStart;
    @SideOnly(Side.CLIENT)
    public float angle;

    public TileEntityCrossingGate() {
        super(ReferenceNames.NAME_TILE_CROSSING_GATE);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.setArea(nbt.getInteger("BBMinX"), nbt.getInteger("BBMinY"), nbt.getInteger("BBMinZ"), nbt.getInteger("BBMaxX"), nbt.getInteger("BBMaxY"), nbt.getInteger("BBMaxZ"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (this.area != null)
        {
            nbt.setInteger("BBMinX", (int)this.area.minX);
            nbt.setInteger("BBMinY", (int)this.area.minY);
            nbt.setInteger("BBMinZ", (int)this.area.minZ);
            nbt.setInteger("BBMaxX", (int)this.area.maxX);
            nbt.setInteger("BBMaxY", (int)this.area.maxY);
            nbt.setInteger("BBMaxZ", (int)this.area.maxZ);
        }
    }

    @Override
    public void onBlockNeighbourChange() {
        //this.redstoneState = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
        this.checkForCarts();
    }

    @Override
    public NBTTagCompound getDescriptionPacketTag(NBTTagCompound nbt) {
        nbt = super.getDescriptionPacketTag(nbt);

        nbt.setBoolean("carts", this.cartsPassing);

        if (this.area != null)
        {
            nbt.setIntArray("bb", new int[] { (int)this.area.minX, (int)this.area.minY, (int)this.area.minZ, (int)this.area.maxX, (int)this.area.maxY, (int)this.area.maxZ });
        }

        return nbt;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);

        NBTTagCompound nbt = packet.func_148857_g();
        if (nbt.hasKey("carts", Constants.NBT.TAG_BYTE))
        {
            boolean carts = nbt.getBoolean("carts");
            if (carts != this.cartsPassing)
            {
                if (carts == true)
                {
                    this.movingTo = STATE_CLOSED;
                }
                else
                {
                    this.movingTo = STATE_OPEN;
                }

                this.timeStart = System.currentTimeMillis();
            }

            this.cartsPassing = carts;
        }

        if (nbt.hasKey("bb", Constants.NBT.TAG_INT_ARRAY))
        {
            int[] bb = nbt.getIntArray("bb");
            if (bb.length == 6)
            {
                this.setArea(bb[0], bb[1], bb[2], bb[3], bb[4], bb[5]);
            }
        }
    }

    @Override
    public void setRotation(int rot) {
        super.setRotation(rot);

        this.initArea();
    }

    public void initArea()
    {
        ForgeDirection dir = ForgeDirection.getOrientation(this.getRotation()).getOpposite();
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1);
        this.area = bb.expand(2.0d, 2.0d, 2.0d).offset(dir.offsetX * 3.0d, dir.offsetY * 3.0d, dir.offsetZ * 3.0d);
    }

    public void setArea(int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
    {
        int r = 32;
        minX = MathHelper.clamp_int(minX, this.xCoord - r, this.xCoord + r);
        minY = MathHelper.clamp_int(minY, this.yCoord - r, this.yCoord + r);
        minZ = MathHelper.clamp_int(minZ, this.zCoord - r, this.zCoord + r);
        maxX = MathHelper.clamp_int(maxX, this.xCoord - r, this.xCoord + r);
        maxY = MathHelper.clamp_int(maxY, this.yCoord - r, this.yCoord + r);
        maxZ = MathHelper.clamp_int(maxZ, this.zCoord - r, this.zCoord + r);

        this.area = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public AxisAlignedBB getArea()
    {
        if (this.area == null)
        {
            this.initArea();
        }

        return this.area;
    }

    protected void checkForCarts()
    {
        boolean carts = this.worldObj.getEntitiesWithinAABB(EntityMinecart.class, this.getArea()).isEmpty() == false;
        if (carts != this.cartsPassing)
        {
            this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        this.cartsPassing = carts;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}
