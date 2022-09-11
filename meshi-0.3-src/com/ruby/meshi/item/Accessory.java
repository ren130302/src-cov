// Warning: No line numbers available in class file
 package com.ruby.meshi.item;
import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.ItemStack;
 import net.minecraft.world.World;
public interface Accessory {
   default void playerPostTick(World world, PlayerEntity player, ItemStack stack) {}
 }

