// Warning: No line numbers available in class file
 package com.ruby.meshi.block;
import java.util.Random;
 import net.minecraft.block.BlockState;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
public interface ExtraParticle {
   @OnlyIn(Dist.CLIENT)
   void paticleTick(BlockState paramBlockState, World paramWorld, BlockPos paramBlockPos, Random paramRandom);
 }

