 package com.ruby.meshi.block;
import com.ruby.meshi.item.HiganItems;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.CropsBlock;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IBlockReader;
public class RicePlant
   extends CropsBlock
   implements CustomItemBlock {
   protected RicePlant(Block.Properties builder) {
     super(builder);
   }

   public IItemProvider func_199772_f() {
     return (IItemProvider)HiganItems.RICE_SEED;
   }

   public ItemStack func_185473_a(IBlockReader worldIn, BlockPos pos, BlockState state) {
     return new ItemStack(func_199772_f());
   }

   public Item getBlockItem(Block block, Item.Properties prop) {
     return EMPTY;
   }
 }

