 package com.ruby.meshi.block;
import java.util.function.Supplier;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.FlowingFluidBlock;
 import net.minecraft.fluid.FlowingFluid;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.state.EnumProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
@HideCreateTab
 public class HotSpring
   extends FlowingFluidBlock
 {
   public static final EnumProperty<com.ruby.meshi.fluid.HotSpring.SpringColor> COLOR = com.ruby.meshi.fluid.HotSpring.COLOR;
  public HotSpring(Supplier<? extends FlowingFluid> supplier, Block.Properties p_i48368_1_) {
     super(supplier, p_i48368_1_);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)COLOR, com.ruby.meshi.fluid.HotSpring.SpringColor.DEFAULT));
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)COLOR });
   }

   public IFluidState func_204507_t(BlockState state) {
     return (IFluidState)super.func_204507_t(state).func_206870_a((IProperty)COLOR, state.func_177229_b((IProperty)COLOR));
   }
 }

