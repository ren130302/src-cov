 package com.ruby.meshi.client.inventory;
import com.ruby.meshi.init.HiganContainerType;
 import com.ruby.meshi.util.NetworkHandler;
 import java.util.function.Supplier;
 import net.minecraft.client.gui.screen.inventory.InventoryScreen;
 import net.minecraft.client.gui.widget.button.Button;
 import net.minecraft.client.gui.widget.button.ImageButton;
 import net.minecraft.util.ResourceLocation;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.client.event.GuiScreenEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
 public class InventoryScreenHook
 {
   private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("meshi", "textures/guis/meshi_button.png");
  @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void init(GuiScreenEvent.InitGuiEvent.Post event) {
     if (event.getGui() instanceof InventoryScreen) {
       InventoryScreen screen = (InventoryScreen)event.getGui();
       final Supplier<Integer> left = () -> Integer.valueOf(screen.getGuiLeft() + 134);
       final Supplier<Integer> top = () -> Integer.valueOf(screen.height / 2 - 22);
       ImageButton imageButton = new ImageButton(((Integer)left.get()).intValue(), ((Integer)top.get()).intValue(), 20, 18, 0, 0, 19, BUTTON_TEXTURE, b -> NetworkHandler.openServerContainer(HiganContainerType.EXTEND_INVENTORY))
         {
                    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_)
           {
             func_191746_c(((Integer)left.get()).intValue(), ((Integer)top.get()).intValue());
             super.renderButton(p_renderButton_1_, p_renderButton_2_, p_renderButton_3_);
           }
         };
     }  }
 }

