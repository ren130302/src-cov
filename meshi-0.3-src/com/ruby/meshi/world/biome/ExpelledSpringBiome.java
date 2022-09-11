 package com.ruby.meshi.world.biome;
import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.world.gen.HiganGenerater;
 import net.minecraft.world.biome.Biome;
 import net.minecraft.world.gen.GenerationStage;
 import net.minecraft.world.gen.feature.Feature;
 import net.minecraft.world.gen.feature.IFeatureConfig;
 import net.minecraft.world.gen.feature.OreFeatureConfig;
 import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
 import net.minecraft.world.gen.placement.CountRangeConfig;
 import net.minecraft.world.gen.placement.IPlacementConfig;
 import net.minecraft.world.gen.placement.Placement;
 import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
 import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
public class ExpelledSpringBiome extends ExpelledBaseBiome {
   protected ExpelledSpringBiome() {
     super((new Biome.Builder()).func_222351_a(SurfaceBuilder.field_215396_G, (ISurfaceBuilderConfig)SurfaceBuilder.field_215425_v).func_205415_a(Biome.RainType.RAIN).func_205419_a(Biome.Category.FOREST).func_205421_a(0.75F).func_205420_b(0.25F).func_205414_c(0.7F).func_205417_d(0.4F).func_205412_a(4159204).func_205413_b(329011).func_205418_a((String)null));
     addTree();
   }
  private void addTree() {
     func_203611_a(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.func_222280_a(HiganGenerater.SAKURA_TREE, (IFeatureConfig)IFeatureConfig.field_202429_e, Placement.field_215027_m, (IPlacementConfig)new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
   }

   public void addModOres(Biome biomeIn) {
     super.addModOres(biomeIn);
     biomeIn.func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, (IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, SakuraBlocks.SAKURA_ORE.func_176223_P(), 1), Placement.field_215028_n, (IPlacementConfig)new CountRangeConfig(1, 0, 0, 16)));
   }
 }

