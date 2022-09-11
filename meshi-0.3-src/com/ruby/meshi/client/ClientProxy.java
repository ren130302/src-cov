 package com.ruby.meshi.client;
 import com.ruby.meshi.block.Indlight;
 import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.block.tileentity.BambooPotTileEntity;
 import com.ruby.meshi.block.tileentity.CardboardTileEntity;
 import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import com.ruby.meshi.block.tileentity.HearthTileEntity;
 import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
 import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
 import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;
 import com.ruby.meshi.block.tileentity.WallShelfTileEntity;
 import com.ruby.meshi.client.renderer.BambooPotRender;
 import com.ruby.meshi.client.renderer.CardboardRender;
 import com.ruby.meshi.client.renderer.CollectorPressurePlateItemRender;
 import com.ruby.meshi.client.renderer.HearthRender;
 import com.ruby.meshi.client.renderer.MillstoneRender;
 import com.ruby.meshi.client.renderer.MiniatureRender;
 import com.ruby.meshi.client.renderer.SlideDoorRender;
 import com.ruby.meshi.client.renderer.WallShelfItemRender;
 import com.ruby.meshi.entity.ScarecrowEntity;
 import com.ruby.meshi.entity.ShurikenEntity;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.item.ItemStack;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IEnviromentBlockReader;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.client.event.ColorHandlerEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.client.registry.ClientRegistry;
 import net.minecraftforge.fml.client.registry.IRenderFactory;
 import net.minecraftforge.fml.client.registry.RenderingRegistry;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
 public class ClientProxy {
   @SubscribeEvent
   public static void colorEvent(ColorHandlerEvent.Block e) {
     e.getBlockColors().func_186722_a((s, r, p, l) -> ((Indlight)s.func_177230_c()).getColorCode(), SakuraBlocks.INDLIGHT);
   }

   @SubscribeEvent
   public static void colorEvent(ColorHandlerEvent.Item e) {
     Stream.<Block>of(SakuraBlocks.INDLIGHT).forEach(l -> e.getItemColors().func_199877_a((), new IItemProvider[] { (IItemProvider)l }));
   }

   public static void renderRegister() {
     ClientRegistry.bindTileEntitySpecialRenderer(WallShelfTileEntity.class, new WallShelfItemRender());
     ClientRegistry.bindTileEntitySpecialRenderer(CollectorPressurePlateTileEntity.class, new CollectorPressurePlateItemRender());
     ClientRegistry.bindTileEntitySpecialRenderer(BambooPotTileEntity.class, new BambooPotRender());
     ClientRegistry.bindTileEntitySpecialRenderer(MillstoneTileEntity.class, new MillstoneRender());
     ClientRegistry.bindTileEntitySpecialRenderer(HearthTileEntity.class, new HearthRender());
     ClientRegistry.bindTileEntitySpecialRenderer(CardboardTileEntity.class, new CardboardRender());
     ClientRegistry.bindTileEntitySpecialRenderer(SlideDoorTileEntity.class, (TileEntityRenderer)new SlideDoorRender());
     ClientRegistry.bindTileEntitySpecialRenderer(MiniatureTileEntity.class, new MiniatureRender());
     RenderingRegistry.registerEntityRenderingHandler(ShurikenEntity.class, com.ruby.meshi.client.renderer.ShurikenRender::new);
     RenderingRegistry.registerEntityRenderingHandler(ScarecrowEntity.class, com.ruby.meshi.client.renderer.ScarecrowRender::new);
   }
 }

