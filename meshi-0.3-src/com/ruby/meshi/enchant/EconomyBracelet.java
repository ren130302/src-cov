 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
public class EconomyBracelet extends EnchantmentBase {
   protected EconomyBracelet(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
     super(rarityIn, typeIn, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return 5 + (enchantmentLevel - 1) * 40;
   }

   public int func_223551_b(int enchantmentLevel) {
     return super.func_77321_a(enchantmentLevel) + (func_77325_b() - enchantmentLevel + 1) * 15;
   }

   public int func_77325_b() {
     return 3;
   }

   public boolean func_77326_a(Enchantment ench) {
     return (ench instanceof InfinityThrow) ? false : super.func_77326_a(ench);
   }
 }

