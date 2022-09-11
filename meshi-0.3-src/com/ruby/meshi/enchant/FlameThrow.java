 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.enchantment.Enchantments;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
public class FlameThrow
   extends Enchantment {
   protected FlameThrow(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
     super(rarityIn, typeIn, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return 20;
   }

   public int func_223551_b(int enchantmentLevel) {
     return 50;
   }

   public int func_77325_b() {
     return 1;
   }

   public ITextComponent func_200305_d(int level) {
     return Enchantments.field_185311_w.func_200305_d(level);
   }
 }

