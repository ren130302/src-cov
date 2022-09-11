 package com.ruby.meshi.world.gen;
import com.google.common.collect.Sets;
 import com.ruby.meshi.block.SakuraBlocks;
 import java.util.Set;
 import java.util.function.Predicate;
 import java.util.stream.Collectors;
 import net.minecraft.block.SaplingBlock;
 import net.minecraft.world.biome.Biome;
 import net.minecraft.world.gen.GenerationStage;
 import net.minecraft.world.gen.feature.Feature;
 import net.minecraft.world.gen.feature.IFeatureConfig;
 import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
 import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
 import net.minecraft.world.gen.placement.IPlacementConfig;
 import net.minecraft.world.gen.placement.Placement;
 import net.minecraftforge.common.BiomeDictionary;

 
 public class HiganGenerater
 {
   public static final Feature<NoFeatureConfig> SAKURA_TREE = (Feature<NoFeatureConfig>)register("sakura_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.SAKURA_SAPLING));
   public static final Feature<NoFeatureConfig> MAPLE_TREE = (Feature<NoFeatureConfig>)register("maple_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.MAPLE_SAPLING));
   public static final Feature<NoFeatureConfig> GINKGO_TREE = (Feature<NoFeatureConfig>)register("ginkgo_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.GINKGO_SAPLING));
   public static final Feature<NoFeatureConfig> HINOKI_TREE = (Feature<NoFeatureConfig>)register("hinoki_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.HINOKI_SAPLING));

   private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
     return (F)value.setRegistryName("meshi", key);
   }

   public static void addBiomeGen() {
     Set<Biome> forests = getBiomes(new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST });
     Set<Biome> forestsAndmountains = getBiomes(new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MOUNTAIN });
     addTree(getSelectBiomes(forestsAndmountains, b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.COLD)), MAPLE_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
             addTree(getSelectBiomes(forests, b -> !BiomeDictionary.hasType(b, BiomeDictionary.Type.COLD)), SAKURA_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
 
 
 
     
     addTree(forestsAndmountains, HINOKI_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
   }

 
 
   private static <F extends IFeatureConfig, D extends IPlacementConfig> void addTree(Set<Biome> biomes, Feature<F> feature, F conf, Placement<D> placer, D placeConf) {
     for (Biome b : biomes) {
       b.func_203611_a(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.func_222280_a(feature, (IFeatureConfig)conf, placer, (IPlacementConfig)placeConf));
     }
   }
  private static <D extends IPlacementConfig> void addRandomTree(Set<Biome> biomes, Feature<?>[] features, IFeatureConfig[] iFeatureConfigs, float[] weight, Placement<D> placer, D placeConf) {
     for (Biome b : biomes) {
       b.func_203611_a(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.func_222280_a(Feature.field_202292_al, (IFeatureConfig)new MultipleRandomFeatureConfig((Feature[])features, iFeatureConfigs, weight, features[0], (IFeatureConfig)IFeatureConfig.field_202429_e), placer, (IPlacementConfig)placeConf));
     }
   }
  public static Set<Biome> getBiomes(BiomeDictionary.Type... type) {
     Set<Biome> biome = Sets.newHashSet();
     for (BiomeDictionary.Type t : type) {
       biome.addAll(BiomeDictionary.getBiomes(t));
     }
       biome.removeIf(b -> b instanceof com.ruby.meshi.world.biome.ExpelledBaseBiome);    
     return biome;
   }
  private static Set<Biome> getSelectBiomes(Set<Biome> biomes, Predicate<Biome> tester) {
     return (Set<Biome>)biomes.stream()
       .filter(tester)
       .collect(Collectors.toSet());
   }
 }

