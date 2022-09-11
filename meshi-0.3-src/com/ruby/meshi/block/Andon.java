 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
public class Andon extends PlayerFacingBlock {
   public static final BooleanProperty BOTTOM = BlockStateProperties.field_222513_b;
   public static final VoxelShape AABB = Block.func_208617_a(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
  public Andon(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)BOTTOM, Boolean.valueOf(false)));
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)BOTTOM });
   }

   public void func_220082_b(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
     super.func_220082_b(state, worldIn, pos, oldState, isMoving);
     onGround(state, worldIn, pos);
   }

   public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
     super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
     onGround(state, worldIn, pos);
   }
  private void onGround(BlockState state, World worldIn, BlockPos pos) {
     worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)BOTTOM, Boolean.valueOf(!worldIn.func_175623_d(pos.func_177977_b()))));
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

