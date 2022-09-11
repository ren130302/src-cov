 package com.ruby.meshi.init;
import java.util.function.Function;
 import net.minecraftforge.common.ForgeConfigSpec;
 import org.apache.commons.lang3.tuple.Pair;
public class SakuraConfig {
   public static final ForgeConfigSpec clientSpec;
   public static final Client CLIENT;
    public static class Client {
     Client(ForgeConfigSpec.Builder builder) {
       builder.comment("Client only settings").push("client");
       this.sakuraFancyRender = builder.comment("Toggle off to fast render mod leaves.").translation("sakura.configgui.sakuraRender").define("sakuraFancyRender", true);
            builder.pop();
     }
        public final ForgeConfigSpec.BooleanValue sakuraFancyRender;
   }
  static {
     Pair<Client, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(Client::new);
     clientSpec = specPair.getRight();
     CLIENT = specPair.getLeft();
   }
 }

