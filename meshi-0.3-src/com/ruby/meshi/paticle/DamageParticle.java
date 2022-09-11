 package com.ruby.meshi.paticle;
import net.minecraft.client.particle.IParticleFactory;
 import net.minecraft.client.particle.IParticleRenderType;
 import net.minecraft.client.particle.Particle;
 import net.minecraft.client.particle.SpriteTexturedParticle;
 import net.minecraft.particles.BasicParticleType;
 import net.minecraft.particles.IParticleData;
 import net.minecraft.world.World;
public class DamageParticle extends SpriteTexturedParticle {
   protected DamageParticle(World p_i50998_1_, double p_i50998_2_, double p_i50998_4_, double p_i50998_6_) {
     super(p_i50998_1_, p_i50998_2_, p_i50998_4_, p_i50998_6_);
   }

   public IParticleRenderType func_217558_b() {
     return IParticleRenderType.field_217602_b;
   }
  public static class DamageSprite
     implements IParticleFactory<BasicParticleType>
   {
     public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
       return null;
     }
   }
 }

