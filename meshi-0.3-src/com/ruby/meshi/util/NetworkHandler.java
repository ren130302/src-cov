 package com.ruby.meshi.util;
import com.ruby.meshi.util.packet.ContainerHandler;
 import com.ruby.meshi.util.packet.NBTSyncHandler;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.nbt.INBT;
 import net.minecraft.util.ResourceLocation;
 import net.minecraftforge.fml.network.NetworkRegistry;
 import net.minecraftforge.fml.network.PacketDistributor;
 import net.minecraftforge.fml.network.simple.SimpleChannel;

 public class NetworkHandler
 {
   private static final String PROTOCOL_VERSION = Integer.toString(1);
   private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("meshi", "main_channel"))
     .clientAcceptedVersions(PROTOCOL_VERSION::equals)
     .serverAcceptedVersions(PROTOCOL_VERSION::equals)
     .networkProtocolVersion(() -> PROTOCOL_VERSION)
     .simpleChannel();
  public static void register() {
     int disc = 0;
     HANDLER.registerMessage(disc++, ContainerHandler.class, ContainerHandler::encode, ContainerHandler::decode, ContainerHandler::handle);
     HANDLER.registerMessage(disc++, NBTSyncHandler.class, NBTSyncHandler::encode, NBTSyncHandler::decode, NBTSyncHandler::handle);
   }
  public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
     HANDLER.send(target, message);
   }
  public static void openServerContainer(ContainerType<?> type) {
     HANDLER.sendToServer(new ContainerHandler(type.getRegistryName()));
   }
  public static void sendExtendInvCap(ServerPlayerEntity player) {
     INBT nbt = CapabilityExtendInventory.EXTEND_INVENTORY.writeNBT(player.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY).orElse(CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance()), null);
     HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new NBTSyncHandler(nbt));
   }
 }

