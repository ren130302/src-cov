  package com.ruby.meshi.client.renderer.animation;
  public interface EntityModelAnimation {
    boolean shouldRenderPart(RenderPart paramRenderPart);
      void animationTick();      int getMaxTimer();   
    public static final ThreadLocalRandom rand = ThreadLocalRandom.current();
 
 
    public enum RenderPart { HEAD,
      TAIL,
      RIGHT_HAND,
      LEFT_HAND,
      RIGHT_LEG,
      LEFT_LEG; }

 
 
 
 
    default void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
      position.accept(Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(0.0F));
    }
   default void scalef(TriConsumer<Float, Float, Float> scale, float partialTicks) {
      scale.accept(Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(0.0F));
    }
   default void rotateX(Consumer<Float> angle, float partialTicks) {
      angle.accept(Float.valueOf(0.0F));
    }
   default void rotateY(Consumer<Float> angle, float partialTicks) {
      angle.accept(Float.valueOf(0.0F));
    }
   default void rotateZ(Consumer<Float> angle, float partialTicks) {
      angle.accept(Float.valueOf(0.0F));
    }
   default float getDiractionVal(float val, Direction direction, Direction.Axis axis) {
      float offset = 0.0F;
      if (direction.func_176740_k() == axis) {
        offset = val;
      }
      return offset;
    }
   default float getDiractionOffsetVal(float val, Direction direction, Direction.Axis axis) {
      float offset = 0.0F;
      if (direction.func_176740_k() == axis) {
        offset = val * direction.func_176743_c().func_179524_a();
      }
      return offset;
    }
   default float lerp(float partialTicks, float prev, float now) {
      return MathHelper.func_219799_g(partialTicks, prev, now);
    }
  }

