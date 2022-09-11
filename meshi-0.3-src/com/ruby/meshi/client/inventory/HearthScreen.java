 package com.ruby.meshi.client.inventory;
 import com.ruby.meshi.common.inventory.HearthContainer;
 import net.minecraft.client.gui.IGuiEventListener;
 import net.minecraft.client.gui.recipebook.IRecipeShownListener;
 import net.minecraft.client.gui.recipebook.RecipeBookGui;
 import net.minecraft.client.gui.screen.inventory.ContainerScreen;
 import net.minecraft.client.gui.widget.Widget;
 import net.minecraft.client.gui.widget.button.Button;
 import net.minecraft.client.gui.widget.button.ImageButton;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.container.ClickType;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.text.ITextComponent;
public class HearthScreen extends ContainerScreen<HearthContainer> implements IRecipeShownListener {
   private static final ResourceLocation BOOK_BUTTON = new ResourceLocation("textures/gui/recipe_button.png");
   private static final ResourceLocation TEXTURE = new ResourceLocation("meshi", "textures/guis/hearth.png");

   public HearthScreen(HearthContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
     super((Container)screenContainer, inv, titleIn);
     this.book = new RecipeBookGui();
   }

   public void init() {
     super.init();
     this.widthTooNarrow = (this.width < 379);
     this.book.func_201520_a(this.width, this.height, this.minecraft, this.widthTooNarrow, (RecipeBookContainer)this.field_147002_h);
     this.field_147003_i = this.book.func_193011_a(this.widthTooNarrow, this.width, this.field_146999_f);
     this.children.add(this.book);
     func_212928_a((IGuiEventListener)this.book);
        addButton((Widget)new ImageButton(this.field_147003_i + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, BOOK_BUTTON, button -> {
             this.book.func_201518_a(this.widthTooNarrow);
             this.book.func_191866_a();
             this.field_147003_i = this.book.func_193011_a(this.widthTooNarrow, this.width, this.field_146999_f);
             ((ImageButton)button).func_191746_c(this.field_147003_i + 5, this.height / 2 - 49);
           }));
   }

   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
     if (this.book.func_191878_b() && this.widthTooNarrow) {
       func_146976_a(partialTicks, mouseX, mouseY);
       this.book.render(mouseX, mouseY, partialTicks);
     } else {
       this.book.render(mouseX, mouseY, partialTicks);
       super.render(mouseX, mouseY, partialTicks);
       this.book.func_191864_a(this.field_147003_i, this.field_147009_r, true, partialTicks);
     }       func_191948_b(mouseX, mouseY);
     this.book.func_191876_c(this.field_147003_i, this.field_147009_r, mouseX, mouseY);
     func_212932_b((IGuiEventListener)this.book);
   }

   protected void func_146979_b(int mouseX, int mouseY) {
     this.font.func_211126_b(this.title.func_150254_d(), 8.0F, 6.0F, 4210752);
        this.font.func_211126_b(this.field_213127_e.func_145748_c_().func_150254_d(), 8.0F, (this.field_147000_g - 96 + 2), 4210752);
   }

   protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
     GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
     this.minecraft.func_110434_K().func_110577_a(TEXTURE);
     int k = this.field_147003_i;
     int l = this.field_147009_r;
     blit(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
     blit(k + 90, l + 35, 176, 0, 23 - (int)(23.0F * progress()), 16);
   }
  private float progress() {
     int total = ((HearthContainer)func_212873_a_()).getCooktime();
     int now = ((HearthContainer)func_212873_a_()).getProgress();
     if (total == 0 || now < 0) {
       return 1.0F;
     }
     return ((HearthContainer)func_212873_a_()).getProgress() / ((HearthContainer)func_212873_a_()).getCooktime();
   }

   public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
     if (this.book.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
       return true;
     }
     return (this.widthTooNarrow && this.book.func_191878_b()) ? true : super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
   }

   protected void func_184098_a(Slot slotIn, int slotId, int mouseButton, ClickType type) {
     super.func_184098_a(slotIn, slotId, mouseButton, type);
     this.book.func_191874_a(slotIn);
   }

   public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
     return this.book.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_) ? false : super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
   }

   protected boolean func_195361_a(double p_195361_1_, double p_195361_3_, int p_195361_5_, int p_195361_6_, int p_195361_7_) {
     boolean lvt_8_1_ = (p_195361_1_ < p_195361_5_ || p_195361_3_ < p_195361_6_ || p_195361_1_ >= (p_195361_5_ + this.field_146999_f) || p_195361_3_ >= (p_195361_6_ + this.field_147000_g));
     return (this.book.func_195604_a(p_195361_1_, p_195361_3_, this.field_147003_i, this.field_147009_r, this.field_146999_f, this.field_147000_g, p_195361_7_) && lvt_8_1_);
   }

   public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
     return this.book.charTyped(p_charTyped_1_, p_charTyped_2_) ? true : super.charTyped(p_charTyped_1_, p_charTyped_2_);
   }

   public void func_192043_J_() {
     this.book.func_193948_e();
   }

   public RecipeBookGui func_194310_f() {
     return this.book;
   }

   public void removed() {
     this.book.func_191871_c();
     super.removed();
   }

   public void tick() {
     super.tick();
     this.book.func_193957_d();
   }
 }

