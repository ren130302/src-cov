 package com.ruby.meshi.client.paticle;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.SakuraLeave;
 import net.minecraft.client.particle.IParticleRenderType;
 import net.minecraft.client.particle.Particle;
 import net.minecraft.client.renderer.ActiveRenderInfo;
 import net.minecraft.client.renderer.BufferBuilder;
 import net.minecraft.client.renderer.RenderHelper;
 import net.minecraft.client.renderer.Tessellator;
 import net.minecraft.client.renderer.texture.TextureManager;
 import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.tags.Tag;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.math.AxisAlignedBB;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorldReader;
 import net.minecraft.world.World;

 public class PetalParticle
   extends Particle
 {
   static final float PI180 = 0.017453292F;
   float scale = 0.1F;

 
 
 
 
   public PetalParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
     this(worldIn, posXIn, posYIn, posZIn, 0.0D, 0.0D, 0.0D);
   }
  public PetalParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
     super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
     func_187115_a(0.1F, 0.1F);
     this.field_187129_i = 0.0D;
     this.field_187130_j = (-this.field_187136_p.nextFloat() * 0.05F);
     this.field_187131_k = 0.0D;
     this.field_70547_e = Math.max((int)(600.0D / (Math.random() * 0.8D + 0.6D)), 1);
     this.pitch = 90.0F * (this.field_187136_p.nextFloat() - 0.5F) * 0.5F + 90.0F;
     this.prevPitch = this.pitch;
     this.yaw = 360.0F * this.field_187136_p.nextFloat();
     this.rotateMotion = 20.0F * (this.field_187136_p.nextFloat() - 0.5F);
     this.petalType = SakuraLeave.SakuraType.GREEN;
     float c = 1.0F - this.field_187136_p.nextFloat() * 0.3F;
     func_70538_b(1.0F, c, c);
        this.field_70545_g = 1.0F - (this.field_187136_p.nextFloat() - 0.2F) * 0.6F;
     this.moveResistance = 1.0F - this.field_187136_p.nextFloat() * 0.6F;
        func_189213_a();
   }

 
 
 
   public PetalParticle setPetal(SakuraLeave.SakuraType type) {
     this.petalType = type;
     return this;
   }

   public void func_189213_a() {
     this.field_187123_c = this.field_187126_f;
     this.field_187124_d = this.field_187127_g;
     this.field_187125_e = this.field_187128_h;
     this.prevYaw = this.yaw;
     if (this.field_70546_d++ >= this.field_70547_e) {
       func_187112_i();
     } else {
       if (!this.onWater && isInWater()) {
         this.onWater = true;
         this.rotateMotion = 0.0F;
       }      this.field_187130_j -= 0.004D * this.petalType.MASS * this.field_70545_g;            func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
       
       this.field_187129_i *= 0.9599999785423279D * this.moveResistance;
       this.field_187130_j *= 0.9599999785423279D;
       this.field_187131_k *= 0.9599999785423279D * this.moveResistance;
       this.yaw += this.rotateMotion;
       if (this.field_187124_d == this.field_187127_g) {
         this.field_187132_l = true;
         this.rotateMotion = 0.0F;
         this.field_70546_d++;
       }      if (this.field_187132_l) {
         this.field_187129_i *= 0.8999999761581421D - (this.petalType.MASS * 0.2F);
         this.field_187131_k *= 0.8999999761581421D - (this.petalType.MASS * 0.2F);
       }
       else if (this.onWater) {
         waterMove();
       } else {
         windMove();
       }    }  }
 
   private boolean isInWater() {
     if (this.field_187122_b.func_72953_d(func_187116_l())) {
       BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
       IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
            VoxelShape box = ifluidstate.func_215676_d((IBlockReader)this.field_187122_b, blockpos);
       if (!box.func_197766_b()) {
         AxisAlignedBB aabb = ifluidstate.func_215676_d((IBlockReader)this.field_187122_b, blockpos).func_197752_a().func_186670_a(blockpos).func_72317_d(0.0D, 0.1D, 0.0D);
         return func_187116_l().func_72326_a(aabb);
       }    }    return false;
   }

   private void waterMove() {
     BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
     IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
     VoxelShape box = ifluidstate.func_215676_d((IBlockReader)this.field_187122_b, blockpos);
     if (!box.func_197766_b()) {
       AxisAlignedBB aabb = ifluidstate.func_215676_d((IBlockReader)this.field_187122_b, blockpos).func_197752_a().func_186670_a(blockpos).func_72317_d(0.0D, 0.1D, 0.0D);
       if (func_187116_l().func_72326_a(aabb)) {
         Vec3d vec = ifluidstate.func_215673_c((IBlockReader)this.field_187122_b, blockpos).func_186678_a(0.1D);
         this.field_187129_i = vec.field_72450_a;
         this.field_187127_g = aabb.field_72337_e;
         this.field_187131_k = vec.field_72449_c;
       }    }  }
   public boolean isInFluid(Tag<Fluid> p_213290_1_) {
     BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
     if (!this.field_187122_b.func_217354_b(blockpos.func_177958_n() >> 4, blockpos.func_177952_p() >> 4)) {
       return false;
     }
     this.field_187122_b.func_72953_d(func_187116_l());
     IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
     return ifluidstate.getFluidState().func_206886_c().isAABBInsideLiquid(ifluidstate, (IWorldReader)this.field_187122_b, blockpos, func_187116_l()).booleanValue();
   }

   void windMove() {
     this.field_187129_i += WindManager.getMotionX();
     this.field_187130_j += WindManager.getMotionY();
     this.field_187131_k += WindManager.getMotionZ();
   }
  public int getTextuerID() {
     return this.petalType.ID;
   }

   public IParticleRenderType func_217558_b() {
     return PETAL;
   }

   public void func_180434_a(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
     float partialPitch = (float)MathHelper.func_219803_d(partialTicks, this.prevPitch, this.pitch);
     float partialYaw = (float)MathHelper.func_219803_d(partialTicks, this.prevYaw, this.yaw);
     rotationX = MathHelper.func_76134_b(partialYaw * 0.017453292F);
     rotationYZ = MathHelper.func_76126_a(partialYaw * 0.017453292F);
     rotationXY = -rotationYZ * MathHelper.func_76126_a(partialPitch * 0.017453292F);
     rotationXZ = rotationX * MathHelper.func_76126_a(partialPitch * 0.017453292F);
     rotationZ = MathHelper.func_76134_b(partialPitch * 0.017453292F);
     float minU = 0.15625F * getTextuerID();
     float maxU = minU + 0.15625F;
     float minV = 0.3125F * (getTextuerID() / 6);
     float maxV = minV + 0.3125F;
     float x = (float)(MathHelper.func_219803_d(partialTicks, this.field_187123_c, this.field_187126_f) - field_70556_an);
     float y = (float)(MathHelper.func_219803_d(partialTicks, this.field_187124_d, this.field_187127_g) - field_70554_ao);
     float z = (float)(MathHelper.func_219803_d(partialTicks, this.field_187125_e, this.field_187128_h) - field_70555_ap);
     Vec3d[] avec3d = { new Vec3d((-rotationX * this.scale - rotationXY * this.scale), (-rotationZ * this.scale), (-rotationYZ * this.scale - rotationXZ * this.scale)), new Vec3d((-rotationX * this.scale + rotationXY * this.scale), (rotationZ * this.scale), (-rotationYZ * this.scale + rotationXZ * this.scale)), new Vec3d((rotationX * this.scale + rotationXY * this.scale), (rotationZ * this.scale), (rotationYZ * this.scale + rotationXZ * this.scale)), new Vec3d((rotationX * this.scale - rotationXY * this.scale), (-rotationZ * this.scale), (rotationYZ * this.scale - rotationXZ * this.scale)) };
        int i = func_189214_a(partialTicks);
     int j = i >> 16 & 0xFFFF;
     int k = i & 0xFFFF;
     buffer.func_181662_b(x + (avec3d[0]).field_72450_a, y + (avec3d[0]).field_72448_b, z + (avec3d[0]).field_72449_c).func_187315_a(maxU, maxV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
     buffer.func_181662_b(x + (avec3d[1]).field_72450_a, y + (avec3d[1]).field_72448_b, z + (avec3d[1]).field_72449_c).func_187315_a(maxU, minV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
     buffer.func_181662_b(x + (avec3d[2]).field_72450_a, y + (avec3d[2]).field_72448_b, z + (avec3d[2]).field_72449_c).func_187315_a(minU, minV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
     buffer.func_181662_b(x + (avec3d[3]).field_72450_a, y + (avec3d[3]).field_72448_b, z + (avec3d[3]).field_72449_c).func_187315_a(minU, maxV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
   }

   static final IParticleRenderType PETAL = new ParticleRenderType();
  static class ParticleRenderType implements IParticleRenderType {
     static final ResourceLocation LOCATION = new ResourceLocation("meshi", "textures/entitys/petal.png");

 
     public void func_217600_a(BufferBuilder buffer, TextureManager textureManager) {
       RenderHelper.func_74518_a();
       GlStateManager.disableBlend();
       GlStateManager.depthMask(true);
       this; textureManager.func_110577_a(LOCATION);
       buffer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
     }

     public void func_217599_a(Tessellator tessellator) {
       tessellator.func_78381_a();
     }

     public String toString() {
       return "PETAL";
     }
   }
 }

