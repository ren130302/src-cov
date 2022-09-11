 package com.ruby.meshi.block;
import com.ruby.meshi.client.paticle.PetalParticle;
 import com.ruby.meshi.client.paticle.SakuraParticleManager;
 import com.ruby.meshi.client.paticle.WindManager;
 import com.ruby.meshi.init.SakuraConfig;
 import java.util.Arrays;
 import java.util.Random;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.LeavesBlock;
 import net.minecraft.item.DyeColor;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
public class SakuraLeave
   extends LeavesBlock
   implements ExtraParticle {
   private SakuraType type;
    public SakuraLeave(Block.Properties properties) {
     super(properties);
     this.type = SakuraType.GREEN;
   }
  public SakuraLeave setPetalType(SakuraType type) {
     this.type = type;
     return this;
   }

   public void func_196265_a(BlockState state, World worldIn, BlockPos pos, Random random) {
     super.func_196265_a(state, worldIn, pos, random);
   }

   @OnlyIn(Dist.CLIENT)
   public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
     super.func_180655_c(stateIn, worldIn, pos, rand);
   }

   @OnlyIn(Dist.CLIENT)
   public void paticleTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
     if (rand.nextFloat() < (WindManager.isStrongWind() ? 0.1D : 0.05D)) {
       if (worldIn.func_175623_d(pos.func_177977_b())) {
         SakuraParticleManager.addEffect((new PetalParticle(worldIn, (pos.func_177958_n() + getPetalRandomRange(rand)), (pos.func_177956_o() - 0.1F), (pos.func_177952_p() + getPetalRandomRange(rand)))).setPetal(this.type));
       }
       if (WindManager.isStrongWind()) {
         Direction dir = WindManager.getWindDir();
         if (worldIn.func_175623_d(pos.func_177972_a(dir))) {
           SakuraParticleManager.addEffect((new PetalParticle(worldIn, (pos.func_177958_n() + getPetalRandomRange(rand) + dir.func_82601_c()), (pos.func_177956_o() + getPetalRandomRange(rand)), (pos.func_177952_p() + getPetalRandomRange(rand) + dir.func_82599_e()))).setPetal(this.type));
         }
       }    }  }
   float getPetalRandomRange(Random rand) {
     return Math.min(rand.nextFloat() + 0.1F, 0.9F);
   }

   public BlockRenderLayer func_180664_k() {
     return ((Boolean)SakuraConfig.CLIENT.sakuraFancyRender.get()).booleanValue() ? BlockRenderLayer.CUTOUT_MIPPED : super.func_180664_k();
   }
  public enum SakuraType {
     GREEN(0, 1.0F, DyeColor.GREEN),
     PINK(1, 0.7F, DyeColor.PINK),
     RED(2, 1.2F, DyeColor.RED),
     YELLOW(3, 1.2F, DyeColor.YELLOW);

 
     SakuraType(int id, float mass, DyeColor color) {
       this.ID = id;
       this.MASS = mass;
       this.COLOR = color;
     }
    public static SakuraType getPetal(DyeColor color) {
       return Arrays.<SakuraType>stream(values()).filter(t -> (t.COLOR == color)).findFirst().orElse(PINK);
     }
   }
 }

