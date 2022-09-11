 package com.ruby.meshi.block;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.PaneBlock;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
public class PaneBlock extends PaneBlock {
   public PaneBlock(Block.Properties builder) {
     super(builder);
   }

   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return this.field_196274_w ? super.func_220071_b(state, worldIn, pos, context) : VoxelShapes.func_197880_a();
   }
 }

