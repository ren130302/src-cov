 package com.ruby.meshi.client.gui.recipebook;
import com.ruby.meshi.block.tileentity.HearthTileEntity;
 import com.ruby.meshi.common.inventory.HearthContainer;
 import com.ruby.meshi.item.HiganItems;
 import com.ruby.meshi.util.EnumHelper;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Map;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;
 import net.minecraft.client.gui.recipebook.RecipeList;
 import net.minecraft.client.util.RecipeBookCategories;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.Items;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.util.IItemProvider;

 public class HiganRecipeBookCategories
 {
   public static final RecipeBookCategories GRIND = create("grind", new ItemStack[] { new ItemStack((IItemProvider)HiganItems.RAW_RICE) });
   public static final RecipeBookCategories HEARTH = create("hearth", new ItemStack[] { new ItemStack((IItemProvider)HiganItems.STRAW) });
   public static final RecipeBookCategories HEARTH_SEARCH = create("hearth_search", new ItemStack[] { new ItemStack((IItemProvider)Items.field_151111_aL) });
  private static RecipeBookCategories create(String name, ItemStack... icons) {
     return EnumHelper.<RecipeBookCategories>addEnum(RecipeBookCategories.class, name, (RecipeBookCategories.values()).length, new Class[] { ItemStack[].class }, new Object[] { icons });
   }

 
 
 
 
   public static boolean isHiganRecipe(IRecipe<?> recipe) {
     return (recipe instanceof com.ruby.meshi.crafting.GrindRecipe || recipe instanceof com.ruby.meshi.crafting.HearthRecipe || recipe instanceof com.ruby.meshi.crafting.HearthShapelessRecipe);
   }

 
 
   public static RecipeBookCategories getCategorie(IRecipe<?> recipe) {
     if (recipe instanceof com.ruby.meshi.crafting.GrindRecipe) {
       return GRIND;
     }
     return HEARTH;
   }

 
 
 
   public static void postRebuildTable(Map<RecipeBookCategories, List<RecipeList>> map, List<RecipeList> list) {
     list.removeIf(rl -> rl.func_192711_b().stream().anyMatch(()));
     ((List)map.get(RecipeBookCategories.SEARCH)).removeIf(rl -> rl.func_192711_b().stream().anyMatch(HiganRecipeBookCategories::isHiganRecipe));
     map.put(HEARTH_SEARCH, (List<RecipeList>)((List)HearthContainer.CATEGORIES.get()).stream()
         .filter(c -> (c != HEARTH_SEARCH))
         .flatMap(c -> ((List)map.get(c)).stream())
         .collect(Collectors.toList()));
     Comparator<RecipeList> sorter = (a, b) -> getResultName(a).compareTo(getResultName(b));
        ((List<RecipeList>)map.get(HEARTH)).sort(sorter);
     ((List<RecipeList>)map.get(HEARTH_SEARCH)).sort(sorter);
     ((List<RecipeList>)map.get(GRIND)).sort(sorter);
   }

   private static String getResultName(RecipeList list) {
     return ((ItemStack)list.func_192711_b().stream().findFirst().map(r -> r.func_77571_b()).orElse(ItemStack.field_190927_a)).func_200301_q().getString();
   }
 }

