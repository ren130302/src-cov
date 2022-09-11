 package com.ruby.meshi.block;
 import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.IProperty;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.World;
public class CollectorPressurePlate extends CollectionAndDeliveryBase {
   public CollectorPressurePlate(Block.Properties properties, int horizon, int vertical) {
     super(properties, horizon, vertical);
   }

   public void onPressedSwitch(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
     CollectorPressurePlateTileEntity tile = (CollectorPressurePlateTileEntity)worldIn.func_175625_s(pos);
     boolean isForce = ((Boolean)state.func_177229_b((IProperty)FORCE)).booleanValue();
        Set<Item> collectorFilter = (Set<Item>)getSlotL        if (!isForce || !collectorFilter.isEmpty()) {                                           
         Set<Item> matchedFilter = (Set<Item>)playerEntryList.stream().map(CollectionAndDeliveryBase.InventoryEntry::getItem).collect(Collectors.toSet());
 
 
 
 
 
 
 
 
         
         Set<CollectionAndDeliveryBase.InventoryEntry> chestEntrySet = (Set<CollectionAndDeliveryBase.InventoryEntry>)getSurroundingIInventory(worldIn, pos, this.horizonSize, this.verticalSize).stream().flatMap(inv -> createInventoryEntrys(inv, getSlotList(inv), (), ()).stream().sorted().distinct()).collect(Collectors.toSet());
         if (!chestEntrySet.isEmpty())
         {
           playerEntryList.forEach(playerEntry -> chestEntrySet.stream().filter(()).forEach(()));
         }
       }    }  }
 }

