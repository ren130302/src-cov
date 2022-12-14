 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;
 import com.ruby.meshi.client.CreativeTab;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.DoorBlock;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.Item;
 import net.minecraft.item.TallBlockItem;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.DoorHingeSide;
 import net.minecraft.state.properties.DoubleBlockHalf;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.Mirror;
 import net.minecraft.util.Rotation;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.World;
public class SlideDoor
   extends DoorBlock implements CustomItemBlock {
   public static final BooleanProperty MIRROR = BooleanProperty.func_177716_a("mirror");
   public static final BooleanProperty MOVED = BooleanProperty.func_177716_a("move");
  protected static final VoxelShape SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 8.0D);
   protected static final VoxelShape NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 9.0D);
   protected static final VoxelShape WEST_AABB = Block.func_208617_a(8.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
   protected static final VoxelShape EAST_AABB = Block.func_208617_a(7.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
  public SlideDoor(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)MIRROR, Boolean.valueOf(false))).func_206870_a((IProperty)MOVED, Boolean.valueOf(false)));
   }

   public boolean hasTileEntity(BlockState state) {
     return (((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue() || ((Boolean)state.func_177229_b((IProperty)MOVED)).booleanValue());
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     if (hasTileEntity(state)) {
       return new SlideDoorTileEntity();
     }
     return null;
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return (((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue() || ((Boolean)state.func_177229_b((IProperty)MOVED)).booleanValue()) ? BlockRenderType.INVISIBLE : super.func_149645_b(state);
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     Direction direction = (Direction)state.func_177229_b((IProperty)field_176520_a);
     switch (direction)
        { default:
         return EAST_AABB;
       case SOUTH:
         return SOUTH_AABB;
       case WEST:
         return WEST_AABB;
       case NORTH:
         break; }  return NORTH_AABB;
   }

   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return ((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue() ? VoxelShapes.func_197880_a() : state.func_196954_c(worldIn, pos);
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.SOLID;
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     state = (BlockState)state.func_177231_a((IProperty)field_176519_b);
     worldIn.func_180501_a(pos, state, 10);
     onMove(state, worldIn, pos);
     func_196426_b(worldIn, pos, ((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue());
     return true;
   }

   public void func_176512_a(World worldIn, BlockPos pos, boolean open) {
     BlockState blockstate = worldIn.func_180495_p(pos);
     if (blockstate.func_177230_c() == this && ((Boolean)blockstate.func_177229_b((IProperty)field_176519_b)).booleanValue() != open) {
       worldIn.func_180501_a(pos, (BlockState)blockstate.func_206870_a((IProperty)field_176519_b, Boolean.valueOf(open)), 10);
       onMove(blockstate, worldIn, pos);
       func_196426_b(worldIn, pos, open);
     }  }
 
   public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
     boolean flag = (worldIn.func_175640_z(pos) || worldIn.func_175640_z(pos.func_177972_a((state.func_177229_b((IProperty)field_176523_O) == DoubleBlockHalf.LOWER) ? Direction.UP : Direction.DOWN)));
     if (blockIn != this && flag != ((Boolean)state.func_177229_b((IProperty)field_176522_N)).booleanValue()) {
       if (flag != ((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue()) {
         onMove(state, worldIn, pos);
         func_196426_b(worldIn, pos, flag);
       }      worldIn.func_180501_a(pos, (BlockState)((BlockState)state.func_206870_a((IProperty)field_176522_N, Boolean.valueOf(flag))).func_206870_a((IProperty)field_176519_b, Boolean.valueOf(flag)), 2);    }  }
 
   void onMove(BlockState state, World worldIn, BlockPos pos) {
     boolean isOpen = ((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue();
     if (isOpen) {
       worldIn.func_175690_a(pos, createTileEntity(state, (IBlockReader)worldIn));
     }
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     BlockPos blockpos = context.func_195995_a();
     if (blockpos.func_177956_o() < 255 && context.func_195991_k().func_180495_p(blockpos.func_177984_a()).func_196953_a(context)) {
       World world = context.func_195991_k();
       boolean flag = (world.func_175640_z(blockpos) || world.func_175640_z(blockpos.func_177984_a()));
       Vec3d vec = context.func_221532_j();
       return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)field_176520_a, (Comparable)context.func_195992_f())).func_206870_a((IProperty)field_176521_M, (Comparable)func_208073_b(context))).func_206870_a((IProperty)field_176522_N, Boolean.valueOf(flag))).func_206870_a((IProperty)field_176519_b, Boolean.valueOf(flag))).func_206870_a((IProperty)field_176523_O, (Comparable)DoubleBlockHalf.LOWER)).func_206870_a((IProperty)MIRROR, Boolean.valueOf(isMirrorTextuer(context)));
     }    return null;
   }

   DoorHingeSide func_208073_b(BlockItemUseContext context) {
     BlockPos blockpos = context.func_195995_a();
     Direction direction = context.func_195992_f();
     int j = direction.func_82601_c();
     int k = direction.func_82599_e();
     Vec3d vec3d = context.func_221532_j();
     double d0 = vec3d.field_72450_a - blockpos.func_177958_n();
     double d1 = vec3d.field_72449_c - blockpos.func_177952_p();
     return ((j >= 0 || d1 >= 0.5D) && (j <= 0 || d1 <= 0.5D) && (k >= 0 || d0 <= 0.5D) && (k <= 0 || d0 >= 0.5D)) ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
   }
  boolean isMirrorTextuer(BlockItemUseContext context) {
     BlockPos blockpos = context.func_195995_a();
     World world = context.func_195991_k();
     Direction placerFacing = context.func_195992_f();
        if (context.func_195998_g()) {
       return false;
     }
     for (Direction dir : Direction.Plane.HORIZONTAL) {
       BlockState state = world.func_180495_p(blockpos.func_177971_a(dir.func_176730_m()));
            if (state.func_177230_c() instanceof SlideDoor) {
         Direction facing = (Direction)state.func_177229_b((IProperty)field_176520_a);
         boolean isMirror = ((Boolean)state.func_177229_b((IProperty)MIRROR)).booleanValue();
         if (facing == placerFacing.func_176734_d() && !isMirror) {
           return true;
         }
         if (facing == placerFacing && isMirror) {
           return true;
         }
       }    }    return false;
   }

 
 
   public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
     if (state.func_177230_c() != newState.func_177230_c()) {
       super.func_196243_a(state, worldIn, pos, newState, isMoving);
     }
   }

   public BlockState func_185499_a(BlockState state, Rotation rot) {
     if (((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue()) {
       return state;
     }
     return super.func_185499_a(state, rot);
   }

   public BlockState func_185471_a(BlockState state, Mirror mirrorIn) {
     if (((Boolean)state.func_177229_b((IProperty)field_176519_b)).booleanValue()) {
       return state;
     }
     return super.func_185471_a(state, mirrorIn);
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)MIRROR, (IProperty)MOVED });
   }

   public Item getBlockItem(Block block, Item.Properties prop) {
     return (Item)new TallBlockItem((Block)this, getProperty(prop).func_200916_a(CreativeTab.ITEM_GROUP));
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
  public static class LayerCutout extends SlideDoor {
     public LayerCutout(Block.Properties properties) {
       super(properties);
     }

     public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT;
     }
   }
  public static class LayerTranslucent extends SlideDoor {
     public LayerTranslucent(Block.Properties properties) {
       super(properties);
     }

     public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.TRANSLUCENT;
     }
   }
 }

