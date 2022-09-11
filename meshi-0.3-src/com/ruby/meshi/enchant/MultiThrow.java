 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TextFormatting;
 import net.minecraft.util.text.TranslationTextComponent;
public class MultiThrow
   extends Enchantment {
   protected MultiThrow(Enchantment.Rarity rarityIn, EnchantmentType type, EquipmentSlotType... slots) {
     super(rarityIn, type, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return (enchantmentLevel == 1) ? 30 : ((enchantmentLevel == 2) ? 70 : 90);
   }

   public int func_223551_b(int enchantmentLevel) {
     return (enchantmentLevel == 1) ? 69 : ((enchantmentLevel == 2) ? 85 : 100);
   }

   public int func_77325_b() {
     return 3;
   }

   public ITextComponent func_200305_d(int level) {
     TranslationTextComponent translationTextComponent = new TranslationTextComponent(func_77320_a() + "_" + level, new Object[0]);
     if (func_190936_d()) {
       translationTextComponent.func_211708_a(TextFormatting.RED);
     } else {
       translationTextComponent.func_211708_a(TextFormatting.GRAY);
     }       return (ITextComponent)translationTextComponent;
   }

   public boolean isAllowedOnBooks() {
     return false;
   }
 }

