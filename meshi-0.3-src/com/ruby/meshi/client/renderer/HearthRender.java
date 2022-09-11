 package com.ruby.meshi.client.renderer;
import com.ruby.meshi.block.Hearth;
 import com.ruby.meshi.block.tileentity.HearthTileEntity;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.renderer.entity.model.RendererModel;
 import net.minecraft.client.renderer.model.Model;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.state.IProperty;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.math.MathHelper;
 import org.lwjgl.opengl.GL11;
public class HearthRender
   extends TileEntityRenderer<HearthTileEntity>
 {
   private static final ModelHearth model = new ModelHearth();
   private static final ResourceLocation RESOURCE = new ResourceLocation("meshi", "textures/entitys/hearth.png");

   public void render(HearthTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
     GL11.glPushMatrix();
     func_147499_a(RESOURCE);
     GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
     model.renderWood();
        if (tileEntityIn.func_145831_w() != null && tileEntityIn.func_195044_w() != null) {
       BlockState state = tileEntityIn.func_195044_w();
       GL11.glRotatef((90 * ((Direction)state.func_177229_b((IProperty)Hearth.field_185512_D)).func_176736_b()), 0.0F, 1.0F, 0.0F);
       Hearth.HearthStateType type = (Hearth.HearthStateType)state.func_177229_b((IProperty)Hearth.TYPE);
       if (type == Hearth.HearthStateType.MEAT) {
         model.renderRods();
         model.renderMeat(MathHelper.func_219799_g(partialTicks, tileEntityIn.now_roll, tileEntityIn.next_roll));
       }      if (type == Hearth.HearthStateType.FISH) {
         model.renderFish();
       }
       if (type == Hearth.HearthStateType.OTHER) {
         model.renderRods();
         model.renderPot();
       }    }       GL11.glPopMatrix();
   }
  private static class ModelHearth extends Model {
     public RendererModel bone;
     public RendererModel potRod;
     public RendererModel fish;
     public RendererModel meat;
     public RendererModel pot;
     public RendererModel rod;
     public RendererModel rod2;
     public RendererModel wood;
     private float[] rotY = new float[8];
     private float[] rotX = new float[8];
     private float[] fishRotY = new float[4];
    public ModelHearth() {
       this.bone = new RendererModel(this, 0, 30);
       this.bone.func_78789_a(-8.0F, -0.5F, -0.5F, 16, 1, 1);
       this.bone.field_78795_f = 6.2831855F;
       this.potRod = new RendererModel(this, 0, 30);
       this.potRod.func_78789_a(-8.0F, 9.0F, -0.5F, 16, 1, 1);
       this.potRod.field_78795_f = 6.2831855F;
       this.fish = new RendererModel(this, 0, 17);
       this.fish.func_78789_a(-1.0F, -12.0F, 5.0F, 3, 13, 0);
       this.fish.func_78793_a(0.0F, 10.0F, 0.0F);
       this.fish.field_78795_f = -0.34906584F;
            this.meat = new RendererModel(this, 8, 18);
       this.meat.func_78789_a(-5.0F, -3.0F, -3.0F, 10, 6, 6);
       this.pot = new RendererModel(this, 6, 0);
       this.pot.func_78789_a(-5.0F, 4.0F, -5.0F, 10, 8, 10);
       this.rod = new RendererModel(this, 0, 0);
       this.rod.func_78789_a(-7.0F, 0.0F, -1.0F, 1, 15, 2);
       this.rod2 = new RendererModel(this, 0, 0);
       this.rod2.func_78789_a(-7.0F, 0.0F, -1.0F, 1, 15, 2);
       this.rod2.field_78796_g = 3.1415927F;
       this.wood = new RendererModel(this, 44, 22);
       this.wood.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 2, 8);
       this.wood.func_78793_a(0.0F, 1.0F, 0.0F);
       this.wood.field_78795_f = 0.34906584F;
       this.meat.func_78792_a(this.bone);
       int i;
       for (i = 0; i < 7; i++) {
         this.rotY[i] = (float)(161.56762218461793D * i) / 180.0F;
         this.rotX[i] = (i % 2 == 0) ? 0.17453292F : 0.34906584F;
       }           for (i = 0; i < 4; i++) {
         this.fishRotY[i] = (float)(Math.PI * (90 * i + 45)) / 180.0F;
       }
     }
    public void renderWood() {
       this.wood.func_78793_a(0.0F, 1.0F, 0.0F);
            for (int i = 0; i < 7; i++) {
         this.wood.field_78796_g = this.rotY[i];
         this.wood.field_78795_f = this.rotX[i];
         this.wood.func_78785_a(0.0625F);
       }    }
     public void renderRods() {
       this.rod.func_78785_a(0.0625F);
       this.rod2.func_78785_a(0.0625F);
     }
    public void renderMeat(float roll) {
       this.meat.func_78793_a(0.0F, 11.0F, 0.0F);
       this.meat.field_78795_f = roll;
       this.meat.func_78785_a(0.0625F);
     }
    public void renderFish() {
       for (int i = 0; i < 4; i++) {
         this.fish.field_78796_g = this.fishRotY[i];
         this.fish.func_78785_a(0.0625F);
       }    }
     public void renderPot() {
       this.potRod.func_78785_a(0.0625F);
       this.pot.func_78785_a(0.0625F);
     }
   }
 }

