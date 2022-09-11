 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TextFormatting;
 import net.minecraft.util.text.TranslationTextComponent;
public class SnipeThrow
   extends Enchantment {
   protected SnipeThrow(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
     super(rarityIn, typeIn, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return enchantmentLevel * 20 + 10;
   }

   public int func_223551_b(int enchantmentLevel) {
     return func_77321_a(enchantmentLevel) + 10;
   }

   public boolean isAllowedOnBooks() {
     return false;
   }

   public int func_77325_b() {
     return 4;
   }

   public ITextComponent func_200305_d(int level) {
     TranslationTextComponent translationTextComponent = new TranslationTextComponent(func_77320_a(), new Object[0]);
     if (func_190936_d()) {
       translationTextComponent.func_211708_a(TextFormatting.RED);
     } else {
       translationTextComponent.func_211708_a(TextFormatting.GRAY);
     }       return (ITextComponent)translationTextComponent;
   }
 }

