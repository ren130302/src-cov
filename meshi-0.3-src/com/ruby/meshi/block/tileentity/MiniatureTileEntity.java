 package com.ruby.meshi.block.tileentity;
import com.ruby.meshi.block.Miniature;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.nio.ByteBuffer;
 import java.util.List;
 import javax.annotation.Nullable;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.Blocks;
 import net.minecraft.client.renderer.BufferBuilder;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.item.crafting.RecipeManager;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.nbt.NBTUtil;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.scoreboard.Scoreboard;
 import net.minecraft.state.IProperty;
 import net.minecraft.tags.NetworkTagManager;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Rotation;
 import net.minecraft.util.SoundCategory;
 import net.minecraft.util.SoundEvent;
 import net.minecraft.util.math.AxisAlignedBB;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.ChunkPos;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IEnviromentBlockReader;
 import net.minecraft.world.ITickList;
 import net.minecraft.world.IWorld;
 import net.minecraft.world.LightType;
 import net.minecraft.world.World;
 import net.minecraft.world.biome.Biome;
 import net.minecraft.world.chunk.AbstractChunkProvider;
 import net.minecraft.world.chunk.Chunk;
 import net.minecraft.world.dimension.Dimension;
 import net.minecraft.world.dimension.DimensionType;
 import net.minecraft.world.gen.ChunkGenerator;
 import net.minecraft.world.storage.MapData;
