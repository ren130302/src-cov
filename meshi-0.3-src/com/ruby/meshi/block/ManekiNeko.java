 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.ManekiNekoTileEntity;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
public class ManekiNeko
   extends PlayerFacingBlock {
   public static final VoxelShape AABB = Block.func_208617_a(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);
  public ManekiNeko(Block.Properties properties) {
     super(properties);
   }

   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return new ManekiNekoTileEntity();
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return AABB;
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT;
   }

   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
     return true;
   }

   public boolean func_220060_c(BlockState state, IBlockReader worldIn, BlockPos pos) {
     return false;
   }

   public boolean func_220081_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
     return false;
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }
 }

