 package com.ruby.meshi.client.renderer;
import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
 import net.minecraft.client.renderer.entity.model.RendererModel;
 import net.minecraft.client.renderer.model.Model;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.ResourceLocation;
 import org.lwjgl.opengl.GL11;
public class MillstoneRender
   extends TileEntityRenderer<MillstoneTileEntity>
 {
   private static final ModelMillStone model = new ModelMillStone();
   private static final ResourceLocation RESOURCE = new ResourceLocation("meshi", "textures/entitys/millstone.png");

   public void render(MillstoneTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
     GL11.glPushMatrix();
     func_147499_a(RESOURCE);
     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
     model.render(entity, partialTicks, 0.0625F);
     GL11.glPopMatrix();
   }
  private static class ModelMillStone
     extends Model
   {
     private RendererModel top;
     private RendererModel bottm;
        public ModelMillStone() {
       this.field_78090_t = 64;
       this.field_78089_u = 64;
       this.bottm = new RendererModel(this, 0, 25);
       this.bottm.func_78789_a(-8.0F, 0.0F, -8.0F, 16, 8, 16);
       this.bottm.func_78793_a(0.0F, 0.0F, 0.0F);
       this.bottm.func_78787_b(64, 64);
       this.bottm.field_78809_i = true;
       setRotation(this.bottm, 0.0F, 0.0F, 0.0F);
       this.top = new RendererModel(this, 0, 0);
       this.top.func_78789_a(-8.0F, -8.0F, -8.0F, 16, 8, 16);
       this.top.func_78793_a(0.0F, 0.0F, 0.0F);
       this.top.func_78787_b(64, 64);
       this.top.field_78809_i = true;
       setRotation(this.top, 0.0F, 0.0F, 0.0F);
     }
    public void render(MillstoneTileEntity entity, float partialTicks, float scale) {
       this.bottm.field_78796_g = (float)(Math.PI * entity.getRoll()) / 180.0F;
       this.bottm.func_78785_a(scale);
       this.top.func_78785_a(scale);
     }
    private void setRotation(RendererModel model, float x, float y, float z) {
       model.field_78795_f = x;
       model.field_78796_g = y;
       model.field_78808_h = z;
     }
   }
 }

