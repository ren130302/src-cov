 package com.ruby.meshi.util;
import net.minecraft.entity.Entity;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.util.Direction;
 import net.minecraft.util.ResourceLocation;
 import net.minecraftforge.common.capabilities.Capability;
 import net.minecraftforge.common.capabilities.ICapabilityProvider;
 import net.minecraftforge.common.capabilities.ICapabilitySerializable;
 import net.minecraftforge.common.util.LazyOptional;
 import net.minecraftforge.event.AttachCapabilitiesEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class CapabilityHandler
 {
   public static final ResourceLocation EXTEND_INVENTORY = new ResourceLocation("meshi", "extend_inv");
  @SubscribeEvent
   public static void attach(AttachCapabilitiesEvent<Entity> event) {
     if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity)
       event.addCapability(EXTEND_INVENTORY, (ICapabilityProvider)new CapExtendInvProvider());  }
   static class CapExtendInvProvider
     implements ICapabilitySerializable<ListNBT>
   {
     CapabilityExtendInventory.ExtendInventory inv = (CapabilityExtendInventory.ExtendInventory)CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance();
     LazyOptional<CapabilityExtendInventory.ExtendInventory> opt = LazyOptional.of(() -> this.inv);

     public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
       if (cap == CapabilityExtendInventory.EXTEND_INVENTORY)
       {
                return this.opt.cast();
       }
       return LazyOptional.empty();
     }

     public ListNBT serializeNBT() {
       ListNBT list = new ListNBT();
            for (int j = 0; j < this.inv.accessoryInventory.size(); j++) {
         if (!((ItemStack)this.inv.accessoryInventory.get(j)).func_190926_b()) {
           CompoundNBT compoundnbt1 = new CompoundNBT();
           compoundnbt1.func_74774_a("Slot", (byte)j);
           ((ItemStack)this.inv.accessoryInventory.get(j)).func_77955_b(compoundnbt1);
           list.add(compoundnbt1);
         }      }           for (int k = 0; k < this.inv.etcInventory.size(); k++) {
         if (!((ItemStack)this.inv.etcInventory.get(k)).func_190926_b()) {
           CompoundNBT compoundnbt2 = new CompoundNBT();
           compoundnbt2.func_74774_a("Slot", (byte)(k + 50));
           ((ItemStack)this.inv.etcInventory.get(k)).func_77955_b(compoundnbt2);
           list.add(compoundnbt2);
         }      }           return list;
     }

     public void deserializeNBT(ListNBT nbt) {
       this.inv.accessoryInventory.clear();
       this.inv.etcInventory.clear();
            for (int i = 0; i < nbt.size(); i++) {
         CompoundNBT compoundnbt = nbt.func_150305_b(i);
         int j = compoundnbt.func_74771_c("Slot") & 0xFF;
         ItemStack itemstack = ItemStack.func_199557_a(compoundnbt);
         if (!itemstack.func_190926_b())
           if (j >= 0 && j < this.inv.accessoryInventory.size() + 50) {
             this.inv.accessoryInventory.set(j, itemstack);
           } else if (j >= 50 && j < this.inv.etcInventory.size() + 100) {
             this.inv.etcInventory.set(j - 50, itemstack);
           }       }    }
   }
 }

