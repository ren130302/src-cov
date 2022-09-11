 package com.ruby.meshi.block.tileentity;
import com.ruby.meshi.block.Hearth;
 import com.ruby.meshi.common.inventory.HearthContainer;
 import com.ruby.meshi.crafting.CookingTimerRecipe;
 import com.ruby.meshi.crafting.HearthRecipe;
 import com.ruby.meshi.crafting.HearthShapelessRecipe;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Map;
 import java.util.Optional;
 import java.util.Random;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;
 import java.util.stream.Stream;
 import net.minecraft.block.BlockState;
 import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentHelper;
 import net.minecraft.enchantment.Enchantments;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.IRecipeHelperPopulator;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.TieredItem;
 import net.minecraft.item.crafting.AbstractCookingRecipe;
 import net.minecraft.item.crafting.FurnaceRecipe;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.INBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.particles.BasicParticleType;
 import net.minecraft.particles.IParticleData;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.state.IProperty;
 import net.minecraft.tileentity.ITickableTileEntity;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.IntArray;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 import net.minecraft.world.World;
 import net.minecraft.world.server.ServerWorld;
public class HearthTileEntity
   extends LockableTileEntity
   implements ITickableTileEntity, IRecipeHelperPopulator
 {
   private NonNullList<ItemStack> contents;
   public static final int INV_SIZE = 10;
   public static final int[] INPUT_SLOT = IntStream.range(1, 10).toArray();

 
 
 
   private static FurnaceInventory furnaceInv = new FurnaceInventory();
   public static final ThreadLocalRandom rand = ThreadLocalRandom.current();
  public HearthTileEntity() {
     super(HiganTileEntityType.HEARTH);
     this.contents = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
     this.next_roll = (float)Math.random();
     reset();
   }

   public void func_73660_a() {
     int magnification = 1;
     if (!(func_145831_w()).field_72995_K) {
       if (this.progress <= 0) {
         if (canCooking()) {
           this.progress = getCookTime();
           func_70296_d();
         }      } else {
         this.progress -= magnification;
         if (this.progress <= 0) {
           cookItem();
           reset();
           func_70296_d();
         }      }    } else {
       this.now_roll = this.next_roll;
       if (this.next_roll >= 6.283185307179586D) {
         this.next_roll = this.now_roll = 0.017453292F;
       }
       this.next_roll += 0.017453292F;
     }  }
   private void cookItem() {
     if (!isInputEmpty()) {
       Optional<? extends IRecipe<? extends IInventory>> recipe = (Optional)getRecipe();
       if (recipe.isPresent()) {
         ItemStack output = ((IRecipe)recipe.get()).func_77571_b().func_77946_l();
                if (hasEnchantRecipe(((IRecipe)recipe.get()).func_199560_c())) {
           output = enchantReBuild((ItemStack)this.contents.get(2), (ItemStack)this.contents.get(8));
         }
               boolean crit = false;
         if (output.func_190916_E() > 1) {
           crit = rand.nextBoolean();
         }
         else if (output.func_77976_d() > 1) {
           crit = (rand.nextFloat() < 0.2F);
         }               BasicParticleType basicParticleType = ParticleTypes.field_197613_f;
         if (crit) {
           int count = output.func_190916_E();
           if (count < output.func_77976_d()) {
             output.func_190920_e(count + 1);
           }
           basicParticleType = ParticleTypes.field_197632_y;
         }        double d0 = 0.25D;
         double d1 = rand.nextGaussian() * 0.02D;
         double d2 = 0.25D;
         ((ServerWorld)func_145831_w()).func_195598_a((IParticleData)basicParticleType, this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.75D, this.field_174879_c.func_177952_p() + 0.5D, 8, d0, d1, d2, 0.0D);
                insertInventorySlotContents(0, output);                      } 
   }
 
   public static boolean hasEnchantRecipe(ResourceLocation resourceLocation) {
     return resourceLocation.toString().matches("meshi:hearth/enchant_.*");
   }
  private void reset() {
     this.progress = -1;
   }
  private void setParts(ItemStack stack) {
     func_145831_w().func_175656_a(this.field_174879_c, (BlockState)func_195044_w().func_206870_a((IProperty)Hearth.TYPE, Hearth.HearthStateType.getType(stack)));
   }
  private void removeParts() {
     func_145831_w().func_175656_a(this.field_174879_c, (BlockState)func_195044_w().func_206870_a((IProperty)Hearth.TYPE, Hearth.HearthStateType.NONE));
   }
  public void insertInventorySlotContents(int index, ItemStack insertStack) {
     if (insertStack == null || insertStack.func_190926_b()) {
       return;
     }
     if (isSlotEmpty(index)) {
       func_70299_a(index, insertStack);
     } else if (func_70301_a(index).func_77969_a(insertStack)) {
       ItemStack stack = (ItemStack)this.contents.get(index);
       stack.func_190920_e(stack.func_190916_E() + insertStack.func_190916_E());
       if (stack.func_190916_E() > func_70297_j_()) {
         stack.func_190920_e(func_70297_j_());
       }
       func_70296_d();
     }  }
   private boolean canCooking() {
     if (isInputEmpty()) {
       return false;
     }
     return isExistenceRacipe(getRecipe());
   }

   private boolean isExistenceRacipe(Optional<? extends IRecipe<?>> optional) {
     if (optional.isPresent()) {
       ItemStack output = ((IRecipe)optional.get()).func_77571_b();
       return hasInsert(0, output);
     }    return false;
   }
  public Optional<? extends IRecipe<?>> getRecipe() {
     Optional<? extends IRecipe<?>> recipe = (Optional)getSmeltingRecipe();
     if (isExistenceRacipe(recipe)) {
       return recipe;
     }
     recipe = getRecipe((IRecipeType)HearthShapelessRecipe.TYPE, this, func_145831_w());
     if (isExistenceRacipe(recipe)) {
       return recipe;
     }
     return (Optional)getRecipe(HearthRecipe.TYPE, this, func_145831_w());
   }
  public int getCookTime() {
     return ((Integer)getRecipe().filter(r -> r instanceof CookingTimerRecipe).map(r -> Integer.valueOf(((CookingTimerRecipe)r).getCookTime())).orElse(Integer.valueOf(getSmeltingTime()))).intValue();
   }
  private boolean hasInsert(int slot, ItemStack stack) {
     if (isSlotEmpty(slot)) {
       return true;
     }
     if (stack == null || stack.func_190926_b() || !func_70301_a(slot).func_77969_a(stack)) {
       return false;
     }
     int outResult = func_70301_a(slot).func_190916_E() + stack.func_190916_E();
     return (outResult <= func_70297_j_() && outResult <= stack.func_77976_d());
   }
  private boolean isInputEmpty() {
     return Arrays.stream(INPUT_SLOT).allMatch(this::isSlotEmpty);
   }
  private boolean isSlotEmpty(int slot) {
     return ((ItemStack)this.contents.get(slot)).func_190926_b();
   }
  public Optional<? extends FurnaceRecipe> getSmeltingRecipe() {
     return getRecipe(IRecipeType.field_222150_b, furnaceInv.setDummyStack(getInputStream()), func_145831_w());
   }
  public int getSmeltingTime() {
     return ((Integer)getSmeltingRecipe().<Integer>map(AbstractCookingRecipe::func_222137_e).orElse(Integer.valueOf(200))).intValue() * 4;
   }
  public <T extends IRecipe<U>, U extends IInventory> Optional<T> getRecipe(IRecipeType<T> type, U inv, World world) {
     return func_145831_w().func_199532_z().func_215371_a(type, (IInventory)inv, world);
   }

   public int func_70302_i_() {
     return 10;
   }

   public boolean func_191420_l() {
     return this.contents.stream().allMatch(ItemStack::func_190926_b);
   }

   public ItemStack func_70301_a(int index) {
     return (ItemStack)this.contents.get(index);
   }

   public ItemStack func_70298_a(int index, int count) {
     ItemStack itemstack = ItemStackHelper.func_188382_a((List)this.contents, index, count);
     if (!itemstack.func_190926_b()) {
       func_70296_d();
     }
     if (isInputSlotNum(index) &&      !canCooking()) {
       reset();
     }
       return itemstack;
   }

   public ItemStack func_70304_b(int index) {
     return ItemStackHelper.func_188383_a((List)this.contents, index);
   }

   public void func_70299_a(int index, ItemStack stack) {
     if (isInputSlotNum(index) &&      !func_70301_a(index).func_77969_a(stack)) {
       reset();
     }
        this.contents.set(index, stack);
     if (stack.func_190916_E() > func_70297_j_()) {
       stack.func_190920_e(func_70297_j_());
     }
        if (canCooking()) {
       setParts(((IRecipe)getRecipe().get()).func_77571_b());
     } else if (func_70301_a(0).func_190926_b() &&      func_195044_w().func_177229_b((IProperty)Hearth.TYPE) != Hearth.HearthStateType.NONE) {
       removeParts();
     }    
     func_70296_d();
   }
  public boolean isInputSlotNum(int slot) {
     return (slot >= INPUT_SLOT[0] && slot <= INPUT_SLOT[INPUT_SLOT.length - 1]);
   }

   public boolean func_70300_a(PlayerEntity player) {
     if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
       return false;
     }
     return (player.func_70092_e(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D);
   }

   public void func_174888_l() {
     this.contents.clear();
   }

   protected ITextComponent func_213907_g() {
     return (ITextComponent)new TranslationTextComponent(String.join(".", new CharSequence[] { "container", "meshi", func_195044_w().func_177230_c().getRegistryName().func_110623_a() }), new Object[0]);
   }

   protected Container func_213906_a(int id, PlayerInventory player) {
     IntArray intArray = new IntArray(2);
     intArray.func_221477_a(0, this.progress);
     intArray.func_221477_a(1, getCookTime());
     return (Container)new HearthContainer(id, player, this, intArray);
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

   public void func_145839_a(CompoundNBT compound) {
     super.func_145839_a(compound);
     this.contents = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
     readData(compound);
   }

   public CompoundNBT func_189515_b(CompoundNBT compound) {
     super.func_189515_b(compound);
     return writeData(compound);
   }
  public void readData(CompoundNBT compound) {
     ItemStackHelper.func_191283_b(compound, this.contents);
     this.progress = compound.func_74762_e("progress");
   }
  public CompoundNBT writeData(CompoundNBT compound) {
     ItemStackHelper.func_191282_a(compound, this.contents);
     compound.func_74768_a("progress", this.progress);
     return compound;
   }
  public int getProgress() {
     return this.progress;
   }
  private static class FurnaceInventory
     implements IInventory {
     private ItemStack stack = ItemStack.field_190927_a;
    public IInventory setDummyStack(Stream<ItemStack> stream) {
       List<ItemStack> list = (List<ItemStack>)stream.filter(is -> !is.func_190926_b()).collect(Collectors.toList());
       if (list.size() == 1) {
         this.stack = list.get(0);
       } else {
         this.stack = ItemStack.field_190927_a;
       }      return this;
     }

     public ItemStack func_70301_a(int index) {
       return this.stack;
     }

     public int func_70302_i_() {
       return 3;
     }

 
 
     public boolean func_191420_l() {
       return false;
     }

     public ItemStack func_70298_a(int index, int count) {
       return null;
     }

     public ItemStack func_70304_b(int index) {
       return null;
     }

 
 
 
 
     public boolean func_70300_a(PlayerEntity player) {
       return true;
     }
        private FurnaceInventory() {} }
   public void func_194018_a(RecipeItemHelper helper) {
     getInputStream().forEach(helper::func_195932_a);
   }
  private Stream<ItemStack> getInputStream() {
     return Arrays.stream(INPUT_SLOT).mapToObj(this.contents::get);
   }

   private ItemStack enchantReBuild(ItemStack base, ItemStack parts) {
     String KEY_ENCHANTMENTS = "Enchantments";
     Random rand = func_145831_w().func_201674_k();
     ItemStack stack = base.func_77946_l();
        stack.func_196085_b(0);    
     ItemStack baseCopy = base.func_77946_l();
     ItemStack partsCopy = parts.func_77946_l();
     ListNBT baseEnc = base.func_77986_q();
     ListNBT partsEnc = parts.func_77986_q();
       int baseTier = getEncCount(base);
     int partsTier = getEncCount(parts);
        partsCopy.func_196082_o().func_218657_a(KEY_ENCHANTMENTS, (INBT)subListNBT(partsEnc, 0, partsTier));
     Map<Enchantment, Integer> baseEncMap = EnchantmentHelper.func_82781_a(baseCopy);
     Map<Enchantment, Integer> partsEncMap = EnchantmentHelper.func_82781_a(partsCopy);
        if (partsEnc.isEmpty())      return stack; 
     if (baseEnc.isEmpty()) {
       EnchantmentHelper.func_82782_a(partsEncMap, stack);
        }
     else {
           int materialTier = Math.max(0, Math.min(baseTier, partsTier) - 1);
       for (Map.Entry<Enchantment, Integer> entry : partsEncMap.entrySet()) {
         int maxLV = ((Enchantment)entry.getKey()).func_77325_b();
         baseEncMap.merge(entry.getKey(), entry.getValue(), (b, p) -> Integer.valueOf((maxLV > 1) ? Math.min(maxLV + materialTier, calcMergeLv(b.intValue(), p.intValue())) : 1));
       }
 
 
       
       if (isCurse(rand, baseEncMap)) {
         baseEncMap.put(Enchantments.field_190940_C, Integer.valueOf(1));
       }
            EnchantmentHelper.func_82782_a(baseEncMap, stack);       
     return stack;
   }
  private boolean isCurse(Random rand, Map<Enchantment, Integer> enchantMap) {
     int curse = -5;
        for (Map.Entry<Enchantment, Integer> entry : enchantMap.entrySet()) {
       int maxLV = ((Enchantment)entry.getKey()).func_77325_b();
       curse = (int)(curse + Math.pow(Math.max(0, ((Integer)entry.getValue()).intValue() - maxLV), 2.0D) * 5.0D);
     }       if (curse <= 0 || rand.nextFloat() > 0.97F) {
       return false;
     }
     return (rand.nextInt(100) < curse);
   }
  private int calcMergeLv(int i1, int i2) {
     if (i1 == i2) {
       return i1 + 1;
     }
     return (int)Math.ceil((i1 + i2) / 2.0D);
   }
  private int getEncCount(ItemStack stack) {
     if (stack.func_77973_b() instanceof TieredItem) {
       return Math.max(1, ((TieredItem)stack.func_77973_b()).func_200891_e().func_200925_d());
     }
     return 1;
   }
  private ListNBT subListNBT(ListNBT org, int from, int to) {
     ListNBT list = new ListNBT();
     to = Math.min(to, org.size());
     for (int i = from; i < to; i++) {
       list.add(org.func_150305_b(i));
     }
     return list;
   }
 }

