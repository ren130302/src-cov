 package com.ruby.meshi.world.gen;
import com.mojang.datafixers.Dynamic;
 import java.util.Random;
 import java.util.Set;
 import java.util.function.Function;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.Blocks;
 import net.minecraft.block.SaplingBlock;
 import net.minecraft.state.IProperty;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MutableBoundingBox;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.gen.IWorldGenerationReader;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
public class HiganTreeFeature
   extends AbstractTreeFeature<NoFeatureConfig>
 {
   private final SaplingBlock sapling;
    public HiganTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config, SaplingBlock sapling) {
     super(config, false);
     this.sapling = sapling;
   }

   protected boolean func_208519_a(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox p_208519_5_) {
     return generateSapling((IWorld)worldIn, rand, pos);
   }
  private boolean generateSapling(IWorld world, Random rand, BlockPos pos) {
     if (world.func_175623_d(pos)) {
       BlockState block = world.func_180495_p(pos.func_177977_b());
       if (block.func_177230_c() == Blocks.field_196658_i || block.func_177230_c() == Blocks.field_150346_d) {
         this.sapling.func_176478_d(world, pos, (BlockState)this.sapling.func_176223_P().func_177231_a((IProperty)SaplingBlock.field_176479_b), rand);
         return true;
       }    }    return false;
   }
 }

