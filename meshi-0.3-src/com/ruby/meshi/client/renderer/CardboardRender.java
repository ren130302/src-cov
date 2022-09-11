 package com.ruby.meshi.client.renderer;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.Cardboard;
 import com.ruby.meshi.block.tileentity.CardboardTileEntity;
 import com.ruby.meshi.client.renderer.animation.EntityModelAnimation;
 import net.minecraft.client.renderer.entity.model.CatModel;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.entity.passive.CatEntity;
 import net.minecraft.state.IProperty;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.ResourceLocation;
public class CardboardRender extends TileEntityRenderer<CardboardTileEntity> {
   private static final WrapCatModel CAT_RENDER = new WrapCatModel(0.0F);

 
   public void render(CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
     if (!tileEntityIn.hasCatNBT()) {
       return;
     }
     float scale = 0.0625F;
     Direction direction = (Direction)tileEntityIn.func_195044_w().func_177229_b((IProperty)Cardboard.field_185512_D);
     func_147499_a(getCatTexture(tileEntityIn));
     tileEntityIn.getAnimations().forEach(a -> {
           renderHead(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
           renderFrontLeg(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
           renderTail(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
         });
   }
  private void renderTail(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
     if (animation.shouldRenderPart(EntityModelAnimation.RenderPart.TAIL)) {
       GlStateManager.pushMatrix();
       animation.translatef((i, j, k) -> GlStateManager.translatef((float)x + 0.5F + i.floatValue() + getDirOffset(direction, 0.1F, Direction.Axis.X), (float)y + 0.11F + j.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.Y), (float)z + 0.5F + k.floatValue() + getDirOffset(direction, 0.1F, Direction.Axis.Z)), partialTicks);
      
       animation.scalef((i, j, k) -> GlStateManager.scalef(0.8F + i.floatValue(), 0.8F + j.floatValue(), 0.8F + k.floatValue()), partialTicks);
       animation.rotateZ(a -> GlStateManager.rotatef(a.floatValue(), 0.0F, 0.0F, 1.0F), partialTicks);
       animation.rotateY(a -> GlStateManager.rotatef(getPartRotate(direction) + a.floatValue(), 0.0F, 1.0F, 0.0F), partialTicks);
       animation.rotateX(a -> GlStateManager.rotatef(a.floatValue(), 1.0F, 0.0F, 0.0F), partialTicks);
       GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
       CAT_RENDER.renderTail(scale);
       GlStateManager.popMatrix();
     }  }
   private void renderHead(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
     if (animation.shouldRenderPart(EntityModelAnimation.RenderPart.HEAD)) {
       GlStateManager.pushMatrix();
       animation.translatef((i, j, k) -> GlStateManager.translatef((float)x + 0.5F + i.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.X), (float)y + 0.15F + j.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.Y), (float)z + 0.5F + k.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.Z)), partialTicks);
      
       animation.scalef((i, j, k) -> GlStateManager.scalef(0.8F + i.floatValue(), 0.8F + j.floatValue(), 0.8F + k.floatValue()), partialTicks);
       animation.rotateZ(a -> GlStateManager.rotatef(a.floatValue(), 0.0F, 0.0F, 1.0F), partialTicks);
       animation.rotateY(a -> GlStateManager.rotatef(getPartRotate(direction) + a.floatValue(), 0.0F, 1.0F, 0.0F), partialTicks);
       animation.rotateX(a -> GlStateManager.rotatef(a.floatValue(), 1.0F, 0.0F, 0.0F), partialTicks);
       GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
       CAT_RENDER.renderHead(scale);
       GlStateManager.popMatrix();
     }  }
   private void renderFrontLeg(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
     if (animation.shouldRenderPart(EntityModelAnimation.RenderPart.LEFT_HAND)) {
       GlStateManager.pushMatrix();
       animation.translatef((i, j, k) -> GlStateManager.translatef((float)x + i.floatValue() + 0.5F + getDirOffset(direction, -0.15F, Direction.Axis.X) + getDirOffset(direction, 0.15F, Direction.Axis.Z), (float)y + 0.11F + j.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.Y), (float)z + 0.5F + k.floatValue() + getDirOffset(direction, 0.15F, Direction.Axis.X) + getDirOffset(direction, -0.15F, Direction.Axis.Z)), partialTicks);
      
       animation.scalef((i, j, k) -> GlStateManager.scalef(0.8F + i.floatValue(), 0.8F + j.floatValue(), 0.8F + k.floatValue()), partialTicks);
       animation.rotateZ(a -> GlStateManager.rotatef(a.floatValue(), 0.0F, 0.0F, 1.0F), partialTicks);
       animation.rotateY(a -> GlStateManager.rotatef(getPartRotate(direction) + a.floatValue(), 0.0F, 1.0F, 0.0F), partialTicks);
       animation.rotateX(a -> GlStateManager.rotatef(a.floatValue(), 1.0F, 0.0F, 0.0F), partialTicks);
       GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
       CAT_RENDER.renderFrontLeftLeg(scale);
       GlStateManager.popMatrix();
     }    if (animation.shouldRenderPart(EntityModelAnimation.RenderPart.RIGHT_HAND)) {
       GlStateManager.pushMatrix();
       animation.translatef((i, j, k) -> GlStateManager.translatef((float)x + i.floatValue() + 0.5F + getDirOffset(direction, -0.15F, Direction.Axis.X) + getDirOffset(direction, -0.15F, Direction.Axis.Z), (float)y + 0.11F + j.floatValue() + getDirOffset(direction, 0.2F, Direction.Axis.Y), (float)z + 0.5F + k.floatValue() + getDirOffset(direction, -0.15F, Direction.Axis.X) + getDirOffset(direction, -0.15F, Direction.Axis.Z)), partialTicks);
      
       animation.scalef((i, j, k) -> GlStateManager.scalef(0.8F + i.floatValue(), 0.8F + j.floatValue(), 0.8F + k.floatValue()), partialTicks);
       animation.rotateZ(a -> GlStateManager.rotatef(a.floatValue(), 0.0F, 0.0F, 1.0F), partialTicks);
       animation.rotateY(a -> GlStateManager.rotatef(getPartRotate(direction) + a.floatValue(), 0.0F, 1.0F, 0.0F), partialTicks);
       animation.rotateX(a -> GlStateManager.rotatef(a.floatValue(), 1.0F, 0.0F, 0.0F), partialTicks);
       GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
       CAT_RENDER.renderFrontRightLeg(scale);
       GlStateManager.popMatrix();
     }  }
   private float getDirOffset(Direction dir, float value, Direction.Axis axis) {
     float offset = 0.0F;
     if (dir.func_176740_k() == axis) {
       offset = value * dir.func_176743_c().func_179524_a();
     }
     return offset;
   }
  private int getPartRotate(Direction dir) {
     int headRotate = 0;
     switch (dir) {
       case EAST:
         headRotate = 90;
         break;
       case NORTH:
         headRotate = 180;
         break;
           case WEST:
         headRotate = 270;
         break;
     }    return headRotate;
   }
  public static class WrapCatModel
     extends CatModel<CatEntity> {
     public WrapCatModel(float p_i51069_1_) {
       super(p_i51069_1_);
       this.field_78156_g.func_78793_a(0.0F, 0.0F, 0.0F);
       this.field_78158_e.func_78793_a(0.0F, 0.0F, 0.0F);
       this.field_78155_f.func_78793_a(0.0F, 0.0F, 0.0F);
       this.field_78160_c.func_78793_a(0.0F, 0.0F, 0.0F);
       this.field_78157_d.func_78793_a(0.0F, 0.0F, 0.0F);
     }
    public void renderHead(float scale) {
       this.field_78156_g.func_78785_a(scale);
     }

     public void renderTail(float scale) {
       this.field_78155_f.func_78785_a(scale);
     }
    public void renderFrontLeftLeg(float scale) {
       this.field_78160_c.func_78785_a(scale);
     }
    public void renderFrontRightLeg(float scale) {
       this.field_78157_d.func_78785_a(scale);
     }
   }
  private ResourceLocation getCatTexture(CardboardTileEntity tile) {
     return (ResourceLocation)CatEntity.field_213425_bD.get(Integer.valueOf((tile != null) ? tile.getCatType() : 0));
   }
 }

