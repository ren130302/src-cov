 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import java.util.List;
 import java.util.Set;
 import java.util.function.BiPredicate;
 import java.util.function.IntPredicate;
 import java.util.function.Predicate;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.ChestBlock;
 import net.minecraft.block.PressurePlateBlock;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.container.INamedContainerProvider;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.stats.Stats;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
public abstract class CollectionAndDeliveryBase
   extends PressurePlateBlock
 {
   public static final BooleanProperty FORCE = BlockStateProperties.field_208180_g;

   public CollectionAndDeliveryBase(Block.Properties properties, int horizon, int vertical) {
     super(PressurePlateBlock.Sensitivity.EVERYTHING, properties);
     this.horizonSize = horizon;
     this.verticalSize = vertical;
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)FORCE, Boolean.valueOf(false)));
   }

   public BlockState func_196258_a(BlockItemUseContext context) {
     return (BlockState)super.func_196258_a(context).func_206870_a((IProperty)FORCE, Boolean.valueOf(context.func_195998_g()));
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)FORCE });
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (worldIn.field_72995_K) {
       return true;
     }
     INamedContainerProvider inamedcontainerprovider = func_220052_b(state, worldIn, pos);
     if (inamedcontainerprovider != null) {
       player.func_213829_a(inamedcontainerprovider);
       player.func_71029_a(Stats.field_199092_j.func_199076_b(Stats.field_188063_ac));
     }       return true;
   }

   public INamedContainerProvider func_220052_b(BlockState state, World world, BlockPos pos) {
     TileEntity tileentity = world.func_175625_s(pos);
     return (tileentity instanceof INamedContainerProvider) ? (INamedContainerProvider)tileentity : null;
   }

   public boolean func_189539_a(BlockState state, World worldIn, BlockPos pos, int id, int param) {
     super.func_189539_a(state, worldIn, pos, id, param);
     TileEntity tileentity = worldIn.func_175625_s(pos);
     return (tileentity == null) ? false : tileentity.func_145842_c(id, param);
   }

 
 
 
 
 
   public Set<IInventory> getSurroundingIInventory(World world, BlockPos pos, int horizonSize, int verticalSize) {
     return (Set<IInventory>)BlockPos.func_218281_b(pos.func_177982_a(-horizonSize, 0, -horizonSize), pos.func_177982_a(horizonSize, verticalSize, horizonSize))
       .map(p -> (world.func_180495_p(p).func_177230_c() instanceof ChestBlock) ? ChestBlock.func_220105_a(world.func_180495_p(p), world, p, false) : world.func_175625_s(p))
       .filter(e -> (e instanceof IInventory && !(e instanceof CollectorPressurePlateTileEntity)))
       .map(IInventory.class::cast)
       .collect(Collectors.toSet());
   }
  public List<ItemStack> getSlotList(IInventory inv) {
     return (List<ItemStack>)IntStream.range(0, inv.func_70302_i_())
       .mapToObj(inv::func_70301_a)
       .collect(Collectors.toList());
   }

   public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
     if (!worldIn.field_72995_K && entityIn instanceof PlayerEntity) {
       int i = func_176576_e(state);
       if (i == 0) {
         onPressedSwitch(state, worldIn, pos, (PlayerEntity)entityIn);
       }
     }    super.func_196262_a(state, worldIn, pos, entityIn);
   }

 
 
 
 
 
   public List<InventoryEntry> createInventoryEntrys(IInventory targetInventory, List<ItemStack> targetList, Predicate<ItemStack> stackTester, BiPredicate<Integer, ItemStack> slotTester) {
     InventoryEntry.Builder entryBuilder = InventoryEntry.build(targetInventory);
     return (List<InventoryEntry>)IntStream.range(0, targetList.size())
       .mapToObj(slotId -> {
           ItemStack stack = targetList.get(slotId);
           return (stackTester.test(stack) && slotTester.test(Integer.valueOf(slotId), stack)) ? entryBuilder.create(slotId) : null;
                }).filter(e -> (e != null))
       .collect(Collectors.toList());
   }

   public void searchAndInsert(InventoryEntry fromEntry, InventoryEntry toEntry, boolean isEmptyInsert) {
     ItemStack fromStack = fromEntry.getStack();
     int slot = toEntry.getSlot();
        for (int i = 0; i < toEntry.getInventory().func_70302_i_(); i++) {
       if (toEntry.insertStack(fromStack, slot)) {
                fromEntry.getInventory().func_70296_d();
         toEntry.getInventory().func_70296_d();
       }      if (fromStack.func_190926_b()) {
         fromEntry.setStack(ItemStack.field_190927_a);
         break;
       }      int nextSlot = toEntry.getNextStackableSlot(fromStack, slot);
       if (nextSlot >= 0) {
         slot = nextSlot;
       }
       else if (isEmptyInsert) {
                nextSlot = toEntry.getNextEmptySlot(slot);
         if (nextSlot >= 0) {
           slot = nextSlot;
         } else {
           break;
         }      } else {
         break;
       }    }  }
 
   public static class InventoryEntry
     implements Comparable<InventoryEntry>
   {
     private final IInventory inventory;
        private final int slot;    
     private InventoryEntry(IInventory inventory, int slot) {
       this.inventory = inventory;
       this.slot = slot;
     }
    public IInventory getInventory() {
       return this.inventory;
     }
    public int getSlot() {
       return this.slot;
     }
    public ItemStack getStack() {
       return getInventory().func_70301_a(this.slot);
     }
    public void setStack(ItemStack stack) {
       getInventory().func_70299_a(this.slot, stack);
     }
    public Item getItem() {
       return getStack().func_77973_b();
     }
    public boolean isItemEqual(InventoryEntry entry) {
       return getStack().func_77969_a(entry.getStack());
     }
    public boolean insertStack(ItemStack stack, int slot) {
       if (stack.func_190926_b()) {
         return false;
       }
       ItemStack inventoryStack = getInventory().func_70301_a(slot);
            if (inventoryStack.func_190926_b()) {
         getInventory().func_70299_a(slot, stack.func_77979_a(getInventory().func_70297_j_()));
         return true;
       }           if (stack.func_77969_a(inventoryStack) && stack.func_77985_e()) {        
         int insertCount = getInventory().func_70297_j_() - inventoryStack.func_190916_E();
         if (insertCount > 0) {
                    inventoryStack.func_190920_e(inventoryStack.func_190916_E() + stack.func_77979_a(insertCount).func_190916_E());
           return true;
         }      }           return false;
     }

 
 
     public int getNextStackableSlot(ItemStack stack, int baseSlot) {
       return getNextSlot(baseSlot, slot -> {
             ItemStack inventoryStack = getInventory().func_70301_a(slot);
             return (stack.func_77969_a(inventoryStack) && getInventory().func_70297_j_() - inventoryStack.func_190916_E() > 0);
           });
     }

 
 
     public int getNextEmptySlot(int baseSlot) {
       return getNextSlot(baseSlot, slot -> getInventory().func_70301_a(slot).func_190926_b());
     }

     private int getNextSlot(int slot, IntPredicate tester) {
       return IntStream.iterate(slot, e -> (e < getInventory().func_70302_i_() - 1) ? (e + 1) : 0)
         .limit(getInventory().func_70302_i_())
         .filter(tester)
         .findFirst()
         .orElse(-1);
     }

     public int hashCode() {
       int prime = 31;
       int result = 1;
       result = 31 * result + ((this.inventory == null) ? 0 : this.inventory.hashCode());
       result = 31 * result + ((getItem() == null) ? 0 : getItem().hashCode());
       return result;
     }

     public boolean equals(Object obj) {
       if (this == obj)
         return true;      if (obj == null)        return false;       if (getClass() != obj.getClass())
         return false; 
       InventoryEntry other = (InventoryEntry)obj;
       if (this.inventory == null) {
         if (other.inventory != null)
           return false;      } else if (!this.inventory.equals(other.inventory)) {
         return false;
       }  if (getItem() == null) {
         if (other.getItem() != null)
           return false;      } else if (!getItem().equals(other.getItem())) {
         return false;
       }  return true;
     }

     public String toString() {
       return String.format("inv %s:%s , stack : %s", new Object[] { this.inventory.getClass().getSimpleName(), Integer.valueOf(getSlot()), getStack() });
     }

     public int compareTo(InventoryEntry o) {
       return this.slot - o.slot;
     }
    public static Builder build(IInventory factory) {
       return new Builder(factory);
     }
    public static class Builder
     {
       private IInventory inventory;
            private Builder(IInventory inventory) {
         this.inventory = inventory;
       }
      public CollectionAndDeliveryBase.InventoryEntry create(int slot) {
         return new CollectionAndDeliveryBase.InventoryEntry(this.inventory, slot); } } } public static class Builder { public CollectionAndDeliveryBase.InventoryEntry create(int slot) { return new CollectionAndDeliveryBase.InventoryEntry(this.inventory, slot); }        return new CollectionAndDeliveryBase.InventoryEntry(this.inventory, slot); } } } public static class Builder { public CollectionAndDeliveryBase.InventoryEntry create(int slot) { return new CollectionAndDeliveryBase.InventoryEntry(this.inventory, slot); }
        private IInventory inventory;
     private Builder(IInventory inventory) {
       this.inventory = inventory;
     } }
   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return (TileEntity)new CollectorPressurePlateTileEntity();
   }
    public abstract void onPressedSwitch(BlockState paramBlockState, World paramWorld, BlockPos paramBlockPos, PlayerEntity paramPlayerEntity);
 }

