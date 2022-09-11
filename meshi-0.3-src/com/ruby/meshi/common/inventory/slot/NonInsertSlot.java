 package com.ruby.meshi.common.inventory.slot;
import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
public class NonInsertSlot
   extends Slot {
   public NonInsertSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
     super(inventoryIn, index, xPosition, yPosition);
   }

   public boolean func_75214_a(ItemStack stack) {
     return false;
   }
 }

