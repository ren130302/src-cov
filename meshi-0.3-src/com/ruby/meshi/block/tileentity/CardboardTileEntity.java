 package com.ruby.meshi.block.tileentity;
import com.google.common.collect.Lists;
 import com.ruby.meshi.block.Cardboard;
 import com.ruby.meshi.client.renderer.animation.AnimationSet;
 import com.ruby.meshi.client.renderer.animation.AnimationTile;
 import com.ruby.meshi.client.renderer.animation.EntityModelAnimation;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.List;
 import java.util.function.Predicate;
 import javax.annotation.Nullable;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.passive.CatEntity;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.state.IProperty;
 import net.minecraft.tileentity.ITickableTileEntity;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.AxisAlignedBB;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(value = Dist.CLIENT, _interface = AnimationTile.class)
 public class CardboardTileEntity
   extends TileEntity
   implements ITickableTileEntity, AnimationTile {
   private CompoundNBT nbt;
   private static final String CAT = "cat";
   private static final String CAT_DATA = "cat_data";
   @OnlyIn(Dist.CLIENT)
   private List<EntityModelAnimation> animation;
   public boolean isOccupied = false;
    public CardboardTileEntity() {
     super(HiganTileEntityType.CARDBOARD);
   }

   public void func_73660_a() {
     if ((func_145831_w()).field_72995_K) {
       animateTick();
     }
   }

   public void animateTick() {
     if (getAnimations().isEmpty()) {
       int animeNo = this.field_145850_b.func_201674_k().nextInt(3);
       if (animeNo == 0) {
         getAnimations().addAll(AnimationSet.createSwingHead(getDirection(), true));
       } else if (animeNo == 1) {
         getAnimations().addAll(AnimationSet.createWatchHead(getDirection(), func_174877_v(), searchEntity(e -> e instanceof net.minecraft.entity.LivingEntity), true));
       } else {
         getAnimations().addAll(AnimationSet.createTail(getDirection()));
       }
        } else if (!hasCatNBT()) {
       getAnimations().clear();
     }    
     getAnimations().forEach(EntityModelAnimation::animationTick);
   }
  public Entity searchEntity(Predicate<Entity> tester) {
     return this.field_145850_b.func_175674_a(null, (new AxisAlignedBB(this.field_174879_c)).func_186662_g(10.0D), tester).stream().findAny().orElse(null);
   }
  private Direction getDirection() {
     return (Direction)func_195044_w().func_177229_b((IProperty)Cardboard.field_185512_D);
   }
  public void compoundCat(CatEntity cat) {
     getNBT().func_218657_a("cat_data", (INBT)cat.func_189511_e(new CompoundNBT()));
     cat.func_70106_y();
   }
  public void createCatFromNBT() {
     if (hasCatNBT()) {
       CatEntity cat = (CatEntity)EntityType.field_220360_g.func_200721_a(func_145831_w());
       cat.func_70020_e(getCatNBT());
       func_145831_w().func_217376_c((Entity)cat);
       this.nbt = null;
     }  }
   public boolean hasCatNBT() {
     return (this.nbt != null && !this.nbt.isEmpty());
   }
  public int getCatType() {
     if (this.nbt == null || this.nbt.isEmpty()) {
       return 0;
     }
     return getCatNBT().func_74762_e("CatType");
   }
  @Nullable
   public CompoundNBT getCatNBT() {
     if (this.nbt != null && !this.nbt.isEmpty()) {
       return this.nbt.func_74775_l("cat_data");
     }
     return null;
   }
  private CompoundNBT getNBT() {
     if (this.nbt == null) {
       this.nbt = new CompoundNBT();
     }
     return this.nbt;
   }

   public void func_145839_a(CompoundNBT compound) {
     super.func_145839_a(compound);
     readData(compound);
     this.isOccupied = hasCatNBT();
   }

   public CompoundNBT func_189515_b(CompoundNBT compound) {
     super.func_189515_b(compound);
     return writeData(compound);
   }
  public void readData(CompoundNBT compound) {
     this.nbt = compound.func_74775_l("cat");
   }
  public CompoundNBT writeData(CompoundNBT compound) {
     if (this.nbt != null && !this.nbt.isEmpty()) {
       compound.func_218657_a("cat", (INBT)this.nbt);
     }
     return compound;
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

   public List<EntityModelAnimation> getAnimations() {
     if (this.animation == null) {
       this.animation = Lists.newArrayList();
     }
     return this.animation;
   }
 }

