 package com.ruby.meshi.client.renderer;
import com.google.common.collect.Lists;
 import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
 import java.nio.ByteBuffer;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Random;
 import java.util.function.BiConsumer;
 import javax.vecmath.Matrix4f;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.BlockRendererDispatcher;
 import net.minecraft.client.renderer.BufferBuilder;
 import net.minecraft.client.renderer.RenderHelper;
 import net.minecraft.client.renderer.Tessellator;
 import net.minecraft.client.renderer.model.BakedQuad;
 import net.minecraft.client.renderer.model.IBakedModel;
 import net.minecraft.client.renderer.model.ItemCameraTransforms;
 import net.minecraft.client.renderer.model.ItemOverrideList;
 import net.minecraft.client.renderer.texture.AtlasTexture;
 import net.minecraft.client.renderer.texture.TextureAtlasSprite;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
 import net.minecraft.client.renderer.vertex.VertexFormat;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.world.IEnviromentBlockReader;
 import net.minecraft.world.World;
 import net.minecraftforge.client.model.data.IModelData;
 import net.minecraftforge.client.model.pipeline.LightUtil;
 import org.apache.commons.lang3.tuple.Pair;
 import org.lwjgl.opengl.GL11;

 public class MiniatureRender
   extends TileEntityRenderer<MiniatureTileEntity>
 {
   protected static BlockRendererDispatcher blockRenderer;
   Random rand = new Random();
   BB wrapBB = new BB();

   public void render(MiniatureTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
     if (blockRenderer == null) {
       blockRenderer = Minecraft.func_71410_x().func_175602_ab();
     }
     Tessellator tessellator = Tessellator.func_178181_a();
     this.wrapBB.buffer = tessellator.func_178180_c();
     this.wrapBB.offsetSize = 1.0F / te.getSize();
     func_147499_a(AtlasTexture.field_110575_b);
     RenderHelper.func_74518_a();
     if (Minecraft.func_71379_u()) {
       GlStateManager.shadeModel(7425);
     } else {
       GlStateManager.shadeModel(7424);
     }       GlStateManager.enableCull();    
     GL11.glPushMatrix();
     GlStateManager.disableBlend();
     this.wrapBB.func_181668_a(7, DefaultVertexFormats.field_176600_a);
        if (te.renderSolidCache != null) {
       te.renderSolidCache.rewind();
       this.wrapBB.func_178993_a(te.renderSolidState);
       this.wrapBB.putBulkData(te.renderSolidCache);
     } else {
       renderMiniatureBlocks(te, this.wrapBB, true, (b, s) -> {
             te.renderSolidCache = b;
                        te.renderSolidState = s;
           });
     }    GL11.glTranslated(x, y, z);    
     this.wrapBB.func_178969_c(0.0D, 0.0D, 0.0D);
     tessellator.func_78381_a();
     GL11.glPopMatrix();
       GL11.glPushMatrix();
     GlStateManager.enableBlend();
     GlStateManager.blendFunc(770, 771);
     this.wrapBB.func_181668_a(7, DefaultVertexFormats.field_176600_a);
        if (te.renderCache != null) {
       te.renderCache.rewind();
       this.wrapBB.func_178993_a(te.renderState);
       this.wrapBB.putBulkData(te.renderCache);
     } else {
       renderMiniatureBlocks(te, this.wrapBB, false, (b, s) -> {
             te.renderCache = b;
                        te.renderState = s;
           });
     }    GL11.glTranslated(x, y, z);    
     this.wrapBB.func_181674_a((float)-x, (float)-y, (float)-z);
     this.wrapBB.func_178969_c(0.0D, 0.0D, 0.0D);
     tessellator.func_78381_a();
     GL11.glPopMatrix();
        RenderHelper.func_74519_b();
   }

   public void renderMiniatureBlocks(MiniatureTileEntity te, BufferBuilder buffer, boolean isSolid, BiConsumer<ByteBuffer, BufferBuilder.State> container) {
     BlockPos worldPos = te.func_174877_v();
     World world = te.getInnerWorld();
     BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
       for (int i = 0; i < te.getSize(); i++) {
       for (int j = 0; j < te.getSize(); j++) {
         for (int k = 0; k < te.getSize(); k++) {
           mpos.func_181079_c(i, j, k);
           BlockState innerState = te.getInnerState(i, j, k);
           if (innerState != MiniatureTileEntity.EMPTY) {
             this.wrapBB.innerPos = (BlockPos)mpos;
             IBakedModel model = blockRenderer.func_175023_a().func_178125_b(innerState);
             if (te.isNoTexResize()) {
               model = new BakeWraper(model, mpos.func_185334_h(), te.getSize());
             }
             float offsetSize = 1.0F / te.getSize();
             this.wrapBB.func_178969_c((mpos.func_177958_n() * offsetSize), (mpos.func_177956_o() * offsetSize), (mpos.func_177952_p() * offsetSize));
             if (isSolid) {
               if (innerState.func_185901_i() != BlockRenderType.INVISIBLE && hasPriority(innerState.func_177230_c().func_180664_k())) {
                 blockRenderer.func_175019_b().renderModel((IEnviromentBlockReader)world, model, innerState, (BlockPos)mpos, this.wrapBB, true, this.rand, 42L, model.getModelData((IEnviromentBlockReader)world, worldPos, innerState, null));
               }
             } else {
               if (innerState.func_185901_i() != BlockRenderType.INVISIBLE && !hasPriority(innerState.func_177230_c().func_180664_k())) {
                 blockRenderer.func_175019_b().renderModel((IEnviromentBlockReader)world, model, innerState, (BlockPos)mpos, this.wrapBB, true, this.rand, 42L, model.getModelData((IEnviromentBlockReader)world, worldPos, innerState, null));
               }
               if (!innerState.func_204520_s().func_206888_e()) {
                 blockRenderer.func_215331_a((BlockPos)mpos, (IEnviromentBlockReader)world, this.wrapBB, innerState.func_204520_s());
               }
             }          }        }      }    }    this.wrapBB.func_178966_f().flip();
     container.accept(ByteBuffer.allocate(this.wrapBB.func_178966_f().limit()).put(this.wrapBB.func_178966_f()), this.wrapBB.func_181672_a());
     this.wrapBB.func_178966_f().rewind();
   }
  class BakeWraper
     implements IBakedModel {
     IBakedModel model;
     BlockPos pos;
     int size;
        public BakeWraper(IBakedModel model, BlockPos pos, int size) {
       this.model = model;
       this.pos = pos;
       this.size = size;
     }
    BakeWraper setSize(int size) {
       this.size = size;
       return this;
     }
    BakeWraper setPos(BlockPos pos) {
       this.pos = pos;
       return this;
     }
    BakeWraper setModel(IBakedModel model) {
       this.model = model;
       return this;
     }

     public List<BakedQuad> func_200117_a(BlockState state, Direction side, Random rand) {
       return this.model.func_200117_a(state, side, rand);
     }

     public boolean func_177555_b() {
       return this.model.func_177555_b();
     }

     public boolean func_177556_c() {
       return this.model.func_177556_c();
     }

     public boolean func_188618_c() {
       return this.model.func_188618_c();
     }

     public TextureAtlasSprite func_177554_e() {
       return this.model.func_177554_e();
     }

     public ItemCameraTransforms func_177552_f() {
       return this.model.func_177552_f();
     }

     public ItemOverrideList func_188617_f() {
       return this.model.func_188617_f();
     }

     public IBakedModel getBakedModel() {
       return this.model.getBakedModel();
     }

     public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
       List<BakedQuad> quads = this.model.getQuads(state, side, rand, extraData);
       List<BakedQuad> transQuads = Lists.newArrayList();
       for (BakedQuad quad : quads) {
         int[] data = Arrays.copyOf(quad.func_178209_a(), (quad.func_178209_a()).length);
         float[] v = new float[4];

 
 
         
         float uOffset = (quad.func_187508_a().func_94212_f() - quad.func_187508_a().func_94209_e()) / this.size;
         float vOffset = (quad.func_187508_a().func_94210_h() - quad.func_187508_a().func_94206_g()) / this.size;
         float[][] uv = new float[4][4];
         int[] posUV = get2DOffset(quad.func_178210_d());
                uv[0][0] = quad.func_187508_a().func_94209_e() + posUV[0] * uOffset;
         uv[0][1] = quad.func_187508_a().func_94206_g() + posUV[1] * vOffset;
                uv[1][0] = quad.func_187508_a().func_94209_e() + posUV[0] * uOffset;
         uv[1][1] = quad.func_187508_a().func_94210_h() - (this.size - 1 - posUV[1]) * vOffset;
                uv[2][0] = quad.func_187508_a().func_94212_f() - (this.size - 1 - posUV[0]) * uOffset;
         uv[2][1] = quad.func_187508_a().func_94210_h() - (this.size - 1 - posUV[1]) * vOffset;
                uv[3][0] = quad.func_187508_a().func_94212_f() - (this.size - 1 - posUV[0]) * uOffset;
         uv[3][1] = quad.func_187508_a().func_94206_g() + posUV[1] * vOffset;
                for (int i = 0; i < v.length; i++) {
           v = uv[i];
           LightUtil.pack(v, data, quad.getFormat(), i, 2);
         }               TextureAtlasSprite ap = quad.func_187508_a();
         transQuads.add(new BakedQuad(data, quad.func_178211_c(), quad.func_178210_d(), quad.func_187508_a(), quad.shouldApplyDiffuseLighting(), quad.getFormat()));
       }      return transQuads;
     }
    int[] get2DOffset(Direction dir) {
       int[] uv = new int[2];
       switch (dir) {
         case DOWN:
           return new int[] { this.pos.func_177958_n(), this.size - 1 - this.pos.func_177952_p() };
         case EAST:
           return new int[] { this.size - 1 - this.pos.func_177952_p(), this.size - 1 - this.pos.func_177956_o() };
         case NORTH:
           return new int[] { this.size - 1 - this.pos.func_177958_n(), this.size - 1 - this.pos.func_177956_o() };
         case SOUTH:
           return new int[] { this.pos.func_177958_n(), this.size - 1 - this.pos.func_177956_o() };
         case UP:
           return new int[] { this.pos.func_177958_n(), this.pos.func_177952_p() };
         case WEST:
           return new int[] { this.pos.func_177952_p(), this.size - 1 - this.pos.func_177956_o() };
       }      
       return uv;
     }

     public boolean isAmbientOcclusion(BlockState state) {
       return this.model.isAmbientOcclusion(state);
     }

     public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
       return this.model.handlePerspective(cameraTransformType);
     }

     public IModelData getModelData(IEnviromentBlockReader world, BlockPos pos, BlockState state, IModelData tileData) {
       return this.model.getModelData(world, pos, state, tileData);
     }

     public TextureAtlasSprite getParticleTexture(IModelData data) {
       return this.model.getParticleTexture(data);
     }
   }

   boolean hasPriority(BlockRenderLayer layer) {
     return (layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT);
   }
  class BB extends BufferBuilder {
     BufferBuilder buffer;
     BlockPos innerPos;
     float offsetSize;
        public BB() {
       super(0);
     }

     public int hashCode() {
       return this.buffer.hashCode();
     }

     public void func_181674_a(float cameraX, float cameraY, float cameraZ) {
       this.buffer.func_181674_a(cameraX, cameraY, cameraZ);
     }

     public boolean equals(Object obj) {
       return this.buffer.equals(obj);
     }

     public BufferBuilder.State func_181672_a() {
       return this.buffer.func_181672_a();
     }

     public void func_178993_a(BufferBuilder.State state) {
       this.buffer.func_178993_a(state);
     }

     public void func_178965_a() {
       this.buffer.func_178965_a();
     }

     public void func_181668_a(int glMode, VertexFormat format) {
       this.buffer.func_181668_a(glMode, format);
     }

     public BufferBuilder func_187315_a(double u, double v) {
       return this.buffer.func_187315_a(u, v);
     }

     public BufferBuilder func_187314_a(int skyLight, int blockLight) {
       return this.buffer.func_187314_a(skyLight, blockLight);
     }

     public void func_178962_a(int vertex0, int vertex1, int vertex2, int vertex3) {
       this.buffer.func_178962_a(vertex0, vertex1, vertex2, vertex3);
     }

     public void func_178987_a(double x, double y, double z) {
       this.buffer.func_178987_a(x - this.innerPos.func_177958_n(), y - this.innerPos.func_177956_o(), z - this.innerPos.func_177952_p());
     }

     public String toString() {
       return this.buffer.toString();
     }

     public int func_78909_a(int vertexIndex) {
       return this.buffer.func_78909_a(vertexIndex);
     }

     public void func_178978_a(float red, float green, float blue, int vertexIndex) {
       this.buffer.func_178978_a(red, green, blue, vertexIndex);
     }

     public void func_178994_b(float red, float green, float blue, int vertexIndex) {
       this.buffer.func_178994_b(red, green, blue, vertexIndex);
     }

     public void func_178972_a(int index, int red, int green, int blue) {
       this.buffer.func_178972_a(index, red, green, blue);
     }

     public void func_78914_f() {
       this.buffer.func_78914_f();
     }

     public BufferBuilder func_181666_a(float red, float green, float blue, float alpha) {
       return this.buffer.func_181666_a(red, green, blue, alpha);
     }

     public BufferBuilder func_181669_b(int red, int green, int blue, int alpha) {
       return this.buffer.func_181669_b(red, green, blue, alpha);
     }

     public void func_178981_a(int[] vertexData) {
       this.buffer.func_178981_a(scale(vertexData, this.offsetSize));
     }

     public void func_181675_d() {
       this.buffer.func_181675_d();
     }

     public BufferBuilder func_181662_b(double x, double y, double z) {
       double renderX = (x - this.innerPos.func_177958_n()) * this.offsetSize;
       double renderY = (y - this.innerPos.func_177956_o()) * this.offsetSize;
       double renderZ = (z - this.innerPos.func_177952_p()) * this.offsetSize;
       return this.buffer.func_181662_b(renderX, renderY, renderZ);
     }

     public void func_178975_e(float x, float y, float z) {
       this.buffer.func_178975_e(x, y, z);
     }

     public BufferBuilder func_181663_c(float x, float y, float z) {
       return this.buffer.func_181663_c(x, y, z);
     }

     public void func_178969_c(double x, double y, double z) {
       this.buffer.func_178969_c(x, y, z);
     }

     public void func_178977_d() {
       this.buffer.func_178977_d();
     }

     public ByteBuffer func_178966_f() {
       return this.buffer.func_178966_f();
     }

     public VertexFormat func_178973_g() {
       return this.buffer.func_178973_g();
     }

     public int func_178989_h() {
       return this.buffer.func_178989_h();
     }

     public int func_178979_i() {
       return this.buffer.func_178979_i();
     }

     public void func_178968_d(int argb) {
       this.buffer.func_178968_d(argb);
     }

     public void func_178990_f(float red, float green, float blue) {
       this.buffer.func_178990_f(red, green, blue);
     }

     public void putColorRGBA(int index, int red, int green, int blue, int alpha) {
       this.buffer.putColorRGBA(index, red, green, blue, alpha);
     }

     public boolean isColorDisabled() {
       return this.buffer.isColorDisabled();
     }

     public void putBulkData(ByteBuffer buffer) {
       this.buffer.putBulkData(buffer);
     }

 
 
 
     private int[] scale(int[] v, float s) {
       v[0] = transform(v[0], s);
       v[7] = transform(v[7], s);
       v[14] = transform(v[14], s);
       v[21] = transform(v[21], s);
            v[1] = transform(v[1], s);
       v[8] = transform(v[8], s);
       v[15] = transform(v[15], s);
       v[22] = transform(v[22], s);
            v[2] = transform(v[2], s);
       v[9] = transform(v[9], s);
       v[16] = transform(v[16], s);
       v[23] = transform(v[23], s);
            return v;
     }
    private int transform(int i, float s) {
       float f = Float.intBitsToFloat(i);
       f *= s;
       return Float.floatToRawIntBits(f);
     }
   }
 }

