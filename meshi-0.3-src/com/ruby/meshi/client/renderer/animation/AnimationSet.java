 package com.ruby.meshi.client.renderer.animation;
import com.google.common.collect.Lists;
 import java.util.List;
 import net.minecraft.entity.Entity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;

 public class AnimationSet
 {
   public static List<EntityModelAnimation> createSwingHead(Direction direction, boolean hand) {
     List<EntityModelAnimation> list = Lists.newArrayList();
     EntityModelAnimation head = new SwingHead(direction);
     list.add(head);
     if (hand) {
       list.add((new ViewHand(direction)).setParent(head));
     }
     return list;
   }
  public static List<EntityModelAnimation> createWatchHead(Direction direction, BlockPos pos, Entity entity, boolean hand) {
     List<EntityModelAnimation> list = Lists.newArrayList();
     EntityModelAnimation head = (new WatchEntity(direction, pos)).setTarget(entity);
     list.add(head);
     if (hand) {
       list.add((new ViewHand(direction)).setParent(head));
     }
     return list;
   }
  public static List<EntityModelAnimation> createTail(Direction direction) {
     List<EntityModelAnimation> list = Lists.newArrayList();
     list.add(new SwingTail(direction));
     return list;
   }
 }

