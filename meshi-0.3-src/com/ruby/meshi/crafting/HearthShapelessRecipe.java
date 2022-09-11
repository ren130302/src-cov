 package com.ruby.meshi.crafting;
import com.google.common.collect.Lists;
 import com.google.gson.JsonArray;
 import com.google.gson.JsonObject;
 import com.google.gson.JsonParseException;
 import it.unimi.dsi.fastutil.ints.IntList;
 import java.util.List;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.item.crafting.ShapedRecipe;
 import net.minecraft.network.PacketBuffer;
 import net.minecraft.util.JSONUtils;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.registry.Registry;
 import net.minecraft.world.World;
 import net.minecraftforge.common.util.RecipeMatcher;
 import net.minecraftforge.registries.ForgeRegistryEntry;
public class HearthShapelessRecipe
   implements IRecipe<IInventory>, NonLockRecipe, CookingTimerRecipe {
   public static final IRecipeType<HearthShapelessRecipe> TYPE;
    static {
     final ResourceLocation key = HiganRecipeSerializer.HEARTH_SHAPELESS.getRegistryName();
     TYPE = (IRecipeType<HearthShapelessRecipe>)Registry.func_218322_a(Registry.field_218367_H, key, new IRecipeType<HearthShapelessRecipe>()
         {
           public String toString() {
             return key.toString();
           }
         });
   }

 
 
 
   public HearthShapelessRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, int time) {
     this.id = idIn;
     this.group = groupIn;
     this.recipeOutput = recipeOutputIn;
     this.recipeItems = recipeItemsIn;
     this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
     this.cookingTime = time;
   }

   public ResourceLocation func_199560_c() {
     return this.id;
   }

   public IRecipeSerializer<?> func_199559_b() {
     return HiganRecipeSerializer.HEARTH_SHAPELESS;
   }

   public String func_193358_e() {
     return this.group;
   }

   public ItemStack func_77571_b() {
     return this.recipeOutput;
   }

   public NonNullList<Ingredient> func_192400_c() {
     return this.recipeItems;
   }

   public boolean func_77569_a(IInventory inv, World worldIn) {
     RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
     List<ItemStack> inputs = Lists.newArrayList();
     int i = 0;
        for (int j = 0; j < inv.func_70302_i_(); j++) {
       ItemStack itemstack = inv.func_70301_a(j);
       if (!itemstack.func_190926_b()) {
         i++;
         if (this.isSimple) {
           recipeitemhelper.func_221264_a(itemstack, 1);
         } else {
           inputs.add(itemstack);
         }      }    }       return (i == this.recipeItems.size() && (this.isSimple ? recipeitemhelper.func_194116_a(this, (IntList)null) : (RecipeMatcher.findMatches(inputs, (List)this.recipeItems) != null)));
   }

   public ItemStack func_77572_b(IInventory inv) {
     return this.recipeOutput.func_77946_l();
   }

   public boolean func_194133_a(int width, int height) {
     return (width * height >= this.recipeItems.size());
   }

   public int getCookTime() {
     return this.cookingTime;
   }

   public IRecipeType<?> func_222127_g() {
     return TYPE;
   }
  public static class Serializer
     extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<HearthShapelessRecipe> {
     public HearthShapelessRecipe read(ResourceLocation recipeId, JsonObject json) {
       String s = JSONUtils.func_151219_a(json, "group", "");
       int cookingTime = JSONUtils.func_151208_a(json, "cookingtime", 200);
       NonNullList<Ingredient> nonnulllist = readIngredients(JSONUtils.func_151214_t(json, "ingredients"));
       if (nonnulllist.isEmpty())
         throw new JsonParseException("No ingredients for shapeless recipe");      if (nonnulllist.size() > 9) {
         throw new JsonParseException("Too many ingredients for shapeless recipe the max is 9");
       }
       ItemStack itemstack = ShapedRecipe.func_199798_a(JSONUtils.func_152754_s(json, "result"));
       return new HearthShapelessRecipe(recipeId, s, itemstack, nonnulllist, cookingTime);
     }

     private static NonNullList<Ingredient> readIngredients(JsonArray p_199568_0_) {
       NonNullList<Ingredient> nonnulllist = NonNullList.func_191196_a();
            for (int i = 0; i < p_199568_0_.size(); i++) {
         Ingredient ingredient = Ingredient.func_199802_a(p_199568_0_.get(i));
         if (!ingredient.func_203189_d()) {
           nonnulllist.add(ingredient);
         }
       }           return nonnulllist;
     }

     public HearthShapelessRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
       String s = buffer.func_150789_c(32767);
       int i = buffer.func_150792_a();
       NonNullList<Ingredient> nonnulllist = NonNullList.func_191197_a(i, Ingredient.field_193370_a);
            for (int j = 0; j < nonnulllist.size(); j++) {
         nonnulllist.set(j, Ingredient.func_199566_b(buffer));
       }
            ItemStack itemstack = buffer.func_150791_c();
       int cookingTime = buffer.readInt();
       return new HearthShapelessRecipe(recipeId, s, itemstack, nonnulllist, cookingTime);
     }

     public void write(PacketBuffer buffer, HearthShapelessRecipe recipe) {
       buffer.func_180714_a(recipe.func_193358_e());
       buffer.func_150787_b(recipe.func_192400_c().size());
            for (Ingredient ingredient : recipe.func_192400_c()) {
         ingredient.func_199564_a(buffer);
       }
            buffer.func_150788_a(recipe.func_77571_b());
       buffer.writeInt(recipe.getCookTime());
     }
   }
 }

