//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package easiervillagertrading;

import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.village.*;
import org.lwjgl.input.*;
import java.io.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import net.minecraft.nbt.*;

public class BetterGuiMerchant extends GuiMerchant
{
    private /* synthetic */ int xBase;
    private static final /* synthetic */ ResourceLocation icons;
    private /* synthetic */ int scrollCount;
    private /* synthetic */ int frames;
    
    BetterGuiMerchant(final InventoryPlayer inventoryPlayer, final GuiMerchant guiMerchant, final World world) {
        super(inventoryPlayer, guiMerchant.getMerchant(), world);
        this.xBase = 0;
        this.scrollCount = 0;
        if (ConfigurationHandler.showLeft()) {
            this.xBase = -ConfigurationHandler.leftPixelOffset();
            if (this.xBase == 0) {
                this.xBase = -this.getXSize();
            }
        }
        else {
            this.xBase = this.getXSize() + 5;
        }
    }
    
    private boolean areItemStacksMergable(final ItemStack itemStack, final ItemStack itemStack2) {
        return itemStack != null && itemStack2 != null && itemStack.getItem() == itemStack2.getItem() && (!itemStack.getHasSubtypes() || itemStack.getItemDamage() == itemStack2.getItemDamage()) && ItemStack.areItemStackTagsEqual(itemStack, itemStack2);
    }
    
    public void mouseScrolled(final int n) {
        final MerchantRecipeList getRecipes;
        if ((getRecipes = this.getMerchant().getRecipes((EntityPlayer)this.mc.player)) != null) {
            this.scrollCount -= n;
            while ((getRecipes.size() - this.scrollCount) * 18 + 40 < this.height) {
                --this.scrollCount;
            }
            if (this.scrollCount < 0) {
                this.scrollCount = 0;
            }
        }
    }
    
