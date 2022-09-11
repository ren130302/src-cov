 package com.ruby.meshi.crafting;
import it.unimi.dsi.fastutil.ints.IntList;
 import java.lang.reflect.Field;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.RecipeItemHelper;

 public class GrindRecipeItemHelper
   extends RecipeItemHelper
 {
   public boolean func_194116_a(IRecipe<?> recipe, IntList packedItemList) {
     if (recipe instanceof GrindRecipe) {
       return func_194118_a(recipe, packedItemList, ((GrindRecipe)recipe).getRequestCount());
     }
     return super.func_194116_a(recipe, packedItemList);
   }

   public static void hookHelper(Class<?> target, Object instance) {
     for (Field field : target.getDeclaredFields()) {
       if (field.getType() == RecipeItemHelper.class) {
         field.setAccessible(true);
         try {
           field.set(instance, new GrindRecipeItemHelper());
         } catch (Exception e) {
           e.printStackTrace();
         }      }    }  }
 }

