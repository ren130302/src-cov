 package com.ruby.meshi.client.renderer;
import com.ruby.meshi.block.SlideDoor;
 import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;
 import java.util.Random;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.BlockRendererDispatcher;
 import net.minecraft.client.renderer.BufferBuilder;
 import net.minecraft.client.renderer.chunk.ChunkRenderCache;
 import net.minecraft.client.renderer.model.IBakedModel;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.properties.DoorHingeSide;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.world.IEnviromentBlockReader;
 import net.minecraftforge.client.MinecraftForgeClient;
 import net.minecraftforge.client.model.ModelDataManager;
 import net.minecraftforge.client.model.animation.TileEntityRendererFast;
 import net.minecraftforge.client.model.data.IModelData;
public class SlideDoorRender extends TileEntityRendererFast<SlideDoorTileEntity> {
   protected static BlockRendererDispatcher blockRenderer;
    public void renderTileEntityFast(SlideDoorTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer) {
     BlockState state = te.func_195044_w();
     BlockPos pos = te.func_174877_v();
     if (blockRenderer == null) {
       blockRenderer = Minecraft.func_71410_x().func_175602_ab();
     }
     ChunkRenderCache chunkRenderCache = MinecraftForgeClient.getRegionRenderCache(te.func_145831_w(), pos);
     IBakedModel model = blockRenderer.func_175023_a().func_178125_b(state);
     IModelData data = model.getModelData((IEnviromentBlockReader)chunkRenderCache, pos, state, ModelDataManager.getModelData(te.func_145831_w(), pos));
     Direction facing = ((Direction)state.func_177229_b((IProperty)SlideDoor.field_176520_a)).func_176735_f();
     if (state.func_177229_b((IProperty)SlideDoor.field_176521_M) == DoorHingeSide.RIGHT) {
       facing = facing.func_176734_d();
     }
     BlockPos renderPos = pos.func_177971_a(facing.func_176730_m());
     double renderX = x - renderPos.func_177958_n() + MathHelper.func_219799_g(partialTicks, te.nowPosX, te.posX);
     double renderY = y - renderPos.func_177956_o();
     double renderZ = z - renderPos.func_177952_p() + MathHelper.func_219799_g(partialTicks, te.nowPosZ, te.posZ);
     Random rand = new Random();
     buffer.func_178969_c(renderX, renderY, renderZ);
     blockRenderer.func_175019_b().renderModel((IEnviromentBlockReader)chunkRenderCache, model, state, renderPos, buffer, false, rand, 42L, data);
   }
 }

