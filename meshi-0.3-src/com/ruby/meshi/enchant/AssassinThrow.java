 package com.ruby.meshi.enchant;
import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.util.text.ITextComponent;
public class AssassinThrow extends EnchantmentBase {
   protected AssassinThrow(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
     super(rarityIn, typeIn, slots);
   }

   public int func_77321_a(int enchantmentLevel) {
     return 40 + (enchantmentLevel - 1) * 10;
   }

   public int func_223551_b(int enchantmentLevel) {
     return super.func_77321_a(enchantmentLevel) + 5;
   }

   public int func_77325_b() {
     return 6;
   }

   public boolean func_77326_a(Enchantment ench) {
     boolean isHunter = (ench instanceof PowerThrow || ench instanceof QuickThrow || ench instanceof HunterThrow);
     boolean isRogue = (ench instanceof CriticalThrow || ench instanceof PoisonThrow || ench instanceof RogueThrow);
     return (isHunter || isRogue) ? false : super.func_77326_a(ench);
   }
 }

