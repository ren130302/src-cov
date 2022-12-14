 package com.ruby.meshi.client.renderer;
import com.mojang.blaze3d.platform.GlStateManager;
 import java.util.function.Consumer;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.model.ItemCameraTransforms;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.item.ItemStack;
 import net.minecraft.tileentity.TileEntity;
public abstract class SimpleItemRender<T extends TileEntity>
   extends TileEntityRenderer<T>
 {
   private static final Minecraft mc = Minecraft.func_71410_x();
  public void renderItem(ItemStack stack, Consumer<ItemStack> transleter, Consumer<ItemStack> rotater, float scale) {
     if (!stack.func_190926_b()) {
       GlStateManager.pushMatrix();
       transleter.accept(stack);
       rotater.accept(stack);
       GlStateManager.scalef(scale, scale, scale);
       mc.func_175599_af().func_181564_a(stack, ItemCameraTransforms.TransformType.FIXED);
       GlStateManager.popMatrix();
     }  }
 }

