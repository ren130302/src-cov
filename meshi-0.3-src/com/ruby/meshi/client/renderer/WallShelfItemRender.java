 package com.ruby.meshi.client.renderer;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.tileentity.WallShelfTileEntity;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.texture.AtlasTexture;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
public class WallShelfItemRender extends SimpleItemRender<WallShelfTileEntity> {
   private final Minecraft mc = Minecraft.func_71410_x();

   public void render(WallShelfTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
     (this.mc.func_175598_ae()).field_78724_e.func_110577_a(AtlasTexture.field_110575_b);
        GlStateManager.enableLighting();
     if (entity.isDouble()) {
       renderDouble(entity, x, y, z, partialTicks, destroyStage);
     }
     GlStateManager.enableLighting();
   }

   private void renderDouble(WallShelfTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
     Direction dir = (Direction)entity.func_195044_w().func_177229_b((IProperty)BlockStateProperties.field_208157_J);
       int rotateYaw = getRotation(dir);
     if (entity.hasItem((byte)0)) {
       double renderX = getRightOffsetX(dir) + x;
       double renderZ = getRightOffsetZ(dir) + z;
       renderItem(entity.func_70301_a(0), renderX, y, renderZ, rotateYaw);
     }    if (entity.hasItem((byte)1)) {
       double renderX = getLeftOffsetX(dir) + x;
       double renderZ = getLeftOffsetZ(dir) + z;
       renderItem(entity.func_70301_a(1), renderX, y, renderZ, rotateYaw);
     }  }
 
 
 
   private void renderItem(ItemStack stack, double x, double y, double z, int rotateYaw) {
     renderItem(stack, s -> GlStateManager.translated(x + 0.5D, y + (this.mc.func_175599_af().func_175050_a(s) ? 0.5D : 0.6D), z + 0.5D), s -> GlStateManager.rotatef((180 - rotateYaw), 0.0F, 1.0F, 0.0F), 0.5F);
   }

 
 
   public int getRotation(Direction dir) {
     switch (dir) {
       case EAST:
       case WEST:
         return 90;
     }    return 0;
   }

   public double getRightOffsetX(Direction dir) {
     switch (dir) {
       case EAST:
       case NORTH:
         return 0.25D;
       case WEST:
       case SOUTH:
         return -0.25D;
     }    return 0.0D;
   }

   public double getRightOffsetZ(Direction dir) {
     switch (dir) {
       case EAST:
       case SOUTH:
         return 0.25D;
       case WEST:
       case NORTH:
         return -0.25D;
     }    return 0.0D;
   }

   public double getLeftOffsetX(Direction dir) {
     switch (dir) {
       case EAST:
       case SOUTH:
         return 0.25D;
       case WEST:
       case NORTH:
         return -0.25D;
     }    return 0.0D;
   }

   public double getLeftOffsetZ(Direction dir) {
     switch (dir) {
       case WEST:
       case SOUTH:
         return 0.25D;
       case EAST:
       case NORTH:
         return -0.25D;
     }    return 0.0D;
   }

   public double getSingleOffsetX(Direction dir) {
     return 0.0D;
   }
  public double geSingleOffsetZ(Direction dir) {
     return 0.0D;
   }
 }

