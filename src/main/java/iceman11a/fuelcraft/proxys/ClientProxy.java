package iceman11a.fuelcraft.proxys;

import iceman11a.fuelcraft.reference.ReferenceReflection;
import net.minecraft.client.gui.inventory.GuiContainer;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerKeyBindings() {
	}

	@Override
	public void registerRenderers() {
	}

	@Override
	public void setupReflection() {
		ReferenceReflection.fieldGuiContainer_theSlot = ReflectionHelper.findField(GuiContainer.class, "u", "field_147006_u", "theSlot");
	}
}
