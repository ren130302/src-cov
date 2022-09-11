 package com.ruby.meshi.world.biome;
import com.ruby.meshi.block.SakuraBlocks;
 import java.util.Random;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityClassification;
 import net.minecraft.world.biome.Biome;
 import net.minecraft.world.biome.DefaultBiomeFeatures;
 import net.minecraft.world.chunk.IChunk;
 import net.minecraft.world.gen.GenerationStage;
 import net.minecraft.world.gen.feature.Feature;
 import net.minecraft.world.gen.feature.IFeatureConfig;
 import net.minecraft.world.gen.feature.OreFeatureConfig;
 import net.minecraft.world.gen.placement.CountRangeConfig;
 import net.minecraft.world.gen.placement.IPlacementConfig;
 import net.minecraft.world.gen.placement.Placement;
public abstract class ExpelledBaseBiome
   extends Biome {
   protected ExpelledBaseBiome(Biome.Builder biomeBuilder) {
     super(biomeBuilder);
        DefaultBiomeFeatures.func_222326_g(this);
     DefaultBiomeFeatures.func_222288_h(this);
     addModOres(this);
   }
  public void addModOres(Biome biomeIn) {
     biomeIn.func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, (IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, SakuraBlocks.MAPLE_ORE.func_176223_P(), 8), Placement.field_215028_n, (IPlacementConfig)new CountRangeConfig(20, 0, 0, 64)));
     biomeIn.func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, (IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, SakuraBlocks.GINKGO_ORE.func_176223_P(), 4), Placement.field_215028_n, (IPlacementConfig)new CountRangeConfig(1, 0, 0, 32)));
     biomeIn.func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, (IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, SakuraBlocks.SAKURA_ORE.func_176223_P(), 1), Placement.field_215028_n, (IPlacementConfig)new CountRangeConfig(1, 0, 0, 32)));
   }

   public void func_206854_a(Random random, IChunk chunkIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
     super.func_206854_a(random, chunkIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);
   }

 
 
   public float func_76741_f() {
     return 0.0F;
   }
 }

