 package com.ruby.meshi.entity;
import java.util.function.BiFunction;
 import net.minecraft.entity.EntityClassification;
 import net.minecraft.entity.EntityType;

 public class HiganEntityType
 {
   public static final EntityType<ShurikenEntity> SHURIKEN = create("shuriken_entity", EntityType.Builder.func_220319_a(EntityClassification.MISC).setCustomClientFactory(ShurikenEntity::new).setShouldReceiveVelocityUpdates(true).func_220321_a(0.25F, 0.25F));
   public static final EntityType<ScarecrowEntity> SCARECROW = create("scarecrow_entity", EntityType.Builder.func_220319_a(EntityClassification.MISC).setCustomClientFactory(ScarecrowEntity::new).setShouldReceiveVelocityUpdates(true).func_220321_a(0.5F, 1.975F));

   public static <T extends net.minecraft.entity.Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
     return (EntityType<T>)builder.func_206830_a(name).setRegistryName("meshi", name);
   }
 }

