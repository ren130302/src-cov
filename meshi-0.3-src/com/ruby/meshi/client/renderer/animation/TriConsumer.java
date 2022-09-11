 package com.ruby.meshi.client.renderer.animation;
import java.util.Objects;
 @FunctionalInterface
 public interface TriConsumer<T, U, V> {
   void accept(T paramT, U paramU, V paramV);
    default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
     Objects.requireNonNull(after);
        return (l, r, v) -> {
         accept((T)l, (U)r, (V)v);
         after.accept(l, r, v);
       };
   }
 }

