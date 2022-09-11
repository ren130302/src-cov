 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
 import java.util.List;
 import java.util.Optional;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.CropsBlock;
 import net.minecraft.block.DirectionalBlock;
 import net.minecraft.block.IWaterLoggable;
 import net.minecraft.block.RedstoneTorchBlock;
 import net.minecraft.block.material.PushReaction;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.BucketItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemUseContext;
 import net.minecraft.item.Items;
 import net.minecraft.pathfinding.PathType;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.Rotation;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.RayTraceResult;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
 import net.minecraft.world.storage.loot.LootContext;
 import net.minecraft.world.storage.loot.LootParameters;

 public class Miniature
   extends DirectionalBlock
 {
   public static final VoxelShape UP_AABB = Block.func_208617_a(0.0D, 15.9D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape DOWN_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);
   public static final VoxelShape EAST_AABB = Block.func_208617_a(15.9D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 0.1D);
   public static final VoxelShape SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 15.9D, 16.0D, 16.0D, 16.0D);
   public static final VoxelShape WEST_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 0.1D, 16.0D, 16.0D);
   public VoxelShape[][][] shapes = makeShapes(getSize());
  public static final BooleanProperty ENABLED = BlockStateProperties.field_208180_g;
 
   public Miniature(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)field_176387_N, (Comparable)Direction.NORTH)).func_206870_a((IProperty)ENABLED, Boolean.valueOf(false)));
   }
  int getSize() {
     return 8;
   }
  private VoxelShape[][][] makeShapes(int size) {
     VoxelShape[][][] shapes = new VoxelShape[size][size][size];
        float shapeOffset = 16.0F * 1.0F / size;
     for (int i = 0; i < size; i++) {
       double minX = (i * shapeOffset);
       for (int j = 0; j < size; j++) {
         double minY = (j * shapeOffset);
         for (int k = 0; k < size; k++) {
           double minZ = (k * shapeOffset);
           shapes[i][j][k] = Block.func_208617_a(minX, minY, minZ, minX + shapeOffset, minY + shapeOffset, minZ + shapeOffset);
         }      }    }    return shapes;
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (!worldIn.field_72995_K) {
       TileEntity te = worldIn.func_175625_s(pos);
       boolean isUpdated = false;
       if (te != null && te instanceof MiniatureTileEntity) {
         MiniatureTileEntity mini = (MiniatureTileEntity)te;
         ItemStack stack = player.func_184586_b(handIn);
         int size = (getSize() == mini.getSize()) ? getSize() : mini.getSize();
                BlockPos innerTargetPos = calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b().func_176734_d(), size);
         BlockPos innerHitPos = calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b(), size);
               if (!stack.func_190926_b()) {          
           ItemStack copyStack = stack.func_77946_l();
           BlockState innerTargeState = mini.getInnerState(innerTargetPos.func_177958_n(), innerTargetPos.func_177956_o(), innerTargetPos.func_177952_p());
                    if (!onItemUse(worldIn, pos, mini, mini.getInnerWorld(), innerTargetPos, innerTargeState, stack, player, handIn, hit)) {            
             if (stack.func_77973_b() instanceof BlockItem) {
               DummyItemUseContext context = new DummyItemUseContext(mini.getInnerWorld(), player, handIn, copyStack, hit);
               if (innerTargeState.func_204520_s().func_206888_e()) {
                 context.innerPos = innerHitPos;
               } else {
                 context.innerPos = innerTargetPos;
               }              if (((BlockItem)copyStack.func_77973_b()).func_195942_a(context) == ActionResultType.SUCCESS) {
                 
                 if (!((Boolean)state.func_177229_b((IProperty)ENABLED)).booleanValue()) {
                   worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)ENABLED, Boolean.valueOf(true)));
                 }
                 isUpdated = true;
               }            } else if (stack.func_77973_b() instanceof BucketItem) {
               Fluid fluid = ((BucketItem)stack.func_77973_b()).getFluid();
               if (innerTargeState.func_177230_c() instanceof IWaterLoggable) {
                 IWaterLoggable container = (IWaterLoggable)innerTargeState.func_177230_c();
                 if (!fluid.func_207188_f().func_206888_e()) {
                   if (container.func_204510_a((IBlockReader)mini.getInnerWorld(), innerTargetPos, innerTargeState, fluid)) {
                     isUpdated = container.func_204509_a((IWorld)mini.getInnerWorld(), innerTargetPos, innerTargeState, fluid.func_207188_f());
                   } else {
                     BlockState fluidState = ((BucketItem)stack.func_77973_b()).getFluid().func_207188_f().func_206883_i();
                     if (!fluidState.hasTileEntity()) {
                       isUpdated = mini.setInnerState(innerHitPos, fluidState);
                     }
                   }                } else {
                   isUpdated = (container.func_204508_a((IWorld)mini.getInnerWorld(), innerTargetPos, innerTargeState) != Fluids.field_204541_a);
                 }
                            } else if (!fluid.func_207188_f().func_206888_e()) {
                 BlockState fluidState = ((BucketItem)stack.func_77973_b()).getFluid().func_207188_f().func_206883_i();
                 if (!fluidState.hasTileEntity()) {
                   isUpdated = mini.setInnerState(innerHitPos, fluidState);
                 }
               } else {
                 isUpdated = mini.setInnerState(innerTargetPos, MiniatureTileEntity.EMPTY);
               }            }          } else {                        isUpdated = true          } 
         } else {
           
           BlockState innerState = mini.getInnerState(innerTargetPos.func_177958_n(), innerTargetPos.func_177956_o(), innerTargetPos.func_177952_p());
           if (innerState != MiniatureTileEntity.EMPTY) {
            
             innerState.func_177230_c().func_176208_a(mini.getInnerWorld(), innerTargetPos, innerState, player);
             isUpdated = mini.setInnerState(innerTargetPos, MiniatureTileEntity.EMPTY);
                        validCheck(mini, innerTargetPos, player);
             worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)ENABLED, Boolean.valueOf(!mini.isInnerEmpty())));
           }        }                if (!isUpdated && 
           isOuterPos(mini, innerHitPos)) {
           BlockPos outerPos = pos.func_177972_a(hit.func_216354_b());
           BlockState outerState = worldIn.func_180495_p(outerPos);
           boolean isSuccess = false;
                    if (outerState.isAir((IBlockReader)worldIn, outerPos)) {
             Optional<ItemStack> searchItem = player.field_71071_by.field_70462_a.stream().filter(s -> s.func_77969_a(new ItemStack((IItemProvider)this))).findFirst();
             if (searchItem.isPresent() || player.func_184812_l_()) {
               outerState = (BlockState)((BlockState)func_176223_P().func_206870_a((IProperty)field_176387_N, (Comparable)hit.func_216354_b().func_176734_d())).func_206870_a((IProperty)ENABLED, Boolean.valueOf(true));
               isSuccess = worldIn.func_175656_a(outerPos, outerState);
               if (isSuccess) {
                 if (!player.func_184812_l_()) {
                   ((ItemStack)searchItem.get()).func_190918_g(1);
                 }
                 ((MiniatureTileEntity)worldIn.func_175625_s(outerPos)).setSize(mini.getSize());
                 ((MiniatureTileEntity)worldIn.func_175625_s(outerPos)).setNoTexResize(mini.isNoTexResize());
               }            }          }                   if (isSuccess && outerState.func_177230_c() instanceof Miniature) {
             return outerState.func_215687_a(worldIn, player, handIn, new BlockRayTraceResult(hit.func_216347_e(), hit.func_216354_b(), outerPos, hit.func_216353_d()));
           }
         }        
         if (isUpdated) {
           BlockState updateState = worldIn.func_180495_p(pos);
           worldIn.func_184138_a(pos, updateState, updateState, 2);
           worldIn.func_195592_c(pos, (Block)this);
         }      }    }    
     return true;
   }

 
   private boolean isOuterPos(MiniatureTileEntity mini, BlockPos innerHitPos) {
     return !mini.isInInnerRange(innerHitPos.func_177958_n(), innerHitPos.func_177956_o(), innerHitPos.func_177952_p());
   }

 
   @Deprecated
   ItemStack getReturnItem(World outerWorld, BlockPos outerPos, BlockState innerState) {
     ItemStack drop;
     if (innerState.func_177230_c() instanceof CropsBlock) {
       drop = ((CropsBlock)innerState.func_177230_c()).func_185473_a((IBlockReader)outerWorld, outerPos, innerState);
     } else {
       drop = new ItemStack((IItemProvider)innerState.func_177230_c());
     }    return drop;
   }

 
 
 
   boolean onItemUse(World outerWorld, BlockPos outerPos, MiniatureTileEntity mini, World innerWorld, BlockPos innerPos, BlockState innerState, ItemStack stack, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (Items.field_151137_ax == stack.func_77973_b())
     { if (innerState.func_196959_b((IProperty)RedstoneTorchBlock.field_196528_a)) {
         boolean isPower = ((Boolean)innerState.func_177229_b((IProperty)RedstoneTorchBlock.field_196528_a)).booleanValue();
         BlockState powerState = (BlockState)innerState.func_206870_a((IProperty)RedstoneTorchBlock.field_196528_a, Boolean.valueOf(!isPower));
         if (!powerState.hasTileEntity()) {
           mini.setInnerState(innerPos, powerState);
           return true;
         }      }      if (innerState.func_196959_b((IProperty)BlockStateProperties.field_208193_t)) {
         boolean isPower = ((Boolean)innerState.func_177229_b((IProperty)BlockStateProperties.field_208193_t)).booleanValue();
         BlockState powerState = (BlockState)innerState.func_206870_a((IProperty)BlockStateProperties.field_208193_t, Boolean.valueOf(!isPower));
         if (!powerState.hasTileEntity()) {
           mini.setInnerState(innerPos, powerState);
           return true;
         }      }  }
     else { if (stack.func_77973_b() == Item.func_150898_a((Block)this)) {
         if (!mini.isInnerEmpty()) {
           ItemStack copy = player.func_184812_l_() ? new ItemStack((IItemProvider)this) : stack.func_77979_a(1);
           mini.writeData(copy.func_190925_c("BlockEntityTag"));
           Block.func_180635_a(outerWorld, outerPos, copy);
         }        return true;
       }  if (Items.field_151055_y == stack.func_77973_b()) {
         mini.func_189667_a(getRotation(player.func_174811_aO(), hit));
         return true;
       }  if ((stack.func_77973_b() instanceof net.minecraft.item.HoeItem || stack.func_77973_b() instanceof net.minecraft.item.ShovelItem || stack.func_77973_b() instanceof net.minecraft.item.BoneMealItem || stack.func_77973_b() instanceof net.minecraft.item.FlintAndSteelItem) &&        !outerWorld.field_72995_K &&        stack.func_196084_a((ItemUseContext)(new DummyItemUseContext(innerWorld, player, handIn, stack.func_77946_l(), hit)).setPos(innerPos)) == ActionResultType.SUCCESS) {
         return true;
       } }
       return false;
   }

 
 
 
   Rotation getRotation(Direction direction, BlockRayTraceResult hit) {
     return Rotation.CLOCKWISE_90;
   }
  void validCheck(MiniatureTileEntity mini, BlockPos pos, PlayerEntity player) {
     for (Direction dir : Direction.values()) {
       BlockPos offsetPos = pos.func_177971_a(dir.func_176730_m());
       BlockState s = mini.getInnerState(offsetPos.func_177958_n(), offsetPos.func_177956_o(), offsetPos.func_177952_p());
       if (s != MiniatureTileEntity.EMPTY && !s.func_196955_c((IWorldReader)mini.getInnerWorld(), offsetPos)) {
         s.func_177230_c().func_176208_a(mini.getInnerWorld(), offsetPos, s, player);
         mini.setInnerState(offsetPos, MiniatureTileEntity.EMPTY);
         validCheck(mini, offsetPos, player);
       }    }  }
 
   @Deprecated
   public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
     List<ItemStack> list = super.func_220076_a(state, builder);
     ItemStack stack = list.stream().findFirst().orElse(ItemStack.field_190927_a);
     if (stack != ItemStack.field_190927_a) {
       TileEntity te = (TileEntity)builder.func_216019_b(LootParameters.field_216288_h);
       if (te != null && te instanceof MiniatureTileEntity &&        !((MiniatureTileEntity)te).isInnerEmpty()) {
         ((MiniatureTileEntity)te).writeData(stack.func_190925_c("BlockEntityTag"));
       }
     }       return list;
   }
  BlockPos calcHitPos(BlockPos pos, Vec3d hitVec, Direction face, int size) {
     Vec3d offsetVec = getOffsetVec3d(hitVec.func_178786_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), face, size);
     return new BlockPos(offsetVec.func_82615_a() / (1.0F / size), offsetVec.func_82617_b() / (1.0F / size), offsetVec.func_82616_c() / (1.0F / size));
   }
  Vec3d getOffsetVec3d(Vec3d vec, Direction face, int size) {
     return vec.func_72441_c((face.func_82601_c() * 1.0F / size * 0.5F), (face.func_96559_d() * 1.0F / size * 0.5F), (face.func_82599_e() * 1.0F / size * 0.5F));
   }

   public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
     super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
     TileEntity te = worldIn.func_175625_s(pos);
     if (te != null && te instanceof MiniatureTileEntity) {
       worldIn.func_184138_a(pos, state, state, 2);
     }
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     BlockState updateState = super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
     TileEntity te = worldIn.func_175625_s(currentPos);
     if (te != null && te instanceof MiniatureTileEntity &&      worldIn.func_201670_d()) {
       ((MiniatureTileEntity)te).removeCache();
     }
        return updateState;
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     VoxelShape shape = VoxelShapes.func_197880_a();
     if (!((Boolean)state.func_177229_b((IProperty)ENABLED)).booleanValue()) {
       shape = getFaceShape((Direction)state.func_177229_b((IProperty)field_176387_N));
     } else {
       for (Direction dir : Direction.values()) {
         BlockState offsetState = worldIn.func_180495_p(pos.func_177972_a(dir));
         if (offsetState.func_177230_c() != this &&          offsetState.func_196954_c(worldIn, pos.func_177972_a(dir)) == VoxelShapes.func_197868_b()) {
           shape = VoxelShapes.func_197872_a(shape, getFaceShape(dir));
         }
       }      
       TileEntity te = worldIn.func_175625_s(pos);
       if (te != null && te instanceof MiniatureTileEntity) {
         MiniatureTileEntity mini = (MiniatureTileEntity)te;
         if (mini.shapeCache != null) {
           return mini.shapeCache;
         }
         int size = (getSize() == mini.getSize()) ? getSize() : mini.getSize();
         VoxelShape[][][] s = (getSize() == size) ? this.shapes : makeShapes(size);
         for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
             for (int k = 0; k < size; k++) {
               BlockState innerState = mini.getInnerState(i, j, k);
               if (innerState != MiniatureTileEntity.EMPTY) {
                 shape = VoxelShapes.func_197872_a(shape, s[i][j][k]);
               }
             }          }        }        mini.shapeCache = shape;         } 
     return shape;
   }

   VoxelShape getFaceShape(Direction dir) {
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
     }    return VoxelShapes.func_197880_a();
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     BlockState blockstate = func_176223_P();
     boolean flg = (context.func_195996_i().func_179543_a("BlockEntityTag") != null && context.func_195996_i().func_179543_a("BlockEntityTag").func_74764_b("state"));
     return (BlockState)((BlockState)blockstate.func_206870_a((IProperty)field_176387_N, (Comparable)context.func_196000_l().func_176734_d())).func_206870_a((IProperty)ENABLED, Boolean.valueOf(flg));
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT;
   }

   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return new MiniatureTileEntity();
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return ((Boolean)state.func_177229_b((IProperty)ENABLED)).booleanValue() ? BlockRenderType.INVISIBLE : super.func_149645_b(state);
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)ENABLED, (IProperty)field_176387_N });
   }

   public boolean func_196266_a(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
     return false;
   }

   public PushReaction func_149656_h(BlockState state) {
     return PushReaction.BLOCK;
   }

   public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader worldIn, BlockPos pos, PlayerEntity player) {
     TileEntity te = worldIn.func_175625_s(pos);
     if (te != null && te instanceof MiniatureTileEntity &&      target instanceof BlockRayTraceResult) {
       BlockRayTraceResult hit = (BlockRayTraceResult)target;
       int size = (getSize() == ((MiniatureTileEntity)te).getSize()) ? getSize() : ((MiniatureTileEntity)te).getSize();
       BlockPos innerTargetPos = calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b().func_176734_d(), size);
       return new ItemStack((IItemProvider)((MiniatureTileEntity)te).getInnerState(innerTargetPos).func_177230_c());
     }       return getBlock().func_185473_a(worldIn, pos, state);
   }
  class DummyItemUseContext
     extends BlockItemUseContext {
     BlockPos innerPos;
        public DummyItemUseContext(World world, PlayerEntity player, Hand handIn, ItemStack stack, BlockRayTraceResult rayTraceResultIn) {
       super(world, player, handIn, stack, rayTraceResultIn);
     }
    public DummyItemUseContext setPos(BlockPos pos) {
       this.innerPos = pos;
       return this;
     }

     public BlockPos func_195995_a() {
       return this.innerPos;
     }
   }
 }

