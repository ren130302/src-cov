 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.ResourceLocation;

 public class HiganEnchant
 {
   public static final Enchantment QUICK_THROW = regiter("quick_throw", new QuickThrow(Enchantment.Rarity.COMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment POWER_THROW = regiter("power_throw", new PowerThrow(Enchantment.Rarity.COMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment CRITICAL_THROW = regiter("critical_throw", new CriticalThrow(Enchantment.Rarity.COMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment POISON_THROW = regiter("poison_throw", new PoisonThrow(Enchantment.Rarity.COMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment SNIPE_THROW = regiter("snipe_throw", new SnipeThrow(Enchantment.Rarity.COMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));

   public static final Enchantment ECONOMY_BRACELET = regiter("economy_bracelet", new EconomyBracelet(Enchantment.Rarity.UNCOMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment UNBREAKING_BRACELET = regiter("unbreaking_bracelet", new UnbreakingBracelet(Enchantment.Rarity.UNCOMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment PICKPOCKET = regiter("pickpocket", new Pickpocket(Enchantment.Rarity.UNCOMMON, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));

   public static final Enchantment FLAME_THROW = regiter("flame_throw", new FlameThrow(Enchantment.Rarity.RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment HUNTER_THROW = regiter("hunter_throw", new HunterThrow(Enchantment.Rarity.RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment ROGUE_THROW = regiter("rogue_throw", new RogueThrow(Enchantment.Rarity.RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment MULTI_THROW = regiter("multi_throw", new MultiThrow(Enchantment.Rarity.RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment INFINITY_THROW = regiter("infinity_throw", new InfinityThrow(Enchantment.Rarity.RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));

   public static final Enchantment ASSASSIN_THROW = regiter("assassin_throw", new AssassinThrow(Enchantment.Rarity.VERY_RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
   public static final Enchantment RETURN_THROW = regiter("return_throw", new ReturnThrow(Enchantment.Rarity.VERY_RARE, HiganEnchantType.BRACELET, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));

   private static Enchantment regiter(String key, Enchantment enc) {
     return (Enchantment)enc.setRegistryName(new ResourceLocation("meshi", key));
   }
 }