public class MiniatureTileEntity
   extends TileEntity
 {
   public static final BlockState EMPTY = Blocks.field_150350_a.func_176223_P();

 
 
   private int innerSize = 8;
 
   private final MiniWorld inner = new MiniWorld();
 
   public MiniatureTileEntity() {
     super(HiganTileEntityType.MINIATUE);
   }

   public void setSize(int size) {
     this.innerSize = size;
     this.inner.setSize(size);
   }
  public int getSize() {
     return this.innerSize;
   }
  public boolean setInnerState(BlockPos pos, BlockState state) {
     boolean isSet = this.inner.func_175656_a(pos, state);
     if (isSet) {
       func_145831_w().func_175656_a(func_174877_v(), (BlockState)func_195044_w().func_206870_a((IProperty)Miniature.ENABLED, Boolean.valueOf(!isInnerEmpty())));
       func_70296_d();
     }    return isSet;
   }
  public boolean isInnerEmpty() {
     return this.inner.isEmpty();
   }

 
   public BlockState getInnerState(BlockPos pos) {
     return this.inner.func_180495_p(pos);
   }

 
   public IFluidState getInnerFluidState(BlockPos pos) {
     return this.inner.func_204610_c(pos);
   }
  public World getInnerWorld() {
     return this.inner;
   }

 
   public BlockState getInnerState(int x, int y, int z) {
     return this.inner.getInnerBlockState(x, y, z);
   }

   public void func_70296_d() {
     super.func_70296_d();
     removeCache();
   }
  public void removeCache() {
     this.shapeCache = null;
     this.renderSolidCache = null;
     this.renderCache = null;
   }

   public void func_145839_a(CompoundNBT compound) {
     super.func_145839_a(compound);
     readData(compound);
   }

   public CompoundNBT func_189515_b(CompoundNBT compound) {
     CompoundNBT nbt = super.func_189515_b(compound);
     writeData(nbt);
     return nbt;
   }

   public void readData(CompoundNBT nbt) {
     if (nbt.func_74764_b("ntrs")) {
       this.isNoTexResize = nbt.func_74767_n("ntrs");
     }
       if (nbt.func_74764_b("size")) {
       this.innerSize = nbt.func_74762_e("size");
       this.inner.setSize(this.innerSize);
     }    this.inner.loadState(nbt);
     removeCache();
   }
  public CompoundNBT writeData(CompoundNBT nbt) {
     this.inner.saveState(nbt);
     nbt.func_74757_a("ntrs", this.isNoTexResize);
     nbt.func_74768_a("size", this.innerSize);
     return nbt;
   }

   public void handleUpdateTag(CompoundNBT tag) {
     super.handleUpdateTag(tag);
     readData(tag);
   }

   public CompoundNBT func_189517_E_() {
     CompoundNBT tag = super.func_189517_E_();
     writeData(tag);
     return tag;
   }

   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
     readData(pkt.func_148857_g());
   }

   public SUpdateTileEntityPacket func_189518_D_() {
     CompoundNBT var1 = new CompoundNBT();
     writeData(var1);
     return new SUpdateTileEntityPacket(func_174877_v(), 5, var1);
   }

   public void func_145834_a(World worldIn) {
     super.func_145834_a(worldIn);
     this.inner.isRemote = worldIn.func_201670_d();
   }

   public void func_189667_a(Rotation rotationIn) {
     if (rotationIn == Rotation.CLOCKWISE_90) {
       this.inner.rotateInnerState(false);
     } else if (rotationIn == Rotation.COUNTERCLOCKWISE_90) {
       this.inner.rotateInnerState(true);
     }  }
   public boolean isInInnerRange(int x, int y, int z) {
     return this.inner.hasBlockRange(x, y, z);
   }

   public AxisAlignedBB getRenderBoundingBox() {
     return (this.shapeCache != null && !this.shapeCache.func_197766_b()) ? this.shapeCache.func_197752_a().func_186670_a(this.field_174879_c) : super.getRenderBoundingBox();
   }
  public boolean isNoTexResize() {
     return this.isNoTexResize;
   }
  public void setNoTexResize(boolean isNTRS) {
     this.isNoTexResize = isNTRS;
   }
  private class MiniWorld extends World implements IEnviromentBlockReader {
     private BlockState[][][] container = new BlockState[getSize()][getSize()][getSize()];

     public MiniWorld() {
       super(null, new MiniatureTileEntity.DummyDT(MiniatureTileEntity.this), (a, b) -> null, null, false);
       clearContainer();
     }
    void setSize(int size) {
       if (size != this.container.length) {
         this.container = new BlockState[size][size][size];
         clearContainer();
       }    }
     int getSize() {
       return MiniatureTileEntity.this.getSize();
     }
    void clearContainer() {
       for (int i = 0; i < this.container.length; i++) {
         for (int j = 0; j < (this.container[i]).length; j++) {
           for (int k = 0; k < (this.container[i][j]).length; k++) {
             this.container[i][j][k] = MiniatureTileEntity.EMPTY;
           }
         }      }    }
 
 
     public void rotateInnerState(boolean isCCW) {
       this.container = rotateClockwise(this.container, isCCW);
       for (int i = 0; i < getSize(); i++) {
         for (int j = 0; j < getSize(); j++) {
           for (int k = 0; k < getSize(); k++) {
             if (this.container[i][j][k] != MiniatureTileEntity.EMPTY) {
               BlockState newState = this.container[i][j][k].func_185907_a(isCCW ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90);
               if (!newState.hasTileEntity()) {
                 this.container[i][j][k] = newState;
               }
             }          }        }      }      MiniatureTileEntity.this.func_70296_d();
     }

 
     BlockState[][][] rotateClockwise(BlockState[][][] data, boolean isCCW) {
       int rows = getSize();
       int cols = getSize();
       BlockState[][][] t = new BlockState[getSize()][getSize()][getSize()];
       for (int y = 0; y < getSize(); y++) {
         for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
             if (!isCCW) {
               t[rows - i - 1][y][j] = data[j][y][i];
             } else {
               t[j][y][rows - i - 1] = data[i][y][j];
             }          }        }      }      return t;
     }
    public boolean isEmpty() {
       for (int i = 0; i < getSize(); i++) {
         for (int j = 0; j < getSize(); j++) {
           for (int k = 0; k < getSize(); k++) {
             if (this.container[i][j][k] != MiniatureTileEntity.EMPTY) {
               return false;
             }
           }        }      }      return true;
     }
    public void loadState(CompoundNBT nbt) {
       if (this.container.length != getSize()) {
         setSize(getSize());
       }
       clearContainer();
       ListNBT nbtList = (ListNBT)nbt.func_74781_a("state");
       for (INBT contents : nbtList) {
         CompoundNBT c = (CompoundNBT)contents;
         BlockPos pos = NBTUtil.func_186861_c(c);
         BlockState state = NBTUtil.func_190008_d(c.func_74775_l("state"));
         if (hasBlockRange(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
           this.container[pos.func_177958_n()][pos.func_177956_o()][pos.func_177952_p()] = state;
         }
       }    }
     public void saveState(CompoundNBT nbt) {
       ListNBT nbtList = new ListNBT();
       for (int i = 0; i < getSize(); i++) {
         for (int j = 0; j < getSize(); j++) {
           for (int k = 0; k < getSize(); k++) {
             if (hasBlockRange(i, j, k) && this.container[i][j][k] != MiniatureTileEntity.EMPTY) {
               CompoundNBT posNBT = NBTUtil.func_186859_a(new BlockPos(i, j, k));
               CompoundNBT stateNBT = NBTUtil.func_190009_a(this.container[i][j][k]);
               posNBT.func_218657_a("state", (INBT)stateNBT);
               nbtList.add(posNBT);
             }          }        }      }      nbt.func_218657_a("state", (INBT)nbtList);
     }

 
     @Deprecated
     public TileEntity func_175625_s(BlockPos pos) {
       return null;
     }

     public boolean func_175656_a(BlockPos pos, BlockState state) {
       if (!hasBlockRange(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
         Direction outFace = getOutFace(pos);
         BlockPos outerOffsetPos = getThisBlockPosition().func_177972_a(outFace);
         BlockState outerState = getOuterBlockState(outerOffsetPos);
         if (outerState.func_177230_c() instanceof Miniature) {
           TileEntity te = getOuterWorld().func_175625_s(outerOffsetPos);
           if (te != null && te instanceof MiniatureTileEntity && (
             (MiniatureTileEntity)te).getSize() == getSize()) {
             boolean isUpdate = ((MiniatureTileEntity)te).setInnerState(pos.func_177967_a(outFace, -getSize()), state);
             if (isUpdate) {
               getOuterWorld().func_184138_a(outerOffsetPos, outerState, outerState, 2);
             }
             return isUpdate;
           }        }               return false;      } 
       if (state.hasTileEntity()) {
         return false;
       }
       this.container[pos.func_177958_n()][pos.func_177956_o()][pos.func_177952_p()] = state;
       MiniatureTileEntity.this.func_70296_d();
       state.func_196946_a((IWorld)this, pos, 10);
       return true;
     }
    @Nullable
     Direction getOutFace(BlockPos pos) {
       return Direction.func_176737_a(fixPos(pos.func_177958_n()), fixPos(pos.func_177956_o()), fixPos(pos.func_177952_p()));
     }

 
     BlockPos getThisBlockPosition() {
       return MiniatureTileEntity.this.func_174877_v();
     }

 
     BlockState getOuterBlockState(BlockPos outerPos) {
       return getOuterWorld().func_180495_p(outerPos);
     }

 
     IFluidState getOuterFluidState(BlockPos outerPos) {
       return getOuterWorld().func_204610_c(outerPos);
     }

 
     World getOuterWorld() {
       return MiniatureTileEntity.this.func_145831_w();
     }

     public boolean func_180501_a(BlockPos pos, BlockState newState, int flags) {
       return func_175656_a(pos, newState);
     }

 
 
 
     public BlockState func_180495_p(BlockPos pos) {
       if (!hasBlockRange(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
         Direction outFace = getOutFace(pos);
         BlockPos outerOffsetPos = getThisBlockPosition().func_177972_a(outFace);
         BlockState outerState = getOuterBlockState(outerOffsetPos);
         if (outerState.func_177230_c() instanceof Miniature) {
           TileEntity te = getOuterWorld().func_175625_s(outerOffsetPos);
           if (te != null && te instanceof MiniatureTileEntity) {
             if (((MiniatureTileEntity)te).getSize() == getSize()) {
               return ((MiniatureTileEntity)te).getInnerState(pos.func_177967_a(outFace, -getSize()));
             }
             return MiniatureTileEntity.EMPTY;
           }        }                   } 
       
       return this.container[pos.func_177958_n()][pos.func_177956_o()][pos.func_177952_p()];
     }

 
 
 
 
     public BlockState getInnerBlockState(int x, int y, int z) {
       if (hasBlockRange(x, y, z)) {
         return this.container[x][y][z];
       }
       return MiniatureTileEntity.EMPTY;
     }
    boolean hasBlockRange(int x, int y, int z) {
       return (x >= 0 && y >= 0 && z >= 0 && x < this.container.length && y < (this.container[x]).length && z < (this.container[x][y]).length);
     }
    private int fixPos(int pos) {
       return (pos < 0) ? -1 : ((pos >= getSize()) ? 1 : 0);
     }

 
     public IFluidState func_204610_c(BlockPos pos) {
       if (!hasBlockRange(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
         Direction outFace = getOutFace(pos);
         BlockPos outerOffsetPos = getThisBlockPosition().func_177972_a(outFace);
         BlockState outerState = getOuterBlockState(outerOffsetPos);
         if (outerState.func_177230_c() instanceof Miniature) {
           TileEntity te = getOuterWorld().func_175625_s(outerOffsetPos);
           if (te != null && te instanceof MiniatureTileEntity) {
             if (((MiniatureTileEntity)te).getSize() == getSize()) {
               return ((MiniatureTileEntity)te).getInnerFluidState(pos.func_177967_a(outFace, -getSize()));
             }
             return Fluids.field_204541_a.func_207188_f();
           }        }                   } 
       
       return this.container[pos.func_177958_n()][pos.func_177956_o()][pos.func_177952_p()].func_204520_s();
     }

     public Biome func_180494_b(BlockPos pos) {
       return getOuterWorld().func_180494_b(MiniatureTileEntity.this.func_174877_v());
     }

     public int func_175642_b(LightType type, BlockPos pos) {
       return getOuterWorld().func_175642_b(type, MiniatureTileEntity.this.func_174877_v());
     }

     public ITickList<Block> func_205220_G_() {
       return getOuterWorld().func_205220_G_();
     }

     public ITickList<Fluid> func_205219_F_() {
       return getOuterWorld().func_205219_F_();
     }

 
 
     public List<? extends PlayerEntity> func_217369_A() {
       return getOuterWorld().func_217369_A();
     }

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     public Entity func_73045_a(int id) {
       return getOuterWorld().func_73045_a(id);
     }

 
     public int func_201669_a(BlockPos pos, int amount) {
       return getOuterWorld().func_201669_a(pos, amount);
     }

     public MapData func_217406_a(String p_217406_1_) {
       return getOuterWorld().func_217406_a(p_217406_1_);
     }

 
 
     public int func_217395_y() {
       return getOuterWorld().func_217395_y();
     }

 
 
     public Scoreboard func_96441_U() {
       return getOuterWorld().func_96441_U();
     }

     public RecipeManager func_199532_z() {
       return getOuterWorld().func_199532_z();
     }

     public NetworkTagManager func_205772_D() {
       return getOuterWorld().func_205772_D();
     }

 
     public boolean func_195585_a(@Nullable Entity entityIn, VoxelShape shape) {
       return true;
     }

     public boolean func_201670_d() {
       return this.isRemote;
     }

     public AbstractChunkProvider func_72863_F() {
       return getOuterWorld().func_72863_F();
     }
   }
  private class DummyDT
     extends DimensionType
   {
     protected DummyDT() {
       super(0, null, null, null, false);
     }

     public Dimension func_218270_a(World worldIn) {
       return new DummyDim();
     }
    private class DummyDim
       extends Dimension {
       public DummyDim() {
         super(null, null);
       }

       public ChunkGenerator<?> func_186060_c() {
         return null;
       }

       public BlockPos func_206920_a(ChunkPos chunkPosIn, boolean checkValid) {
         return null;
       }

       public BlockPos func_206921_a(int posX, int posZ, boolean checkValid) {
         return null;
       }

       public float func_76563_a(long worldTime, float partialTicks) {
         return 0.0F;
       }

       public boolean func_76569_d() {
         return true;
       }

       public Vec3d func_76562_b(float celestialAngle, float partialTicks) {
         return null;
       }

       public boolean func_76567_e() {
         return false;
       }

       public boolean func_76568_b(int x, int z) {
         return false;
       }
     }
   }
 }

