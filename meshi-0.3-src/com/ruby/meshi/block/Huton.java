 package com.ruby.meshi.block;
import net.minecraft.block.BedBlock;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.item.DyeColor;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
public class Huton extends BedBlock {
   public static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
  public Huton(Block.Properties properties) {
     super(DyeColor.WHITE, properties);
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return SHAPE;
   }

   public boolean hasTileEntity(BlockState state) {
     return false;
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return BlockRenderType.MODEL;
   }
 }

