 package com.ruby.meshi.world.gen;
import com.google.common.collect.Lists;
 import com.mojang.datafixers.Dynamic;
 import java.util.List;
 import java.util.Objects;
 import java.util.Random;
 import java.util.Set;
 import java.util.function.Function;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.RotatedPillarBlock;
 import net.minecraft.state.IProperty;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.util.math.MutableBoundingBox;
 import net.minecraft.world.IWorldWriter;
 import net.minecraft.world.gen.IWorldGenerationBaseReader;
 import net.minecraft.world.gen.IWorldGenerationReader;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
public class ExtendBigTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
   private BlockState log_block;
   private BlockState leave_block;
    public ExtendBigTreeFeature setBlocks(BlockState log, BlockState leave) {
     this.log_block = log;
     this.leave_block = leave;
     return this;
   }
  public ExtendBigTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49918_1_, boolean p_i49918_2_) {
     super(p_i49918_1_, p_i49918_2_);
   }
  private void crossSection(IWorldGenerationReader p_208529_1_, BlockPos p_208529_2_, float p_208529_3_, MutableBoundingBox p_208529_4_, Set<BlockPos> p_208529_5_) {
     int i = (int)(p_208529_3_ + 0.618D);
        for (int j = -i; j <= i; j++) {
       for (int k = -i; k <= i; k++) {
         if (Math.pow(Math.abs(j) + 0.5D, 2.0D) + Math.pow(Math.abs(k) + 0.5D, 2.0D) <= (p_208529_3_ * p_208529_3_)) {
           BlockPos blockpos = p_208529_2_.func_177982_a(j, 0, k);
           if (func_214572_g((IWorldGenerationBaseReader)p_208529_1_, blockpos)) {
             func_208520_a(p_208529_5_, (IWorldWriter)p_208529_1_, blockpos, this.leave_block, p_208529_4_);
           }
         }      }    }  }
 
   private float treeShape(int p_208527_1_, int p_208527_2_) {
     if (p_208527_2_ < p_208527_1_ * 0.3F) {
       return -1.0F;
     }
     float f = p_208527_1_ / 2.0F;
     float f1 = f - p_208527_2_;
     float f2 = MathHelper.func_76129_c(f * f - f1 * f1);
     if (f1 == 0.0F) {
       f2 = f;
     } else if (Math.abs(f1) >= f) {
       return 0.0F;
     }       return f2 * 0.5F;
   }

   private float foliageShape(int y) {
     if (y >= 0 && y < 5) {
       return (y != 0 && y != 4) ? 3.0F : 2.0F;
     }
     return -1.0F;
   }

   private void foliageCluster(IWorldGenerationReader p_202393_1_, BlockPos p_202393_2_, MutableBoundingBox p_202393_3_, Set<BlockPos> p_202393_4_) {
     for (int i = 0; i < 5; i++) {
       crossSection(p_202393_1_, p_202393_2_.func_177981_b(i), foliageShape(i), p_202393_3_, p_202393_4_);
     }
   }

   private int makeLimb(Set<BlockPos> p_208523_1_, IWorldGenerationReader p_208523_2_, BlockPos p_208523_3_, BlockPos p_208523_4_, boolean p_208523_5_, MutableBoundingBox p_208523_6_) {
     if (!p_208523_5_ && Objects.equals(p_208523_3_, p_208523_4_)) {
       return -1;
     }
     BlockPos blockpos = p_208523_4_.func_177982_a(-p_208523_3_.func_177958_n(), -p_208523_3_.func_177956_o(), -p_208523_3_.func_177952_p());
     int i = getGreatestDistance(blockpos);
     float f = blockpos.func_177958_n() / i;
     float f1 = blockpos.func_177956_o() / i;
     float f2 = blockpos.func_177952_p() / i;
        for (int j = 0; j <= i; j++) {
       BlockPos blockpos1 = p_208523_3_.func_177963_a((0.5F + j * f), (0.5F + j * f1), (0.5F + j * f2));
       if (p_208523_5_) {
         func_208520_a(p_208523_1_, (IWorldWriter)p_208523_2_, blockpos1, (BlockState)this.log_block.func_206870_a((IProperty)RotatedPillarBlock.field_176298_M, (Comparable)getLoxAxis(p_208523_3_, blockpos1)), p_208523_6_);
       }
       else if (!func_214587_a((IWorldGenerationBaseReader)p_208523_2_, blockpos1)) {
         return j;
       }    }       return -1;
   }

 
   private int getGreatestDistance(BlockPos posIn) {
     int i = MathHelper.func_76130_a(posIn.func_177958_n());
     int j = MathHelper.func_76130_a(posIn.func_177956_o());
     int k = MathHelper.func_76130_a(posIn.func_177952_p());
     if (k > i && k > j) {
       return k;
     }
     return (j > i) ? j : i;
   }

   private Direction.Axis getLoxAxis(BlockPos p_197170_1_, BlockPos p_197170_2_) {
     Direction.Axis direction$axis = Direction.Axis.Y;
     int i = Math.abs(p_197170_2_.func_177958_n() - p_197170_1_.func_177958_n());
     int j = Math.abs(p_197170_2_.func_177952_p() - p_197170_1_.func_177952_p());
     int k = Math.max(i, j);
     if (k > 0) {
       if (i == k) {
         direction$axis = Direction.Axis.X;
       } else if (j == k) {
         direction$axis = Direction.Axis.Z;
       }    }    
     return direction$axis;
   }
  private void makeFoliage(IWorldGenerationReader p_208525_1_, int p_208525_2_, BlockPos p_208525_3_, List<FoliageCoordinates> p_208525_4_, MutableBoundingBox p_208525_5_, Set<BlockPos> p_208525_6_) {
     for (FoliageCoordinates BigTreeBase$foliagecoordinates : p_208525_4_) {
       if (trimBranches(p_208525_2_, BigTreeBase$foliagecoordinates.getBranchBase() - p_208525_3_.func_177956_o())) {
         foliageCluster(p_208525_1_, BigTreeBase$foliagecoordinates, p_208525_5_, p_208525_6_);
       }
     }  }
 
   private boolean trimBranches(int p_208522_1_, int p_208522_2_) {
     return (p_208522_2_ >= p_208522_1_ * 0.2D);
   }
  private void makeTrunk(Set<BlockPos> p_208526_1_, IWorldGenerationReader p_208526_2_, BlockPos p_208526_3_, int p_208526_4_, MutableBoundingBox p_208526_5_) {
     makeLimb(p_208526_1_, p_208526_2_, p_208526_3_, p_208526_3_.func_177981_b(p_208526_4_), true, p_208526_5_);
   }
  private void makeBranches(Set<BlockPos> p_208524_1_, IWorldGenerationReader p_208524_2_, int p_208524_3_, BlockPos p_208524_4_, List<FoliageCoordinates> p_208524_5_, MutableBoundingBox p_208524_6_) {
     for (FoliageCoordinates BigTreeBase$foliagecoordinates : p_208524_5_) {
       int i = BigTreeBase$foliagecoordinates.getBranchBase();
       BlockPos blockpos = new BlockPos(p_208524_4_.func_177958_n(), i, p_208524_4_.func_177952_p());
       if (!blockpos.equals(BigTreeBase$foliagecoordinates) && trimBranches(p_208524_3_, i - p_208524_4_.func_177956_o())) {
         makeLimb(p_208524_1_, p_208524_2_, blockpos, BigTreeBase$foliagecoordinates, true, p_208524_6_);
       }
     }  }
 
   public boolean func_208519_a(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
     Random random = new Random(rand.nextLong());
     int i = checkLocation(changedBlocks, worldIn, position, 5 + random.nextInt(12), p_208519_5_);
     if (i == -1) {
       return false;
     }
     setDirtAt(worldIn, position.func_177977_b(), position);
     int j = (int)(i * 0.618D);
     if (j >= i) {
       j = i - 1;
     }
        double d0 = 1.0D;
     int k = (int)(1.382D + Math.pow(1.0D * i / 13.0D, 2.0D));
     if (k < 1) {
       k = 1;
     }
        int l = position.func_177956_o() + j;
     int i1 = i - 5;
     List<FoliageCoordinates> list = Lists.newArrayList();
     list.add(new FoliageCoordinates(position.func_177981_b(i1), l));
        for (; i1 >= 0; i1--) {
       float f = treeShape(i, i1);
       if (f >= 0.0F) {
         for (int j1 = 0; j1 < k; j1++) {
           double d1 = 1.0D;
           double d2 = 1.0D * f * (random.nextFloat() + 0.328D);
           double d3 = (random.nextFloat() * 2.0F) * Math.PI;
           double d4 = d2 * Math.sin(d3) + 0.5D;
           double d5 = d2 * Math.cos(d3) + 0.5D;
           BlockPos blockpos = position.func_177963_a(d4, (i1 - 1), d5);
           BlockPos blockpos1 = blockpos.func_177981_b(5);
           if (makeLimb(changedBlocks, worldIn, blockpos, blockpos1, false, p_208519_5_) == -1) {
             int k1 = position.func_177958_n() - blockpos.func_177958_n();
             int l1 = position.func_177952_p() - blockpos.func_177952_p();
             double d6 = blockpos.func_177956_o() - Math.sqrt((k1 * k1 + l1 * l1)) * 0.381D;
             int i2 = (d6 > l) ? l : (int)d6;
             BlockPos blockpos2 = new BlockPos(position.func_177958_n(), i2, position.func_177952_p());
             if (makeLimb(changedBlocks, worldIn, blockpos2, blockpos, false, p_208519_5_) == -1) {
               list.add(new FoliageCoordinates(blockpos, blockpos2.func_177956_o()));
             }
           }        }      }       
     makeFoliage(worldIn, i, position, list, p_208519_5_, changedBlocks);
     makeTrunk(changedBlocks, worldIn, position, j, p_208519_5_);
     makeBranches(changedBlocks, worldIn, i, position, list, p_208519_5_);
     return true;
   }

   private int checkLocation(Set<BlockPos> p_208528_1_, IWorldGenerationReader p_208528_2_, BlockPos p_208528_3_, int p_208528_4_, MutableBoundingBox p_208528_5_) {
     if (!isSoilOrFarm((IWorldGenerationBaseReader)p_208528_2_, p_208528_3_.func_177977_b(), getSapling())) {
       return -1;
     }
     int i = makeLimb(p_208528_1_, p_208528_2_, p_208528_3_, p_208528_3_.func_177981_b(p_208528_4_ - 1), false, p_208528_5_);
     if (i == -1) {
       return p_208528_4_;
     }
     return (i < 6) ? -1 : i;
   }
  static class FoliageCoordinates
     extends BlockPos
   {
     private final int branchBase;
        public FoliageCoordinates(BlockPos pos, int p_i45635_2_) {
       super(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
       this.branchBase = p_i45635_2_;
     }
    public int getBranchBase() {
       return this.branchBase;
     }
   }
 }

