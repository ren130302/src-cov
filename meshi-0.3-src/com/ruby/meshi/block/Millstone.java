 package com.ruby.meshi.block;
 import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
 import com.ruby.meshi.client.CreativeTab;
 import com.ruby.meshi.client.renderer.SimpleItemStackRender;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.InventoryHelper;
 import net.minecraft.inventory.container.INamedContainerProvider;
 import net.minecraft.item.Item;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.stats.Stats;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
public class Millstone extends Block implements CustomItemBlock {
   public static final BooleanProperty ENABLE = BlockStateProperties.field_208180_g;
  public Millstone(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)ENABLE, Boolean.valueOf(false)));
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     builder.func_206894_a(new IProperty[] { (IProperty)ENABLE });
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT;
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return BlockRenderType.INVISIBLE;
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (worldIn.field_72995_K) {
       return true;
     }
     INamedContainerProvider inamedcontainerprovider = func_220052_b(state, worldIn, pos);
     if (inamedcontainerprovider != null) {
       player.func_213829_a(inamedcontainerprovider);
       player.func_71029_a(Stats.field_199092_j.func_199076_b(Stats.field_188061_aa));
     }    return true;
   }

   public INamedContainerProvider func_220052_b(BlockState state, World world, BlockPos pos) {
     TileEntity tileentity = world.func_175625_s(pos);
     return (tileentity instanceof INamedContainerProvider) ? (INamedContainerProvider)tileentity : null;
   }

   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return (TileEntity)new MillstoneTileEntity();
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.ITEM_GROUP).setTEISR(() -> new SimpleItemStackRender(this));
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }

   public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
     if (state.func_177230_c() != newState.func_177230_c()) {
       TileEntity tileentity = worldIn.func_175625_s(pos);
       if (tileentity instanceof MillstoneTileEntity) {
         InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
         worldIn.func_175666_e(pos, this);
       }           super.func_196243_a(state, worldIn, pos, newState, isMoving);    } 
   }
 }

