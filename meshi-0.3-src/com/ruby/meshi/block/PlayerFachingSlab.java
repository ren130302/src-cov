 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.SlabBlock;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.state.DirectionProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.Direction;
public class PlayerFachingSlab extends SlabBlock {
   public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.field_208157_J;
  public PlayerFachingSlab(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)HORIZONTAL_FACING, (Comparable)Direction.NORTH));
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     return (BlockState)super.func_196258_a(context).func_206870_a((IProperty)HORIZONTAL_FACING, (Comparable)context.func_195992_f().func_176734_d());
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)HORIZONTAL_FACING });
   }
 }