    private void slotClick(final int n) {
        this.mc.playerController.windowClick(this.mc.player.openContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
    }
    
    private boolean inputSlotsAreEmpty() {
        return !this.inventorySlots.getSlot(0).getHasStack() && !this.inventorySlots.getSlot(1).getHasStack() && !this.inventorySlots.getSlot(2).getHasStack();
    }
    
    private void transact(final MerchantRecipe merchantRecipe) {
        int fillSlot = -1;
        final int fillSlot2 = this.fillSlot(0, merchantRecipe.getItemToBuy());
        if (merchantRecipe.hasSecondItemToBuy()) {
            fillSlot = this.fillSlot(1, merchantRecipe.getSecondItemToBuy());
        }
        this.getslot(2, merchantRecipe.getItemToSell(), fillSlot2, fillSlot);
        if (fillSlot2 != -1) {
            this.slotClick(0);
            this.slotClick(fillSlot2);
        }
        if (fillSlot != -1) {
            this.slotClick(1);
            this.slotClick(fillSlot);
        }
    }
    
    private boolean hasEnoughItemsInInventory(final ItemStack itemStack) {
        int getCount = itemStack.getCount();
        for (int i = this.inventorySlots.inventorySlots.size() - 36; i < this.inventorySlots.inventorySlots.size(); ++i) {
            final ItemStack getStack = this.inventorySlots.getSlot(i).getStack();
            if (getStack != null) {
                if (this.areItemStacksMergable(itemStack, getStack)) {
                    getCount -= getStack.getCount();
                }
                if (getCount <= 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void drawTooltip(final String s, final int n, final int n2, int n3, int n4) {
        n3 -= this.guiLeft;
        n4 -= this.guiTop;
        if (n3 >= n && n3 <= n + 16 && n4 >= n2 && n4 <= n2 + 16) {
            this.drawHoveringText(s, n3, n4);
        }
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        final int eventDWheel = Mouse.getEventDWheel();
        if (eventDWheel != 0) {
            this.mouseScrolled((eventDWheel < 0) ? -1 : 1);
        }
    }
    
    private int getTopAdjust(final int n) {
        int n2 = (n * 18 + 20 - this.ySize) / 2;
        if (n2 < 0) {
            n2 = 0;
        }
        final int n3 = n2;
        final int guiTop = this.guiTop;
        this.getClass();
        if (n3 > guiTop - 20 / 2) {
            final int guiTop2 = this.guiTop;
            this.getClass();
            n2 = guiTop2 - 20 / 2;
        }
        return n2;
    }
    
    protected void drawGuiContainerBackgroundLayer(final float n, final int n2, final int n3) {
        super.drawGuiContainerBackgroundLayer(n, n2, n3);
    }
    
    private int fillSlot(final int n, final ItemStack itemStack) {
        int getCount = itemStack.getCount();
        for (int i = this.inventorySlots.inventorySlots.size() - 36; i < this.inventorySlots.inventorySlots.size(); ++i) {
            final ItemStack getStack = this.inventorySlots.getSlot(i).getStack();
            if (getStack != null) {
                boolean b = false;
                if (this.areItemStacksMergable(itemStack, getStack)) {
                    if (itemStack.getCount() + getStack.getCount() > itemStack.getMaxStackSize()) {
                        b = true;
                    }
                    getCount -= getStack.getCount();
                    this.slotClick(i);
                    this.slotClick(n);
                }
                if (b) {
                    this.slotClick(i);
                }
                if (getCount <= 0) {
                    return (getCount < 0) ? i : -1;
                }
            }
        }
        return -1;
    }
    
    private void drawTooltip(final ItemStack itemStack, final int n, final int n2, int n3, int n4) {
        if (itemStack == null) {
            return;
        }
        n3 -= this.guiLeft;
        n4 -= this.guiTop;
        if (n3 >= n && n3 <= n + 16 && n4 >= n2 && n4 <= n2 + 16) {
            this.renderToolTip(itemStack, n3, n4);
        }
    }
    
    private boolean canReceiveOutput(final ItemStack itemStack) {
        int getCount = itemStack.getCount();
        for (int i = this.inventorySlots.inventorySlots.size() - 36; i < this.inventorySlots.inventorySlots.size(); ++i) {
            final ItemStack getStack = this.inventorySlots.getSlot(i).getStack();
            if (getStack == null || getStack.isEmpty()) {
                return true;
            }
            if (this.areItemStacksMergable(itemStack, getStack) && itemStack.getMaxStackSize() >= itemStack.getCount() + getStack.getCount()) {
                getCount -= getStack.getMaxStackSize() - getStack.getCount();
            }
            if (getCount <= 0) {
                return true;
            }
        }
        return false;
    }
    
    private void getslot(final int n, final ItemStack itemStack, final int... array) {
        int getCount = itemStack.getCount();
        this.slotClick(n);
        for (int i = this.inventorySlots.inventorySlots.size() - 36; i < this.inventorySlots.inventorySlots.size(); ++i) {
            final ItemStack getStack = this.inventorySlots.getSlot(i).getStack();
            if (getStack != null) {
                if (!getStack.isEmpty()) {
                    if (this.areItemStacksMergable(itemStack, getStack) && getStack.getCount() < getStack.getMaxStackSize()) {
                        getCount -= getStack.getMaxStackSize() - getStack.getCount();
                        this.slotClick(i);
                    }
                    if (getCount <= 0) {
                        return;
                    }
                }
            }
        }
        for (int j = this.inventorySlots.inventorySlots.size() - 36; j < this.inventorySlots.inventorySlots.size(); ++j) {
            boolean b = false;
            for (int length = array.length, k = 0; k < length; ++k) {
                if (j == array[k]) {
                    b = true;
                }
            }
            if (!b) {
                final ItemStack getStack2 = this.inventorySlots.getSlot(j).getStack();
                if (getStack2 == null || getStack2.isEmpty()) {
                    this.slotClick(j);
                    return;
                }
            }
        }
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        if (n3 == 0 && n - this.guiLeft >= this.xBase && n - this.guiLeft <= this.xBase + 85) {
            final MerchantRecipeList getRecipes = this.getMerchant().getRecipes((EntityPlayer)this.mc.player);
            if (getRecipes == null) {
                return;
            }
            final int size = getRecipes.size();
            final int n4 = n2 + this.getTopAdjust(size) - this.guiTop - 20;
            if (n4 >= 0) {
                final int n5 = n4 / 18 + this.scrollCount;
                if (n5 >= 0 && n5 < size) {
                    final GuiButton guiButton = this.buttonList.get(0);
                    final GuiButton guiButton2 = this.buttonList.get(1);
                    for (int i = 0; i < size; ++i) {
                        this.actionPerformed(guiButton2);
                    }
                    for (int j = 0; j < n5; ++j) {
                        this.actionPerformed(guiButton);
                    }
                    final MerchantRecipe merchantRecipe = (MerchantRecipe)getRecipes.get(n5);
                    while (!merchantRecipe.isRecipeDisabled() && this.inputSlotsAreEmpty() && this.hasEnoughItemsInInventory(merchantRecipe) && this.canReceiveOutput(merchantRecipe.getItemToSell())) {
                        this.transact(merchantRecipe);
                        if (!isShiftKeyDown()) {
                            break;
                        }
                    }
                }
            }
            else if (n - this.guiLeft < this.xBase + 18) {
                this.mouseScrolled(1);
            }
            else if (n - this.guiLeft < this.xBase + 40) {
                this.mouseScrolled(-1);
            }
        }
        else {
            super.mouseClicked(n, n2, n3);
        }
    }
    
    private boolean hasEnoughItemsInInventory(final MerchantRecipe merchantRecipe) {
        return this.hasEnoughItemsInInventory(merchantRecipe.getItemToBuy()) && (!merchantRecipe.hasSecondItemToBuy() || this.hasEnoughItemsInInventory(merchantRecipe.getSecondItemToBuy()));
    }
    
    protected void drawGuiContainerForegroundLayer(final int n, final int n2) {
        if (++this.frames % 300 == 0) {}
        super.drawGuiContainerForegroundLayer(n, n2);
        final MerchantRecipeList getRecipes = this.getMerchant().getRecipes((EntityPlayer)this.mc.player);
        if (getRecipes == null) {
            return;
        }
        final int topAdjust = this.getTopAdjust(getRecipes.size());
        this.fontRenderer.drawString(String.valueOf(new StringBuilder().append(getRecipes.size()).append(" trades")), this.xBase + 40, -topAdjust, 16711935);
        if (this.frames % 300 == 0) {}
        RenderHelper.enableGUIStandardItemLighting();
        for (int i = 0; i < getRecipes.size() - this.scrollCount; ++i) {
            final MerchantRecipe merchantRecipe = (MerchantRecipe)getRecipes.get(i + this.scrollCount);
            final ItemStack getItemToBuy = merchantRecipe.getItemToBuy();
            final ItemStack itemStack = merchantRecipe.hasSecondItemToBuy() ? merchantRecipe.getSecondItemToBuy() : null;
            final ItemStack getItemToSell = merchantRecipe.getItemToSell();
            if (this.frames % 300 == 0) {}
            this.drawItem(getItemToBuy, this.xBase + 0, i * 18 - topAdjust + 20);
            this.drawItem(itemStack, this.xBase + 18, i * 18 - topAdjust + 20);
            this.drawItem(getItemToSell, this.xBase + 60, i * 18 - topAdjust + 20);
            NBTTagList list;
            if (getItemToSell.getItem() instanceof ItemEnchantedBook) {
                final ItemEnchantedBook itemEnchantedBook = (ItemEnchantedBook)getItemToSell.getItem();
                list = ItemEnchantedBook.getEnchantments(getItemToSell);
            }
            else {
                list = getItemToSell.getEnchantmentTagList();
            }
            if (list != null) {
                final StringBuilder obj = new StringBuilder();
                for (int j = 0; j < list.tagCount(); ++j) {
                    final short getShort = list.getCompoundTagAt(j).getShort("id");
                    final short getShort2 = list.getCompoundTagAt(j).getShort("lvl");
                    final Enchantment getEnchantmentByID = Enchantment.getEnchantmentByID((int)getShort);
                    if (getEnchantmentByID != null) {
                        if (j > 0) {
                            obj.append(", ");
                        }
                        obj.append(getEnchantmentByID.getTranslatedName((int)getShort2));
                    }
                }
                String s = String.valueOf(obj);
                if (this.xBase < 0) {
                    s = this.fontRenderer.trimStringToWidth(s, -this.xBase - 85 - 5);
                }
                if (this.frames % 300 == 0) {}
                this.fontRenderer.drawString(s, this.xBase + 85, i * 18 - topAdjust + 24, 16776960);
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        this.mc.getTextureManager().bindTexture(BetterGuiMerchant.icons);
        final int n3 = this.xBase + 40;
        final int[] array = new int[getRecipes.size()];
        for (int k = 0; k < getRecipes.size() - this.scrollCount; ++k) {
            final int n4 = k * 18 - topAdjust + 20;
            final MerchantRecipe merchantRecipe2 = (MerchantRecipe)getRecipes.get(k + this.scrollCount);
            if (!merchantRecipe2.isRecipeDisabled() && this.inputSlotsAreEmpty() && this.hasEnoughItemsInInventory(merchantRecipe2) && this.canReceiveOutput(merchantRecipe2.getItemToSell())) {
                this.drawTexturedModalRect(n3, n4, 108, 36, 18, 18);
                array[k] = 0;
            }
            else if (!merchantRecipe2.isRecipeDisabled()) {
                this.drawTexturedModalRect(n3, n4, 90, 54, 18, 18);
                array[k] = 1;
            }
            else {
                this.drawTexturedModalRect(n3, n4, 216, 54, 18, 18);
                array[k] = 2;
            }
        }
        if (this.scrollCount > 0) {
            this.drawTexturedModalRect(this.xBase + 0, -topAdjust - 3, 162, 36, 18, 18);
        }
        if ((getRecipes.size() - 1 - this.scrollCount) * 18 + 40 >= this.height) {
            if (this.frames % 300 == 0) {}
            this.drawTexturedModalRect(this.xBase + 18, -topAdjust - 3, 18, 36, 18, 18);
        }
        for (int l = 0; l < getRecipes.size(); ++l) {
            final int n5 = l * 18 - topAdjust + 20;
            final MerchantRecipe merchantRecipe3 = (MerchantRecipe)getRecipes.get(l);
            final ItemStack getItemToBuy2 = merchantRecipe3.getItemToBuy();
            final ItemStack itemStack2 = merchantRecipe3.hasSecondItemToBuy() ? merchantRecipe3.getSecondItemToBuy() : null;
            final ItemStack getItemToSell2 = merchantRecipe3.getItemToSell();
            this.drawTooltip(getItemToBuy2, this.xBase + 0, n5, n, n2);
            this.drawTooltip(itemStack2, this.xBase + 18, n5, n, n2);
            this.drawTooltip(getItemToSell2, this.xBase + 60, n5, n, n2);
            switch (array[l]) {
                case 0: {
                    this.drawTooltip(I18n.format("msg.cantrade", (Object[])null), n3, n5, n, n2);
                    break;
                }
                case 1: {
                    this.drawTooltip(I18n.format("msg.notradeinv", (Object[])null), n3, n5, n, n2);
                    break;
                }
                case 2: {
                    this.drawTooltip(I18n.format("msg.tradelocked", (Object[])null), n3, n5, n, n2);
                    break;
                }
            }
        }
    }
    
    static {
        icons = new ResourceLocation("easiervillagertrading", "textures/icons.png");
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
    }
    
    private void drawItem(final ItemStack itemStack, final int n, final int n2) {
        if (itemStack == null) {
            return;
        }
        this.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        this.itemRender.renderItemOverlays(this.fontRenderer, itemStack, n, n2);
    }
}
