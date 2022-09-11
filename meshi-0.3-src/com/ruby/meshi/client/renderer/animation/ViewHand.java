 package com.ruby.meshi.client.renderer.animation;
import javax.annotation.Nullable;
 import net.minecraft.util.Direction;
public class ViewHand
   implements EntityModelAnimation
 {
   float prevMoveAmount;
   float moveAmount;
   float prevRotateYaw;
   float rotateYaw;
   float prevRotatePitch;
   float rotatePitch;
   int timer = 0;

   @Nullable
   EntityModelAnimation.RenderPart part = null; final Direction direction;  EntityModelAnimation.RenderPart part = null; final Direction direction;

   public ViewHand(Direction direction) {
     this.direction = direction;
     this.maxTimer = 100 + rand.nextInt(100);
     this.moveAmount = 0.3F;
   }
  public ViewHand(Direction direction, EntityModelAnimation.RenderPart part) {
     this(direction);
     this.part = part;
   }

   public boolean shouldRenderPart(EntityModelAnimation.RenderPart part) {
     if (this.part != null) {
       return (this.part == part);
     }
     return (part == EntityModelAnimation.RenderPart.LEFT_HAND || part == EntityModelAnimation.RenderPart.RIGHT_HAND);
   }

   public void animationTick() {
     this.prevMoveAmount = this.moveAmount;
     this.prevRotateYaw = this.rotateYaw;
     this.prevRotatePitch = this.rotatePitch;

 
     
     handMove();
   }

 
 
   public ViewHand setParent(EntityModelAnimation parent) {
     this.parent = parent;
     this.maxTimer = parent.getMaxTimer();
     return this;
   }

   public void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
     position.accept(Float.valueOf(getDiractionOffsetVal(lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Direction.Axis.X)),        Float.valueOf(0.0F),        Float.valueOf(getDiractionOffsetVal(lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Direction.Axis.Z)));
   }

   public boolean isFinished() {
     return (this.timer > this.maxTimer);
   }

   public int getMaxTimer() {
     return this.maxTimer;
   }
 }

