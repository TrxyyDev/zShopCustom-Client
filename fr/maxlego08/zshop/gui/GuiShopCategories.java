package fr.maxlego08.zshop.gui;

import fr.maxlego08.zshop.ShopManager;
import fr.maxlego08.zshop.gui.buttons.GuiButtonShopCategory;
import fr.maxlego08.zshop.util.Lang;
import fr.maxlego08.zshop.util.ShopCategory;
import fr.maxlego08.zshop.util.ZGui;
import net.minecraft.client.gui.GuiButton;

public class GuiShopCategories extends ZGui {

	private ShopCategory[] z = new ShopCategory[ShopCategory.values().length];

	@Override
	public void initGui() {
		this.buttonList.clear();

		int posX = this.width / 7;
		int posY = this.height / 5;

		int sizeX = (this.width / 9) + 3;
		int sizeY = (this.height / 6);
		int a = 0;
		int d = 0;
		for (ShopCategory c : ShopCategory.values()) {
			if (a == 4) {
				a = 0;
				posY += this.height / 5 + this.height / 41;
				posX = this.width / 7;
			}
			this.buttonList.add(new GuiButtonShopCategory(d, posX + this.width / 20, posY + this.height / 20,
					sizeX + this.width / 28, sizeY, c.getItem(), c.getTitle(), 0));
			posX += this.width / 6 - this.width / 109;
			z[d] = c;
			a++;
			d++;

		}

	}

	@Override
	public void drawScreen(int x, int y, float p_73863_3_) {

		drawShop(Lang.guiShopMain);

		super.drawScreen(x, y, p_73863_3_);
		
		this.buttonList.forEach(button -> {
			if (button instanceof GuiButtonShopCategory){
				((GuiButtonShopCategory)button).hover(x, y);
			}
		});
		
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		int id = b.id;
		if (id >= 0 && id <= z.length) {
			ShopManager.openShop(z[id]);
		}
		super.actionPerformed(b);
	}

}
