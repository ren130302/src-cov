 package com.ruby.meshi.block;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.FourWayBlock;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.World;
public class Kotatsu extends FourWayBlock {
   protected Kotatsu(Block.Properties properties) {
     super(5.0F, 5.0F, 10.0F, 10.0F, 10.0F, properties);
     func_180632_j((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((IProperty)field_196409_a, Boolean.valueOf(false))).func_206870_a((IProperty)field_196411_b, Boolean.valueOf(false))).func_206870_a((IProperty)field_196413_c, Boolean.valueOf(false))).func_206870_a((IProperty)field_196414_y, Boolean.valueOf(false))).func_206870_a((IProperty)field_204514_u, Boolean.valueOf(false)));
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     World world = context.func_195991_k();
     BlockPos blockpos = context.func_195995_a();
     IFluidState ifluidstate = context.func_195991_k().func_204610_c(context.func_195995_a());
     BlockPos blockpos1 = blockpos.func_177978_c();
     BlockPos blockpos2 = blockpos.func_177968_d();
     BlockPos blockpos3 = blockpos.func_177976_e();
     BlockPos blockpos4 = blockpos.func_177974_f();
     BlockState blockstate = world.func_180495_p(blockpos1);
     BlockState blockstate1 = world.func_180495_p(blockpos2);
     BlockState blockstate2 = world.func_180495_p(blockpos3);
     BlockState blockstate3 = world.func_180495_p(blockpos4);
     return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)field_196409_a, Boolean.valueOf(canAttachTo(blockstate, blockstate.func_224755_d((IBlockReader)world, blockpos1, Direction.SOUTH))))).func_206870_a((IProperty)field_196413_c, Boolean.valueOf(canAttachTo(blockstate1, blockstate1.func_224755_d((IBlockReader)world, blockpos2, Direction.NORTH))))).func_206870_a((IProperty)field_196414_y, Boolean.valueOf(canAttachTo(blockstate2, blockstate2.func_224755_d((IBlockReader)world, blockpos3, Direction.EAST))))).func_206870_a((IProperty)field_196411_b, Boolean.valueOf(canAttachTo(blockstate3, blockstate3.func_224755_d((IBlockReader)world, blockpos4, Direction.WEST))))).func_206870_a((IProperty)field_204514_u, Boolean.valueOf((ifluidstate.func_206886_c() == Fluids.field_204546_a)));
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     return facing.func_176740_k().func_176722_c() ? (BlockState)stateIn.func_206870_a((IProperty)field_196415_z.get(facing), Boolean.valueOf(canAttachTo(facingState, facingState.func_224755_d((IBlockReader)worldIn, facingPos, facing.func_176734_d())))) : super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }
  public boolean canAttachTo(BlockState p_220112_1_, boolean p_220112_2_) {
     Block block = p_220112_1_.func_177230_c();
     return ((!func_220073_a(block) && p_220112_2_) || block instanceof Kotatsu);
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT_MIPPED;
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)field_196409_a, (IProperty)field_196411_b, (IProperty)field_196414_y, (IProperty)field_196413_c, (IProperty)field_204514_u });
   }
 }

