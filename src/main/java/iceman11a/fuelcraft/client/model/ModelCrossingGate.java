package iceman11a.fuelcraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelCrossingGate extends ModelBase
{
    public ModelRenderer gate;
    public ModelRenderer base;

    public ModelCrossingGate()
    {
        this.gate = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 32);
        this.gate.addBox(0.0f, 0.0f, 0.0f, 4, 80, 2, 0.0f);

        this.base = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 32);
        this.base.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16, 0.0f);
    }

    public void renderAll()
    {
        //this.base.render(0.0625f);
        this.gate.render(0.0625f);
    }
}
