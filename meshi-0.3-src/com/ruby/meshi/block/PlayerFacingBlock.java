 package com.ruby.meshi.block;
import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.HorizontalBlock;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.util.Direction;
public class PlayerFacingBlock extends HorizontalBlock {
   public PlayerFacingBlock(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((IProperty)field_185512_D, (Comparable)Direction.NORTH));
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     return (BlockState)func_176223_P().func_206870_a((IProperty)field_185512_D, (Comparable)context.func_195992_f().func_176734_d());
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     builder.func_206894_a(new IProperty[] { (IProperty)field_185512_D });
   }
 }

