 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.enchantment.Enchantments;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
public class UnbreakingBracelet
   extends Enchantment {
   protected UnbreakingBracelet(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
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

   public boolean isAllowedOnBooks() {
     return false;
   }

   public ITextComponent func_200305_d(int level) {
     return Enchantments.field_185307_s.func_200305_d(level);
   }
 }

