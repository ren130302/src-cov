 package com.ruby.meshi.item;
import net.minecraft.block.BlockState;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.tags.FluidTags;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
public class WaterWalker
   extends Item implements Accessory {
   public WaterWalker(Item.Properties properties) {
     super(properties);
   }

   public void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
     BlockState underState = world.func_180495_p(new BlockPos(player.field_70165_t, Math.ceil(player.field_70163_u + (player.func_213322_ci()).field_72448_b) - 0.0625D, player.field_70161_v));
     if (!player.func_70093_af() && world.func_175623_d(new BlockPos(player.field_70165_t, Math.floor(player.field_70163_u), player.field_70161_v)) && underState.func_204520_s().func_206884_a(FluidTags.field_206959_a)) {
       player.func_213293_j((player.func_213322_ci()).field_72450_a, 0.0D, (player.func_213322_ci()).field_72449_c);
       player.field_70122_E = true;
     }  }
 }

