package iceman11a.fuelcraft.tileentity;

import iceman11a.fuelcraft.machines.ReferenceNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityDieselProducer extends TileEntity
{
    protected String tileEntityName;
    protected int rotation;

    public TileEntityDieselProducer()
    {
        this(ReferenceNames.NAME_TILE_ENTITY_DIESEL_PRODUCER);
    }

    public TileEntityDieselProducer(String name)
    {
        super();
        this.rotation = 0;
        this.tileEntityName = name;
    }

    public String getTEName()
    {
        return this.tileEntityName;
    }

    public void setRotation(int rot)
    {
        this.rotation = rot;
    }

    public int getRotation()
    {
        return this.rotation;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        this.rotation = nbt.getByte("Rotation");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setByte("Rotation", (byte)this.rotation);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("r", (byte)(this.getRotation() & 0x07));

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        NBTTagCompound nbt = packet.func_148857_g();

        if (nbt.hasKey("r") == true)
        {
            this.setRotation((byte)(nbt.getByte("r") & 0x07));
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "(x=" + xCoord + ",y=" + yCoord + ",z=" + zCoord + ")@" + System.identityHashCode(this);
    }
}