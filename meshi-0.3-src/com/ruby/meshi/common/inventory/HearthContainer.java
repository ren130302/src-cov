 package com.ruby.meshi.common.inventory;
import com.google.common.collect.Lists;
 import com.ruby.meshi.block.tileentity.HearthTileEntity;
 import com.ruby.meshi.client.gui.recipebook.HiganRecipeBookCategories;
 import com.ruby.meshi.init.HiganContainerType;
 import java.util.List;
 import java.util.function.Supplier;
 import net.minecraft.client.util.RecipeBookCategories;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.IRecipeHelperPopulator;
 import net.minecraft.inventory.Inventory;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.FurnaceResultSlot;
 import net.minecraft.inventory.container.RecipeBookContainer;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.item.crafting.ServerRecipePlacer;
 import net.minecraft.util.IIntArray;
 import net.minecraft.util.IntArray;
 import net.minecraft.world.World;
public class HearthContainer
   extends RecipeBookContainer<IInventory>
 {
   public static final Supplier<List<RecipeBookCategories>> CATEGORIES = () -> Lists.newArrayList((Object[])new RecipeBookCategories[] { HiganRecipeBookCategories.HEARTH_SEARCH, HiganRecipeBookCategories.HEARTH, RecipeBookCategories.FURNACE_BLOCKS, RecipeBookCategories.FURNACE_FOOD, RecipeBookCategories.FURNACE_MISC });
   private IInventory inventory;
   private IntArray tracker;
   private HearthTileEntity tile;
   private World world;
    public HearthContainer(int windowId, PlayerInventory playerInventory) {
     this(HiganContainerType.HEARTH, windowId, playerInventory, (IInventory)new Inventory(10));
   }
  public HearthContainer(int windowId, PlayerInventory playerInventory, HearthTileEntity inventory, IntArray progress) {
     this(HiganContainerType.HEARTH, windowId, playerInventory, (IInventory)inventory);
     this.tile = inventory;
   }
  public HearthContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory) {
     super(type, id);
     this.inventory = inventory;
     inventory.func_174889_b(playerInventory.field_70458_d);
     this.tracker = new IntArray(2);
     func_216961_a((IIntArray)this.tracker);
     this.world = playerInventory.field_70458_d.field_70170_p;
     func_216959_a((IIntArray)this.tracker, 2);
        func_75146_a((Slot)new FurnaceResultSlot(playerInventory.field_70458_d, this.inventory, 0, 124, 35));    
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         func_75146_a(new Slot(this.inventory, 1 + j + i * 3, 30 + j * 18, 17 + i * 18));
       }
     }       for (int k = 0; k < 3; k++) {
       for (int i1 = 0; i1 < 9; i1++) {
         func_75146_a(new Slot((IInventory)playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
       }
     }       for (int l = 0; l < 9; l++) {
       func_75146_a(new Slot((IInventory)playerInventory, l, 8 + l * 18, 142));
     }
   }

   protected HearthContainer(ContainerType<?> type, int id) {
     super(type, id);
   }

   public void func_75134_a(PlayerEntity playerIn) {
     super.func_75134_a(playerIn);
     this.inventory.func_174886_c(playerIn);
   }

   public boolean func_75145_c(PlayerEntity playerIn) {
     return this.inventory.func_70300_a(playerIn);
   }

   public void func_75142_b() {
     super.func_75142_b();
     if (this.tile != null) {
       this.tracker.func_221477_a(0, this.tile.getProgress());
       this.tracker.func_221477_a(1, this.tile.getCookTime());
     }  }
   public int getProgress() {
     return this.tracker.func_221476_a(0);
   }
  public int getCooktime() {
     return this.tracker.func_221476_a(1);
   }

   public ItemStack func_82846_b(PlayerEntity playerIn, int index) {
     ItemStack itemstack = ItemStack.field_190927_a;
     Slot slot = this.field_75151_b.get(index);
     if (slot != null && slot.func_75216_d()) {
       ItemStack itemstack1 = slot.func_75211_c();
       itemstack = itemstack1.func_77946_l();
       if (index == 0) {
         if (!func_75135_a(itemstack1, 10, 46, true)) {
           return ItemStack.field_190927_a;
         }
                slot.func_75220_a(itemstack1, itemstack);
       } else if (index >= 10 && index < 37) {
         if (!func_75135_a(itemstack1, 37, 46, false)) {
           return ItemStack.field_190927_a;
         }
       } else if (index >= 37 && index < 46) {
         if (!func_75135_a(itemstack1, 10, 37, false)) {
           return ItemStack.field_190927_a;
         }
       } else if (!func_75135_a(itemstack1, 10, 46, false)) {
         return ItemStack.field_190927_a;
       }           if (itemstack1.func_190926_b()) {
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

   public void func_201771_a(RecipeItemHelper helper) {
     if (this.inventory instanceof IRecipeHelperPopulator) {
       ((IRecipeHelperPopulator)this.inventory).func_194018_a(helper);
     }
   }

   public void func_201768_e() {
     this.inventory.func_174888_l();
   }

   public void func_217056_a(boolean p_217056_1_, IRecipe<?> recipe, ServerPlayerEntity player) {
     (new ServerRecipePlacer(this)).func_194327_a(player, recipe, p_217056_1_);
   }

   public boolean func_201769_a(IRecipe<? super IInventory> recipeIn) {
     return recipeIn.func_77569_a(this.inventory, this.world);
   }

   public int func_201767_f() {
     return 0;
   }

   public int func_201770_g() {
     return 3;
   }

   public int func_201772_h() {
     return 3;
   }

   public int func_203721_h() {
     return 10;
   }

   public List<RecipeBookCategories> getRecipeBookCategories() {
     return CATEGORIES.get();
   }
 }

