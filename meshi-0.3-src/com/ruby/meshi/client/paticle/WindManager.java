 package com.ruby.meshi.client.paticle;
import java.util.Random;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.MathHelper;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.event.TickEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.LogicalSide;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

 
 
 
 
 
 
 
 
 @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
 public class WindManager
 {
   private static Direction windDir = Direction.NORTH; private static int prevWindAngle;  private static Direction windDir = Direction.NORTH; private static int prevWindAngle;
   private static int windAngle = (int)(360.0D * Math.random()); private static float prevWindPower; private static float windPower; private static double motionX;  private static int windAngle = (int)(360.0D * Math.random()); private static float prevWindPower; private static float windPower; private static double motionX;  private static int windAngle = (int)(360.0D * Math.random()); private static float prevWindPower; private static float windPower; private static double motionX;  private static int windAngle = (int)(360.0D * Math.random()); private static float prevWindPower; private static float windPower; private static double motionX;
   private static int maxChangeAngle = 180; private static double motionY; private static double motionZ; private static int coolTimer; private static int chengeTime;  private static int maxChangeAngle = 180; private static double motionY; private static double motionZ; private static int coolTimer; private static int chengeTime;  private static int maxChangeAngle = 180; private static double motionY; private static double motionZ; private static int coolTimer; private static int chengeTime;  private static int maxChangeAngle = 180; private static double motionY; private static double motionZ; private static int coolTimer; private static int chengeTime;  private static int maxChangeAngle = 180; private static double motionY; private static double motionZ; private static int coolTimer; private static int chengeTime;
   private static int minChangeAngle = 40;
   private static float maxWindPow = 0.04F;
   private static int updateRate = 100;
   static {
     chengeTime = 1800 / updateRate;
   }

   @SubscribeEvent
   public static void tick(TickEvent.PlayerTickEvent e) {
     if (e.side == LogicalSide.CLIENT)
     {
       if (e.phase == TickEvent.Phase.START) {
         if (e.player.field_70170_p.func_82737_E() % updateRate == 0L) {
           Random rand = e.player.field_70170_p.field_73012_v;
           prevWindAngle = windAngle;
           prevWindPower = windPower;
           if (--coolTimer < 0) {
                        windAngle += getRandomChange(rand, maxChangeAngle) % 360;
             windPower = maxWindPow * rand.nextFloat();
             coolTimer = chengeTime + rand.nextInt(chengeTime);
           } else {
                        if (rand.nextFloat() < 0.3F) {
               windAngle += getRandomChange(rand, minChangeAngle) % 360;
             }
                        windPower += getRandomChange(rand, maxWindPow) * 0.2F;           
         
         calcMotion((float)(e.player.field_70170_p.func_82737_E() % updateRate) / updateRate);
       } 
     }
   }

   private static int getRandomChange(Random rand, int i) {
     return rand.nextInt(i) - rand.nextInt(i);
   }
  private static float getRandomChange(Random rand, float f) {
     return f * rand.nextFloat() - f * rand.nextFloat();
   }
  private static void calcMotion(float partialTicks) {
     float pow = MathHelper.func_219799_g(partialTicks, prevWindPower, windPower);
     float angle = MathHelper.func_219799_g(partialTicks, prevWindAngle, windAngle);
     double rad = Math.toRadians(angle);
     motionZ = (pow * MathHelper.func_76134_b((float)rad));
     motionX = (pow * -MathHelper.func_76126_a((float)rad));
     windDir = Direction.func_176733_a(angle);
   }

   public static void setWind(int angle, float power) {
     prevWindAngle = windAngle;
     windAngle = angle;
     prevWindPower = windPower;
     windPower = power;
   }
  public static boolean isStrongWind() {
     return (prevWindPower > 0.025F);
   }
  public static float getWindPow() {
     return prevWindPower;
   }
  public static Direction getWindDir() {
     return windDir;
   }
  public static double getMotionX() {
     return motionX;
   }
  public static double getMotionY() {
     return motionY;
   }
  public static double getMotionZ() {
     return motionZ;
   }
 }

