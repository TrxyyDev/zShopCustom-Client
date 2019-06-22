package fr.maxlego08.zshop.gui;

import fr.maxlego08.zshop.ShopItem;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopBack;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopItem;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopBack.BackType;
import fr.maxlego08.zshop.packet.PacketServerShop;
import fr.maxlego08.zshop.util.Colors;
import fr.maxlego08.zshop.util.Lang;
import fr.maxlego08.zshop.util.ShopAction;
import fr.maxlego08.zshop.util.ShopCategory;
import fr.maxlego08.zshop.util.ZGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class GuiShopBuyItem extends ZGui implements GuiYesNoCallback {

	private final ShopCategory category;
	private final int page;
	private final ShopItem item;
	private int quantity;

	private GuiTextField textField;

	public GuiShopBuyItem(ShopCategory category, int page, ShopItem item) {
		this.category = category;
		this.page = page;
		this.item = item;
		this.quantity = item.getItem().stackSize;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		int posX = this.width / 9;
		int posY = this.height / 5;

		int sizeX = (this.width / 10) - 2;
		int sizeY = (this.height / 12);

		this.buttonList.add(new GuiButtonShopBack(33, this.width / 2 - this.width / 10, this.height - this.height / 5,
				sizeX + this.width / 10, sizeY, Lang.back, BackType.MIDDLE));

		this.buttonList.add(new GuiButtonShopBack(35, this.width / 4, this.height - this.height / 3,
				sizeX + this.width / 10, sizeY, Lang.buy, BackType.MIDDLE));
		this.buttonList.add(new GuiButtonShopBack(36, this.width / 2 + this.width / 16, this.height - this.height / 3,
				sizeX + this.width / 10, sizeY, Lang.allSell, BackType.MIDDLE));

		this.textField = new GuiTextField(this.fontRendererObj, this.width / 2 - this.width / 17,
				this.height / 3 + this.height / 50, 50, 20);
		this.textField.setText(String.valueOf(quantity));

		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		int id = button.id;
		
		if (id == 33){
			this.mc.displayGuiScreen(new GuiShop(category, page));
		}
		
		if (id == 35){
			if (item.getBuyPrice() == 0.0){
				mc.ingameGUI.getChatGUI().func_146227_a(new ChatComponentText(Lang.canBuy));
			}else if (hasInventoryFull(quantity)) {
				mc.ingameGUI.getChatGUI().func_146227_a(new ChatComponentText("§eVous devez avoir §6" + ((quantity/64) + 1) + " §eslots vide dans votre inventaire"));
			} else Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new PacketServerShop(ShopAction.BUY_ITEMS, item, quantity));			
			
			if (quantity > 2304){
				mc.ingameGUI.getChatGUI().func_146227_a(new ChatComponentText(Lang.canBuyMore));
			}
		}
		if (id == 36){
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new PacketServerShop(ShopAction.SELL_ITEMS, item, quantity));
		}
		
	}
	
	public void updateScreen() {
		if (this.textField.isFocused()) {
			this.textField.updateCursorCounter();
		}
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {

		this.drawShopBuyItem(Lang.guiShopBuyItem);

		this.drawString(fontRendererObj,
				item.getBuyPrice() != 0.0 ? Lang.price.replace("%price%", item.getBuyPrice() * quantity + "")
						: Lang.canBuyString,
				item.getBuyPrice() != 0 ? this.width / 4 : this.width / 4 - this.width / 20,
				this.height / 2 + this.height / 8 - this.height / 65, Colors.getWhite().getRGB());
		this.drawString(fontRendererObj,
				item.getSellPrice() != 0.0 ? Lang.price.replace("%price%", item.getSellPrice() + "")
						: Lang.canSellString,
				item.getSellPrice() != 0 ? this.width / 2 + this.width / 16 : this.width / 2,
				this.height / 2 + this.height / 8 - this.height / 65, Colors.getWhite().getRGB());

		this.drawItemInToScreen(item.getItem(), width / 2 - this.width / 54, this.height / 3 - this.height / 12, 10,
				false);
		RenderHelper.disableStandardItemLighting();

		this.textField.drawTextBox();

		super.drawScreen(x, y, partialTicks);
		
		int xx = width / 2 - this.width / 54;
		int yy = this.height / 3 - this.height / 12;
		
		if (x >= xx - 5 && x <= xx + 20 && y >= yy - 5 && y <= yy + 20){
			Minecraft.getMinecraft().currentScreen.func_146285_a(item.getItem(), x, y);
		}
		
	}

	
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.textField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        
    }
	
    private char[] intChar = new char[]{'1','2','3','4','5','6','7','8','9','0'};
    
    private boolean isNomber(char c){
    	for(char c2 : intChar){
    		if (c == c2){return true;}
    	}
    	return false;
    }
    
	protected void keyTyped(char ch, int ee) {

        if (this.textField.isFocused() && ee == 14|| isNomber(ch))
        {
            this.textField.textboxKeyTyped(ch, ee);
            String priceString = this.textField.getText();
            try{
            	quantity = Integer.parseInt(priceString);
            	if (quantity > 2304){
                    this.textField.setText("2304");
                    priceString = this.textField.getText();
                    quantity = Integer.parseInt(priceString);
            	}
            }catch (NumberFormatException e) { }
            if (priceString.length() == 0){            	
            	quantity = 1;
            }
        }
	}
	
	private boolean hasInventoryFull(int q){
		ItemStack[] items = this.mc.thePlayer.inventory.mainInventory;
		int slotEmpty = 0;
		int jaibesoindecombiendeslot = (q/64) + 1;
		for(ItemStack itemStack : items) if (itemStack == null) slotEmpty++;
		return slotEmpty < jaibesoindecombiendeslot;
	}
	
	private boolean hasItem(int q, ItemStack item){
		ItemStack[] items = this.mc.thePlayer.inventory.mainInventory;
		int itemNeed = 0;
		for(ItemStack itemStack : items) if (itemStack != null && itemStack.getItem().equals(item.getItem())) {
			itemNeed += itemStack.stackSize;
		}
		return itemNeed < q;
	}
	
	private int getItem(int q, ItemStack item){
		ItemStack[] items = this.mc.thePlayer.inventory.mainInventory;
		int itemNeed = 0;
		for(ItemStack itemStack : items) if (itemStack != null && itemStack.getItem().equals(item.getItem())) {
			itemNeed += itemStack.stackSize;
		}
		return itemNeed;
	}
	
}