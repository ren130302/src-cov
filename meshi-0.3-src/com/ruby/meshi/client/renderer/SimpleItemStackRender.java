 package com.ruby.meshi.client.renderer;
import java.util.concurrent.Callable;
 import net.minecraft.block.Block;
 import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
 import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
 import net.minecraft.item.ItemStack;
 import net.minecraft.tileentity.TileEntity;
public class SimpleItemStackRender
   extends ItemStackTileEntityRenderer implements Callable<ItemStackTileEntityRenderer> {
   private TileEntity entity;
    public SimpleItemStackRender(Block block) {
     this.entity = block.createTileEntity(block.func_176223_P(), null);
   }

   public void func_179022_a(ItemStack itemStackIn) {
     TileEntityRendererDispatcher.field_147556_a.func_203601_b(this.entity);
   }

   public ItemStackTileEntityRenderer call() throws Exception {
     return this;
   }
 }

