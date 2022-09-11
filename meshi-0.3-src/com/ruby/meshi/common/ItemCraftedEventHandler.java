 package com.ruby.meshi.common;
import com.google.common.collect.Lists;
 import com.ruby.meshi.item.HiganItems;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Optional;
 import java.util.Random;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.function.Consumer;
 import java.util.function.Predicate;
 import java.util.stream.Collector;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;
 import java.util.stream.Stream;
 import net.minecraft.enchantment.EnchantmentHelper;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.ArmorItem;
 import net.minecraft.item.ArmorMaterial;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemTier;
 import net.minecraft.item.TieredItem;
 import net.minecraft.world.World;
 import net.minecraftforge.common.Tags;
 import net.minecraftforge.event.entity.player.PlayerEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class ItemCraftedEventHandler {
   private static final List<Consumer<CraftedEventWrapper>> list = Lists.newArrayList();
  static {
     register(vanillaToolEnchant());
   }
  @SubscribeEvent
   public static void craftRelease(PlayerEvent.ItemCraftedEvent event) {
     PlayerEntity player = event.getPlayer();
     if (player != null) {
       World world = (event.getPlayer()).field_70170_p;
       if (world != null && !world.field_72995_K) {
         list.forEach(c -> c.accept(new CraftedEventWrapper(event)));
       }
     }  }
   public static void register(Consumer<CraftedEventWrapper> listener) {
     list.add(listener);
   }
  public static class CraftedEventWrapper {
     public final PlayerEvent.ItemCraftedEvent event;
     public final PlayerEntity player;
     public final World world;
     public final ItemStack crafting;
     public final IInventory inventory;
     public final ItemStack[] inventoryStacks;
        CraftedEventWrapper(PlayerEvent.ItemCraftedEvent event) {
       this.event = event;
       this.player = event.getPlayer();
       this.world = (this.player != null) ? this.player.func_130014_f_() : null;
       this.crafting = event.getCrafting();
       this.inventory = event.getInventory();
       this
                .inventoryStacks = (ItemStack[])IntStream.range(0, this.inventory.func_70302_i_()).mapToObj(i -> this.inventory.func_70301_a(i)).toArray(x$0 -> new ItemStack[x$0]);
     }
    public Stream<ItemStack> getFiltedInventoryStream(Predicate<? super ItemStack> filter) {
       return Arrays.<ItemStack>stream(this.inventoryStacks).filter(filter::test);
     }
    public int getFindCount(Predicate<? super ItemStack> filter) {
       return (int)getFiltedInventoryStream(filter).count();
     }
    public List<ItemStack> getFindList(Predicate<? super ItemStack> filter) {
       return getFiltedInventoryStream(filter).collect((Collector)Collectors.toList());
     }
    public Optional<ItemStack> getFindFirst(Predicate<? super ItemStack> filter) {
       return getFiltedInventoryStream(filter).findFirst();
     }
   }

   private static Consumer<CraftedEventWrapper> vanillaToolEnchant() {
     return event -> {
         Item item = event.crafting.func_77973_b();
         if (item.getRegistryName().func_110624_b().equals("minecraft")) {
           Predicate<ItemStack> allMaterialFilter = ();
           Predicate<ItemStack> mapleMaterialFilter = ();
           int randomRate = 15;
           Random rand = ThreadLocalRandom.current();
           if (item instanceof TieredItem) {
             if (((TieredItem)item).func_200891_e() == ItemTier.IRON) {
               allMaterialFilter = (());
               mapleMaterialFilter = (());
             } else if (((TieredItem)item).func_200891_e() == ItemTier.DIAMOND) {
               allMaterialFilter = (());
               mapleMaterialFilter = (());
               randomRate = 30;
             }          } else if (item instanceof ArmorItem) {
             if (((ArmorItem)item).func_200880_d() == ArmorMaterial.IRON) {
               allMaterialFilter = (());
               mapleMaterialFilter = (());
             } else if (((ArmorItem)item).func_200880_d() == ArmorMaterial.DIAMOND) {
               allMaterialFilter = (());
               mapleMaterialFilter = (());
               randomRate = 20;
             }          }          int mcount = event.getFindCount(mapleMaterialFilter);
           if (mcount > 0) {
             int acount = event.getFindCount(allMaterialFilter);
             EnchantmentHelper.func_77504_a(rand, event.crafting, 5 + rand.nextInt((int)(mcount / acount * randomRate)), (randomRate > 15));
             ((ServerPlayerEntity)event.player).func_71113_k();
           }        }      };
   }
 }

