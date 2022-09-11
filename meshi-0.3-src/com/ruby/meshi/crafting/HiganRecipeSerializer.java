 package com.ruby.meshi.crafting;
import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.util.ResourceLocation;

 
 public class HiganRecipeSerializer
 {
   public static final IRecipeSerializer<?> GRIND = register("grind", new GrindRecipe.Serializer());
   public static final IRecipeSerializer<?> HEARTH = register("hearth", new HearthRecipe.Serializer());
   public static final IRecipeSerializer<?> HEARTH_SHAPELESS = register("hearth_shapeless", new HearthShapelessRecipe.Serializer());
  private static <T extends net.minecraft.item.crafting.IRecipe<?>> IRecipeSerializer<?> register(String key, IRecipeSerializer<T> serializer) {
     return (IRecipeSerializer)serializer.setRegistryName(new ResourceLocation("meshi", key));
   }
 }

