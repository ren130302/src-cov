 package com.ruby.meshi.block;
import com.google.common.collect.Streams;
 import java.util.Random;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.FarmlandBlock;
 import net.minecraft.block.IWaterLoggable;
 import net.minecraft.entity.Entity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
 import net.minecraftforge.common.IPlantable;
 import net.minecraftforge.common.PlantType;
public class PaddyField
   extends FarmlandBlock implements IWaterLoggable {
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.field_208198_y;
  protected PaddyField(Block.Properties builder) {
     super(builder);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)WATERLOGGED, Boolean.valueOf(false)));
   }

   public void func_196267_b(BlockState state, World worldIn, BlockPos pos, Random random) {
     super.func_196267_b(state, worldIn, pos, random);
       int moisture = ((Integer)state.func_177229_b((IProperty)field_176531_a)).intValue();
     boolean waterlogged = ((Boolean)state.func_177229_b((IProperty)WATERLOGGED)).booleanValue();
     BlockPos upperPos = pos.func_177984_a();
     BlockState upperState = worldIn.func_180495_p(upperPos);
     if (moisture == 7) {
       if (!waterlogged) {
         if (upperState.func_177230_c() instanceof IPlantable) {
           PlantType type = ((IPlantable)upperState.func_177230_c()).getPlantType((IBlockReader)worldIn, pos.func_177972_a(Direction.UP));
           if (type == PlantType.Crop && upperState.func_177230_c() != SakuraBlocks.RICE_PLANT &&            random.nextFloat() < 0.75F) {
             upperState.func_196940_a(worldIn, upperPos, random);
           }
         }
            }      else if (upperState.func_177230_c() == SakuraBlocks.RICE_PLANT && 
         random.nextFloat() < 0.75F) {
         upperState.func_196940_a(worldIn, upperPos, random);
       }    }
   }

 
   private void randomDestory(World worldIn, BlockPos pos, Random rand) {
     if (rand.nextFloat() < 0.01F) {
       worldIn.func_175655_b(pos, false);
     }
   }

   public IFluidState func_204507_t(BlockState state) {
     return ((Boolean)state.func_177229_b((IProperty)WATERLOGGED)).booleanValue() ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
   }

   public void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)WATERLOGGED });
   }

   public void func_220082_b(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
     super.func_220082_b(state, worldIn, pos, oldState, isMoving);
     if (oldState.func_177230_c() != this) {
       worldIn.func_175656_a(pos, setHorizontalWater(state, worldIn, pos));
     }
   }

   public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
     if (((Boolean)stateIn.func_177229_b((IProperty)WATERLOGGED)).booleanValue()) {
       worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a((IWorldReader)worldIn));
     }
        return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     return setHorizontalWater(super.func_196258_a(context), context.func_195991_k(), context.func_195995_a());
   }
  public BlockState setHorizontalWater(BlockState state, World worldIn, BlockPos pos) {
     Fluid fluid = worldIn.func_204610_c(pos).func_206886_c();
     if (fluid == Fluids.field_204541_a || fluid == Fluids.field_207212_b)
     {
      
       fluid = Streams.stream(Direction.Plane.HORIZONTAL.iterator()).filter(d -> (worldIn.func_180495_p(pos.func_177972_a(d)).func_177230_c() == this)).map(d -> worldIn.func_204610_c(pos.func_177972_a(d)).func_206886_c()).filter(Fluids.field_204546_a::equals).findAny().orElse(Fluids.field_204541_a);
     }
     return (BlockState)((BlockState)state.func_206870_a((IProperty)WATERLOGGED, Boolean.valueOf((fluid == Fluids.field_204546_a)))).func_206870_a((IProperty)field_176531_a, Integer.valueOf((fluid == Fluids.field_204546_a) ? 7 : 0));
   }

   public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
     if (!((Boolean)worldIn.func_180495_p(pos).func_177229_b((IProperty)WATERLOGGED)).booleanValue()) {
       super.func_180658_a(worldIn, pos, entityIn, fallDistance);
     }
   }

   public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
     super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
     if (!worldIn.field_72995_K) {
       BlockState fromState = worldIn.func_180495_p(fromPos);
       if (fromState.func_177230_c() == this && isHorizontalPos(pos, fromPos) &&        state.func_177229_b((IProperty)WATERLOGGED) != fromState.func_177229_b((IProperty)WATERLOGGED)) {
         worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)WATERLOGGED, fromState.func_177229_b((IProperty)WATERLOGGED)));
       }
     }  }
 
   private boolean isHorizontalPos(BlockPos pos, BlockPos fromPos) {
     if (pos.func_177956_o() == fromPos.func_177956_o()) {
       return Streams.stream(Direction.Plane.HORIZONTAL.iterator())
         .anyMatch(d -> pos.func_177972_a(d).equals(fromPos));
     }
     return false;
   }

   public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
     PlantType type = plantable.getPlantType(world, pos.func_177972_a(facing));
     return (type == PlantType.Crop);
   }
 }

