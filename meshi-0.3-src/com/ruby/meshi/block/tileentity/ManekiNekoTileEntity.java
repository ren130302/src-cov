 package com.ruby.meshi.block.tileentity;
import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.WeakHashMap;
 import java.util.function.Predicate;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.math.BlockPos;
 import net.minecraftforge.event.entity.living.LivingSpawnEvent;
 import net.minecraftforge.eventbus.api.Event;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class ManekiNekoTileEntity
   extends TileEntity
 {
   static final WeakHashMap<TileEntity, Predicate<BlockPos>> map = new WeakHashMap<>();
   int range = 4;
  public ManekiNekoTileEntity() {
     super(HiganTileEntityType.MANEKINEKO);
   }
  public static boolean isEntityDeny(BlockPos pos) {
     return map.values().stream().anyMatch(p -> p.test(pos));
   }

   public void onLoad() {
     super.onLoad();
     map.put(this, pos -> (Math.abs(func_174877_v().func_177958_n() - pos.func_177958_n() >> 4) <= this.range && Math.abs(func_174877_v().func_177952_p() - pos.func_177952_p() >> 4) <= this.range));
   }

   public void onChunkUnloaded() {
     super.onChunkUnloaded();
     map.remove(this);
   }

   public void func_145843_s() {
     super.func_145843_s();
     map.remove(this);
   }
  @SubscribeEvent
   public static void entitySpawn(LivingSpawnEvent.CheckSpawn event) {
     if (!event.isSpawner() &&      event.getEntity() instanceof net.minecraft.entity.monster.IMob) {
       BlockPos entityPos = event.getEntity().func_180425_c();
       if (isEntityDeny(entityPos))
         event.setResult(Event.Result.DENY);    }  }
 }

