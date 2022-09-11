 package com.ruby.meshi.util.packet;
 import java.util.Map;
 import java.util.function.Supplier;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.INamedContainerProvider;
 import net.minecraft.inventory.container.SimpleNamedContainerProvider;
 import net.minecraft.network.PacketBuffer;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 import net.minecraftforge.fml.network.NetworkEvent;
public class ContainerHandler {
   public ContainerHandler(ResourceLocation loc) {
     this.loc = loc;
   }
  public static void encode(ContainerHandler msg, PacketBuffer buf) {
     buf.func_192572_a(msg.loc);
   }
  public static ContainerHandler decode(PacketBuffer buf) {
     return new ContainerHandler(buf.func_192575_l());
   }
  public ResourceLocation getLocation() {
     return this.loc;
   }
  public static void handle(ContainerHandler message, Supplier<NetworkEvent.Context> ctx) {
     ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ((NetworkEvent.Context)ctx.get()).getSender().func_213829_a((INamedContainerProvider)new SimpleNamedContainerProvider((), (ITextComponent)new TranslationTextComponent("", new Object[0]))));
    
     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
   }
  private static ContainerType<?> getContainerType(ResourceLocation loc) {
     return ForgeRegistries.CONTAINERS.getEntries().stream()
       .filter(e -> ((ResourceLocation)e.getKey()).equals(loc))
       .findFirst()
       .map(e -> (ContainerType)e.getValue())
       .orElse(null);
   }
 }

