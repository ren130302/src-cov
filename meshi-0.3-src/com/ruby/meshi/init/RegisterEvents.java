 package com.ruby.meshi.init;
import com.ruby.meshi.block.CustomItemBlock;
 import com.ruby.meshi.block.HideCreateTab;
 import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.client.CreativeTab;
 import com.ruby.meshi.core.MeshiMod;
 import com.ruby.meshi.crafting.HiganRecipeSerializer;
 import com.ruby.meshi.enchant.HiganEnchant;
 import com.ruby.meshi.entity.HiganEntityType;
 import com.ruby.meshi.fluid.MeshiFluids;
 import com.ruby.meshi.item.HiganItems;
 import com.ruby.meshi.world.biome.HiganBiomes;
 import com.ruby.meshi.world.gen.HiganGenerater;
 import java.lang.reflect.Field;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.enchantment.Enchantment;
 import net.minecraft.entity.EntityType;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.tileentity.TileEntityType;
 import net.minecraft.world.biome.Biome;
 import net.minecraft.world.gen.feature.Feature;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 import net.minecraftforge.registries.IForgeRegistryEntry;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
 public class RegisterEvents {
   @SubscribeEvent
   public static void onBlocksRegistry(RegistryEvent.Register<Block> reg) {
     reg.getRegistry().registerAll((IForgeRegistryEntry[])getBlocks());
   }

   @SubscribeEvent
   public static void onItemsRegistry(RegistryEvent.Register<Item> reg) {
     reg.getRegistry().registerAll((IForgeRegistryEntry[])getBlockItems());
     reg.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganItems.class).toArray(x$0 -> new Item[x$0]));
   }
  @SubscribeEvent
   public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganEntityType.class).toArray(x$0 -> new EntityType[x$0]));
   }
  @SubscribeEvent
   public static void onEntityTypeRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganTileEntityType.class).toArray(x$0 -> new TileEntityType[x$0]));
   }
  @SubscribeEvent
   public static void onContinerTypeRegistry(RegistryEvent.Register<ContainerType<?>> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganContainerType.class).toArray(x$0 -> new ContainerType[x$0]));
   }
  @SubscribeEvent
   public static void onWorldGenRegistry(RegistryEvent.Register<Feature<?>> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganGenerater.class).toArray(x$0 -> new Feature[x$0]));
   }
  @SubscribeEvent
   public static void onRecipeSerialize(RegistryEvent.Register<IRecipeSerializer<?>> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganRecipeSerializer.class).toArray(x$0 -> new IRecipeSerializer[x$0]));
   }
  @SubscribeEvent
   public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganBiomes.class).toArray(x$0 -> new Biome[x$0]));
   }
  @SubscribeEvent
   public static void onEnchantRegistry(RegistryEvent.Register<Enchantment> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganEnchant.class).toArray(x$0 -> new Enchantment[x$0]));
   }
  @SubscribeEvent
   public static void onFluidRegistry(RegistryEvent.Register<Fluid> event) {
     event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(MeshiFluids.class).toArray(x$0 -> new Fluid[x$0]));
   }

 
 
   private static Block[] getBlocks() {
     return (Block[])getFields(SakuraBlocks.class).toArray(x$0 -> new Block[x$0]);
   }
  private static Item[] getBlockItems() {
     return (Item[])Stream.<Block>of(getBlocks())
       .map(b -> {
           BlockItem blockItem;
                   Item.Properties prop = new Item.Properties();          
           if (b instanceof CustomItemBlock) {
             Item item = ((CustomItemBlock)b).getBlockItem(b, prop);
           } else {
             if (b.getClass().getAnnotation(HideCreateTab.class) == null) {
               prop = prop.func_200916_a(CreativeTab.ITEM_GROUP);
             }
                        blockItem = new BlockItem(b, prop);                   
           return (blockItem != CustomItemBlock.EMPTY) ? (Item)blockItem.setRegistryName(b.getRegistryName()) : CustomItemBlock.EMPTY;
         }).filter(i -> (i != CustomItemBlock.EMPTY))
       .toArray(x$0 -> new Item[x$0]);
   }

 
   public static <T> Stream<T> getFields(Class<?> c) {
     return Stream.<Field>of(c.getFields())
       .flatMap(f -> {
           try {
             Object obj = f.get(null);

             
             return obj.getClass().isArray() ? Stream.of((Object[])obj) : Stream.of(obj);
           } catch (Exception e) {
             MeshiMod.warnlog("Field get Exception: " + f.toString());
             return null;
           }        });
   }

   public static Item[] getBlockToItem(Block... block) {
     return (Item[])Stream.<Block>of(block).map(Item.field_179220_a::get).toArray(x$0 -> new Item[x$0]);
   }
  public static Block[] getItemToBlock(Item... item) {
     return (Block[])Stream.<Item>of(item).map(Block::func_149634_a).toArray(x$0 -> new Block[x$0]);
   }
 }

