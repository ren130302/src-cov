 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.IProperty;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
public class DeliveryPressurePlate extends CollectionAndDeliveryBase {
   public DeliveryPressurePlate(Block.Properties properties, int horizon, int vertical) {
     super(properties, horizon, vertical);
   }

   public void onPressedSwitch(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
     CollectorPressurePlateTileEntity tile = (CollectorPressurePlateTileEntity)worldIn.func_175625_s(pos);
        boolean isForce = ((Boolean)state.func_177229_b((IProperty)FORCE)).booleanValue();
     
     List<Item> collectorFilter = (List<Item>)getSlotList((IInventory)tile).stream().filter(stack -> !stack.func_190926_b()).map(ItemStack::func_77973_b).collect(Collectors.toList());
     if (!isForce || !collectorFilter.isEmpty()) {
       Set<IInventory> inventorySet = getSurroundingIInventory(worldIn, pos, this.horizonSize, this.verticalSize);
       if (isForce) {
                List<CollectionAndDeliveryBase.InventoryEntry> playerEmptyEntry = createInventoryEntrys((IInventory)playerIn.field_71071_by, (List<ItemStack>)playerIn.field_71071_by.field_70462_a, stack -> stack.func_190926_b(), (id, stack) -> true);
         
         if (!playerEmptyEntry.isEmpty())
         {
           collectorFilter.stream()
             .filter(item -> (collectorFilter.stream().filter(()).count() > playerIn.field_71071_by.field_70462_a.stream().filter(()).count()))
             .forEach(item -> {
                 CollectionAndDeliveryBase.InventoryEntry invEntry = inventorySet.stream().flatMap(()).findFirst().orElse(null);

                 
                 if (invEntry != null && !playerEmptyEntry.isEmpty()) {
                   searchAndInsert(invEntry, playerEmptyEntry.remove(0), true);
                 }
               });
         }
       }            List<CollectionAndDeliveryBase.InventoryEntry> playerEntryList = createInventoryEntrys((IInventory)playerIn.field_71071_by, (List<ItemStack>)playerIn.field_71071_by.field_70462_a, stack ->                   (!stack.func_190926_b() && (col    
         
         Set<Item> matchedFilter = (Set<Item>)playerEntryList.stream().map(CollectionAndDeliveryBase.InventoryEntry::getItem).collect(Collectors.toSet());
 
 
 
 
 
 
         
         List<CollectionAndDeliveryBase.InventoryEntry> chestEntrySet = (List<CollectionAndDeliveryBase.InventoryEntry>)inventorySet.stream().flatMap(inv -> createInventoryEntrys(inv, getSlotList(inv), (), ()).stream().sorted()).collect(Collectors.toList());
         if (!chestEntrySet.isEmpty())
         {
           playerEntryList.forEach(playerEntry -> chestEntrySet.stream().filter(()).forEach(()));
         }
       }    }  }
 }

