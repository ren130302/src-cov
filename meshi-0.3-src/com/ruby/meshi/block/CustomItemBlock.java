 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.Items;
public interface CustomItemBlock {
   public static final Item EMPTY = Items.field_190931_a;
  default Item.Properties getProperty(Item.Properties prop) {
     return prop;
   }
  default Item getBlockItem(Block block, Item.Properties prop) {
     return (Item)new BlockItem(block, getProperty(prop));
   }
 }

