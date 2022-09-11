 package com.ruby.meshi.item;
import net.minecraft.block.Block;
 import net.minecraft.entity.Entity;
 import net.minecraft.inventory.EquipmentSlotType;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
public class CardboardItem extends BlockItem {
   public CardboardItem(Block block, Item.Properties properties) {
     super(block, properties);
   }

   public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
     return (EquipmentSlotType.HEAD == armorType);
   }

   public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
     return EquipmentSlotType.HEAD;
   }
 }

