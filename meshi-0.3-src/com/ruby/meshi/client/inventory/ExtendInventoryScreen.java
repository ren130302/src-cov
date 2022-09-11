 package com.ruby.meshi.client.inventory;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.common.inventory.ExtendInventoryContainer;
 import net.minecraft.client.gui.DisplayEffectsScreen;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.text.ITextComponent;
public class ExtendInventoryScreen
   extends DisplayEffectsScreen<ExtendInventoryContainer>
 {
   private static final ResourceLocation TEXTURE = new ResourceLocation("meshi", "textures/guis/extract_inventory.png");

   public ExtendInventoryScreen(ExtendInventoryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
     super(screenContainer, inv, titleIn);
   }

   protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
     GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
     this.minecraft.func_110434_K().func_110577_a(TEXTURE);
     int i = this.field_147003_i;
     int j = this.field_147009_r;
     blit(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
   }

 
 
 
   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
     super.render(mouseX, mouseY, partialTicks);
     func_191948_b(mouseX, mouseY);
   }
 }

