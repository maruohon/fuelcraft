package iceman11a.fuelcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCrossingGate extends ModelBase
{
    public ModelRenderer gate;
    public ModelRenderer base;

    public ModelCrossingGate()
    {
        this.gate = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 128);
        this.gate.addBox(-4.0f, -2f, -2.0f, 8, 96, 4, 0.0f);

        // Base plate
        this.base = new ModelRenderer(this, 0, 100).setTextureSize(64, 128);
        this.base.addBox(-4f, -16f, -4f, 8, 2, 8, 0.0f);

        // Pole
        ModelRenderer tmp = new ModelRenderer(this, 0, 110).setTextureSize(64, 128);
        tmp.addBox(-2f, -14f, -1f, 4, 11, 2, 0.0f);
        this.base.addChild(tmp);

        // Top bit
        tmp = new ModelRenderer(this, 32, 100).setTextureSize(64, 128);
        tmp.addBox(-2.5f, -3f, -1.5f, 5, 3, 3, 0.0f);
        this.base.addChild(tmp);
    }

    public void renderAll()
    {
        this.base.render(0.0625f);
        this.gate.render(0.0625f);
    }
}
