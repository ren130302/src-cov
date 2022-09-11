 package com.ruby.meshi.crafting;
import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import com.google.gson.JsonSyntaxException;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraft.item.crafting.ShapedRecipe;
 import net.minecraft.network.PacketBuffer;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.JSONUtils;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.registry.Registry;
 import net.minecraft.world.World;
 import net.minecraftforge.registries.ForgeRegistryEntry;
public class GrindRecipe
   implements IRecipe<IInventory>, NonLockRecipe {
   static {
     final ResourceLocation key = HiganRecipeSerializer.GRIND.getRegistryName();
     TYPE = (IRecipeType<GrindRecipe>)Registry.func_218322_a(Registry.field_218367_H, key, new IRecipeType<GrindRecipe>()
         {
           public String toString() {
             return key.toString();
           }
         });
   }

 
 
 
 
   public GrindRecipe(ResourceLocation recipeId, String group, Ingredient ingredient, int reqcount, ItemStack result, ItemStack bonus, float weight, int time) {
     this.id = recipeId;
     this.group = group;
     this.ingredient = ingredient;
     this.reqcount = reqcount;
     this.result = result;
     this.bonus = bonus;
     this.bonuswegiht = weight;
     this.grindingTime = time;
   }

   public boolean func_77569_a(IInventory inv, World worldIn) {
     return this.ingredient.test(inv.func_70301_a(0));
   }

   public ItemStack func_77572_b(IInventory inv) {
     return this.result.func_77946_l();
   }

   public boolean func_194133_a(int width, int height) {
     return (width == 1 && height == 1);
   }

   public ItemStack func_77571_b() {
     return this.result.func_77946_l();
   }

   public ResourceLocation func_199560_c() {
     return this.id;
   }

   public IRecipeSerializer<?> func_199559_b() {
     return HiganRecipeSerializer.GRIND;
   }

   public IRecipeType<?> func_222127_g() {
     return TYPE;
   }

   public String func_193358_e() {
     return this.group;
   }

   public NonNullList<Ingredient> func_192400_c() {
     return NonNullList.func_193580_a(Ingredient.field_193370_a, (Object[])new Ingredient[] { this.ingredient });
   }
  public int getRequestCount() {
     return this.reqcount;
   }
  public int getGrindingTime() {
     return this.grindingTime;
   }
  public ItemStack getResult() {
     return this.result.func_77946_l();
   }
  public ItemStack getBonus() {
     return this.bonus.func_77946_l();
   }
  public float getBonusWeight() {
     return this.bonuswegiht;
   }
  public static class Serializer
     extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrindRecipe> {
     public GrindRecipe read(ResourceLocation recipeId, JsonObject json) {
       ItemStack result;
       String s = JSONUtils.func_151219_a(json, "group", "");
       JsonElement jsonelement = JSONUtils.func_151202_d(json, "ingredient") ? (JsonElement)JSONUtils.func_151214_t(json, "ingredient") : (JsonElement)JSONUtils.func_152754_s(json, "ingredient");
       Ingredient ingredient = Ingredient.func_199802_a(jsonelement);
       if (!json.has("result")) {
         throw new JsonSyntaxException("Missing result, expected to find a string or object");
       }
            if (json.get("result").isJsonObject()) {
         result = ShapedRecipe.func_199798_a(JSONUtils.func_152754_s(json, "result"));
       } else {
         String s1 = JSONUtils.func_151200_h(json, "result");
         ResourceLocation resourcelocation = new ResourceLocation(s1);
         result = new ItemStack((IItemProvider)Registry.field_212630_s.func_218349_b(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
       }      
       int reqcount = JSONUtils.func_151208_a(json, "requestcount", 1);
       ItemStack bonus = ItemStack.field_190927_a;
       if (json.has("bonus")) {
         if (json.get("bonus").isJsonObject()) {
           bonus = ShapedRecipe.func_199798_a(JSONUtils.func_152754_s(json, "bonus"));
         } else {
           String s1 = JSONUtils.func_151200_h(json, "bonus");
           ResourceLocation resourcelocation = new ResourceLocation(s1);
           bonus = new ItemStack((IItemProvider)Registry.field_212630_s.func_218349_b(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
         }      }      
       float bonuswegiht = JSONUtils.func_151221_a(json, "bonusweight", 0.0F);
       int time = JSONUtils.func_151208_a(json, "grindingtime", 200);
       return new GrindRecipe(recipeId, s, ingredient, reqcount, result, bonus, bonuswegiht, time);
     }

     public GrindRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
       String group = buffer.func_150789_c(32767);
       Ingredient ingredient = Ingredient.func_199566_b(buffer);
       int req = buffer.func_150792_a();
       ItemStack result = buffer.func_150791_c();
       ItemStack bonus = buffer.func_150791_c();
       float wegiht = buffer.readFloat();
       int time = buffer.func_150792_a();
       return new GrindRecipe(recipeId, group, ingredient, req, result, bonus, wegiht, time);
     }

     public void write(PacketBuffer buffer, GrindRecipe recipe) {
       buffer.func_180714_a(recipe.group);
       recipe.ingredient.func_199564_a(buffer);
       buffer.func_150787_b(recipe.reqcount);
       buffer.func_150788_a(recipe.result);
       buffer.func_150788_a(recipe.bonus);
       buffer.writeFloat(recipe.bonuswegiht);
       buffer.func_150787_b(recipe.grindingTime);
     }
   }

   public String toString() {
     return this.result.toString() + this.id.toString();
   }
 }

