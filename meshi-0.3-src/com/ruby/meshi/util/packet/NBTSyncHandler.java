 package com.ruby.meshi.util.packet;
import com.ruby.meshi.util.CapabilityExtendInventory;
 import java.util.function.Supplier;
 import net.minecraft.client.Minecraft;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.network.PacketBuffer;
 import net.minecraftforge.fml.network.NetworkEvent;

 public class NBTSyncHandler
 {
   public final CompoundNBT nbt;
    public NBTSyncHandler(INBT nbt) {
     this; this.nbt = compNBT(nbt);
   }
  public static CompoundNBT compNBT(INBT nbt) {
     CompoundNBT tmp = new CompoundNBT();
     tmp.func_218657_a("sync", nbt);
     return tmp;
   }
  public static INBT decompNBT(CompoundNBT nbt) {
     return nbt.func_74781_a("sync");
   }
  public NBTSyncHandler(CompoundNBT nbt) {
     this.nbt = nbt;
   }
  public static void encode(NBTSyncHandler msg, PacketBuffer buf) {
     buf.func_150786_a(msg.nbt);
   }
  public static NBTSyncHandler decode(PacketBuffer buf) {
     return new NBTSyncHandler(buf.func_150793_b());
   }
  public CompoundNBT getNBT() {
     return this.nbt;
   }
  public static void handle(NBTSyncHandler message, Supplier<NetworkEvent.Context> ctx) {
     ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> CapabilityExtendInventory.EXTEND_INVENTORY.readNBT((Minecraft.func_71410_x()).field_71439_g.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY).orElse(CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance()), null, decompNBT(message.nbt)));
       ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
   }
 }

