 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.item.Item;
public class HiganEnchantType {
   static {
     BRACELET = EnchantmentType.create("bracelet", item -> item instanceof com.ruby.meshi.item.NinjaBracelet);
   }
    public static final EnchantmentType BRACELET;
 }

