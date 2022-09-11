 package com.ruby.meshi.common;
import net.minecraft.item.Item;
 import net.minecraft.tags.ItemTags;
 import net.minecraft.tags.Tag;
 import net.minecraft.util.ResourceLocation;
public class HiganTags
 {
   public static class ITEM
   {
     public static final Tag<Item> MEAT = (Tag<Item>)new ItemTags.Wrapper(new ResourceLocation("meshi", "meat"));
     public static final Tag<Item> FISH = (Tag<Item>)new ItemTags.Wrapper(new ResourceLocation("meshi", "fish"));
   }
 }

