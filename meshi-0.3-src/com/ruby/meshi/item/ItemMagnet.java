 package com.ruby.meshi.item;
import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.world.World;

 public class ItemMagnet
   extends Item
   implements Accessory
 {
   public ItemMagnet(Item.Properties properties) {
     super(properties);
   }

   public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
     if (!world.field_72995_K && (world.func_72912_H().func_82573_f() & 0x7L) == 0L &&      entity instanceof PlayerEntity) {
       for (Entity e : world.func_175674_a(entity, entity.func_174813_aQ().func_72314_b(5.0D, 2.0D, 5.0D), e -> (e instanceof net.minecraft.entity.item.ItemEntity || e instanceof net.minecraft.entity.projectile.ThrowableEntity || e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof net.minecraft.entity.item.ExperienceOrbEntity))) {
         if (e.func_70089_S()) {
           e.func_70100_b_((PlayerEntity)entity);
         }
       }    }
   }

   public void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
     if (!world.field_72995_K && (world.func_72912_H().func_82573_f() & 0x7L) == 0L)
       for (Entity e : world.func_175674_a((Entity)player, player.func_174813_aQ().func_72314_b(5.0D, 2.0D, 5.0D), e -> (e instanceof net.minecraft.entity.item.ItemEntity || e instanceof net.minecraft.entity.projectile.ThrowableEntity || e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof net.minecraft.entity.item.ExperienceOrbEntity))) {
         if (e.func_70089_S())
           e.func_70100_b_(player);      }   }
 }

