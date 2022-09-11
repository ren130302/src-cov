 package com.ruby.meshi.fluid;
import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.block.SpringSpawner;
 import com.ruby.meshi.item.HiganItems;
 import java.util.Arrays;
 import java.util.Random;
 import java.util.Set;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.FlowingFluidBlock;
 import net.minecraft.client.Minecraft;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.item.ItemEntity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.fluid.WaterFluid;
 import net.minecraft.item.DyeColor;
 import net.minecraft.item.DyeItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.Items;
 import net.minecraft.particles.IParticleData;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.state.EnumProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.tags.Tag;
 import net.minecraft.util.Direction;
 import net.minecraft.util.IStringSerializable;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.SoundCategory;
 import net.minecraft.util.SoundEvents;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.CubeCoordinateIterator;
 import net.minecraft.world.IEnviromentBlockReader;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;
 import net.minecraft.world.biome.Biome;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.common.BiomeDictionary;
 import net.minecraftforge.fluids.FluidAttributes;
public abstract class HotSpring
   extends WaterFluid
 {
   public static final EnumProperty<SpringColor> COLOR = EnumProperty.func_177709_a("color", SpringColor.class);

   HotSpring() {
     func_207183_f((IFluidState)func_207188_f().func_206870_a((IProperty)COLOR, SpringColor.DEFAULT));
   }

   protected void func_207184_a(StateContainer.Builder<Fluid, IFluidState> builder) {
     super.func_207184_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)COLOR });
   }
  public boolean isEntityInside(IFluidState state, IWorldReader reader, BlockPos pos, Entity entity, double yToTest, Tag<Fluid> tag, boolean testingHead) {
     if (!reader.func_201670_d() && 
       entity instanceof ItemEntity) {
       ItemStack stack = ((ItemEntity)entity).func_92059_d();
       if (stack.func_77973_b() instanceof DyeItem) {
         DyeColor dye = ((DyeItem)stack.func_77973_b()).func_195962_g();
         SpringColor dyeToColor = SpringColor.getColorToDye(dye);
         SpringColor stateColor = (SpringColor)state.func_177229_b((IProperty)COLOR);
         if (dyeToColor != null && dye != stateColor.dye) {
           IWorld world = reader.func_217349_x(pos).getWorldForge();
           if (world != null) {
             Set<BlockPos> fluidPos = SpringSpawner.getFluidToList(reader, pos, (Fluid)this, 100);
             if (dye == DyeColor.BLACK) {
               dyeToColor = SpringColor.DEFAULT;
             }
             for (BlockPos p : fluidPos) {
               world.func_180501_a(p, (BlockState)world.func_204610_c(p).func_206883_i().func_206870_a((IProperty)COLOR, dyeToColor), 3);
             }
                        stack.func_190918_g(1);                 } 
       } else if (stack.func_77973_b() == Items.field_151123_aH) {
         IWorld world = reader.func_217349_x(pos).getWorldForge();
         if (world != null) {
           Set<BlockPos> fluidPos = SpringSpawner.getFluidToList(reader, pos, (Fluid)this, 100);
           fluidPos.forEach(p -> world.func_180501_a(p, (BlockState)world.func_204610_c(p).func_206883_i().func_206870_a((IProperty)COLOR, SpringColor.VANILLA), 3));
           stack.func_190918_g(1);
         }      }    }       return super.isEntityInside(state, reader, pos, entity, yToTest, tag, testingHead);
   }

   protected void func_205574_a(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, IFluidState fluidStateIn) {
     super.func_205574_a(worldIn, pos, blockStateIn, direction, (IFluidState)fluidStateIn.func_206870_a((IProperty)COLOR, worldIn.func_180495_p(pos.func_177972_a(direction.func_176734_d())).func_177229_b((IProperty)COLOR)));
   }

   protected IFluidState func_205576_a(IWorldReader worldIn, BlockPos pos, BlockState blockStateIn) {
     IFluidState state = super.func_205576_a(worldIn, pos, blockStateIn);
     return (state.func_206886_c() == getFluid() && blockStateIn.func_177230_c() == SakuraBlocks.HOT_SPRING) ? (IFluidState)state.func_206870_a((IProperty)COLOR, blockStateIn.func_204520_s().func_177229_b((IProperty)COLOR)) : state;
   }

   public Fluid func_210197_e() {
     return (Fluid)MeshiFluids.FLOWING_HOT_SPING;
   }

   public Fluid func_210198_f() {
     return (Fluid)MeshiFluids.HOT_SPING;
   }

   public Item func_204524_b() {
     return HiganItems.HOT_SPRING_BUCKET;
   }

   @OnlyIn(Dist.CLIENT)
   public void func_204522_a(World worldIn, BlockPos pos, IFluidState state, Random random) {
     super.func_204522_a(worldIn, pos, state, random);
        if (worldIn.func_175623_d(pos.func_177984_a()) && random.nextFloat() < 0.05F) {
       double d0 = (pos.func_177958_n() + random.nextFloat());
       double d1 = (pos.func_177956_o() + 0.2F + state.func_206882_g() * 0.1F);
       double d2 = (pos.func_177952_p() + random.nextFloat());
       float color = 0.7F + 0.1F * random.nextFloat();
       (Minecraft.func_71410_x()).field_71452_i.func_199280_a((IParticleData)ParticleTypes.field_197601_L, d0, d1, d2, 0.0D, 0.0D, 0.0D).func_70538_b(color, color, color);
     }    Biome biome = worldIn.func_180494_b(pos);
     float bubbleRate = BiomeDictionary.getTypes(biome).contains(BiomeDictionary.Type.NETHER) ? 0.2F : 0.02F;
        if (state.func_206889_d() && random.nextFloat() < bubbleRate) {
       double d0 = (pos.func_177958_n() + random.nextFloat());
       double d1 = (pos.func_177956_o() + 0.5F * random.nextFloat());
       double d2 = (pos.func_177952_p() + random.nextFloat());
       (Minecraft.func_71410_x()).field_71452_i.func_199280_a((IParticleData)ParticleTypes.field_197612_e, d0, d1, d2, 0.0D, 0.5D, 0.0D);
       worldIn.func_184134_a(d0, d1, d2, SoundEvents.field_203253_U, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.5F, random.nextFloat() + 0.5F, false);
     }  }
 
   public BlockState func_204527_a(IFluidState state) {
     return (BlockState)((BlockState)SakuraBlocks.HOT_SPRING.func_176223_P().func_206870_a((IProperty)FlowingFluidBlock.field_176367_b, Integer.valueOf(func_207205_e(state)))).func_206870_a((IProperty)COLOR, state.func_177229_b((IProperty)COLOR));
   }

   public boolean func_207187_a(Fluid fluidIn) {
     return (fluidIn == MeshiFluids.HOT_SPING || fluidIn == MeshiFluids.FLOWING_HOT_SPING);
   }

   protected boolean func_205579_d() {
     return false;
   }

   protected FluidAttributes createAttributes() {
     return (FluidAttributes)new HotWater(FluidAttributes.Water.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"))
               .overlay(new ResourceLocation("block/water_overlay"))
         .translationKey("block.meshi.hot_spring")
         .color(-12618012), (Fluid)this);
   }
  public static class Flowing
     extends HotSpring {
     protected void func_207184_a(StateContainer.Builder<Fluid, IFluidState> builder) {
       super.func_207184_a(builder);
       builder.func_206894_a(new IProperty[] { (IProperty)field_207210_b });
     }

     public int func_207192_d(IFluidState state) {
       return ((Integer)state.func_177229_b((IProperty)field_207210_b)).intValue();
     }

     public boolean func_207193_c(IFluidState state) {
       return false;
     }
   }
  public static class Source
     extends HotSpring {
     public int func_207192_d(IFluidState state) {
       return 8;
     }

     public boolean func_207193_c(IFluidState state) {
       return true;
     }
   }
  public static class HotWater extends FluidAttributes.Water {
     protected HotWater(FluidAttributes.Builder builder, Fluid fluid) {
       super(builder, fluid);
     }

     public int getColor(IEnviromentBlockReader world, BlockPos pos) {
       int a = 0;
       int r = 0;
       int g = 0;
       int b = 0;
       int l = Math.min((Minecraft.func_71410_x()).field_71474_y.field_205217_U, 2);
       int i1 = (l * 2 + 1) * (l * 2 + 1);
            CubeCoordinateIterator cubecoordinateiterator = new CubeCoordinateIterator(pos.func_177958_n() - l, pos.func_177956_o(), pos.func_177952_p() - l, pos.func_177958_n() + l, pos.func_177956_o(), pos.func_177952_p() + l);      
       for (BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(); cubecoordinateiterator.func_218301_a(); ) {
         int j1; blockpos$mutableblockpos.func_181079_c(cubecoordinateiterator.func_218304_b(), cubecoordinateiterator.func_218302_c(), cubecoordinateiterator.func_218303_d());
         IFluidState fstate = world.func_204610_c((BlockPos)blockpos$mutableblockpos);
         if (fstate.func_206886_c() == Fluids.field_204541_a) {
           i1--;
           continue;
         }        HotSpring.SpringColor cstate = (fstate.func_206886_c() instanceof HotSpring) ? (HotSpring.SpringColor)world.func_204610_c((BlockPos)blockpos$mutableblockpos).func_177229_b((IProperty)HotSpring.COLOR) : HotSpring.SpringColor.VANILLA;
         switch (cstate) {
           case DEFAULT:
             j1 = getSpringColor(world, (BlockPos)blockpos$mutableblockpos);
             break;
           case VANILLA:
             j1 = super.getColor(world, (BlockPos)blockpos$mutableblockpos);
             break;
           default:
             j1 = cstate.color;
             break;
         }        a += j1 >> 24 & 0xFF;
         r += j1 >> 16 & 0xFF;
         g += j1 >> 8 & 0xFF;
         b += j1 & 0xFF;
       }      return (a / i1 & 0xFF) << 24 | (r / i1 & 0xFF) << 16 | (g / i1 & 0xFF) << 8 | b / i1 & 0xFF;
     }

     int getSpringColor(IEnviromentBlockReader world, BlockPos pos) {
       Biome biome = world.func_180494_b(pos);
       Set<BiomeDictionary.Type> type = BiomeDictionary.getTypes(biome);
       if (biome instanceof com.ruby.meshi.world.biome.ExpelledBaseBiome) {
         return HotSpring.SpringColor.PINK.color;
       }
       return ((Integer)Arrays.<HotSpring.SpringColor>stream(HotSpring.SpringColor.values())
         .filter(c -> Arrays.<BiomeDictionary.Type>stream(c.type).anyMatch(type::contains))
         .findFirst()
         .map(c -> Integer.valueOf(c.color))
         .orElse(Integer.valueOf(biome.func_185361_o() | 0xFF000000))).intValue();
     }

 
 
 
 
 
 
 
 
 
 
     int colorMarge(int from, int to, int margeCount) {
       int a = (from >> 24 & 0xFF) + (to >> 24 & 0xFF) / margeCount;
       int r = (from >> 16 & 0xFF) + (to >> 16 & 0xFF) / margeCount;
       int g = (from >> 8 & 0xFF) + (to >> 8 & 0xFF) / margeCount;
       int b = (from & 0xFF) + (to & 0xFF) / margeCount;
       return a / 2 << 24 | r / 2 << 16 | g / 2 << 8 | b / 2;
     }
   }
  public enum SpringColor
     implements IStringSerializable {
     DEFAULT(0, new BiomeDictionary.Type[0]),
     VANILLA(0, new BiomeDictionary.Type[0]),
     WHITE((String)DyeColor.WHITE, -3355966, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.RIVER }),
     ORANGE((String)DyeColor.ORANGE, -14948, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.MESA }),
     MAGENTA((String)DyeColor.MAGENTA, -363777, (DyeColor)new BiomeDictionary.Type[0]),
     LIGHT_BLUE((String)DyeColor.LIGHT_BLUE, -9576202, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH }),
     YELLOW((String)DyeColor.YELLOW, -3695, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY }),
     LIME((String)DyeColor.LIME, -5965430, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST }),
     PINK((String)DyeColor.PINK, -29042, (DyeColor)new BiomeDictionary.Type[0]),
     GRAY((String)DyeColor.GRAY, -12895429, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.SWAMP }),
     LIGHT_GRAY((String)DyeColor.LIGHT_GRAY, -9211021, (DyeColor)new BiomeDictionary.Type[0]),
     CYAN((String)DyeColor.CYAN, -9309744, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.OCEAN }),
     PURPLE((String)DyeColor.PURPLE, -5083137, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.END }),
     BLUE((String)DyeColor.BLUE, -8153857, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS }),
     BROWN((String)DyeColor.BROWN, -4433617, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.MOUNTAIN }),
     GREEN((String)DyeColor.GREEN, -16720799, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.JUNGLE }),
     RED((String)DyeColor.RED, -53193, (DyeColor)new BiomeDictionary.Type[] { BiomeDictionary.Type.NETHER }),
     BLACK((String)DyeColor.BLACK, -15987700, (DyeColor)new BiomeDictionary.Type[0]);

 
     SpringColor(int color, BiomeDictionary.Type... type) {
       this.color = color;
       this.type = type;
     }

     SpringColor(DyeColor vanilla, int color, BiomeDictionary.Type... type) {
       this.dye = vanilla;
     }

     public String func_176610_l() {
       return toString().toLowerCase();
     }
    public static SpringColor getColorToDye(DyeColor d) {
       for (SpringColor c : values()) {
         if (c.dye == d) {
           return c;
         }
       }      return DEFAULT;
     }
   }
 }

