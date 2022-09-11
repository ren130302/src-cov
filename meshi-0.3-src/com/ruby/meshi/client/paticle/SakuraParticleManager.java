 package com.ruby.meshi.client.paticle;
import com.ruby.meshi.block.ExtraParticle;
 import java.util.Random;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.particle.Particle;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.event.TickEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.LogicalSide;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
 public class SakuraParticleManager
 {
   @SubscribeEvent
   public static void tick(TickEvent.PlayerTickEvent e) {
     if (e.side == LogicalSide.CLIENT)
     {
       if (e.phase == TickEvent.Phase.START) {
         PlayerEntity player = e.player;
         World world = player.func_130014_f_();
         BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();
                int posX = MathHelper.func_76128_c(player.field_70165_t);
         int posY = MathHelper.func_76128_c(player.field_70163_u);
         int posZ = MathHelper.func_76128_c(player.field_70161_v);
         for (int j = 0; j < 500; j++) {
           animateTick(world, posX, posY, posZ, 32, 32, world.field_73012_v, mutableblockpos);
           animateTick(world, posX, posY, posZ, 64, 32, world.field_73012_v, mutableblockpos);
         }      }    }
   }

   private static void animateTick(World world, int x, int y, int z, int xzOffset, int yOffset, Random rand, BlockPos.MutableBlockPos pos) {
     int i = x + rand.nextInt(xzOffset) - rand.nextInt(xzOffset);
     int j = y + rand.nextInt(yOffset) - rand.nextInt(yOffset);
     int k = z + rand.nextInt(xzOffset) - rand.nextInt(xzOffset);
     pos.func_181079_c(i, j, k);
     BlockState blockstate = world.func_180495_p((BlockPos)pos);
     if (blockstate.func_177230_c() instanceof ExtraParticle) {
       ((ExtraParticle)blockstate.func_177230_c()).paticleTick(blockstate, world, (BlockPos)pos, rand);
     }
   }
  @OnlyIn(Dist.CLIENT)
   public static void addEffect(Particle p) {
     if (p != null)
       (Minecraft.func_71410_x()).field_71452_i.func_78873_a(p);  }
 }

