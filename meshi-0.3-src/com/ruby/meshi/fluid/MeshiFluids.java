 package com.ruby.meshi.fluid;
import net.minecraft.fluid.FlowingFluid;
 
 
 public class MeshiFluids
 {
   public static final FlowingFluid FLOWING_HOT_SPING = (FlowingFluid)register("flowing_hot_spring", new HotSpring.Flowing());
   public static final FlowingFluid HOT_SPING = (FlowingFluid)register("hot_spring", new HotSpring.Source());

   private static <T extends net.minecraft.fluid.Fluid> T register(String name, T fluid) {
     return (T)fluid.setRegistryName("meshi", name);
   }
 }

