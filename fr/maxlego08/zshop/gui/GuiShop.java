package fr.maxlego08.zshop.gui;

import java.util.ArrayList;
import java.util.List;

import fr.maxlego08.zshop.ShopItem;
import fr.maxlego08.zshop.ShopManager;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopBack;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopCategory;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopItem;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopBack.BackType;
import fr.maxlego08.zshop.util.Colors;
import fr.maxlego08.zshop.util.Lang;
import fr.maxlego08.zshop.util.ShopCategory;
import fr.maxlego08.zshop.util.ZGui;
import net.minecraft.client.gui.GuiButton;

public class GuiShop extends ZGui {
	
	private final ShopCategory category;
	private final int page;
	
	private List<ShopItem> items = new ArrayList();
	
	public GuiShop(ShopCategory category) {
		this.category = category;
		this.page = 1;
	}

	public GuiShop(ShopCategory category, int page) {
		this.category = category;
		this.page = page;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();
		
		this.drawDefaultButton();
		this.drawItem();
		
		super.initGui();
	}
	
	private void drawDefaultButton(){
		
		int posX = this.width / 9;
		int posY = this.height / 5;
		
		int sizeX = (this.width / 10)-2;
		int sizeY = (this.height / 12);
		
		this.buttonList.add(new GuiButtonShopBack(30, this.width-this.width/5, this.height - this.height/5, sizeX, sizeY, Lang.right, BackType.RIGHT)); 
		this.buttonList.add(new GuiButtonShopBack(31, posX - this.width / 146, this.height - this.height/5, sizeX, sizeY, Lang.left, BackType.LEFT));
		this.buttonList.add(new GuiButtonShopBack(33, this.width / 2 - this.width / 10, this.height - this.height/5, sizeX+this.width/10, sizeY, Lang.back, BackType.MIDDLE));
	}
	

	@Override
	public void drawScreen(int x, int y, float p_73863_3_) {
		this.drawShop(Lang.guiShopDiplayCategory.replace("%name%", category.getTitle()));	
		
		this.drawCenteredString(mc.fontRenderer, Lang.page.replace("%currentPage%", page+"").replace("%maxPage%", ShopManager.getMaxPage(category)+""),
				width / 2, this.height - this.height/4, Colors.getWhite().getRGB());
		super.drawScreen(x, y, p_73863_3_);
		
		this.buttonList.forEach(button -> {
			if (button instanceof GuiButtonShopItem){
				((GuiButtonShopItem)button).hover(x, y);
			}
		});
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		int id = button.id;
		
		if (id == 33){
			this.mc.displayGuiScreen(new GuiShopCategories());
		}
		
		if (id == 30){
			if (ShopManager.getMaxPage(category) != page){
				this.mc.displayGuiScreen(new GuiShop(category, page+1));
			}
		}

		if (id == 31){
			if (page != 1){
				this.mc.displayGuiScreen(new GuiShop(category, page-1));
			}
		}
		
		if (id >= 0 && id <= 12){
			this.mc.displayGuiScreen(new GuiShopBuyItem(category, page, this.items.get(id)));
		}
		
		
		super.actionPerformed(button);
	}
	
	private void drawItem(){
		try{
			
			this.items.clear();
			
			int posX = this.width / 9 + this.width / 50;
			int posY = this.height / 5;
		
			int sizeX = (this.width / 9) + 3;
			int sizeY = (this.height / 6);
		
			int how = 0;
		
			int max = 12;
			int start = (page-1)*12; 
			
			if (ShopManager.getItem(category).size() >= page*12){
				
			}else{
				max = ShopManager.getItem(category).size();
			}
			
			for(int a = start; a != max; a++){
				ShopItem data = ShopManager.getItem(category).get(a);
				this.items.add(data);
				this.buttonList.add(new GuiButtonShopItem(how, posX, posY + this.height/20, sizeX, sizeY, data.getItem(), Lang.buy, 0));
				posX += this.width / 8;
				how++;
				if (how > 14){
					continue;
				}
				if (how == 6){
					posY += this.height/5 + this.height/41;
					posX = this.width / 9 + this.width / 50;
				}
				
				
			}
		}catch (Exception e) { }
		
		
	}
	
	
	
}
