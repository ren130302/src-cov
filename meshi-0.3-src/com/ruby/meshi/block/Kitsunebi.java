 package com.ruby.meshi.block;
import com.ruby.meshi.client.paticle.KitsunebiParticle;
 import java.util.Random;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.particle.Particle;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.util.IItemProvider;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
public class Kitsunebi extends Block {
   public static final VoxelShape AABB = Block.func_208617_a(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
  public Kitsunebi(Block.Properties properties) {
     super(properties);
   }

   @OnlyIn(Dist.CLIENT)
   public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
     if (isVisible((Entity)(Minecraft.func_71410_x()).field_71439_g)) {
       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((Particle)new KitsunebiParticle(worldIn, (pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F), (IItemProvider)func_199767_j()));
     }
   }

   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return isVisible(context.getEntity()) ? AABB : VoxelShapes.func_197880_a();
   }
  private boolean isVisible(Entity entity) {
     if (entity != null && entity instanceof PlayerEntity) {
       PlayerEntity player = (PlayerEntity)entity;
       return (player.func_184614_ca().func_77973_b() == func_199767_j() || player.func_184592_cb().func_77973_b() == func_199767_j());
     }    return false;
   }

   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
     return true;
   }

   public BlockRenderType func_149645_b(BlockState state) {
     return BlockRenderType.INVISIBLE;
   }

   public boolean func_200124_e(BlockState state) {
     return false;
   }

   @OnlyIn(Dist.CLIENT)
   public float func_220080_a(BlockState state, IBlockReader worldIn, BlockPos pos) {
     return 1.0F;
   }

   public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
     return false;
   }
 }

