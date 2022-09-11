 package com.ruby.meshi.world.biome;
import net.minecraft.world.biome.Biome;
 import net.minecraftforge.common.BiomeManager;

 
 public class HiganBiomes
 {
   public static Biome EX_PLAINS_BIOME = register("expelledplains", new ExpelledPlainsBiome());
   public static Biome EX_AUTUMN_BIOME = register("expelledautumn", new ExpelledAutumnBiome());
   public static Biome EX_SPRING_BIOME = register("expelledspring", new ExpelledSpringBiome());
  private static Biome register(String key, Biome biomeIn) {
     biomeIn.setRegistryName("meshi", key);
     return biomeIn;
   }
  public static void addBiomes() {
     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(EX_PLAINS_BIOME, 15));
     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(EX_AUTUMN_BIOME, 10));
     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(EX_SPRING_BIOME, 5));
     BiomeManager.addSpawnBiome(EX_PLAINS_BIOME);
   }
 }

