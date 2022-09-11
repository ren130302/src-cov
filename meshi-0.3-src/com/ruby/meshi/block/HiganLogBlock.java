 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.LogBlock;
 import net.minecraft.block.material.MaterialColor;
 import net.minecraftforge.common.ToolType;
public class HiganLogBlock extends LogBlock {
   public HiganLogBlock(MaterialColor color, Block.Properties prop) {
     super(color, prop);
   }

   public ToolType getHarvestTool(BlockState state) {
     return ToolType.AXE;
   }

   public int getHarvestLevel(BlockState state) {
     return 0;
   }
 }

