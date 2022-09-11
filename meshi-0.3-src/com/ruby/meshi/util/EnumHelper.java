 package com.ruby.meshi.util;
import java.io.Serializable;
 import java.lang.reflect.Constructor;
 import java.lang.reflect.Field;
 import java.lang.reflect.Method;
 import java.util.Arrays;
 import java.util.stream.Stream;
 import sun.misc.Unsafe;
 import sun.reflect.ConstructorAccessor;

 
 
 
 
 
 public class EnumHelper
 {
   public static <T extends Enum<?>> T addEnum(Class<T> target, String name, int ordinal, Class<?>[] paramTypes, Object[] param) {
     Enum enum_;
     T result = null;
     try {
       Method m = Constructor.class.getDeclaredMethod("acquireConstructorAccessor", new Class[0]);
       m.setAccessible(true);
            Constructor<T> cnst = target.getDeclaredConstructor((Class[])Stream.concat(Stream.of((Object[])new Class[] { String.class, int.class }, ), Arrays.stream((Object[])paramTypes)).toArray(x$0 -> new Class[x$0]));
       ConstructorAccessor ca = (ConstructorAccessor)m.invoke(cnst, new Object[0]);
            Object[] args = Stream.concat(Stream.of((Object[])new Serializable[] { name, Integer.valueOf(ordinal) }, ), Arrays.stream(param)).toArray();
       enum_ = (Enum)ca.newInstance(args);
            addValueToEnum(enum_);
     } catch (Exception e) {
       e.printStackTrace();
     }       return (T)enum_;
   }

   private static <T extends Enum<?>> void addValueToEnum(T newValue) throws Exception {
     Field f;
     try {
       f = newValue.getClass().getDeclaredField("$VALUES");
     } catch (NoSuchFieldException e) {
            f = newValue.getClass().getDeclaredField("ENUM$VALUES");    } 
     Field uf = Unsafe.class.getDeclaredField("theUnsafe");
     f.setAccessible(true);
     uf.setAccessible(true);
     Enum[] arrayOfEnum1 = (Enum[])f.get(null);
     Enum[] arrayOfEnum2 = Arrays.<Enum>copyOf(arrayOfEnum1, arrayOfEnum1.length + 1);
     arrayOfEnum2[arrayOfEnum1.length] = (Enum)newValue;
        Unsafe unsafe = (Unsafe)uf.get(null);
     unsafe.putObjectVolatile(unsafe.staticFieldBase(f), unsafe.staticFieldOffset(f), arrayOfEnum2);
   }
 }

