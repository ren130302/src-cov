 package com.ruby.meshi.block.decorate;
import com.ruby.meshi.block.CustomItemBlock;
 import com.ruby.meshi.client.CreativeTab;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.item.Item;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IBlockReader;
public class DecorateBlock
   extends Block
   implements CustomItemBlock {
   public DecorateBlock(Block.Properties properties) {
     super(properties);
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.DECO_GROUP);
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }
 }

