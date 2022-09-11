 package com.ruby.meshi.entity;
import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.item.ArmorStandEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.network.IPacket;
 import net.minecraft.util.DamageSource;
 import net.minecraft.util.Hand;
 import net.minecraft.world.World;
 import net.minecraftforge.fml.network.FMLPlayMessages;
 import net.minecraftforge.fml.network.NetworkHooks;
public class ScarecrowEntity extends ArmorStandEntity {
   public ScarecrowEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
     super(HiganEntityType.SCARECROW, worldIn);
   }

   public ScarecrowEntity(World worldIn, double posX, double posY, double posZ) {
     this(HiganEntityType.SCARECROW, worldIn);
     func_70107_b(posX, posY, posZ);
   }
  public ScarecrowEntity(EntityType<ScarecrowEntity> scarecrow, World worldIn) {
     super(HiganEntityType.SCARECROW, worldIn);
   }

   public IPacket<?> func_213297_N() {
     return NetworkHooks.getEntitySpawningPacket((Entity)this);
   }

   public boolean func_70097_a(DamageSource source, float amount) {
     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
       if (DamageSource.field_76380_i.equals(source)) {
         func_70106_y();
         return false;
       }      System.out.println(amount);    } 
     return false;
   }

   public boolean func_184230_a(PlayerEntity player, Hand hand) {
     if (!func_70089_S()) {
       return false;
     }
     if (player.func_184586_b(hand).func_190926_b()) {
       func_70106_y();
     }
        return super.func_184230_a(player, hand);
   }

   public boolean func_190631_cK() {
     return super.func_190631_cK();
   }
 }

