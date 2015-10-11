package iceman11a.fuelcraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceman11a.fuelcraft.fluid.FluidTankFuelcraft;
import iceman11a.fuelcraft.gui.GuiCartPainter;
import iceman11a.fuelcraft.gui.GuiDieselEngine;
import iceman11a.fuelcraft.gui.GuiFuelCraftInventory;
import iceman11a.fuelcraft.inventory.ContainerDieselEngine;
import iceman11a.fuelcraft.inventory.ContainerDieselProducer;
import iceman11a.fuelcraft.reference.ReferenceNames;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityDieselTrainEngine  extends GuiFuelCraftInventory{
	
	
	protected TileEntityDieselEngine tileEntityDieselEngine;

	protected FluidStack fluidInput;
	protected FluidStack fluidOutput;

	//masa, Use these as you need them
	public static boolean canMove = false; // Can the diesel engine move
	
	public static int DieselAmount = 0; //Amount of diesel engine has
	public static int EngineTemp = 0; // The engines temp
	public static int EngineSpeed =  0; // the current speed of the engine (MPH = Miles per Hour)
	public static int DieselLeft =  0; // Amount of diesel left
	
	
	public TileEntityDieselTrainEngine(String name) {
		super(ReferenceNames.NAME_TILE_DIESEL_PRODUCER);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ContainerDieselEngine getContainer(InventoryPlayer inventoryPlayer) {
		return new ContainerDieselEngine(inventoryPlayer, null);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiFuelCraftInventory getGui(InventoryPlayer inventoryPlayer) {
		return new GuiDieselEngine(this.getContainer(inventoryPlayer), this);
	}
	

}
