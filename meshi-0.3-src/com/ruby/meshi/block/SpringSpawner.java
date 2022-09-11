 package com.ruby.meshi.block;
import com.ruby.meshi.fluid.MeshiFluids;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.LinkedHashSet;
 import java.util.List;
 import java.util.Random;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.Minecraft;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.item.Items;
 import net.minecraft.particles.IParticleData;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.IntegerProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
public class SpringSpawner extends Block {
   public static final IntegerProperty LEVEL = BlockStateProperties.field_208130_ae;
  protected SpringSpawner(Block.Properties builder) {
     super(builder);
     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((IProperty)LEVEL, Integer.valueOf(0)));
   }

   public boolean func_149653_t(BlockState state) {
     return (((Integer)state.func_177229_b((IProperty)LEVEL)).intValue() > 0);
   }

   public void func_196267_b(BlockState state, World worldIn, BlockPos pos, Random random) {
     super.func_196267_b(state, worldIn, pos, random);
     int lvl = ((Integer)state.func_177229_b((IProperty)LEVEL)).intValue();
     if (lvl > 0) {
       if (worldIn.func_175623_d(pos.func_177984_a())) {
         worldIn.func_175656_a(pos.func_177984_a(), SakuraBlocks.HOT_SPRING.func_176223_P());
       } else {
         Set<BlockPos> posSet = getFluidToList((IWorldReader)worldIn, pos.func_177984_a(), (Fluid)MeshiFluids.HOT_SPING, 100 * lvl);
         posSet.removeIf(p -> worldIn.func_204610_c(p).func_206889_d());
                int c = 0;
         for (BlockPos p : posSet) {
           if (worldIn.func_204610_c(p).func_206882_g() == 7 && worldIn.func_204610_c(p.func_177977_b()) == Fluids.field_204541_a.func_207188_f()) {
             worldIn.func_175656_a(p, SakuraBlocks.HOT_SPRING.func_176223_P());
             if (++c > lvl * 2) {
               break;
             }
           }        }        if (c == 0) {
           worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)LEVEL, Integer.valueOf(0)));
         }
       }      worldIn.func_205220_G_().func_205360_a(pos, this, 100);    }  }
 
   public static Set<BlockPos> getFluidToList(IWorldReader world, BlockPos pos, Fluid fluid, int limit) {
     List<BlockPos> nextTargets = new ArrayList<>();
     nextTargets.add(pos);
     Set<BlockPos> fouds = new LinkedHashSet<>();

     
     do {
       nextTargets = (List<BlockPos>)nextTargets.stream().flatMap(target -> Arrays.<Direction>stream(Direction.values()).filter(()).map(target::func_177972_a)).filter(fixedPos -> world.func_204610_c(fixedPos).func_206886_c().func_207187_a(fluid)).limit((limit - fouds.size())).filter(fouds::add).collect(Collectors.toList());
     } while (fouds.size() <= limit && !nextTargets.isEmpty());
     return fouds;
   }

   @OnlyIn(Dist.CLIENT)
   public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
     super.func_180655_c(stateIn, worldIn, pos, rand);
     int lvl = ((Integer)stateIn.func_177229_b((IProperty)LEVEL)).intValue();
     if (lvl > 0) {
       for (int i = 0; i < 5 * lvl; i++) {
         double d0 = (pos.func_177958_n() + rand.nextFloat());
         double d1 = ((pos.func_177956_o() + 1) + rand.nextFloat());
         double d2 = (pos.func_177952_p() + rand.nextFloat());
         (Minecraft.func_71410_x()).field_71452_i.func_199280_a((IParticleData)ParticleTypes.field_197598_I, d0, d1, d2, 0.0D, lvl * 0.1D, 0.0D);
       }    }
   }

   public int func_149738_a(IWorldReader worldIn) {
     return super.func_149738_a(worldIn);
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (!worldIn.field_72995_K) {
       if (player.func_184586_b(handIn).func_77973_b() == Items.field_151133_ar) {
         Set<BlockPos> posSet = getFluidToList((IWorldReader)worldIn, pos.func_177984_a(), (Fluid)MeshiFluids.HOT_SPING, 300);
         posSet.removeIf(p -> !worldIn.func_204610_c(p).func_206889_d());
         for (BlockPos p : posSet) {
           worldIn.func_175656_a(p, (BlockState)worldIn.func_180495_p(p).func_206870_a((IProperty)HotSpring.field_176367_b, Integer.valueOf(7)));
         }
         worldIn.func_175656_a(pos, (BlockState)state.func_206870_a((IProperty)LEVEL, Integer.valueOf(0)));
         return true;
       }      state = (BlockState)state.func_177231_a((IProperty)LEVEL);
       int lvl = ((Integer)state.func_177229_b((IProperty)LEVEL)).intValue();
       worldIn.func_175656_a(pos, state);
       if (lvl > 0) {
         worldIn.func_205220_G_().func_205360_a(pos, this, 100);
       }
     }    return true;
   }

   public void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)LEVEL });
   }

   public boolean func_220081_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
     return false;
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }
 }

