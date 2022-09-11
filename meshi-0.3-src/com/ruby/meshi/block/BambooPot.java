 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.BambooPotTileEntity;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
public class BambooPot
   extends Block {
   public static final VoxelShape SHAPE = Block.func_208617_a(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
  public static final BooleanProperty ATTACHED = BlockStateProperties.field_208174_a;
   public BambooPot(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)ATTACHED, Boolean.valueOf(false)));
   }

 
   public BlockState func_196258_a(BlockItemUseContext context) {
     return super.func_196258_a(context);
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)ATTACHED });
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return BlockRenderType.MODEL;
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT;
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return SHAPE;
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

   public boolean hasTileEntity(BlockState state) {
     return ((Boolean)state.func_177229_b((IProperty)ATTACHED)).booleanValue();
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return new BambooPotTileEntity();
   }
 }

