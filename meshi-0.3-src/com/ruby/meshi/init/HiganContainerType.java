 package com.ruby.meshi.init;
import com.ruby.meshi.common.inventory.CollectorPressurePlateContainer;
 import com.ruby.meshi.common.inventory.ExtendInventoryContainer;
 import com.ruby.meshi.common.inventory.FukuroContainer;
 import com.ruby.meshi.common.inventory.HearthContainer;
 import com.ruby.meshi.common.inventory.MillstoneContainer;
 import net.minecraft.client.gui.ScreenManager;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.network.PacketBuffer;
 import net.minecraftforge.fml.network.IContainerFactory;

 
 
 
 public class HiganContainerType
 {
   public static final ContainerType<CollectorPressurePlateContainer> COLLECTOR_PLATE = create("collector_pressure_plate", CollectorPressurePlateContainer::new);
   public static final ContainerType<MillstoneContainer> MILLSTONE = create("millstone", MillstoneContainer::new);
   public static final ContainerType<HearthContainer> HEARTH = create("hearth", HearthContainer::new);
   public static final ContainerType<FukuroContainer> FUKURO = create("fukuro", FukuroContainer::new);
   public static final ContainerType<ExtendInventoryContainer> EXTEND_INVENTORY = create("extend_inventory", ExtendInventoryContainer::new);

 
 
 
   private static <T extends Container> ContainerType<T> create(String key, IFactory<T> factory) {
     IContainerFactory<T> fact = (windowId, inv, data) -> factory.create(windowId, inv);
     return (ContainerType<T>)(new ContainerType((ContainerType.IFactory)fact)).setRegistryName("meshi", key);
   }

   public static void registerScreenFactories() {
     ScreenManager.func_216911_a(COLLECTOR_PLATE, com.ruby.meshi.client.inventory.CollectorPressurePlateScreen::new);
     ScreenManager.func_216911_a(MILLSTONE, com.ruby.meshi.client.inventory.MillstoneScreen::new);
     ScreenManager.func_216911_a(HEARTH, com.ruby.meshi.client.inventory.HearthScreen::new);
     ScreenManager.func_216911_a(FUKURO, com.ruby.meshi.client.inventory.FukuroScreen::new);
     ScreenManager.func_216911_a(EXTEND_INVENTORY, com.ruby.meshi.client.inventory.ExtendInventoryScreen::new);
   }
    public static interface IFactory<T extends Container> {
     T create(int param1Int, PlayerInventory param1PlayerInventory);
   }
 }

