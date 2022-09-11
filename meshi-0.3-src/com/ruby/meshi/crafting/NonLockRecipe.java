 package com.ruby.meshi.crafting;
import net.minecraft.inventory.IInventory;
 import net.minecraft.item.crafting.IRecipe;
public interface NonLockRecipe
 {
   default boolean isUnlocked(IRecipe<? extends IInventory> recipe) {
     return true;
   }
 }

