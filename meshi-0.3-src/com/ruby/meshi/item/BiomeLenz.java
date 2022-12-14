 package com.ruby.meshi.item;
import com.google.common.collect.Lists;
 import com.ruby.meshi.block.tileentity.ManekiNekoTileEntity;
 import java.util.LinkedList;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.entity.player.ClientPlayerEntity;
 import net.minecraft.client.world.ClientWorld;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity;
 import net.minecraft.entity.projectile.ThrowableEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemUseContext;
 import net.minecraft.util.ActionResult;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.RayTraceContext;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.client.event.InputEvent;
 import net.minecraftforge.client.event.RenderGameOverlayEvent;
 import net.minecraftforge.event.TickEvent;
 import net.minecraftforge.event.entity.living.LivingDamageEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
 public class BiomeLenz
   extends Item implements Accessory {
   private static Vec3d vec;
   private static DPSBukket damageBukket = new DPSBukket();
  public BiomeLenz(Item.Properties properties) {
     super(properties);
   }

   public ActionResultType func_195939_a(ItemUseContext context) {
     return super.func_195939_a(context);
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
     return super.func_77659_a(worldIn, playerIn, handIn);
   }

   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
     super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
   }
  @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void renderText(RenderGameOverlayEvent.Text event) {
     ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
     if (clientPlayerEntity != null) {
       World world = ((PlayerEntity)clientPlayerEntity).field_70170_p;
       if (0 < ((PlayerEntity)clientPlayerEntity).field_71071_by.func_213901_a(HiganItems.BIOME_LENZ)) {
         int hour = (int)((world.func_72912_H().func_76073_f() + 6000L) % 24000L / 1000L);
         int minute = (int)((world.func_72912_H().func_76073_f() + 6000L) % 600L / 10L);
         event.getLeft().add("Time: " + String.format("%02d:%02d", new Object[] { Integer.valueOf(hour), Integer.valueOf(minute) }) + (world.func_72912_H().func_76059_o() ? " Rain" : "") + (world.func_72912_H().func_76061_m() ? " Thunder" : ""));
         event.getLeft().add("NPos: " + getFormatedPos(clientPlayerEntity.func_180425_c()) + "(" + Direction.func_176733_a((clientPlayerEntity.func_189653_aC()).field_189983_j) + ")");
         event.getLeft().add("SPos: " + getFormatedPos(clientPlayerEntity.getBedLocation(((PlayerEntity)clientPlayerEntity).field_71093_bK)));
         if (vec != null) {
           Vec3d subVec = roundDown(func_219968_a(world, (PlayerEntity)clientPlayerEntity, RayTraceContext.FluidMode.NONE).func_216347_e()).func_178788_d(vec);
           event.getLeft().add("RCPos: " + getFormatedPos(new BlockPos(subVec)));
         }        event.getLeft().add("Biome: " + ((PlayerEntity)clientPlayerEntity).field_70170_p.func_180494_b(clientPlayerEntity.func_180425_c()).func_205403_k().func_150254_d());
         event.getLeft().add("DPM: " + String.format("%.1f", new Object[] { Double.valueOf(damageBukket.getDPM()) }) + "(" + String.format("%.1f", new Object[] { Double.valueOf(damageBukket.getDPS()) }) + ")");
         if (ManekiNekoTileEntity.isEntityDeny(clientPlayerEntity.func_180425_c())) {
           event.getLeft().add("No mob spawn area");
         }
       }    }  }
   private static Vec3d roundDown(Vec3d vec) {
     return new Vec3d((int)vec.func_82615_a(), (int)vec.func_82617_b(), (int)vec.func_82616_c());
   }
  @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void rightClick(InputEvent.MouseInputEvent event) {
     if (event.getButton() == 1) {
       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
       if (clientPlayerEntity != null) {
         World world = ((PlayerEntity)clientPlayerEntity).field_70170_p;
         if (0 < ((PlayerEntity)clientPlayerEntity).field_71071_by.func_213901_a(HiganItems.BIOME_LENZ)) {
           vec = roundDown(func_219968_a(world, (PlayerEntity)clientPlayerEntity, RayTraceContext.FluidMode.NONE).func_216347_e());
         }
       }    }  }
   private static String getFormatedPos(BlockPos pos) {
     if (pos == null) {
       return "";
     }
     return pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p();
   }
  @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void onLivingDamage(LivingDamageEvent event) {
     LivingEntity livingEntity;
     Entity attacker = event.getSource().func_76364_f();
     if (attacker instanceof AbstractArrowEntity) {
       attacker = ((AbstractArrowEntity)attacker).func_212360_k();
     }
     if (attacker instanceof ThrowableEntity) {
       livingEntity = ((ThrowableEntity)attacker).func_85052_h();
     }
     if (livingEntity instanceof PlayerEntity &&      livingEntity.func_110124_au() == (Minecraft.func_71410_x()).field_71439_g.func_110124_au()) {
       damageBukket.sum(event.getAmount());
     }
   }

   @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void onTick(TickEvent.ClientTickEvent event) {
     if (event.phase == TickEvent.Phase.END) {
       ClientWorld clientWorld = (Minecraft.func_71410_x()).field_71441_e;
       if (clientWorld != null && clientWorld.func_72912_H().func_82573_f() % 20L == 0L)
         damageBukket.nextBukket();    }  }
   private static class DPSBukket
   {
     private LinkedList<Double> bukket = Lists.newLinkedList();
     private int maxSize = 60;
    public void nextBukket() {
       if (this.bukket.size() >= this.maxSize) {
         this.bukket.remove(0);
       }
       this.bukket.add(Double.valueOf(0.0D));
     }
    public void sum(double e) {
       int index = this.bukket.size() - 1;
       this.bukket.set(index, Double.valueOf(((Double)this.bukket.get(index)).floatValue() + e));
     }
    public double getDPM() {
       return this.bukket.stream().mapToDouble(x -> x.doubleValue()).sum() / 60.0D;
     }
    public double getDPS() {
       if (this.bukket.isEmpty()) {
         nextBukket();
       }
       return ((Double)this.bukket.getLast()).doubleValue();
     }
        private DPSBukket() {}
   }
 }

