 package com.ruby.meshi.entity;
import com.ruby.meshi.item.Accessory;
 import com.ruby.meshi.util.CapabilityExtendInventory;
 import net.minecraft.item.ItemStack;
 import net.minecraftforge.event.TickEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class PlayerTickHandler
 {
   @SubscribeEvent
   public static void playerTickPost(TickEvent.PlayerTickEvent event) {
     if (event.phase == TickEvent.Phase.END)
       (CapabilityExtendInventory.getInventory(event.player)).accessoryInventory.forEach(s -> {
             if (s.func_77973_b() instanceof Accessory)
               ((Accessory)s.func_77973_b()).playerPostTick(event.player.func_130014_f_(), event.player, s);          });  }
 }

