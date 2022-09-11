 package com.ruby.meshi.block.decorate;
import com.ruby.meshi.block.CustomItemBlock;
 import com.ruby.meshi.client.CreativeTab;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.IWaterLoggable;
 import net.minecraft.entity.EntityType;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.pathfinding.PathType;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.DirectionProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tags.FluidTags;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
public class DecorateVerticalSlab extends Block implements CustomItemBlock, IWaterLoggable {
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
   public static final DirectionProperty FACING = BlockStateProperties.field_208155_H;
   public static final BooleanProperty DOUBLE = BooleanProperty.func_177716_a("double");
  public static final VoxelShape UP_AABB = Block.func_208617_a(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape DOWN_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
   public static final VoxelShape EAST_AABB = Block.func_208617_a(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
   public static final VoxelShape SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape WEST_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
  public DecorateVerticalSlab(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)((BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)FACING, (Comparable)Direction.NORTH)).func_206870_a((IProperty)DOUBLE, Boolean.valueOf(false))).func_206870_a((IProperty)WATERLOGGED, Boolean.valueOf(false)));
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     BlockPos blockpos = context.func_195995_a();
     BlockState blockstate = super.func_196258_a(context);
        World world = context.func_195991_k();
     Direction direction = context.func_196000_l().func_176734_d();
        BlockState contextState = context.func_195991_k().func_180495_p(blockpos);    
     if (contextState.func_177230_c() == this) {
       return (BlockState)((BlockState)contextState.func_206870_a((IProperty)DOUBLE, Boolean.valueOf(true))).func_206870_a((IProperty)WATERLOGGED, Boolean.valueOf(false));
     }
        IFluidState ifluidstate = context.func_195991_k().func_204610_c(blockpos);
     blockstate = (BlockState)((BlockState)((BlockState)blockstate.func_206870_a((IProperty)FACING, (Comparable)direction)).func_206870_a((IProperty)DOUBLE, Boolean.valueOf(false))).func_206870_a((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.func_206886_c() == Fluids.field_204546_a)));
     if (blockstate.func_196955_c((IWorldReader)world, blockpos)) {
       return blockstate;
     }
        return blockstate;
   }
  public boolean func_196253_a(BlockState state, BlockItemUseContext useContext) {
     ItemStack itemstack = useContext.func_195996_i();
     if (!((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue() && itemstack.func_77973_b() == func_199767_j()) {
       if (useContext.func_196012_c()) {
         boolean flag = ((useContext.func_221532_j()).field_72448_b - useContext.func_195995_a().func_177956_o() > 0.5D);
         Direction direction = useContext.func_196000_l().func_176734_d();
         return (!((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue() && direction == state.func_177229_b((IProperty)FACING));
       }      return true;       
     return false;
   }

 
   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     if (((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue()) {
       return VoxelShapes.func_197868_b();
     }
     switch ((Direction)state.func_177229_b((IProperty)FACING)) {
       case LAND:
         return UP_AABB;
       case WATER:
         return DOWN_AABB;
       case AIR:
         return EAST_AABB;
       case null:
         return NORTH_AABB;
       case null:
         return SOUTH_AABB;
       case null:
         return WEST_AABB;
     }    return VoxelShapes.func_197868_b();
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)FACING, (IProperty)DOUBLE, (IProperty)WATERLOGGED });
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.DECO_GROUP);
   }

   public boolean func_220074_n(BlockState state) {
     return !((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue();
   }

   public IFluidState func_204507_t(BlockState state) {
     return ((Boolean)state.func_177229_b((IProperty)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
   }

   public boolean func_204509_a(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
     return !((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue() ? super.func_204509_a(worldIn, pos, state, fluidStateIn) : false;
   }

   public boolean func_204510_a(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
     return !((Boolean)state.func_177229_b((IProperty)DOUBLE)).booleanValue() ? super.func_204510_a(worldIn, pos, state, fluidIn) : false;
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     if (((Boolean)stateIn.func_177229_b((IProperty)WATERLOGGED)).booleanValue()) {
       worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)worldIn));
     }
        return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public boolean func_196266_a(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
     switch (type) {
       case LAND:
         return false;
       case WATER:
         return worldIn.func_204610_c(pos).func_206884_a(FluidTags.field_206959_a);
       case AIR:
         return false;
     }    return false;
   }
 }

