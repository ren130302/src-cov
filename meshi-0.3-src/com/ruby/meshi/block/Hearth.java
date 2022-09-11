 package com.ruby.meshi.block;
 import com.ruby.meshi.block.tileentity.HearthTileEntity;
 import com.ruby.meshi.client.CreativeTab;
 import com.ruby.meshi.client.renderer.SimpleItemStackRender;
 import com.ruby.meshi.common.HiganTags;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.InventoryHelper;
 import net.minecraft.inventory.container.INamedContainerProvider;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.EnumProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer;
 import net.minecraft.stats.Stats;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Hand;
 import net.minecraft.util.IStringSerializable;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
public class Hearth extends PlayerFacingBlock implements CustomItemBlock {
   public static final EnumProperty<HearthStateType> TYPE = EnumProperty.func_177709_a("type", HearthStateType.class);
  public static final VoxelShape AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
   public Hearth(Block.Properties properties) {
     super(properties);
     func_180632_j((BlockState)func_176223_P().func_206870_a((IProperty)TYPE, HearthStateType.NONE));
   }

   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
     super.func_206840_a(builder);
     builder.func_206894_a(new IProperty[] { (IProperty)TYPE });
   }

   public BlockRenderLayer func_180664_k() {
     return BlockRenderLayer.CUTOUT;
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return BlockRenderType.INVISIBLE;
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }

   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return (TileEntity)new HearthTileEntity();
   }

   public Item.Properties getProperty(Item.Properties prop) {
     return prop.func_200916_a(CreativeTab.ITEM_GROUP).setTEISR(() -> new SimpleItemStackRender((Block)this));
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     if (state.func_177229_b((IProperty)TYPE) == HearthStateType.NONE) {
       return AABB;
     }
     return super.func_220053_a(state, worldIn, pos, context);
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

   public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
     if (state.func_177230_c() != newState.func_177230_c()) {
       TileEntity tileentity = worldIn.func_175625_s(pos);
       if (tileentity instanceof HearthTileEntity) {
         InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
         worldIn.func_175666_e(pos, (Block)this);
       }           super.func_196243_a(state, worldIn, pos, newState, isMoving);    }  }
 
   public enum HearthStateType implements IStringSerializable {
     NONE,
     MEAT,
     FISH,
     OTHER;
    public static HearthStateType getType(ItemStack stack) {
       if (HiganTags.ITEM.MEAT.func_199685_a_(stack.func_77973_b())) {
         return MEAT;
       }
       if (HiganTags.ITEM.FISH.func_199685_a_(stack.func_77973_b())) {
         return FISH;
       }
       return OTHER;
     }

     public String func_176610_l() {
       return toString();
     }

     public String toString() {
       return super.toString().toLowerCase();
     }
   }
 }

