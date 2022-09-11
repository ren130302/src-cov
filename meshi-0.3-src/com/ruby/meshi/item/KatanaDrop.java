 package com.ruby.meshi.item;
import com.google.common.collect.ArrayListMultimap;
 import java.util.List;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.stream.Collectors;
 import javax.annotation.Nonnull;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.Items;
 import net.minecraft.util.DamageSource;
 import net.minecraft.util.Hand;
 import net.minecraft.util.IItemProvider;
 import net.minecraftforge.event.entity.living.LivingDeathEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class KatanaDrop
 {
   public static final ThreadLocalRandom random = ThreadLocalRandom.current();
   private static final ArrayListMultimap<EntityType<? extends Entity>, KatanaDrop> DROP_TABLE = ArrayListMultimap.create();

 
 
   private KatanaDrop(Item item, int min, int max, float rate) {
     this.item = item;
     this.min = min;
     this.max = Math.max(min, max);
     this.dropRate = rate;
   }
  public static List<ItemStack> getDrop(EntityType<? extends Entity> entity) {
     return (List<ItemStack>)DROP_TABLE.get(entity).stream().filter(KatanaDrop::canDrop).map(KatanaDrop::getStack).collect(Collectors.toList());
   }
  private boolean canDrop() {
     return (random.nextFloat() < this.dropRate);
   }
  private ItemStack getStack() {
     return new ItemStack((IItemProvider)this.item, (this.max - this.min > 1) ? (random.nextInt(this.max - this.min) + this.min) : this.max);
   }
  public static void addTable(EntityType<? extends Entity> type, Item item, float rate) {
     addTable(type, item, 1, 1, rate);
   }
  public static void addTable(EntityType<? extends Entity> type, Item item, int max, float rate) {
     addTable(type, item, 1, max, rate);
   }
  public static void addTable(EntityType<? extends Entity> type, Item item, int min, int max, float rate) {
     DROP_TABLE.put(type, new KatanaDrop(item, min, max, rate));
   }

 
   public static interface KatanaDropListener
   {
     boolean canKatanaDrop(@Nonnull LivingEntity param1LivingEntity1, @Nonnull LivingEntity param1LivingEntity2, ItemStack param1ItemStack, Hand param1Hand);
    
     default void onKatanaDrop(@Nonnull LivingEntity target) {
       List<ItemStack> drop = KatanaDrop.getDrop(target.func_200600_R());
       if (!drop.isEmpty()) {
         target.func_199701_a_(drop.get(KatanaDrop.random.nextInt(drop.size())));
       }
     }
   }
  @SubscribeEvent
   public static void onKill(LivingDeathEvent event) {
     DamageSource source = event.getSource();
     if (source instanceof net.minecraft.util.EntityDamageSource) {
       Entity entity = source.func_76364_f();
       if (entity instanceof AbstractArrowEntity)
       {
         entity = ((AbstractArrowEntity)entity).func_212360_k();
       }
       if (entity instanceof LivingEntity) {
         for (Hand hand : Hand.values()) {
           ItemStack handItem = ((LivingEntity)entity).func_184586_b(hand);
           LivingEntity target = event.getEntityLiving();
           if (target != null &&            handItem.func_77973_b() instanceof KatanaDropListener && (
             (KatanaDropListener)handItem.func_77973_b()).canKatanaDrop((LivingEntity)entity, target, handItem, hand)) {
             ((KatanaDropListener)handItem.func_77973_b()).onKatanaDrop(target);
             break;
           }        }      }    }  
 
 
 
   static {
     addTable(EntityType.field_200725_aD, Items.field_151116_aA, 3, 0.2F);
     addTable(EntityType.field_200725_aD, Items.field_151103_aS, 3, 0.2F);
     addTable(EntityType.field_200725_aD, Items.field_196186_dz, 0.002F);
        addTable(EntityType.field_200741_ag, Items.field_196182_dv, 0.002F);
     addTable(EntityType.field_200741_ag, Items.field_196183_dw, 0.001F);
        addTable(EntityType.field_200797_k, Items.field_151016_H, 3, 0.2F);
     addTable(EntityType.field_200797_k, Items.field_221649_bM, 2, 0.01F);
     addTable(EntityType.field_200797_k, Items.field_196185_dy, 0.002F);
        addTable(EntityType.field_200748_an, Items.field_221672_ax, 0.1F);    
     addTable(EntityType.field_200811_y, Items.field_151059_bz, 3, 0.5F);
     addTable(EntityType.field_200811_y, Items.field_221649_bM, 4, 0.5F);
     addTable(EntityType.field_200811_y, Items.field_151114_aO, 8, 0.5F);
        addTable(EntityType.field_200803_q, Items.field_221620_aV, 4, 0.5F);
     addTable(EntityType.field_200803_q, Items.field_221619_aU, 4, 0.5F);
     addTable(EntityType.field_200803_q, Items.field_221692_bh, 4, 0.5F);
     addTable(EntityType.field_200803_q, Items.field_221694_bi, 4, 0.5F);
     addTable(EntityType.field_200803_q, Items.field_151061_bv, 3, 0.05F);
        addTable(EntityType.field_200740_af, Items.field_151121_aF, 4, 0.5F);    
     addTable(EntityType.field_200792_f, Items.field_151059_bz, 3, 0.1F);
     addTable(EntityType.field_200792_f, Items.field_151072_bj, 8, 0.2F);
        addTable(EntityType.field_200784_X, Items.field_151116_aA, 3, 0.8F);        addTable(EntityType.field_200737_ac, Items.field_151007_F, 4, 0.5F);
     
     addTable(EntityType.field_200791_e, Items.field_151034_e, 2, 0.5F);
     addTable(EntityType.field_200791_e, Items.field_151153_ao, 0.01F);
        addTable(EntityType.field_200759_ay, Items.field_151114_aO, 4, 0.5F);
     addTable(EntityType.field_200759_ay, Items.field_221816_dr, 4, 0.5F);
     addTable(EntityType.field_200759_ay, Items.field_151069_bo, 4, 0.5F);
        addTable(EntityType.field_200795_i, Items.field_151110_aK, 2, 0.25F);    
     addTable(EntityType.field_200724_aC, Items.field_151103_aS, 2, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_151082_bd, 4, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_151147_al, 3, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_151076_bf, 3, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_179558_bo, 3, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_151008_G, 4, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_179561_bm, 4, 0.5F);
     addTable(EntityType.field_200724_aC, Items.field_151116_aA, 3, 0.3F);
     addTable(EntityType.field_200724_aC, Items.field_179556_br, 0.1F);
        addTable(EntityType.field_200736_ab, Items.field_151116_aA, 0.1F);
     addTable(EntityType.field_200736_ab, Items.field_179556_br, 0.05F);
   }
 }

