 package com.ruby.meshi.item;
import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.enchant.HiganEnchant;
 import com.ruby.meshi.enchant.HiganEnchantType;
 import com.ruby.meshi.entity.ShurikenEntity;
 import java.util.Comparator;
 import java.util.List;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.stream.Stream;
 import javax.annotation.Nullable;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
 import net.minecraft.client.entity.player.ClientPlayerEntity;
 import net.minecraft.client.renderer.entity.PlayerRenderer;
 import net.minecraft.client.util.ITooltipFlag;
 import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentHelper;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.ai.attributes.AttributeModifier;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.potion.Effect;
 import net.minecraft.potion.EffectInstance;
 import net.minecraft.potion.Effects;
 import net.minecraft.stats.Stats;
 import net.minecraft.util.ActionResult;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.Hand;
 import net.minecraft.util.HandSide;
 import net.minecraft.util.SoundCategory;
 import net.minecraft.util.SoundEvents;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TextFormatting;
 import net.minecraft.util.text.TranslationTextComponent;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.client.event.RenderSpecificHandEvent;
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.event.ForgeEventFactory;
 import net.minecraftforge.fml.DistExecutor;
public class NinjaBracelet
   extends Item
   implements KatanaDrop.KatanaDropListener {
   public static final ThreadLocalRandom random = ThreadLocalRandom.current();

 
   public NinjaBracelet(Item.Properties properties) {
     this(properties, 1.25F, 1.75F);
   }
  public NinjaBracelet(Item.Properties properties, float velocityBase, float inaccuracyBase) {
     super(properties);
     DistExecutor.runWhenOn(Dist.CLIENT, () -> ());
     this.velocityBase = velocityBase;
     this.inaccuracyBase = inaccuracyBase;
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
     ItemStack bracelet = playerIn.func_184586_b(handIn);
     ItemStack itemstack = playerIn.field_71071_by.field_70462_a.stream().filter(s -> s.func_77973_b() instanceof Shuriken).findFirst().orElse(ItemStack.field_190927_a);
     if (itemstack.func_190926_b()) {
       return new ActionResult(ActionResultType.FAIL, bracelet);
     }
        playerIn.func_184609_a(handIn);
     worldIn.func_184148_a((PlayerEntity)null, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundEvents.field_187797_fA, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
     if (!worldIn.field_72995_K) {
       ShurikenEntity entity = new ShurikenEntity((LivingEntity)playerIn, worldIn);
            checkAddMultiThrow(entity, bracelet);            checkAddCritical(entity, bracelet);            entity.func_70239_b(getThrowingDamage(br            checkAddReturn(entity, bracelet);            checkAddFlame(entity, bracelet);
       
       checkAddInfinity(entity, bracelet, itemstack);
       
       checkAddEconomy(entity, bracelet);
       
       checkAddPoison(entity, bracelet);
       
       entity.setItemStack(itemstack);
       float velocity = this.velocityBase;
       float inaccuracy = this.inaccuracyBase;
            if (hasEnchant(bracelet, new Enchantment[] { HiganEnchant.SNIPE_THROW })) {
         velocity *= 1.1F;
         inaccuracy *= 0.5F;
       }      entity.func_184547_a((Entity)playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0F, velocity, inaccuracy);
       worldIn.func_217376_c((Entity)entity);
            playerIn.func_184811_cZ().func_185145_a(this, getThrowingDelay(bracelet));      
       if (!playerIn.field_71075_bZ.field_75098_d) {
         if (!entity.isNoPickup()) {
           itemstack.func_190918_g(1);
         }
         if (!isUnbreaking(bracelet)) {
           bracelet.func_222118_a(1, (LivingEntity)playerIn, p -> {
                 p.func_213334_d(playerIn.func_184600_cs());
                                ForgeEventFactory.onPlayerDestroyItem(playerIn, playerIn.func_184607_cu(), playerIn.func_184600_cs());
               });
         }
       }    }    playerIn.func_71029_a(Stats.field_75929_E.func_199076_b(this));
     return new ActionResult(ActionResultType.SUCCESS, bracelet);
   }

   public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
     if (entity instanceof LivingEntity) {
       ((LivingEntity)entity).func_70653_a((Entity)player, 1.0F, MathHelper.func_76126_a(player.field_70177_z * 0.017453292F), -MathHelper.func_76134_b(player.field_70177_z * 0.017453292F));
     }
     return true;
   }
  void checkAddPoison(ShurikenEntity entity, ItemStack stack) {
     int lvl = getEnchantLv(stack, new Enchantment[] { HiganEnchant.POISON_THROW, HiganEnchant.ROGUE_THROW, HiganEnchant.ASSASSIN_THROW });
     if (lvl > 0) {
       boolean isVenom = (lvl > 5);
       int amp = isVenom ? 2 : 0;
       int duration = isVenom ? 15 : 10;
       Effect[] effect = { isVenom ? Effects.field_82731_v : Effects.field_76436_u, Effects.field_76437_t, Effects.field_76421_d, Effects.field_188423_x };
       entity.addEffect(new EffectInstance(effect[(isVenom && random.nextBoolean()) ? 0 : random.nextInt(effect.length)], duration * lvl, amp));
     }  }
   void checkAddEconomy(ShurikenEntity entity, ItemStack stack) {
     if (random.nextFloat() < getEconomyRate(stack)) {
       entity.setNonPickup();
     }
   }
  void checkAddInfinity(ShurikenEntity entity, ItemStack stack, ItemStack star) {
     if (hasEnchant(stack, new Enchantment[] { HiganEnchant.INFINITY_THROW })) {
       Item item = star.func_77973_b();
       if (item instanceof Shuriken) {
         entity.setInfinity(((Shuriken)item).getTier());
         entity.setNonPickup();
       }    }  }
   boolean isUnbreaking(ItemStack stack) {
     int lvl = getEnchantLv(stack, new Enchantment[] { HiganEnchant.UNBREAKING_BRACELET });
     return (random.nextFloat() < lvl * 0.25F);
   }
  void checkAddReturn(ShurikenEntity entity, ItemStack stack) {
     if (hasEnchant(stack, new Enchantment[] { HiganEnchant.RETURN_THROW })) {
       entity.setIsReturn(true);
     }
   }
  void checkAddFlame(ShurikenEntity entity, ItemStack stack) {
     if (hasEnchant(stack, new Enchantment[] { HiganEnchant.FLAME_THROW })) {
       entity.func_70015_d(5);
     }
   }
  public float getEconomyRate(ItemStack stack) {
     int lvl = getEnchantLv(stack, new Enchantment[] { HiganEnchant.ECONOMY_BRACELET });
        return (lvl * lvl + 1) * 0.08F;
   }
  public float getThrowingDamage(ItemStack stack) {
     return calcThrowingDamage(getEnchantLv(stack, new Enchantment[] { HiganEnchant.POWER_THROW, HiganEnchant.HUNTER_THROW, HiganEnchant.ASSASSIN_THROW }));
   }
  public float calcThrowingDamage(int enchantLv) {
     float damage = 0.0F;
     if (enchantLv > 0) {
       damage = Math.min(enchantLv, 5);
       if (enchantLv > 5) {
         enchantLv -= 5;
         damage += (enchantLv * 2);
       }    }    return damage;
   }
  public int getThrowingDelay(ItemStack stack) {
     return calcThrowingDelay(getEnchantLv(stack, new Enchantment[] { HiganEnchant.QUICK_THROW, HiganEnchant.HUNTER_THROW, HiganEnchant.ASSASSIN_THROW }));
   }
  public int calcThrowingDelay(int enchantLv) {
     int coolTime = 20;
     if (enchantLv > 0) {
       coolTime -= Math.min(enchantLv * 2, 10);
            if (enchantLv > 5) {
         enchantLv -= 5;
         coolTime -= enchantLv * 5;
       }    }    return Math.max(coolTime, 0);
   }
  void checkAddCritical(ShurikenEntity entity, ItemStack stack) {
     int lvl = getEnchantLv(stack, new Enchantment[] { HiganEnchant.CRITICAL_THROW, HiganEnchant.ROGUE_THROW, HiganEnchant.ASSASSIN_THROW });
     if (lvl > 0) {
       float crit = Math.min(lvl * 0.1F, 0.5F);
       if (lvl > 5) {
         lvl -= 5;
         crit += lvl * 0.25F;
       }      entity.func_70243_d((random.nextFloat() < crit));    }  }
 
   void checkAddMultiThrow(ShurikenEntity entity, ItemStack stack) {
     int lvl = getEnchantLv(stack, new Enchantment[] { HiganEnchant.MULTI_THROW });
     if (lvl > 0) {
       if (lvl == 1 && random.nextBoolean()) {
         entity.setMultiThrow(1);
       } else {
         entity.setMultiThrow(lvl - 1);
       }    }
   }

 
 
 
   public int getEnchantLv(ItemStack stack, Enchantment... enc) {
     return ((Integer)Stream.<Enchantment>of(enc).map(e -> Integer.valueOf(EnchantmentHelper.func_77506_a(e, stack)))
       .max(Comparator.naturalOrder()).orElse(Integer.valueOf(0))).intValue();
   }
  public boolean hasEnchant(ItemStack stack, Enchantment... enc) {
     return (getEnchantLv(stack, enc) > 0);
   }
  @OnlyIn(Dist.CLIENT)
   public void renderItem(RenderSpecificHandEvent event) {
     if (event.getItemStack().func_77973_b() == this) {
       event.setCanceled(true);
       renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), event.getHand());
     }  }
   @OnlyIn(Dist.CLIENT)
   void renderArmFirstPerson(float equippedProgress, float swingProgress, Hand hand) {
     Minecraft mc = Minecraft.func_71410_x();
     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
     HandSide side = (hand == Hand.MAIN_HAND) ? clientPlayerEntity.func_184591_cq() : clientPlayerEntity.func_184591_cq().func_188468_a();
     boolean flag = (side != HandSide.LEFT);
     float f = flag ? 1.0F : -1.0F;
     float f1 = MathHelper.func_76129_c(swingProgress);
     float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
     float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
     float f4 = -0.4F * MathHelper.func_76126_a(swingProgress * 3.1415927F);
     GlStateManager.translatef(f * (f2 + 0.64000005F), f3 + -0.6F + equippedProgress * -0.6F, f4 + -0.71999997F);
     GlStateManager.rotatef(f * 45.0F, 0.0F, 1.0F, 0.0F);
     float f5 = MathHelper.func_76126_a(swingProgress * swingProgress * 3.1415927F);
     float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
     GlStateManager.rotatef(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
     GlStateManager.rotatef(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
     mc.func_110434_K().func_110577_a(clientPlayerEntity.func_110306_p());
     GlStateManager.translatef(f * -1.0F, 3.6F, 3.5F);
     GlStateManager.rotatef(f * 120.0F, 0.0F, 0.0F, 1.0F);
     GlStateManager.rotatef(200.0F, 1.0F, 0.0F, 0.0F);
     GlStateManager.rotatef(f * -135.0F, 0.0F, 1.0F, 0.0F);
     GlStateManager.translatef(f * 5.6F, 0.0F, 0.0F);
     PlayerRenderer playerrenderer = (PlayerRenderer)mc.func_175598_ae().func_78713_a((Entity)clientPlayerEntity);
     GlStateManager.disableCull();
     if (flag) {
       playerrenderer.func_177138_b((AbstractClientPlayerEntity)clientPlayerEntity);
     } else {
       playerrenderer.func_177139_c((AbstractClientPlayerEntity)clientPlayerEntity);
     }    GlStateManager.enableCull();
   }

   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
     return (HiganEnchantType.BRACELET == enchantment.field_77351_y);
   }

 
   public int func_77619_b() {
     return 120;
   }

   @OnlyIn(Dist.CLIENT)
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
     float dmg = getThrowingDamage(stack);
     int speed = getThrowingDelay(stack);
     int eco = (int)(getEconomyRate(stack) * 100.0F);
     if (dmg > 0.0F) {
       tooltip.add((new TranslationTextComponent("attribute.modifier.equals." + AttributeModifier.Operation.ADDITION.func_220371_a(), new Object[] { ItemStack.field_111284_a.format(dmg), new TranslationTextComponent("tooltip.meshi.throwing_damage", new Object[0]) })).func_211708_a(TextFormatting.DARK_GREEN));
     }
     tooltip.add((new TranslationTextComponent("attribute.modifier.equals." + AttributeModifier.Operation.ADDITION.func_220371_a(), new Object[] { ItemStack.field_111284_a.format(speed), new TranslationTextComponent("tooltip.meshi.throwing_delay", new Object[0]) })).func_211708_a(TextFormatting.DARK_GREEN));
     tooltip.add((new TranslationTextComponent("attribute.modifier.equals." + AttributeModifier.Operation.ADDITION.func_220371_a(), new Object[] { ItemStack.field_111284_a.format(eco), new TranslationTextComponent("tooltip.meshi.throwing_save", new Object[0]) })).func_211708_a(TextFormatting.DARK_GREEN));
   }

   public boolean canKatanaDrop(LivingEntity source, LivingEntity target, ItemStack stack, Hand hand) {
     return hasEnchant(stack, new Enchantment[] { HiganEnchant.PICKPOCKET });
   }
 }

