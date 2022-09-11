 package com.ruby.meshi.block;
import java.util.List;
 import java.util.Random;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.OreBlock;
 import net.minecraft.item.ItemStack;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.world.storage.loot.LootContext;
 import net.minecraftforge.common.ToolType;
public class HiganOre
   extends OreBlock {
   public HiganOre(Block.Properties properties) {
     super(properties);
   }

   public ToolType getHarvestTool(BlockState state) {
     return ToolType.PICKAXE;
   }

   public int getHarvestLevel(BlockState state) {
     if (this == SakuraBlocks.SAKURA_ORE) {
       return 3;
     }
     if (this == SakuraBlocks.GINKGO_ORE) {
       return 2;
     }
     return 1;
   }

   protected int func_220281_a(Random rand) {
     if (this == SakuraBlocks.SAKURA_ORE) {
       return MathHelper.func_76136_a(rand, 6, 10);
     }
     if (this == SakuraBlocks.GINKGO_ORE) {
       return MathHelper.func_76136_a(rand, 3, 7);
     }
     return 0;
   }

   public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
     List<ItemStack> list = super.func_220076_a(state, builder);
     list.forEach(stack -> stack.func_190920_e(Math.max(1, stack.func_190916_E() / 2)));
     return list;
   }
 }

