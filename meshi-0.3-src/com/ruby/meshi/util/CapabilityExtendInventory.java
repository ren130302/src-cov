 package com.ruby.meshi.util;
import com.google.common.collect.ImmutableList;
 import java.util.List;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.util.Direction;
 import net.minecraft.util.NonNullList;
 import net.minecraftforge.common.capabilities.Capability;
 import net.minecraftforge.common.capabilities.CapabilityInject;
 import net.minecraftforge.common.capabilities.CapabilityManager;
 import net.minecraftforge.event.entity.player.PlayerEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class CapabilityExtendInventory
 {
   @CapabilityInject(ExtendInventory.class)
   public static Capability<ExtendInventory> EXTEND_INVENTORY = null;
  public void register() {
     CapabilityManager.INSTANCE.register(ExtendInventory.class, new Capability.IStorage<ExtendInventory>()
         {
           public INBT writeNBT(Capability<CapabilityExtendInventory.ExtendInventory> capability, CapabilityExtendInventory.ExtendInventory instance, Direction side) {
             ListNBT nbtTagList = new ListNBT();
             int size = instance.func_70302_i_();
             for (int i = 0; i < size; i++) {
               ItemStack stack = instance.func_70301_a(i);
               if (!stack.func_190926_b()) {
                 CompoundNBT itemTag = new CompoundNBT();
                 itemTag.func_74768_a("Slot", i);
                 stack.func_77955_b(itemTag);
                 nbtTagList.add(itemTag);
               }            }            return (INBT)nbtTagList;
           }
                   public void readNBT(Capability<CapabilityExtendInventory.ExtendInventory> capability, CapabilityExtendInventory.ExtendInventory instance, Direction side, INBT base) {
             ListNBT tagList = (ListNBT)base;
             for (int i = 0; i < tagList.size(); i++) {
               CompoundNBT itemTags = tagList.func_150305_b(i);
               int j = itemTags.func_74762_e("Slot");
                            if (j >= 0 && j < instance.func_70302_i_()) {
                 instance.func_70299_a(j, ItemStack.func_199557_a(itemTags));
               }
             }          }
         }() -> new ExtendInventory());
   }
  @SubscribeEvent
   public static void playerClone(PlayerEvent.Clone event) {
     if (event.isWasDeath()) {
       INBT nbt = EXTEND_INVENTORY.writeNBT(getInventory(event.getOriginal()), null);
       EXTEND_INVENTORY.readNBT(getInventory(event.getPlayer()), null, nbt);
     }  }
   @SubscribeEvent
   public static void palyerJoin(PlayerEvent.PlayerLoggedInEvent event) {
     if (!(event.getEntity().func_130014_f_()).field_72995_K &&      event.getEntity() instanceof PlayerEntity) {
       NetworkHandler.sendExtendInvCap((ServerPlayerEntity)event.getEntity());
     }
   }

   public static ExtendInventory getInventory(PlayerEntity player) {
     return (ExtendInventory)player.getCapability(EXTEND_INVENTORY).orElse(EXTEND_INVENTORY.getDefaultInstance());
   }

 
 
 
 
   public static class ExtendInventory
     implements IInventory
   {
     public final NonNullList<ItemStack> accessoryInventory = NonNullList.func_191197_a(4, ItemStack.field_190927_a);
     public final NonNullList<ItemStack> etcInventory = NonNullList.func_191197_a(1, ItemStack.field_190927_a);
     private final List<NonNullList<ItemStack>> allInventories = (List<NonNullList<ItemStack>>)ImmutableList.of(this.accessoryInventory, this.etcInventory);
    public ItemStack func_70301_a(int index) {
       NonNullList<ItemStack> nonNullList;
       List<ItemStack> list = null;
            for (NonNullList<ItemStack> nonnulllist : this.allInventories) {
         if (index < nonnulllist.size()) {
           nonNullList = nonnulllist;
                    break;        }            } 
       
       return (nonNullList == null) ? ItemStack.field_190927_a : nonNullList.get(index);
     }

     public void func_174888_l() {
       this.accessoryInventory.clear();
       this.etcInventory.clear();
     }

     public int func_70302_i_() {
       return this.accessoryInventory.size() + this.etcInventory.size();
     }

     public boolean func_191420_l() {
       for (ItemStack itemstack1 : this.accessoryInventory) {
         if (!itemstack1.func_190926_b()) {
           return false;
         }
       }           for (ItemStack itemstack2 : this.etcInventory) {
         if (!itemstack2.func_190926_b()) {
           return false;
         }
       }           return true;
     }
    public ItemStack func_70298_a(int index, int count) {
       NonNullList<ItemStack> nonNullList;
       List<ItemStack> list = null;
            for (NonNullList<ItemStack> nonnulllist : this.allInventories) {
         if (index < nonnulllist.size()) {
           nonNullList = nonnulllist;
                    break;        }            } 
       
       return (nonNullList != null && !((ItemStack)nonNullList.get(index)).func_190926_b()) ? ItemStackHelper.func_188382_a((List)nonNullList, index, count) : ItemStack.field_190927_a;
     }

     public ItemStack func_70304_b(int index) {
       NonNullList<ItemStack> nonnulllist = null;
            for (NonNullList<ItemStack> nonnulllist1 : this.allInventories) {
         if (index < nonnulllist1.size()) {
           nonnulllist = nonnulllist1;
                    break;        }            } 
       
       if (nonnulllist != null && !((ItemStack)nonnulllist.get(index)).func_190926_b()) {
         ItemStack itemstack = (ItemStack)nonnulllist.get(index);
         nonnulllist.set(index, ItemStack.field_190927_a);
         return itemstack;
       }      return ItemStack.field_190927_a;
     }

     public void func_70299_a(int index, ItemStack stack) {
       NonNullList<ItemStack> nonnulllist = null;
            for (NonNullList<ItemStack> nonnulllist1 : this.allInventories) {
         if (index < nonnulllist1.size()) {
           nonnulllist = nonnulllist1;
                    break;        }            } 
       
       if (nonnulllist != null) {
         nonnulllist.set(index, stack);
       }
     }

 
 
     public boolean func_70300_a(PlayerEntity player) {
       return player.func_70089_S();
     }
   }
 }

