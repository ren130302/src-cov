 package com.ruby.meshi.init;
import com.ruby.meshi.block.JPChest;
 import com.ruby.meshi.block.ManekiNeko;
 import com.ruby.meshi.block.SakuraBlocks;
 import com.ruby.meshi.block.SlideDoor;
 import java.util.function.Supplier;
 import net.minecraft.block.Block;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.tileentity.TileEntityType;

 
 
 
 
 
 
 public class HiganTileEntityType
 {
   public static final TileEntityType<?> WALL_SHELF = create("wall_shelf", com.ruby.meshi.block.tileentity.WallShelfTileEntity::new, new Block[] { SakuraBlocks.WALL_SHELF });
   public static final TileEntityType<?> COLLECTOR_PLATE = create("collector_pressure_plate", com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity::new, new Block[] { SakuraBlocks.COLLECTOR_PLATE, SakuraBlocks.DELIVERY_PLATE });
   public static final TileEntityType<?> BAMBOO_POT = create("bamboo_pot", com.ruby.meshi.block.tileentity.BambooPotTileEntity::new, new Block[] { SakuraBlocks.BAMBOO_POT });
   public static final TileEntityType<?> MILLSTONE = create("millstone", com.ruby.meshi.block.tileentity.MillstoneTileEntity::new, new Block[] { SakuraBlocks.MILLSTONE });
   public static final TileEntityType<?> HEARTH = create("hearth", com.ruby.meshi.block.tileentity.HearthTileEntity::new, new Block[] { SakuraBlocks.HEARTH });
   public static final TileEntityType<?> CARDBOARD = create("cardboard", com.ruby.meshi.block.tileentity.CardboardTileEntity::new, new Block[] { SakuraBlocks.CARDBOARD });
   public static final TileEntityType<?> SLIDEDOOR = create("slidedoor", com.ruby.meshi.block.tileentity.SlideDoorTileEntity::new, getIsInstanceBlocks(SlideDoor.class));
   public static final TileEntityType<?> MINIATUE = create("miniature", com.ruby.meshi.block.tileentity.MiniatureTileEntity::new, new Block[] { SakuraBlocks.MINIATURE });
   public static final TileEntityType<?> JPCHEST = create("jpchest", com.ruby.meshi.block.tileentity.ContainerTileEntity::new, getIsInstanceBlocks(JPChest.class));
   public static final TileEntityType<?> MANEKINEKO = create("manekineko", com.ruby.meshi.block.tileentity.ManekiNekoTileEntity::new, getIsInstanceBlocks(ManekiNeko.class));
  private static TileEntityType<? extends TileEntity> create(String key, Supplier<? extends TileEntity> tile, Block... block) {
     return (TileEntityType<? extends TileEntity>)TileEntityType.Builder.func_223042_a(tile, block).func_206865_a(null).setRegistryName("meshi", key);
   }
  private static Block[] getIsInstanceBlocks(Class<?> clazz) {
     return (Block[])RegisterEvents.getFields(SakuraBlocks.class).filter(clazz::isInstance).toArray(x$0 -> new Block[x$0]);
   }
 }

