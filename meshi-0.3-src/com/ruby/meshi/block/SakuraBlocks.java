 package com.ruby.meshi.block;
import com.google.common.collect.Maps;
 import com.ruby.meshi.block.decorate.DecorateBlock;
 import com.ruby.meshi.block.decorate.DecorateStairs;
 import com.ruby.meshi.block.decorate.DecorateVerticalSlab;
 import com.ruby.meshi.fluid.MeshiFluids;
 import com.ruby.meshi.world.gen.HinokiTree;
 import com.ruby.meshi.world.gen.SakuraTree;
 import java.util.Map;
 import java.util.stream.Stream;
 import net.minecraft.block.Block;
 import net.minecraft.block.LeavesBlock;
 import net.minecraft.block.PressurePlateBlock;
 import net.minecraft.block.SoundType;
 import net.minecraft.block.material.Material;
 import net.minecraft.block.material.MaterialColor;
 import net.minecraft.fluid.FlowingFluid;
 import net.minecraft.item.DyeColor;

 
 
 public class SakuraBlocks
 {
   public static final Block SAKURA_LOG = register("sakura_log", (Block)new HiganLogBlock(MaterialColor.field_151663_o, Block.Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
   public static final Block SAKURA_LEAVE = register("sakura_leave", (Block)(new SakuraLeave(Block.Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraLeave.SakuraType.PINK));
   public static final Block SAKURA_SAPLING = register("sakura_sapling", (Block)new SakuraSapling(new SakuraTree(SAKURA_LOG, SAKURA_LEAVE), Block.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
   public static final Block SAKURA_PLANKS = register("sakura_planks", (Block)new PlayerFacingBlock(createWoodPropety()));
   public static final Block SAKURA_SLAB = register("sakura_slab", (Block)new PlayerFachingSlab(createWoodPropety()));
   public static final Block MAPLE_LOG = register("maple_log", (Block)new HiganLogBlock(MaterialColor.field_151663_o, Block.Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
   public static final Block MAPLE_LEAVE = register("maple_leave", (Block)(new SakuraLeave(Block.Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraLeave.SakuraType.RED));
   public static final Block MAPLE_SAPLING = register("maple_sapling", (Block)new SakuraSapling(new SakuraTree(MAPLE_LOG, MAPLE_LEAVE), Block.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
   public static final Block GINKGO_LOG = register("ginkgo_log", (Block)new HiganLogBlock(MaterialColor.field_151663_o, Block.Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
   public static final Block GINKGO_LEAVE = register("ginkgo_leave", (Block)(new SakuraLeave(Block.Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraLeave.SakuraType.YELLOW));
   public static final Block GINKGO_SAPLING = register("ginkgo_sapling", (Block)new SakuraSapling(new SakuraTree(GINKGO_LOG, GINKGO_LEAVE), Block.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
   public static final Block HINOKI_LOG = register("hinoki_log", (Block)new HiganLogBlock(MaterialColor.field_151663_o, Block.Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
   public static final Block HINOKI_LEAVE = register("hinoki_leave", (Block)new LeavesBlock(Block.Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c)));
   public static final Block HINOKI_SAPLING = register("hinoki_sapling", (Block)new SakuraSapling(new HinokiTree(), Block.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
   public static final Block BAMBOO_PANE = register("bamboo_pane", (Block)new PaneBlock(createWoodPropety()));
   public static final Block BAMBOO_PANE2 = register("bamboo_pane2", (Block)new PaneBlock(createWoodPropety()));
   public static final Block BAMBOO_PANE3 = register("bamboo_pane3", (Block)new PaneBlock(createWoodPropety()));
   public static final Block RANMA = register("ranma", (Block)new PaneBlock(createWoodPropety()));
   public static final Block BLIND = register("blind", (Block)new PaneBlock(createWoodPropety()));
   public static final Block NOREN_BLUE = register("noren_blue", (Block)new PaneBlock(createMiscPropety().func_200942_a()));
   public static final Block NOREN_PURPLE = register("noren_purple", (Block)new PaneBlock(createMiscPropety().func_200942_a()));
   public static final Block KOTATSU = register("kotatsu", (Block)new Kotatsu(createMiscPropety()));
   public static final Block CARDBOARD = register("cardboard", (Block)new Cardboard(createMiscPropety()));
   public static final Block BRICK_ORANGE = register("brick_orange", new Block(createRockPropety()));
   public static final Block BRICK_WHITE = register("brick_white", new Block(createRockPropety()));
   public static final Block BRICK_BROWN = register("brick_brown", new Block(createRockPropety()));
  public static final Block SHOJI = register("shoji", (Block)new SlideDoor(createMiscPropety()));
   public static final Block SHOJI_YOKOGUMI = register("shoji_yokogumi", (Block)new SlideDoor(createMiscPropety()));
   public static final Block SHOJI_TATEGUMI = register("shoji_tategumi", (Block)new SlideDoor(createMiscPropety()));
   public static final Block SHOJI_YUKIMI = register("shoji_yukimi", (Block)new SlideDoor.LayerTranslucent(createMiscPropety()));
   public static final Block HUSUMA = register("husuma", (Block)new SlideDoor(createMiscPropety()));
   public static final Block GLASS_DOOR = register("glassdoor", (Block)new SlideDoor.LayerTranslucent(createMiscPropety()));
   public static final Block GLASS_DOOR_GRID = register("glassdoor_grid", (Block)new SlideDoor.LayerTranslucent(createMiscPropety()));
  public static final Block[] INDLIGHT = createBlockDyePattern("indlight", Indlight.class, Block.Properties.func_200945_a(Material.field_151594_q).func_200942_a().func_200943_b(0.5F).func_200947_a(SoundType.field_185848_a).func_200951_a(15), DyeColor.values());
   public static final Block SPRING_SPAWNER = register("spring_spawner", new SpringSpawner(createRockPropety().func_200943_b(2.5F)));
   public static final Block HOT_SPRING = register("hot_spring", (Block)new HotSpring(() -> MeshiFluids.HOT_SPING, Block.Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e()));
   public static final Block WALL_SHELF = register("wall_shelf", (Block)new WallShelf(createWoodPropety(1.5F)));
   public static final Block COLLECTOR_PLATE = register("collector_pressure_plate", (Block)new CollectorPressurePlate(createWoodPropety(1.5F).func_200942_a(), 1, 2));
   public static final Block DELIVERY_PLATE = register("delivery_pressure_plate", (Block)new DeliveryPressurePlate(createWoodPropety(1.5F).func_200942_a(), 1, 2));
   public static final Block BAMBOO_POT = register("bamboo_pot", new BambooPot(createWoodPropety()));
   public static final Block KITSUNEBI = register("kitsunebi", new Kitsunebi(Block.Properties.func_200945_a(Material.field_151594_q).func_200951_a(15).func_200942_a()));
   public static final Block MILLSTONE = register("millstone", new Millstone(createRockPropety(2.5F)));
   public static final Block ANDON = register("andon", (Block)new Andon(createWoodPropety(0.5F).func_200951_a(15)));
   public static final Block PADDY_FIELD = register("paddy_field", (Block)new PaddyField(createDirtPropety(0.6F).func_200944_c()));
   public static final Block RICE_PLANT = register("rice_plant", (Block)new RicePlant(createPlantPropety()));
   public static final Block HEARTH = register("hearth", (Block)new Hearth(createRockPropety().func_200951_a(10)));
   public static final Block MAPLE_ORE = register("maple_ore", (Block)new HiganOre(Block.Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
   public static final Block GINKGO_ORE = register("ginkgo_ore", (Block)new HiganOre(Block.Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
   public static final Block SAKURA_ORE = register("sakura_ore", (Block)new HiganOre(Block.Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
   public static final Block TATAMI = register("tatami", (Block)new PlayerFacingBlock(createMiscPropety().func_200943_b(0.5F)));
   public static final Block TATAMI_SLAB = register("tatami_slab", (Block)new PlayerFachingSlab(createMiscPropety().func_200943_b(0.5F)));
   public static final Block TATAMI_TAN = register("tatami_tan", (Block)new PlayerFacingBlock(createMiscPropety().func_200943_b(0.5F)));
   public static final Block TATAMI_TAN_SLAB = register("tatami_tan_slab", (Block)new PlayerFachingSlab(createMiscPropety().func_200943_b(0.5F)));
   public static final Block MINIATURE = register("miniature", (Block)new Miniature(createMiscPropety()));
   public static final Block KITSUNEBI_PRESSURE = register("kitsunebi_pressure", (Block)new KitsunebiPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, createMiscPropety()));
   public static final Block MANEKINEKO = register("manekineko", (Block)new ManekiNeko(createMiscPropety()));
   public static final Block HUTON = register("huton", (Block)new Huton(createMiscPropety()));
  public static final Block JPCHEST_SINGLE = register("jpchest_single", (Block)new JPChest(createWoodPropety()));
 
   public static final Block[] KAWARA_BLOCKS = Builder.create("kawara", createMiscPropety()).build(true);
   public static final Block[] STRAW_BLOCKS = Builder.create("straw", createMiscPropety()).addStandardCube("straw_block").build(true);
   public static final Block[] CHECKERED_OAK_BLOCKS = Builder.create("checkered_oak", createWoodPropety()).build(true);
   public static final Block[] CHECKERED_BIRCH_BLOCKS = Builder.create("checkered_birch", createWoodPropety()).build(true);
   public static final Block[] CHECKERED_PINE_BLOCKS = Builder.create("checkered_pine", createWoodPropety()).build(true);
   public static final Block[] PLASTER_BLCOKS = Builder.create("plaster", createMiscPropety()).build(true);
   public static final Block[] NAMAKO_BLOCKS = Builder.create("namako", createMiscPropety()).build(true);
   public static final Block[] THATCHED_BLOCKS = Builder.create("thatched", createMiscPropety()).build(true);

 
 
 
 
 
 
 
   private static Block register(String name, Block block) {
     return (Block)block.setRegistryName("meshi", name);
   }

   private static <T extends Block & SimpleColorMultiply<T>> Block[] createBlockDyePattern(String basename, Class<T> block, Block.Properties prop, DyeColor... colors) {
     return (Block[])Stream.<DyeColor>of(colors)
       .map(c -> (Block)((Block)((SimpleColorMultiply<Block>)createBlockIns(block, prop)).setColorCode(c)).setRegistryName("meshi", basename + "_" + c.func_176610_l()))
       .toArray(x$0 -> new Block[x$0]);
   }
  private static <T extends Block> T createBlockIns(Class<T> cls, Block.Properties prop) {
     try {
       return (T)cls.getConstructor(new Class[] { Block.Properties.class }).newInstance(new Object[] { prop });
     } catch (Exception e) {
            e.printStackTrace();
       return null;
     }  }
   private static Block.Properties createMiscPropety() {
     return createMiscPropety(1.0F);
   }
  private static Block.Properties createMiscPropety(float hardness) {
     return createPropety(Material.field_151592_s, hardness, SoundType.field_185854_g);
   }
  private static Block.Properties createPlantPropety() {
     return createPropety(Material.field_151585_k, 0.0F, SoundType.field_222472_s).func_200942_a().func_200944_c();
   }
  private static Block.Properties createDirtPropety(float hardness) {
     return createPropety(Material.field_151578_c, hardness, SoundType.field_185849_b);
   }
  private static Block.Properties createRockPropety() {
     return createRockPropety(2.0F);
   }
  private static Block.Properties createRockPropety(float hardness) {
     return createPropety(Material.field_151576_e, hardness, SoundType.field_185851_d);
   }
  private static Block.Properties createWoodPropety() {
     return createWoodPropety(2.0F);
   }
  private static Block.Properties createWoodPropety(float hardness) {
     return createPropety(Material.field_151575_d, hardness, SoundType.field_185848_a);
   }
  private static Block.Properties createPropety(Material materialIn, float hardness, SoundType soundTypeIn) {
     return Block.Properties.func_200945_a(materialIn).func_200943_b(hardness).func_200947_a(soundTypeIn);
   }
  private static class Builder
   {
     private String name;
     private Block.Properties prop;
     private Map<String, Block> blocks;
        private Builder(String name, Block.Properties prop) {
       this.name = name;
       this.prop = prop;
       this.blocks = Maps.newHashMap();
     }
    public static Builder create(String name, Block.Properties prop) {
       Builder builder = new Builder(name, prop);
       return builder;
     }
    private Builder fillRest() {
       if (!this.blocks.containsKey("cube")) {
         addStandardCube();
       }
       if (!this.blocks.containsKey("slab")) {
         addSlab();
       }
       if (!this.blocks.containsKey("stairs")) {
         addStairs();
       }
       return this;
     }
    public Builder addStandardCube() {
       return addStandardCube(this.name);
     }
    public Builder addStandardCube(String name) {
       this.blocks.put("cube", SakuraBlocks.register(name, new DecorateBlock(this.prop)));
       return this;
     }
    public Builder addSlab() {
       return addSlab(this.name + "_slab");
     }
    public Builder addSlab(String name) {
       this.blocks.put("slab", SakuraBlocks.register(name, new DecorateVerticalSlab(this.prop)));
       return this;
     }
    public Builder addStairs() {
       return addStairs(this.name + "_stairs");
     }
    public Builder addStairs(String name) {
       if (!this.blocks.containsKey("cube")) {
         addStandardCube();
       }
       this.blocks.put("stairs", SakuraBlocks.register(name, (Block)new DecorateStairs(((Block)this.blocks.get("cube")).func_176223_P(), this.prop)));
       return this;
     }
    public Block[] build(boolean isFillRest) {
       if (isFillRest) {
         fillRest();
       }
       return (Block[])this.blocks.values().toArray((Object[])new Block[0]);
     }
   }
 }

