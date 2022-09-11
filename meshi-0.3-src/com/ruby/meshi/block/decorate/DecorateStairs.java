 package com.ruby.meshi.block.decorate;
import com.ruby.meshi.block.CustomItemBlock;
 import com.ruby.meshi.client.CreativeTab;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.StairsBlock;
 import net.minecraft.item.Item;
public class DecorateStairs
   extends StairsBlock implements CustomItemBlock {
   public DecorateStairs(BlockState p_i48321_1_, Block.Properties p_i48321_2_) {
     super(p_i48321_1_, p_i48321_2_);
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.DECO_GROUP);
   }
 }

