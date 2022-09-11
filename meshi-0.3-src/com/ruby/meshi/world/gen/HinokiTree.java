 package com.ruby.meshi.world.gen;
import java.util.Random;
 import net.minecraft.block.trees.Tree;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;

 public class HinokiTree
   extends Tree
 {
   protected AbstractTreeFeature<NoFeatureConfig> func_196936_b(Random random) {
     return new HinokiTreeFeature(NoFeatureConfig::func_214639_a, true);
   }
 }

