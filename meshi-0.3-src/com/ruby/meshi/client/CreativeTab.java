 package com.ruby.meshi.client;
import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.enchant.HiganEnchantType;
 import java.util.Comparator;
 import java.util.List;
 import java.util.stream.Collectors;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.item.ItemGroup;
 import net.minecraft.item.ItemStack;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.NonNullList;

 public class CreativeTab
 {
   public static final ItemGroup ITEM_GROUP = (new SortedItemGroup("meshi.base")
     {
       public ItemStack func_78016_d() {
         return new ItemStack((IItemProvider)SakuraBlocks.SAKURA_SAPLING);
       }
     }).func_111229_a(new EnchantmentType[] { HiganEnchantType.BRACELET });
  public static final ItemGroup DECO_GROUP = new SortedItemGroup("meshi.deco")
     {
       public ItemStack func_78016_d() {
         return new ItemStack((IItemProvider)SakuraBlocks.KAWARA_BLOCKS[0]);
       }
     };
  public static abstract class SortedItemGroup
     extends ItemGroup {
     public SortedItemGroup(String label) {
       super(label);
     }

     public void func_78018_a(NonNullList<ItemStack> items) {
       super.func_78018_a(items);
       Comparator<ItemStack> comp = (is1, is2) -> is1.func_77973_b().getRegistryName().func_110623_a().compareTo(is2.func_77973_b().getRegistryName().func_110623_a());
            List<ItemStack> blockList = (List<ItemStack>)items.stream().filter(is -> is.func_77973_b() instanceof net.minecraft.item.BlockItem).sorted(comp).collect(Collectors.toList());            List<ItemStack> itemList = (List<ItemStack>)items.stream().filter(is -> !blockList.contains(is)).sorted(comp).collect(Collectors.toList());
       
       items.clear();
       items.addAll(blockList);
       items.addAll(itemList);
     }
   }
 }

