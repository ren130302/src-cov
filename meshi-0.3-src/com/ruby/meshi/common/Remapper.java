 package com.ruby.meshi.common;
import com.google.common.collect.UnmodifiableIterator;
 import net.minecraft.block.Block;
 import net.minecraft.block.Blocks;
 import net.minecraft.item.Item;
 import net.minecraft.item.Items;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 import net.minecraftforge.registries.IForgeRegistryEntry;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class Remapper {
   @SubscribeEvent
   public static void remapBlocks(RegistryEvent.MissingMappings<Block> event) {
     for (UnmodifiableIterator<RegistryEvent.MissingMappings.Mapping<Block>> unmodifiableIterator = event.getMappings().iterator(); unmodifiableIterator.hasNext(); ) { RegistryEvent.MissingMappings.Mapping<Block> miss = unmodifiableIterator.next();
       if (event.getName().func_110624_b().equals("meshi")) {
         miss.remap((IForgeRegistryEntry)Blocks.field_150350_a);
       } }
    }
   @SubscribeEvent
   public static void remapItems(RegistryEvent.MissingMappings<Item> event) {
     for (UnmodifiableIterator<RegistryEvent.MissingMappings.Mapping<Item>> unmodifiableIterator = event.getMappings().iterator(); unmodifiableIterator.hasNext(); ) { RegistryEvent.MissingMappings.Mapping<Item> miss = unmodifiableIterator.next();
       if (event.getName().func_110624_b().equals("meshi"))
         miss.remap((IForgeRegistryEntry)Items.field_190931_a);  }
    }
 }

