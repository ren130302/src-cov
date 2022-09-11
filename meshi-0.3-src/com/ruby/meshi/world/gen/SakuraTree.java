 package com.ruby.meshi.world.gen;
import java.util.Random;
 import javax.annotation.Nullable;
 import net.minecraft.block.Block;
 import net.minecraft.block.trees.Tree;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
 import net.minecraft.world.gen.feature.TreeFeature;

 public class SakuraTree
   extends Tree
 {
   private Block log;
   private Block leave;
    public SakuraTree(Block log, Block leave) {
     this.log = log;
     this.leave = leave;
   }

   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> func_196936_b(Random random) {
     return (random.nextInt(10) == 0) ? (new ExtendBigTreeFeature(NoFeatureConfig::func_214639_a, true)).setBlocks(this.log.func_176223_P(), this.leave.func_176223_P()) : (AbstractTreeFeature<NoFeatureConfig>)new TreeFeature(NoFeatureConfig::func_214639_a, true, 5, this.log.func_176223_P(), this.leave.func_176223_P(), false);
   }
 }

