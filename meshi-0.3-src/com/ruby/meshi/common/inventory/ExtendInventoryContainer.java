 package com.ruby.meshi.common.inventory;
import com.ruby.meshi.init.HiganContainerType;
 import com.ruby.meshi.util.CapabilityExtendInventory;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
 import net.minecraft.network.PacketBuffer;
 import net.minecraftforge.common.util.LazyOptional;
 import net.minecraftforge.fml.network.IContainerFactory;

 public class ExtendInventoryContainer
   extends Container
   implements IContainerFactory<ExtendInventoryContainer>
 {
   PlayerEntity player;
    public ExtendInventoryContainer(int windowId, PlayerInventory playerInventory) {
     this(HiganContainerType.EXTEND_INVENTORY, windowId, playerInventory);
   }
  public ExtendInventoryContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
     super(type, id);
     this.player = playerInventory.field_70458_d;
     LazyOptional<CapabilityExtendInventory.ExtendInventory> opt = this.player.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY);
     if (opt.isPresent()) {
       CapabilityExtendInventory.ExtendInventory inv = (CapabilityExtendInventory.ExtendInventory)opt.orElseGet(() -> (CapabilityExtendInventory.ExtendInventory)CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance());
       for (int k = 0; k < 4; k++) {
                func_75146_a(new Slot(inv, k, 8, 8 + k * 18)
             {
               public int func_75219_a() {
                 return 1;
               }
                           public boolean func_75214_a(ItemStack stack) {
                 return stack.func_77973_b() instanceof com.ruby.meshi.item.Accessory;
               }
                           public boolean func_111238_b() {
                 return (this.field_75222_d == 0);
               }
             });
       }    }    for (int l = 0; l < 3; l++) {
       for (int j1 = 0; j1 < 9; j1++) {
         func_75146_a(new Slot((IInventory)playerInventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
       }
     }       for (int i1 = 0; i1 < 9; i1++) {
       func_75146_a(new Slot((IInventory)playerInventory, i1, 8 + i1 * 18, 142));
     }
   }

   public boolean func_75145_c(PlayerEntity playerIn) {
     return playerIn.func_70089_S();
   }

   public ExtendInventoryContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
     return new ExtendInventoryContainer(windowId, inv);
   }
 }

