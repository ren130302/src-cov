 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
public class PowerThrow extends EnchantmentBase {
   protected PowerThrow(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
     super(rarityIn, typeIn, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return 5 + (enchantmentLevel - 1) * 18;
   }

   public int func_223551_b(int enchantmentLevel) {
     return super.func_77321_a(enchantmentLevel) + 17;
   }

   public int func_77325_b() {
     return 6;
   }

   public boolean func_77326_a(Enchantment ench) {
     return (ench instanceof QuickThrow || ench instanceof HunterThrow || ench instanceof AssassinThrow) ? false : super.func_77326_a(ench);
   }
 }

