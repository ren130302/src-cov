 package com.ruby.meshi.entity;
import com.google.common.collect.Sets;
 import com.ruby.meshi.item.HiganItems;
 import com.ruby.meshi.item.Shuriken;
 import java.util.Collection;
 import java.util.Set;
 import javax.annotation.Nonnull;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.IRendersAsItem;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity;
 import net.minecraft.item.IItemTier;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemTier;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.network.IPacket;
 import net.minecraft.network.datasync.DataParameter;
 import net.minecraft.network.datasync.DataSerializers;
 import net.minecraft.network.datasync.EntityDataManager;
 import net.minecraft.potion.EffectInstance;
 import net.minecraft.potion.PotionUtils;
 import net.minecraft.util.DamageSource;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.IndirectEntityDamageSource;
 import net.minecraft.util.SoundCategory;
 import net.minecraft.util.SoundEvents;
 import net.minecraft.util.Util;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.EntityRayTraceResult;
 import net.minecraft.util.math.RayTraceResult;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.fml.network.FMLPlayMessages;
 import net.minecraftforge.fml.network.NetworkHooks;

 @OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
 public class ShurikenEntity
   extends AbstractArrowEntity
   implements IRendersAsItem
 {
   private static final DataParameter<ItemStack> ITEMSTACK_DATA = EntityDataManager.func_187226_a(ShurikenEntity.class, DataSerializers.field_187196_f);
  private int timer = 0;
   public float xAngle = this.field_70146_Z.nextFloat() * 90.0F - 45.0F;
  private final Set<EffectInstance> customPotionEffects = Sets.newHashSet();
 
   private int economy = 0;
  private int infinity = 0;
 
 
 
   private int multiThrow = 0;

   public ShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
     super(HiganEntityType.SHURIKEN, worldIn);
   }

   public ShurikenEntity(LivingEntity livingEntityIn, World worldIn) {
     super(HiganEntityType.SHURIKEN, livingEntityIn, worldIn);
     func_189654_d(true);
   }

   public void func_184547_a(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
     super.func_184547_a(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
     this.velocity = velocity;
     this.inaccuracy = inaccuracy;
        this.field_70163_u -= 0.25D;
     func_213317_d(func_213322_ci().func_72441_c(0.0D, 0.05D, 0.0D));
   }

   public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) {
     super.func_70186_c(x, y, z, velocity, inaccuracy);
   }

   protected void func_184549_a(RayTraceResult result) {
     RayTraceResult.Type resultType = result.func_216346_c();
     if (resultType == RayTraceResult.Type.ENTITY) {
       attackEntity((EntityRayTraceResult)result);
     } else if (resultType == RayTraceResult.Type.BLOCK) {
       BlockRayTraceResult blockResult = (BlockRayTraceResult)result;
       BlockPos blockPos = blockResult.func_216350_a();
       BlockState state = this.field_70170_p.func_180495_p(blockPos);
       Vec3d vec3d = blockResult.func_216347_e().func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
       func_213317_d(vec3d);
       Vec3d vec3d1 = vec3d.func_72432_b().func_186678_a(0.05000000074505806D);
       this.field_70165_t -= vec3d1.field_72450_a;
       this.field_70163_u -= vec3d1.field_72448_b;
       this.field_70161_v -= vec3d1.field_72449_c;
       func_184185_a(state.func_215695_r().func_185846_f(), 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
       this.field_70254_i = true;
       this.field_70249_b = 7;
       func_70243_d(false);
       func_213872_b((byte)0);
       func_213865_o(false);
       state.func_215690_a(this.field_70170_p, state, blockResult, (Entity)this);
     }  }
 
   protected void attackEntity(EntityRayTraceResult result) {
     if (!this.field_70170_p.field_72995_K) {
       Entity target = result.func_216348_a();
       Entity shooter = func_212360_k();
            if (this.multiThrow > 0) {
         multiThrow();
       }
       if (this.multiThrow < 0) {
         target.field_70172_ad = 0;
       }
       float dmg = ((Shuriken)func_184550_j().func_77973_b()).getAttackDamage(func_184550_j());
       if (func_70241_g()) {
         dmg *= 1.5F;
       }
       dmg = (float)(dmg + func_70242_d());
            if (target.func_70097_a((DamageSource)new IndirectEntityDamageSource("shuriken", (Entity)this, target), dmg)) {        if (shooter != null && 
           shooter instanceof LivingEntity) {
           ((LivingEntity)shooter).func_130011_c(target);
         }
                if (func_70027_ad() && !(target instanceof net.minecraft.entity.monster.EndermanEntity)) {
           target.func_70015_d(5);
         }
         if (target instanceof LivingEntity && !this.customPotionEffects.isEmpty()) {
           for (EffectInstance effectinstance1 : this.customPotionEffects) {
             ((LivingEntity)target).func_195064_c(effectinstance1);
           }
         }
         func_70106_y();
       }    }  }
 
   protected void func_70088_a() {
     super.func_70088_a();
     func_184212_Q().func_187214_a(ITEMSTACK_DATA, ItemStack.field_190927_a);
   }

   public void func_70071_h_() {
     multiThrow();
        this.timer++;    
     float tempPitth = this.field_70125_A;
     float tempYaw = this.field_70177_z;
     super.func_70071_h_();
     this.field_70127_C = this.field_70125_A = tempPitth;
     this.field_70126_B = this.field_70177_z = tempYaw;
        if (!this.field_70254_i) {
       this.field_70125_A -= 36.0F;
     }
     else if (!this.field_70170_p.field_72995_K &&      this.isReturn && func_212360_k() instanceof PlayerEntity) {
       func_70100_b_((PlayerEntity)func_212360_k());
     }    
     Vec3d motion = func_213322_ci();
     func_213293_j(motion.field_72450_a, motion.field_72448_b - 0.03D, motion.field_72449_c);
   }

   private void multiThrow() {
     if (!this.field_70170_p.field_72995_K &&      this.timer % 3 == 1 &&      this.multiThrow > 0) {
       Entity shooter = func_212360_k();
       if (shooter instanceof LivingEntity) {
         ItemStack stack = pickupInventoryShuriken();
         if (!stack.func_190926_b()) {
           ShurikenEntity entity = new ShurikenEntity((LivingEntity)shooter, this.field_70170_p);
           entity.setItemStack(stack);
           entity.func_184547_a(shooter, shooter.field_70125_A, shooter.field_70177_z, 0.0F, this.velocity, this.inaccuracy);
                    entity.multiThrow = (--this.multiThrow != 0) ? this.multiThrow : -1;
           entity.field_70251_a = this.field_70251_a;
           entity.infinity = this.infinity;
           entity.func_70243_d(func_70241_g());
           entity.setEffect(getEffect());
           entity.setIsReturn(this.isReturn);
           entity.func_70239_b(func_70242_d() * 0.699999988079071D);
           entity.func_70015_d(func_223314_ad());
           this.field_70170_p.func_217376_c((Entity)entity);
           this.field_70170_p.func_184148_a((PlayerEntity)null, shooter.field_70165_t, shooter.field_70163_u, shooter.field_70161_v, SoundEvents.field_187797_fA, SoundCategory.PLAYERS, 0.5F, 0.4F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
           this.multiThrow = -1;
         } else {
           this.multiThrow = 0;
         }      } else {
         this.multiThrow = 0;
       }    }  }
 
   @Nonnull
   private ItemStack pickupInventoryShuriken() {
     if (this.infinity > 0)
     {
       return new ItemStack((this.infinity == 3) ? (IItemProvider)HiganItems.SHURIKEN_DIAMOND : ((this.infinity == 2) ? (IItemProvider)HiganItems.SHURIKEN_IRON : (IItemProvider)HiganItems.SHURIKEN_STONE));
     }
        if (func_212360_k() != null && func_212360_k() instanceof PlayerEntity) {
       PlayerEntity player = (PlayerEntity)func_212360_k();
       ItemStack itemstack = player.field_71071_by.field_70462_a.stream().filter(s -> s.func_77973_b() instanceof Shuriken).findFirst().orElse(ItemStack.field_190927_a);
       if (!player.field_71075_bZ.field_75098_d) {
         if (isNoPickup()) {
           itemstack = itemstack.func_77946_l();
           itemstack.func_190920_e(1);
         } else {
           itemstack = itemstack.func_77979_a(1);
         }      }
       return itemstack;
     }    return ItemStack.field_190927_a;
   }

   public boolean func_189652_ae() {
     return true;
   }

   public void func_70100_b_(PlayerEntity entityIn) {
     if (!this.field_70170_p.field_72995_K && (this.field_70254_i || func_203047_q()) && this.field_70249_b <= 0) {
       boolean flag = (this.field_70251_a == AbstractArrowEntity.PickupStatus.ALLOWED || (this.field_70251_a == AbstractArrowEntity.PickupStatus.CREATIVE_ONLY && entityIn.field_71075_bZ.field_75098_d) || (func_203047_q() && func_212360_k().func_110124_au() == entityIn.func_110124_au()));
            if (!entityIn.field_71075_bZ.field_75098_d && !isNoPickup()) {
         if (this.field_70251_a == AbstractArrowEntity.PickupStatus.ALLOWED && !entityIn.field_71071_by.func_70441_a(func_184550_j())) {
           flag = false;
         }
       } else {
         flag = true;
       }      if (flag) {
         entityIn.func_71001_a((Entity)this, 1);
         func_70106_y();
       } else if (this.isReturn) {
         func_70106_y();
       }    }  }
 
   public void func_70106_y() {
     super.func_70106_y();
   }

   public void func_70037_a(CompoundNBT compound) {
     super.func_70037_a(compound);
     ItemStack itemstack = ItemStack.func_199557_a(compound.func_74775_l("Item"));
     setItemStack(itemstack);
     for (EffectInstance effectinstance : PotionUtils.func_185192_b(compound)) {
       addEffect(effectinstance);
     }
     this.infinity = compound.func_74762_e("infinity");
     this.economy = compound.func_74762_e("economy");
   }

   public void func_213281_b(CompoundNBT compound) {
     super.func_213281_b(compound);
     ItemStack itemstack = func_184550_j();
     if (!itemstack.func_190926_b()) {
       compound.func_218657_a("Item", (INBT)itemstack.func_77955_b(new CompoundNBT()));
     }
     if (!this.customPotionEffects.isEmpty()) {
       ListNBT listnbt = new ListNBT();
            for (EffectInstance effectinstance : this.customPotionEffects) {
         listnbt.add(effectinstance.func_82719_a(new CompoundNBT()));
       }
       compound.func_218657_a("CustomPotionEffects", (INBT)listnbt);
     }    compound.func_74768_a("infinity", this.infinity);
     compound.func_74768_a("economy", this.economy);
   }

   public IPacket<?> func_213297_N() {
     return NetworkHooks.getEntitySpawningPacket((Entity)this);
   }

   public ItemStack func_184543_l() {
     ItemStack itemstack = func_184550_j();
     return itemstack.func_190926_b() ? new ItemStack((IItemProvider)func_184550_j().func_77973_b()) : itemstack;
   }
  public void setItemStack(ItemStack stack) {
     if (stack.func_77973_b() != func_184550_j().func_77973_b() || stack.func_77942_o()) {
       func_184212_Q().func_187227_b(ITEMSTACK_DATA, Util.func_200696_a(stack.func_77946_l(), p_213883_0_ -> p_213883_0_.func_190920_e(1)));
     }
   }

 
   protected ItemStack func_184550_j() {
     return (ItemStack)func_184212_Q().func_187225_a(ITEMSTACK_DATA);
   }
  public void setMultiThrow(int lvl) {
     this.multiThrow = lvl;
   }
  public void addEffect(EffectInstance effect) {
     this.customPotionEffects.add(effect);
   }
  public void setEffect(Collection<EffectInstance> effects) {
     this.customPotionEffects.clear();
     this.customPotionEffects.addAll(effects);
   }
  public Set<EffectInstance> getEffect() {
     return this.customPotionEffects;
   }
  public void setIsReturn(boolean isReturn) {
     this.isReturn = isReturn;
   }
  public void setInfinity(IItemTier iItemTier) {
     this.infinity = (iItemTier == ItemTier.STONE) ? 1 : ((iItemTier == ItemTier.IRON) ? 2 : ((iItemTier == ItemTier.DIAMOND) ? 3 : 0));
   }
  public void setEconomy(int eco) {
     this.economy = eco;
   }
  public void setNonPickup() {
     this.field_70251_a = AbstractArrowEntity.PickupStatus.DISALLOWED;
   }
  public boolean isNoPickup() {
     return (this.field_70251_a == AbstractArrowEntity.PickupStatus.DISALLOWED);
   }
 }

