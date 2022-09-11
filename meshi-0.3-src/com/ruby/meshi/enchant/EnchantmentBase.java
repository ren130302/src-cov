 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TextFormatting;
 import net.minecraft.util.text.TranslationTextComponent;
abstract class EnchantmentBase
   extends Enchantment {
   protected EnchantmentBase(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
     super(rarityIn, typeIn, slots);
   }

   public ITextComponent func_200305_d(int level) {
     if (level < func_77325_b()) {
       return super.func_200305_d(level);
     }
     TranslationTextComponent translationTextComponent = new TranslationTextComponent(func_77320_a() + "_ex", new Object[0]);
     if (func_190936_d()) {
       translationTextComponent.func_211708_a(TextFormatting.RED);
     } else {
       translationTextComponent.func_211708_a(TextFormatting.GRAY);
     }    if (func_77325_b() < level) {
       translationTextComponent.func_150258_a("★");
     }
     return (ITextComponent)translationTextComponent;
   }

   public boolean isAllowedOnBooks() {
     return false;
   }
 }

