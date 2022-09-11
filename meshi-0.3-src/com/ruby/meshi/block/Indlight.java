 package com.ruby.meshi.block;
import com.ruby.meshi.client.CreativeTab;
 import java.util.Arrays;
 import java.util.List;
 import javax.annotation.Nullable;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.DirectionalBlock;
 import net.minecraft.entity.EntityType;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.DyeColor;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
 import net.minecraft.world.storage.loot.LootContext;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
public class Indlight extends DirectionalBlock implements SimpleColorMultiply<Indlight>, CustomItemBlock {
   public static final BooleanProperty NORTH = BlockStateProperties.field_208151_D;
   public static final BooleanProperty EAST = BlockStateProperties.field_208152_E;
   public static final BooleanProperty SOUTH = BlockStateProperties.field_208153_F;
   public static final BooleanProperty WEST = BlockStateProperties.field_208154_G;
  public static final BooleanProperty CONDITIONAL = BlockStateProperties.field_208176_c;
   public static final VoxelShape UP_AABB = Block.func_208617_a(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape DOWN_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
   public static final VoxelShape EAST_AABB = Block.func_208617_a(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
   public static final VoxelShape SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape WEST_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
  public Indlight(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((IProperty)field_176387_N, (Comparable)Direction.NORTH)).func_206870_a((IProperty)NORTH, Boolean.valueOf(false))).func_206870_a((IProperty)EAST, Boolean.valueOf(false))).func_206870_a((IProperty)WEST, Boolean.valueOf(false))).func_206870_a((IProperty)SOUTH, Boolean.valueOf(false))).func_206870_a((IProperty)CONDITIONAL, Boolean.valueOf(false)));
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     Direction dir = (Direction)state.func_177229_b((IProperty)field_176387_N);
     switch (dir) {
       case DOWN:
         return DOWN_AABB;
       case EAST:
         return EAST_AABB;
       case NORTH:
         return NORTH_AABB;
       case SOUTH:
         return SOUTH_AABB;
       case UP:
         return UP_AABB;
       case WEST:
         return WEST_AABB;
     }    return super.func_220053_a(state, worldIn, pos, context);
   }

   public Indlight setColorCode(DyeColor dye) {
     int[] color_table = { 16777215, 16750592, 13435085, 10092543, 16777012, 10092288, 16750797, 6710886, 10000536, 3447295, 6684877, 203, 3942166, 26164, 13369344, 0 };

 
 
 
 
 
 
     
     this.color = color_table[dye.func_196059_a() % color_table.length];
     return this;
   }

   public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
     List<ItemStack> stack = super.func_220076_a(state, builder);
     if (stack == null || stack.isEmpty()) {
       return Arrays.asList(new ItemStack[] { new ItemStack((IItemProvider)Item.func_150898_a((Block)this)) });
     }
     return super.func_220076_a(state, builder);
   }

   public int getColorCode() {
     return this.color;
   }

   @Nullable
   public BlockState func_196258_a(BlockItemUseContext context) {
     BlockState blockstate = func_176223_P();
     World world = context.func_195991_k();
     BlockPos blockpos = context.func_195995_a();
     Direction[] adirection = context.func_196009_e();
     blockstate = (BlockState)blockstate.func_206870_a((IProperty)CONDITIONAL, Boolean.valueOf(context.func_195998_g()));
        for (Direction direction : adirection) {
       blockstate = setSideState((BlockState)blockstate.func_206870_a((IProperty)field_176387_N, (Comparable)direction), (IWorldReader)world, blockpos);
            if (blockstate.func_196955_c((IWorldReader)world, blockpos)) {
         return blockstate;
       }
     }       return blockstate;
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     return setSideState(stateIn, (IWorldReader)worldIn, currentPos);
   }
  private BlockState setSideState(BlockState stateIn, IWorldReader worldIn, BlockPos currentPos) {
     switch ((Direction)stateIn.func_177229_b((IProperty)field_176387_N)) {
       case UP:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST))));
       case DOWN:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST))));
       case NORTH:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST))));
       case SOUTH:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST))));
            case EAST:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH))));
       case WEST:
         return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a((IProperty)NORTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))))
           .func_206870_a((IProperty)SOUTH, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))))
           .func_206870_a((IProperty)EAST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))))
           .func_206870_a((IProperty)WEST, Boolean.valueOf(canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH))));
     }    
     return stateIn;
   }
  private boolean canChain(IWorldReader worldIn, BlockState state, BlockPos targetPos) {
     BlockState targetState = worldIn.func_180495_p(targetPos);
     if (targetState.func_200015_d((IBlockReader)worldIn, targetPos)) {
       return ((Boolean)state.func_177229_b((IProperty)CONDITIONAL)).booleanValue();
     }
     if (targetState.func_177230_c() instanceof Indlight && targetState.func_177229_b((IProperty)field_176387_N) == state.func_177229_b((IProperty)field_176387_N)) {
       return (state.func_177229_b((IProperty)CONDITIONAL) == targetState.func_177229_b((IProperty)CONDITIONAL));
     }
        return false;
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.TRANSLUCENT;
   }

   @OnlyIn(Dist.CLIENT)
   public float func_220080_a(BlockState state, IBlockReader worldIn, BlockPos pos) {
     return 1.0F;
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

   public void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     builder.func_206894_a(new IProperty[] { (IProperty)field_176387_N, (IProperty)EAST, (IProperty)SOUTH, (IProperty)WEST, (IProperty)NORTH, (IProperty)CONDITIONAL });
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.DECO_GROUP);
   }
 }

