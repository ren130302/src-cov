 package com.ruby.meshi.item;
import com.google.common.collect.Lists;
 import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.client.CreativeTab;
 import com.ruby.meshi.fluid.MeshiFluids;
 import java.util.List;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.item.BlockNamedItem;
 import net.minecraft.item.BucketItem;
 import net.minecraft.item.Food;
 import net.minecraft.item.IItemTier;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemTier;
 import net.minecraft.item.Items;
 import net.minecraft.util.ResourceLocation;

 public class HiganItems
 {
   public static final Item SAKURA_GEM = register("sakura_gem", new Item(baseProp()));
   public static final Item GINKGO_GEM = register("ginkgo_gem", new Item(baseProp()));
   public static final Item MAPLE_INGOT = register("maple_ingot", new Item(baseProp()));
   public static final Item PADDY_FIELD_HOE = register("paddy_field_hoe", (Item)new PaddyFieldHoe((IItemTier)ItemTier.DIAMOND, 0.0F, baseProp().func_200917_a(1)));
   public static final Item RICE_SEED = register("rice_seed", (Item)new BlockNamedItem(SakuraBlocks.RICE_PLANT, baseProp()));
   public static final Item STRAW = register("straw", new Item(baseProp()));
   public static final Item BIOME_LENZ = register("biome_lenz", new BiomeLenz(baseProp().func_200917_a(1)));
   public static final Item ITEM_MAGNET = register("item_magnet", new ItemMagnet(baseProp().func_200917_a(1)));
   public static final Item RAW_RICE = register("rawrice", new Item(baseProp()));
   public static final Item KATANA = register("katana", (Item)new Katana(baseProp()));
   public static final Item SHURIKEN_STONE = register("shuriken_stone", new Shuriken((IItemTier)ItemTier.STONE, baseProp().func_200917_a(64)));
   public static final Item SHURIKEN_IRON = register("shuriken_iron", new Shuriken((IItemTier)ItemTier.IRON, baseProp().func_200917_a(64)));
   public static final Item SHURIKEN_DIAMOND = register("shuriken_diamond", new Shuriken((IItemTier)ItemTier.DIAMOND, baseProp().func_200917_a(64)));
   public static final Item NINJA_BRACELET = register("ninja_bracelet", new NinjaBracelet(baseProp().func_200918_c(384)));
   public static final Item SCARECROW = register("scarecrow", new ScarecrowItem(new Item.Properties()));
   public static final Item FUKURO = register("fukuro", new Fukuro(baseProp().func_200917_a(1)));
   public static final Item HOT_SPRING_BUCKET = register("hot_spring_bucket", (Item)new BucketItem(() -> MeshiFluids.HOT_SPING, baseProp().func_200919_a(Items.field_151133_ar).func_200917_a(1)));
   public static final Item CLIMBING_CLAW = register("climbing_claw", new ClimbingClaw(baseProp().func_200917_a(1)));
   public static final Item WATER_WALKER = register("water_warker", new WaterWalker(baseProp().func_200917_a(1)));

   public static final Item[] FOODS = createFoods();

   private static Item register(String key, Item item) {
     return (Item)item.setRegistryName(new ResourceLocation("meshi", key));
   }
  private static Item.Properties baseProp() {
     return (new Item.Properties()).func_200916_a(CreativeTab.ITEM_GROUP);
   }
  private static Item[] createFoods() {
     List<Item> items = Lists.newArrayList();
    
     items.add(createFood("agedashi", food(4).func_221453_d()));
     items.add(createFood("dango_anko", food(3).func_221453_d()));
     items.add(createFood("dango_kinako", food(3).func_221453_d()));
     items.add(createFood("dango_mitarashi", food(3).func_221453_d()));
     items.add(createFood("dango_sansyoku", food(3).func_221453_d()));
     items.add(createFood("dango_zunda", food(3).func_221453_d()));
     items.add(createFood("kushi_buta", food(4).func_221453_d()));
     items.add(createFood("kushi_gyu", food(4).func_221453_d()));
     items.add(createFood("kushi_tori", food(4).func_221453_d()));
     items.add(createFood("meshi", food(4).func_221453_d()));
     items.add(createFood("meshi_azuki", food(6).func_221453_d()));
     items.add(createFood("meshi_buta", food(8).func_221453_d()));
     items.add(createFood("meshi_egg", food(6).func_221453_d()));
     items.add(createFood("meshi_gyu", food(8).func_221453_d()));
     items.add(createFood("meshi_katsu", food(9).func_221453_d()));
     items.add(createFood("meshi_kinoko", food(8).func_221453_d()));
     items.add(createFood("meshi_natto", food(6).func_221453_d()));
     items.add(createFood("meshi_natto_egg", food(7).func_221453_d()));
     items.add(createFood("meshi_oyako", food(8).func_221453_d()));
     items.add(createFood("mochi", food(3).func_221453_d()));
     items.add(createFood("mochi_cooked", food(4).func_221453_d()));
     items.add(createFood("mochi_sakura", food(4).func_221453_d()));
     items.add(createFood("natto", food(3).func_221453_d()));
     items.add(createFood("noodle_ramen", food(8).func_221453_d()));
     items.add(createFood("noodle_udon", food(8).func_221453_d()));
     items.add(createFood("ohagi", food(4).func_221453_d()));
     items.add(createFood("onigiri", food(4).func_221453_d()));
     items.add(createFood("pizza", food(6).func_221453_d()));
     items.add(createFood("sherbet_berry", food(4).func_221453_d()));
     items.add(createFood("sherbet_berry_milk", food(5).func_221453_d()));
     items.add(createFood("tofu", food(3).func_221453_d()));
        return (Item[])items.stream().toArray(x$0 -> new Item[x$0]);
   }
  private static Meshi.Builder food(int hunger) {
     Meshi.Builder builder = (Meshi.Builder)(new Meshi.Builder()).func_221456_a(hunger).func_221454_a((hunger < 5) ? (hunger * 0.5F) : (hunger * 0.75F));
     if (hunger < 4) {
       builder = (Meshi.Builder)builder.func_221457_c();
     }
     return builder;
   }
  private static Item createFood(String name, Food food) {
     return register(name, new Meshi(baseProp().func_221540_a(food)));
   }
 }

