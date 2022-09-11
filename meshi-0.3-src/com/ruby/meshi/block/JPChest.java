 package com.ruby.meshi.block;
import com.ruby.meshi.block.tileentity.ContainerTileEntity;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.InventoryHelper;
 import net.minecraft.inventory.container.ChestContainer;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.INamedContainerProvider;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
public class JPChest
   extends PlayerFacingBlock
   implements ContainerTileEntity.MeshiContainer {
   public JPChest(Block.Properties properties) {
     super(properties);
   }

   public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
     if (worldIn.field_72995_K) {
       return true;
     }
     INamedContainerProvider inamedcontainerprovider = func_220052_b(state, worldIn, pos);
     if (inamedcontainerprovider != null) {
       player.func_213829_a(inamedcontainerprovider);
     }
     return true;
   }

   public INamedContainerProvider func_220052_b(BlockState state, World worldIn, BlockPos pos) {
     return (INamedContainerProvider)worldIn.func_175625_s(pos);
   }

   public boolean hasTileEntity(BlockState state) {
     return true;
   }

   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
     return (TileEntity)(new ContainerTileEntity()).setContainerBlock(this);
   }

   public int getSize() {
     return 54;
   }

   public Container createMenu(int id, PlayerInventory player, IInventory inv) {
     return (Container)ChestContainer.func_216984_b(id, player, inv);
   }

   public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
     if (state.func_177230_c() != newState.func_177230_c()) {
       TileEntity tileentity = worldIn.func_175625_s(pos);
       if (tileentity instanceof IInventory) {
         InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
         worldIn.func_175666_e(pos, (Block)this);
       }           super.func_196243_a(state, worldIn, pos, newState, isMoving);    } 
   }
 }

