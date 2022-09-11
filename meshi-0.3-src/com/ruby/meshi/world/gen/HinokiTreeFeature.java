 package com.ruby.meshi.world.gen;
import com.mojang.datafixers.Dynamic;
 import com.ruby.meshi.block.SakuraBlocks;
 import java.util.Random;
 import java.util.Set;
 import java.util.function.Consumer;
 import java.util.function.Function;
 import net.minecraft.block.BlockState;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MutableBoundingBox;
 import net.minecraft.util.math.Vec3i;
 import net.minecraft.world.IWorldWriter;
 import net.minecraft.world.gen.IWorldGenerationBaseReader;
 import net.minecraft.world.gen.IWorldGenerationReader;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
public class HinokiTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
   private final BlockState log = SakuraBlocks.HINOKI_LOG.func_176223_P();
   private final BlockState leave = SakuraBlocks.HINOKI_LEAVE.func_176223_P();
  public HinokiTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_, boolean doBlockNofityOnPlace) {
     super(p_i49920_1_, doBlockNofityOnPlace);
   }

   protected boolean func_208519_a(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
     int log_minheight = 6;
     int log_height = log_minheight + rand.nextInt(3);
     int leave_base = 2;
     int leave_top = 5;
     if (isSoil((IWorldGenerationBaseReader)worldIn, position.func_177977_b(), getSapling()) && position.func_177956_o() < worldIn.getMaxHeight() - log_height - 1) {
            for (int leave_y = leave_base; leave_y <= log_height + leave_top; leave_y++) {
         int posY = leave_y - leave_base;
         int size = 3;
         if (posY == 0) {
           size = 1;
         } else if (posY == 1) {
           size = 2;
         } else if (posY >= log_height + leave_top - 3) {
           size = 0;
         } else if (posY >= log_height + leave_top - 5) {
           size = 1;
         } else if (posY >= log_height + leave_top - 7) {
           size = 2;
         }        horizonLeaveBox(worldIn, position.func_177981_b(leave_y), size, p -> func_202278_a((IWorldWriter)worldIn, p, this.leave));           
       for (int i = 0; i < log_height; i++) {
         if (func_214572_g((IWorldGenerationBaseReader)worldIn, position.func_177981_b(i))) {
           func_208520_a(changedBlocks, (IWorldWriter)worldIn, position.func_177981_b(i), this.log, p_208519_5_);
         }
       }      return true;    } 
     return false;
   }
  private void horizonLeaveBox(IWorldGenerationReader worldIn, BlockPos pos, int size, Consumer<BlockPos> cons) {
     if (size == 0) {
       cons.accept(pos);
     } else {
       int limit = 2;
       BlockPos.func_218281_b(pos.func_177982_a(-size, 0, -size), pos.func_177982_a(size, 0, size))
         .filter(p -> (func_214572_g((IWorldGenerationBaseReader)worldIn, p) && Math.abs(pos.func_218139_n((Vec3i)p)) <= size))
         .filter(p -> (Math.max(Math.abs(pos.func_177958_n() - p.func_177958_n()), Math.abs(pos.func_177952_p() - p.func_177952_p())) <= limit))
         .forEach(cons);
     }  }
 }

