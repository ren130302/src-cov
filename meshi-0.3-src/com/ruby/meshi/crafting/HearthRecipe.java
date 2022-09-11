 package com.ruby.meshi.crafting;
import com.google.common.annotations.VisibleForTesting;
 import com.google.common.collect.Maps;
 import com.google.common.collect.Sets;
 import com.google.gson.JsonArray;
 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import com.google.gson.JsonParseException;
 import com.google.gson.JsonSyntaxException;
 import java.util.Map;
 import java.util.Set;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.network.PacketBuffer;
 import net.minecraft.util.JSONUtils;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.registry.Registry;
 import net.minecraft.world.World;
 import net.minecraftforge.common.crafting.CraftingHelper;
 import net.minecraftforge.common.crafting.IShapedRecipe;
 import net.minecraftforge.registries.ForgeRegistryEntry;
public class HearthRecipe implements IShapedRecipe<IInventory>, NonLockRecipe, CookingTimerRecipe {
   public static final IRecipeType<HearthRecipe> TYPE;
    static {
     final ResourceLocation key = HiganRecipeSerializer.HEARTH.getRegistryName();
     TYPE = (IRecipeType<HearthRecipe>)Registry.func_218322_a(Registry.field_218367_H, key, new IRecipeType<HearthRecipe>()
         {
           public String toString() {
             return key.toString();
           }
         });
   }

 
 
 
 
 
   public HearthRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn, int time) {
     this.id = idIn;
     this.group = groupIn;
     this.recipeWidth = recipeWidthIn;
     this.recipeHeight = recipeHeightIn;
     this.recipeItems = recipeItemsIn;
     this.recipeOutput = recipeOutputIn;
     this.cookingTime = time;
   }

   public ResourceLocation func_199560_c() {
     return this.id;
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

   public boolean func_194133_a(int width, int height) {
     return (width >= this.recipeWidth && height >= this.recipeHeight);
   }

   public boolean func_77569_a(IInventory inv, World worldIn) {
     for (int i = 0; i <= 3 - this.recipeWidth; i++) {
       for (int j = 0; j <= 3 - this.recipeHeight; j++) {
         if (checkMatch(inv, i, j, true)) {
           return true;
         }
                if (checkMatch(inv, i, j, false)) {
           return true;
         }
       }    }       return false;
   }
  private boolean checkMatch(IInventory craftingInventory, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         int k = i - p_77573_2_;
         int l = j - p_77573_3_;
         Ingredient ingredient = Ingredient.field_193370_a;
         if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
           if (p_77573_4_) {
             ingredient = (Ingredient)this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
           } else {
             ingredient = (Ingredient)this.recipeItems.get(k + l * this.recipeWidth);
           }        }        
         if (!ingredient.test(craftingInventory.func_70301_a(i + j * 3 + 1))) {
           return false;
         }
       }    }       return true;
   }

   public ItemStack func_77572_b(IInventory inv) {
     return func_77571_b().func_77946_l();
   }
  public int getWidth() {
     return this.recipeWidth;
   }
  public int getHeight() {
     return this.recipeHeight;
   }

   public int getRecipeWidth() {
     return this.recipeWidth;
   }

   public int getRecipeHeight() {
     return this.recipeHeight;
   }
  private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
     NonNullList<Ingredient> nonnulllist = NonNullList.func_191197_a(patternWidth * patternHeight, Ingredient.field_193370_a);
     Set<String> set = Sets.newHashSet(keys.keySet());
     set.remove(" ");
        for (int i = 0; i < pattern.length; i++) {
       for (int j = 0; j < pattern[i].length(); j++) {
         String s = pattern[i].substring(j, j + 1);
         Ingredient ingredient = keys.get(s);
         if (ingredient == null) {
           throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
         }
                set.remove(s);
         nonnulllist.set(j + patternWidth * i, ingredient);
       }    }       if (!set.isEmpty()) {
       throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
     }
     return nonnulllist;
   }

   @VisibleForTesting
   static String[] shrink(String... toShrink) {
     int i = Integer.MAX_VALUE;
     int j = 0;
     int k = 0;
     int l = 0;
        for (int i1 = 0; i1 < toShrink.length; i1++) {
       String s = toShrink[i1];
       i = Math.min(i, firstNonSpace(s));
       int j1 = lastNonSpace(s);
       j = Math.max(j, j1);
       if (j1 < 0) {
         if (k == i1) {
           k++;
         }
                l++;
       } else {
         l = 0;
       }    }       if (toShrink.length == l) {
       return new String[0];
     }
     String[] astring = new String[toShrink.length - l - k];
        for (int k1 = 0; k1 < astring.length; k1++) {
       astring[k1] = toShrink[k1 + k].substring(i, j + 1);
     }
        return astring;
   }

   private static int firstNonSpace(String str) {
     int i;
     for (i = 0; i < str.length() && str.charAt(i) == ' '; i++);
    
     return i;
   }
  private static int lastNonSpace(String str) {
     int i;
     for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; i--);
    
     return i;
   }
  private static String[] patternFromJson(JsonArray jsonArr) {
     String[] astring = new String[jsonArr.size()];
     if (astring.length > 3)
       throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");    if (astring.length == 0) {
       throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
     }
     for (int i = 0; i < astring.length; i++) {
       String s = JSONUtils.func_151206_a(jsonArr.get(i), "pattern[" + i + "]");
       if (s.length() > 3) {
         throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
       }
            if (i > 0 && astring[0].length() != s.length()) {
         throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
       }
            astring[i] = s;       
     return astring;
   }

   private static Map<String, Ingredient> deserializeKey(JsonObject json) {
     Map<String, Ingredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)json.entrySet()) {
       if (((String)entry.getKey()).length() != 1) {
         throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
       }
            if (" ".equals(entry.getKey())) {
         throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
       }
            map.put(entry.getKey(), Ingredient.func_199802_a(entry.getValue()));       
     map.put(" ", Ingredient.field_193370_a);
     return map;
   }
  public static ItemStack deserializeItem(JsonObject p_199798_0_) {
     String s = JSONUtils.func_151200_h(p_199798_0_, "item");
     Item item = (Item)Registry.field_212630_s.func_218349_b(new ResourceLocation(s)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + s + "'"));
       if (p_199798_0_.has("data")) {
       throw new JsonParseException("Disallowed data tag found");
     }
     int i = JSONUtils.func_151208_a(p_199798_0_, "count", 1);
     return CraftingHelper.getItemStack(p_199798_0_, true);
   }

   public IRecipeSerializer<?> func_199559_b() {
     return HiganRecipeSerializer.HEARTH;
   }

   public IRecipeType<?> func_222127_g() {
     return TYPE;
   }

   public int getCookTime() {
     return this.cookingTime;
   }
  private static CompoundNBT patternNBTFromJson(JsonArray jsonArr, CompoundNBT nbt) {
     if (jsonArr.size() == 0) {
       throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
     }
     for (int i = 0; i < jsonArr.size(); i++) {
       String s = JSONUtils.func_151206_a(jsonArr.get(i), "nbt[" + i + "]");
       String[] tmp = s.split(":");
       if (tmp.length == 2) {
         if (tmp[1].matches("\\d+")) {
           nbt.func_74768_a(tmp[0], Integer.parseInt(tmp[1]));
         } else if (tmp[1].equalsIgnoreCase("true") || tmp[1].equalsIgnoreCase("false")) {
           nbt.func_74757_a(tmp[0], Boolean.parseBoolean(tmp[1]));
         }      }       
     return nbt;
   }
  public static class Serializer
     extends ForgeRegistryEntry<IRecipeSerializer<?>>
     implements IRecipeSerializer<HearthRecipe>
   {
     public HearthRecipe read(ResourceLocation recipeId, JsonObject json) {
       String s = JSONUtils.func_151219_a(json, "group", "");
       Map<String, Ingredient> map = HearthRecipe.deserializeKey(JSONUtils.func_152754_s(json, "key"));
       String[] astring = HearthRecipe.shrink(HearthRecipe.patternFromJson(JSONUtils.func_151214_t(json, "pattern")));
       int i = astring[0].length();
       int j = astring.length;
       NonNullList<Ingredient> nonnulllist = HearthRecipe.deserializeIngredients(astring, map, i, j);
       ItemStack itemstack = HearthRecipe.deserializeItem(JSONUtils.func_152754_s(json, "result"));
       int time = JSONUtils.func_151208_a(json, "cookingtime", 200);
       if (JSONUtils.func_151202_d(json, "nbt")) {
         HearthRecipe.patternNBTFromJson(JSONUtils.func_151214_t(json, "nbt"), itemstack.func_190925_c("BlockEntityTag"));
       }
            return new HearthRecipe(recipeId, s, i, j, nonnulllist, itemstack, time);
     }

     public HearthRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
       int i = buffer.func_150792_a();
       int j = buffer.func_150792_a();
       String s = buffer.func_150789_c(32767);
       NonNullList<Ingredient> nonnulllist = NonNullList.func_191197_a(i * j, Ingredient.field_193370_a);
            for (int k = 0; k < nonnulllist.size(); k++) {
         nonnulllist.set(k, Ingredient.func_199566_b(buffer));
       }
            ItemStack itemstack = buffer.func_150791_c();
       int time = buffer.func_150792_a();
       return new HearthRecipe(recipeId, s, i, j, nonnulllist, itemstack, time);
     }

     public void write(PacketBuffer buffer, HearthRecipe recipe) {
       buffer.func_150787_b(recipe.recipeWidth);
       buffer.func_150787_b(recipe.recipeHeight);
       buffer.func_180714_a(recipe.group);
            for (Ingredient ingredient : recipe.recipeItems) {
         ingredient.func_199564_a(buffer);
       }
            buffer.func_150788_a(recipe.recipeOutput);
       buffer.func_150787_b(recipe.cookingTime);
     }
   }
 }

