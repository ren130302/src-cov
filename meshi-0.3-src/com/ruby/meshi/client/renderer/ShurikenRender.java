 package com.ruby.meshi.client.renderer;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.entity.ShurikenEntity;
 import java.util.function.Consumer;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.entity.EntityRenderer;
 import net.minecraft.client.renderer.entity.EntityRendererManager;
 import net.minecraft.client.renderer.model.ItemCameraTransforms;
 import net.minecraft.client.renderer.texture.AtlasTexture;
 import net.minecraft.entity.Entity;
 import net.minecraft.item.ItemStack;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.math.MathHelper;

 public class ShurikenRender
   extends EntityRenderer<ShurikenEntity>
 {
   private static final ResourceLocation RESOURCE = new ResourceLocation("meshi", "textures/entitys/claw.png");
  public ShurikenRender(EntityRendererManager renderManager) {
     super(renderManager);
   }

   public void doRender(ShurikenEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
     (mc.func_175598_ae()).field_78724_e.func_110577_a(AtlasTexture.field_110575_b);
     ItemStack stack = entity.func_184543_l();
     GlStateManager.enableLighting();
     float scale = 0.25F;
     if (!stack.func_190926_b()) {
       GlStateManager.pushMatrix();
            GlStateManager.translated(x, y, z);
       GlStateManager.rotatef(MathHelper.func_219799_g(partialTicks, entity.field_70126_B, entity.field_70177_z) - 90.0F, 0.0F, 1.0F, 0.0F);
       GlStateManager.rotatef(entity.xAngle, 1.0F, 0.0F, 0.0F);
       GlStateManager.rotatef(MathHelper.func_219799_g(partialTicks, entity.field_70127_C, entity.field_70125_A), 0.0F, 0.0F, 1.0F);
       GlStateManager.scalef(scale, scale, scale);
       mc.func_175599_af().func_181564_a(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();    } 
     GlStateManager.enableLighting();
   }
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
 
   protected ResourceLocation getEntityTexture(ShurikenEntity entity) {
     return RESOURCE;
   }
 }

