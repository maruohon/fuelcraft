package iceman11a.fuelcraft.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import iceman11a.fuelcraft.client.model.ModelCrossingGate;
import iceman11a.fuelcraft.client.renderer.RenderUtils;
import iceman11a.fuelcraft.reference.Reference;
import iceman11a.fuelcraft.tileentity.TileEntityCrossingGate;

public class TileEntityRendererCrossingGate extends TileEntitySpecialRenderer
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/model/crossing_gate.png");
    private ModelCrossingGate model = new ModelCrossingGate();
    private float r = 0.0f;
    private float g = 0.4f;
    private float b = 0.8f;
    private float a = 0.9f;

    private void setColors(float r, float g, float b, float a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /*public void renderBeamVertical(double x, double y, double z, double yMin, double yMax, double radius, double rot, double flowSpeed, boolean powered)
    {
        Tessellator tessellator = Tessellator.instance;
        double tx1 = 0.0d, tx2 = 0.0d;
        double tz1 = 0.0d, tz2 = 0.0d;
        double angle = 0.0d;

        double vScale = yMax - yMin;
        double v1 = -rot * flowSpeed;
        double v2 = (vScale * 2.0d) + v1;

        int r_i = (powered ? 160 : 255);
        int g_i = (powered ? 255 : 160);
        int b_i = (powered ? 230 : 160);
        int r_o = (powered ? 210 : 255);
        int g_o = (powered ? 255 : 160);
        int b_o = (powered ? 230 : 160);

        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
        this.bindTexture(TEXTURE);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0f);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0f);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        OpenGlHelper.glBlendFunc(770, 1, 1, 0);

        // Beam (inner part)
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r_i, g_i, b_i, 200);

        for (int i = 0; i < 8; ++i)
        {
            tx1 = Math.sin(rot + angle) * radius;
            tz1 = Math.cos(rot + angle) * radius;
            angle += Math.PI / 4.0d;
            tx2 = Math.sin(rot + angle) * radius;
            tz2 = Math.cos(rot + angle) * radius;
            tessellator.addVertexWithUV(tx1, yMin, tz1, 0.125, v1);
            tessellator.addVertexWithUV(tx1, yMax, tz1, 0.125, v2);
            tessellator.addVertexWithUV(tx2, yMax, tz2, 0.875, v2);
            tessellator.addVertexWithUV(tx2, yMin, tz2, 0.875, v1);
        }

        tessellator.draw();

        // Glow (outer part)
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glDepthMask(false);

        v1 = -rot * flowSpeed * 3.0d;
        v2 = (vScale * 2.0d) + v1;
        radius *= 2.0d;
        rot = Math.PI / 8.0d;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r_o, g_o, b_o, 80);

        for (int i = 0; i < 8; ++i)
        {
            tx1 = Math.sin(rot + angle) * radius;
            tz1 = Math.cos(rot + angle) * radius;
            angle += Math.PI / 4.0d;
            tx2 = Math.sin(rot + angle) * radius;
            tz2 = Math.cos(rot + angle) * radius;
            tessellator.addVertexWithUV(tx1, yMin, tz1, 0.125, v1);
            tessellator.addVertexWithUV(tx1, yMax, tz1, 0.125, v2);
            tessellator.addVertexWithUV(tx2, yMax, tz2, 0.875, v2);
            tessellator.addVertexWithUV(tx2, yMin, tz2, 0.875, v1);
        }

        tessellator.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }*/

    public void renderArea(double x, double y, double z, AxisAlignedBB aabb, float pTicks)
    {
        //GL11.glColorMask(true, true, true, true);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);

        //GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

        //GL11.glEnable(GL11.GL_ALPHA_TEST);
        //GL11.glDisable(GL11.GL_FOG);
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPushMatrix();
        //GL11.glColor4f(0.0f, 0.4f, 0.8f, 0.6f);

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        float playerOffsetX = -(float) (player.lastTickPosX + (player.posX - player.lastTickPosX) * pTicks);
        float playerOffsetY = -(float) (player.lastTickPosY + (player.posY - player.lastTickPosY) * pTicks);
        float playerOffsetZ = -(float) (player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * pTicks);

        //GL11.glTranslated(x, y, z);
        GL11.glTranslatef(playerOffsetX, playerOffsetY, playerOffsetZ);

        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glColor4f(this.r, this.g, this.b, 1.0f);

        RenderUtils.renderAABB(aabb);

        GL11.glColor4f(this.r, this.g, this.b, this.a);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glEnable(GL11.GL_BLEND);

        RenderUtils.renderAABB(aabb);
        //System.out.printf("%6.2f %6.2f %6.2f %6.2f %6.2f %6.2f\n", aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
        GL11.glPolygonOffset(-1.f, -1.f);

        //GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);


        /*double x1, double y1, double z1, double x2, double y2, double z2
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        //GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);*/
        //GL11.glDepthMask(true);
        //OpenGlHelper.glBlendFunc(770, 1, 1, 0);

        /*Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setColorRGBA(200, 200, 255, 200);

        t.addVertex(x1, y1, z1);
        t.addVertex(x2, y1, z1);
        t.addVertex(x2, y2, z1);
        t.addVertex(x1, y2, z1);

        t.addVertex(x1, y1, z1);
        t.addVertex(x1, y1, z2);
        t.addVertex(x1, y2, z2);
        t.addVertex(x1, y2, z1);

        t.addVertex(x1, y1, z1);
        t.addVertex(x2, y1, z1);
        t.addVertex(x2, y1, z2);
        t.addVertex(x1, y1, z2);

        t.addVertex(x2, y1, z1);
        t.addVertex(x2, y1, z2);
        t.addVertex(x2, y2, z2);
        t.addVertex(x2, y2, z1);

        t.addVertex(x1, y1, z2);
        t.addVertex(x2, y1, z2);
        t.addVertex(x2, y2, z2);
        t.addVertex(x1, y2, z2);

        t.addVertex(x1, y2, z1);
        t.addVertex(x2, y2, z1);
        t.addVertex(x2, y2, z2);
        t.addVertex(x1, y2, z2);

        t.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();*/
    }

    public void renderGate(double x, double y, double z, int facing, float angle, float pTicks)
    {
        GL11.glPushMatrix();
        this.bindTexture(TEXTURE);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        //GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslated(x, y, z);

        float rotation = 0.0f;

        switch (facing) {
            case 2: // North
                rotation = 180.0f;
                break;
            case 3: // South
                rotation = 0.0f;
                break;
            case 4: // West
                rotation = -90.0f;
                break;
            case 5: // East
                rotation = 90.0f;
                break;
        }

        GL11.glRotatef(rotation, 0.0f, 1.0f, 0.0f);

        model = new ModelCrossingGate(); // FIXME remove after debugging the models
        this.model.gate.rotateAngleZ = (float)(angle * Math.PI / 180.0f);
        this.model.renderAll();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public void updateAngle(TileEntityCrossingGate te)
    {
        float moveTimeMs = 2000;

        if (te.movingToLast != te.movingTo)
        {
            te.startAngle = te.currentAngle;
            te.movingToLast = te.movingTo;
        }

        if (te.movingTo != TileEntityCrossingGate.STATE_NOT_MOVING)
        {
            float angleDiff = ((System.currentTimeMillis() - te.timeStart) * 90 / moveTimeMs);

            // moveTimeMs to move from one end position to other end position
            if (te.movingTo == TileEntityCrossingGate.STATE_OPEN)
            {
                te.currentAngle = te.startAngle - angleDiff;
            }
            else if (te.movingTo == TileEntityCrossingGate.STATE_CLOSED)
            {
                te.currentAngle = te.startAngle + angleDiff;
            }

            te.currentAngle = MathHelper.clamp_float(te.currentAngle, 0.0f, 90.0f);

            if ((System.currentTimeMillis() - te.timeStart) > 100 && (te.currentAngle == 0.0f || te.currentAngle == 90.0f))
            {
                te.movingTo = TileEntityCrossingGate.STATE_NOT_MOVING;
                te.movingToLast = te.movingTo;
            }
        }
    }

    public void renderTileEntityAt(TileEntityCrossingGate te, double x, double y, double z, float pTicks)
    {
        this.renderGate(x + 0.5d, y + 1.0d, z + 0.5d, te.getRotation(), te.currentAngle, pTicks);
        this.updateAngle(te);

        if (te.renderArea == true)
        {
            AxisAlignedBB bb = te.getEditArea();
            if (bb != null)
            {
                bb = te.getAbsoluteArea(bb);
                this.setColors(0.8f, 0.0f, 0.4f, 0.4f);
                this.renderArea(x, y, z, bb, pTicks);
            }
            else
            {
                bb = te.getAbsoluteArea();
                if (bb != null)
                {
                    this.setColors(0.0f, 0.4f, 0.8f, 0.4f);
                    this.renderArea(x, y, z, bb, pTicks);
                }
            }
        }
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
    {
        this.renderTileEntityAt((TileEntityCrossingGate)te, x, y, z, f);
    }
}
