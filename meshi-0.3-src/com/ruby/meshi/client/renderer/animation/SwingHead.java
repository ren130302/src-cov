 package com.ruby.meshi.client.renderer.animation;
import java.util.function.Consumer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.MathHelper;

 public class SwingHead
   implements EntityModelAnimation
 {
   float prevMoveAmount;
   float moveAmount;
   float prevRotateYaw;
   float rotateYaw;
   float prevRotatePitch;
   float rotatePitch;
   int timer = 0;

 
 
   public SwingHead(Direction direction) {
     this.direction = direction;
     this.maxTimer = 600 + rand.nextInt(600);
     this.moveTime = 8 + rand.nextInt(4) - 2;
     this.moveAmount = 0.37F;
     this.lookX = this.lookY = 0.0F;
   }

   public void animationTick() {
     this.prevMoveAmount = this.moveAmount;
     this.prevRotateYaw = this.rotateYaw;
     this.prevRotatePitch = this.rotatePitch;

 
     
     headMove();
   }

   void headMove() {
     if (Math.abs(this.lookX - this.rotatePitch) > 0.1F) {
       this.rotatePitch = lerp(0.2F, this.rotatePitch, this.lookX);
       this.rotateYaw = lerp(0.2F, this.rotateYaw, this.lookY);
     } else if (rand.nextFloat() < 0.001F) {
       this.lookX = 70.0F * rand.nextFloat() - 35.0F;
       this.lookX += 10.0F * Math.signum(this.lookX);
       this.lookX = MathHelper.func_76131_a(this.lookX, -35.0F, 35.0F);
       this.lookY = 17.5F * rand.nextFloat() - 8.75F;
     }  }
 
   public boolean isFinished() {
     return (this.timer > this.maxTimer);
   }

   public boolean shouldRenderPart(EntityModelAnimation.RenderPart part) {
     return (part == EntityModelAnimation.RenderPart.HEAD);
   }

   public void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
     position.accept(Float.valueOf(getDiractionOffsetVal(lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Direction.Axis.X)),        Float.valueOf(0.0F),        Float.valueOf(getDiractionOffsetVal(lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Direction.Axis.Z)));
   }

   public void rotateY(Consumer<Float> angle, float partialTicks) {
     angle.accept(Float.valueOf(lerp(partialTicks, this.prevRotatePitch, this.rotatePitch)));
   }

   public void rotateX(Consumer<Float> angle, float partialTicks) {
     angle.accept(Float.valueOf(-getDiractionVal(lerp(partialTicks, this.prevRotateYaw, this.rotateYaw), this.direction, Direction.Axis.Z)));
   }

   public void rotateZ(Consumer<Float> angle, float partialTicks) {
     angle.accept(Float.valueOf(getDiractionOffsetVal(lerp(partialTicks, this.prevRotateYaw, this.rotateYaw), this.direction, Direction.Axis.X)));
   }

   public int getMaxTimer() {
     return this.maxTimer;
   }
 }

