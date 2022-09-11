 package com.ruby.meshi.client.gui.recipebook;
import com.google.gson.JsonElement;
 import com.ruby.meshi.crafting.GrindRecipe;
 import com.ruby.meshi.crafting.GrindRecipeItemHelper;
 import it.unimi.dsi.fastutil.ints.IntList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.function.Predicate;
 import java.util.stream.Stream;
 import net.minecraft.client.gui.recipebook.RecipeBookGui;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraftforge.common.crafting.IIngredientSerializer;

 public class GrindRecipeBookGui
   extends RecipeBookGui
 {
   public GrindRecipeBookGui() {
     GrindRecipeItemHelper.hookHelper(RecipeBookGui.class, this);
   }

   public void func_193951_a(IRecipe<?> recipe, List<Slot> slots) {
     if (recipe instanceof GrindRecipe) {
       this.field_191915_z.func_192685_a(recipe);
       this.field_191915_z.func_194187_a(new WrapIngredient(((GrindRecipe)recipe).ingredient, ((GrindRecipe)recipe).getRequestCount()), ((Slot)slots.get(0)).field_75223_e, ((Slot)slots.get(0)).field_75221_f);
       ItemStack outPut = recipe.func_77571_b();
       this.field_191915_z.func_194187_a(new WrapIngredient(Ingredient.func_193369_a(new ItemStack[] { outPut }, ), outPut.func_190916_E()), ((Slot)slots.get(1)).field_75223_e, ((Slot)slots.get(1)).field_75221_f);
       ItemStack bonus = ((GrindRecipe)recipe).getBonus();
       if (!bonus.func_190926_b()) {
         this.field_191915_z.func_194187_a(new WrapIngredient(Ingredient.func_193369_a(new ItemStack[] { bonus }, ), bonus.func_190916_E()), ((Slot)slots.get(2)).field_75223_e, ((Slot)slots.get(2)).field_75221_f);
       }
     }  }
 
   static class WrapIngredient
     extends Ingredient
   {
     private final Ingredient original;
     private final int count;
        protected WrapIngredient(Ingredient org, int count) {
       super(Stream.empty());
       this.original = org;
       this.count = count;
     }

     public Predicate<ItemStack> and(Predicate<? super ItemStack> other) {
       return this.original.and(other);
     }

     public Predicate<ItemStack> negate() {
       return this.original.negate();
     }

     public Predicate<ItemStack> or(Predicate<? super ItemStack> other) {
       return this.original.or(other);
     }

     public ItemStack[] func_193365_a() {
       ItemStack[] stacks = this.original.func_193365_a();
       Arrays.<ItemStack>stream(stacks).forEach(s -> s.func_190920_e(this.count));
       return stacks;
     }

     public boolean test(ItemStack p_test_1_) {
       return this.original.test(p_test_1_);
     }

     public IntList func_194139_b() {
       return this.original.func_194139_b();
     }

     public JsonElement func_200304_c() {
       return this.original.func_200304_c();
     }

     public boolean func_203189_d() {
       return this.original.func_203189_d();
     }

 
 
     public boolean isSimple() {
       return this.original.isSimple();
     }

     public IIngredientSerializer<? extends Ingredient> getSerializer() {
       return this.original.getSerializer();
     }
   }
 }

