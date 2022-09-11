 package com.ruby.meshi.block.tileentity;
import com.ruby.meshi.block.SlideDoor;
 import com.ruby.meshi.init.HiganTileEntityType;
 import net.minecraft.block.BlockState;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.properties.DoorHingeSide;
 import net.minecraft.tileentity.ITickableTileEntity;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.Direction;
public class SlideDoorTileEntity extends TileEntity implements ITickableTileEntity {
   public float posX;
   public float posZ;
   public float nowPosX;
   public float nowPosZ;
    public SlideDoorTileEntity() {
     super(HiganTileEntityType.SLIDEDOOR);
   }

   public void func_73660_a() {
     this.nowPosX = this.posX;
     this.nowPosZ = this.posZ;
     Direction facing = ((Direction)func_195044_w().func_177229_b((IProperty)SlideDoor.field_176520_a)).func_176735_f();
     if (func_195044_w().func_177229_b((IProperty)SlideDoor.field_176521_M) == DoorHingeSide.RIGHT) {
       facing = facing.func_176734_d();
     }
     boolean isOpen = ((Boolean)func_195044_w().func_177229_b((IProperty)SlideDoor.field_176519_b)).booleanValue();
     if (isOpen) {
       this.posX = facing.func_82601_c();
       this.posZ = facing.func_82599_e();
       if (this.nowPosX != this.posX && this.nowPosZ != this.posZ &&        !(func_145831_w()).field_72995_K) {
         func_145831_w().func_180501_a(this.field_174879_c, (BlockState)func_195044_w().func_206870_a((IProperty)SlideDoor.MOVED, Boolean.valueOf(true)), 2);
       }
     } else {
            this.posX = 0.0F;
       this.posZ = 0.0F;
       if (this.nowPosX == 0.0F && this.nowPosZ == 0.0F) {
         if (!(func_145831_w()).field_72995_K) {
           func_145831_w().func_180501_a(func_174877_v(), (BlockState)func_195044_w().func_206870_a((IProperty)SlideDoor.MOVED, Boolean.valueOf(false)), 2);
         }
         func_145831_w().func_175713_t(func_174877_v());
       }    }  }
 
   public boolean hasFastRenderer() {
     return true;
   }
 }

