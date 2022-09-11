 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
 import net.minecraft.world.biome.Biome;
public class FermentedCask
   extends Block {
   public FermentedCask(Block.Properties properties) {
     super(properties);
   }

   public void getType(World worldIn, BlockPos pos) {
     Biome.TempCategory temp = worldIn.func_180494_b(pos).func_150561_m();
   }
 }

