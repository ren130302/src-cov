  package com.ruby.meshi.core;
  import com.ruby.meshi.client.ClientProxy;
  import com.ruby.meshi.init.HiganContainerType;
  import com.ruby.meshi.init.SakuraConfig;
  import com.ruby.meshi.util.CapabilityExtendInventory;
  import com.ruby.meshi.util.NetworkHandler;
  import com.ruby.meshi.world.biome.HiganBiomes;
  import com.ruby.meshi.world.gen.HiganGenerater;
  import net.minecraftforge.fml.ModLoadingContext;
  import net.minecraftforge.fml.common.Mod;
  import net.minecraftforge.fml.config.ModConfig;
  import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
  import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
  import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
  import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
  import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
  import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;
  @Mod("meshi")
  public class MeshiMod
  {
    public static final String MOD_ID = "meshi";
    public static final Logger LOGGER = LogManager.getLogger();
   public MeshiMod() {
      FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
      FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
      FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
      FMLJavaModLoadingContext.get().getModEventBus().addListener(this::noSign);
          ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SakuraConfig.clientSpec);
    }

    private void preInit(FMLCommonSetupEvent event) {
      HiganGenerater.addBiomeGen();
      HiganBiomes.addBiomes();
      NetworkHandler.register();
      (new CapabilityExtendInventory()).register();
    }

    private void doClientStuff(FMLClientSetupEvent event) {
      HiganContainerType.registerScreenFactories();
      ClientProxy.renderRegister();
    }

 
    public static String modIDAppend(String str) {
      return "meshi:" + str;
    }
   public static void warnlog(String mes) {
      LOGGER.warn("[meshi]:" + mes);
    }
      public void noSign(FMLFingerprintViolationEvent event) {}
  }

