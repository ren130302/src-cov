 package com.ruby.meshi.common.inventory;
import com.ruby.meshi.init.HiganContainerType;
 import com.ruby.meshi.item.Fukuro;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.Inventory;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
 import net.minecraft.world.World;

 public class FukuroContainer
   extends Container
 {
   private IInventory inventory;
   private World world;
    public FukuroContainer(int windowId, PlayerInventory playerInventory) {
     this(HiganContainerType.FUKURO, windowId, playerInventory, (IInventory)new Inventory(1));
   }
  public FukuroContainer(int windowId, PlayerInventory playerInventory, Fukuro.FukuroInventory inventory) {
     this(HiganContainerType.FUKURO, windowId, playerInventory, inventory);
   }
  public FukuroContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory) {
     super(type, id);
     this.inventory = inventory;
     inventory.func_174889_b(playerInventory.field_70458_d);
     this.world = playerInventory.field_70458_d.field_70170_p;
        func_75146_a(new FukuroSlot(this.inventory, 0, 80, 33));    
     for (int k = 0; k < 3; k++) {
       for (int i1 = 0; i1 < 9; i1++) {
         func_75146_a(new Slot((IInventory)playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
       }
     }       for (int l = 0; l < 9; l++) {
       func_75146_a(new Slot((IInventory)playerInventory, l, 8 + l * 18, 142));
     }
   }

   public boolean func_75145_c(PlayerEntity playerIn) {
     return this.inventory.func_70300_a(playerIn);
   }

   public void func_75134_a(PlayerEntity playerIn) {
     super.func_75134_a(playerIn);
     playerIn.func_184602_cy();
   }

   public boolean func_94530_a(ItemStack stack, Slot slotIn) {
     return true;
   }

   public ItemStack func_82846_b(PlayerEntity playerIn, int index) {
     ItemStack itemstack = ItemStack.field_190927_a;
     Slot slot = this.field_75151_b.get(index);
     if (slot != null && slot.func_75216_d()) {
       ItemStack itemstack1 = slot.func_75211_c();
       itemstack = itemstack1.func_77946_l();
       if (index != 0)
       {
        
         if (index >= 1 && index < 27) {
           if (!func_75135_a(itemstack1, 27, 36, false)) {
             return ItemStack.field_190927_a;
           }
         } else if (index >= 27 && index < 36) {
           if (!func_75135_a(itemstack1, 1, 27, false)) {
             return ItemStack.field_190927_a;
           }
         } else if (!func_75135_a(itemstack1, 1, 36, false)) {
           return ItemStack.field_190927_a;
         }      }
       if (itemstack1.func_190926_b()) {
         slot.func_75215_d(ItemStack.field_190927_a);
       } else {
         slot.func_75218_e();
       }           if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
         return ItemStack.field_190927_a;
       }
            ItemStack itemstack2 = slot.func_190901_a(playerIn, itemstack1);
       if (index == 0) {
         playerIn.func_71019_a(itemstack2, false);
       }
     }       return itemstack;
   }
  static class FukuroSlot
     extends Slot {
     public FukuroSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
       super(inventoryIn, index, xPosition, yPosition);
     }

     public boolean func_75214_a(ItemStack stack) {
       return Fukuro.isItemValid(stack);
     }
   }
 }

