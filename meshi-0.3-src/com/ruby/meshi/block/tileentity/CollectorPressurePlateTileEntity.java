 package com.ruby.meshi.block.tileentity;
import com.ruby.meshi.block.CollectionAndDeliveryBase;
 import com.ruby.meshi.common.inventory.CollectorPressurePlateContainer;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.List;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.state.IProperty;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
public class CollectorPressurePlateTileEntity
   extends LockableTileEntity {
   public static final byte INV_SIZE = 10;
   private NonNullList<ItemStack> chestContents;
    public CollectorPressurePlateTileEntity() {
     super(HiganTileEntityType.COLLECTOR_PLATE);
     this.chestContents = NonNullList.func_191197_a(10, ItemStack.field_190927_a);
   }

   public boolean isForce() {
     return ((Boolean)func_195044_w().func_177229_b((IProperty)CollectionAndDeliveryBase.FORCE)).booleanValue();
   }

   public void func_70296_d() {
     super.func_70296_d();
     func_145831_w().func_184138_a(func_174877_v(), func_195044_w(), func_195044_w(), 3);
   }

   public boolean func_94041_b(int index, ItemStack stack) {
     ItemStack copyStack = stack.func_77946_l();
     copyStack.func_190920_e(1);
     this.chestContents.set(index, copyStack);
     func_70296_d();
     return false;
   }

   public int func_70302_i_() {
     return 10;
   }

   public boolean func_191420_l() {
     for (ItemStack itemstack : this.chestContents) {
       if (!itemstack.func_190926_b()) {
         return false;
       }
     }       return true;
   }

   public ItemStack func_70301_a(int index) {
     return (ItemStack)this.chestContents.get(index);
   }

   public ItemStack func_70298_a(int index, int count) {
     return func_70304_b(index);
   }

   public ItemStack func_70304_b(int index) {
     ItemStack stack = ItemStackHelper.func_188383_a((List)this.chestContents, index);
     if (!stack.func_190926_b()) {
       func_70296_d();
     }
     return ItemStack.field_190927_a;
   }

   public void func_70299_a(int index, ItemStack stack) {
     this.chestContents.set(index, stack.func_77946_l());
     func_70296_d();
   }

   public boolean func_70300_a(PlayerEntity player) {
     if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
       return false;
     }
     return (player.func_70092_e(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D);
   }

   public void func_174888_l() {
     this.chestContents.clear();
   }

   protected ITextComponent func_213907_g() {
     return (ITextComponent)new TranslationTextComponent(String.join(".", new CharSequence[] { "container", "meshi", func_195044_w().func_177230_c().getRegistryName().func_110623_a() }), new Object[0]);
   }

   protected Container func_213906_a(int windowId, PlayerInventory inventory) {
     return new CollectorPressurePlateContainer(windowId, inventory, this);
   }
  public ItemStack getDisplayItem() {
     return (ItemStack)this.chestContents.get(0);
   }

   public void func_145839_a(CompoundNBT compound) {
     super.func_145839_a(compound);
     readContents(compound);
   }
  private void readContents(CompoundNBT compound) {
     this.chestContents = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
     ItemStackHelper.func_191283_b(compound, this.chestContents);
   }

   public CompoundNBT func_189515_b(CompoundNBT compound) {
     super.func_189515_b(compound);
     writeContents(compound);
     return compound;
   }
  private CompoundNBT writeContents(CompoundNBT compound) {
     ItemStackHelper.func_191282_a(compound, this.chestContents);
     return compound;
   }

 
   public void handleUpdateTag(CompoundNBT tag) {
     super.handleUpdateTag(tag);
     readContents(tag);
   }

   public CompoundNBT func_189517_E_() {
     CompoundNBT tag = super.func_189517_E_();
     writeContents(tag);
     return tag;
   }

   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
     readContents(pkt.func_148857_g());
   }

   public SUpdateTileEntityPacket func_189518_D_() {
     CompoundNBT var1 = new CompoundNBT();
     writeContents(var1);
     return new SUpdateTileEntityPacket(func_174877_v(), 0, var1);
   }
 }

