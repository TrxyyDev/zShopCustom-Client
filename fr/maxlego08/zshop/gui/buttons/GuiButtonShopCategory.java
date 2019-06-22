package fr.maxlego08.zshop.gui.buttons;

import fr.maxlego08.zshop.util.Colors;
import fr.maxlego08.zshop.util.ZButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Timer;

public class GuiButtonShopCategory extends ZButton {

	private static final String __OBFID = "CL_00000672";
	public int coord1;
	public int coord2;
	public int longeur;
	public int longeur2;
	public String texte2;
	private Item item;
	private ItemStack itemStack;
	
	private Timer timer = new Timer(50F);

	private int step;

	public GuiButtonShopCategory(int p_i1041_1_, int p_i1041_2_, int p_i1041_3_, int largeur, int largeur2, Item item,
			String text2, int step) {
		super(p_i1041_1_, p_i1041_2_, p_i1041_3_, largeur, largeur2, text2);
		this.coord1 = p_i1041_2_;
		this.coord2 = p_i1041_3_;
		this.longeur = largeur;
		this.longeur2 = largeur2;
		this.texte2 = text2;
		this.step = step;
		this.item = item;
		this.itemStack = new ItemStack(item);
	}

	private final RenderItem itemRenderer = new RenderItem();

	@Override
	public void drawButton(Minecraft p_146112_1_, int mouseX, int mouseY) {
		if (this.enabled) {
			int xPosition = this.field_146128_h;
			int yPosition = this.field_146129_i;
			int width = this.field_146120_f;
			int height = this.field_146121_g;

			boolean hovered = ((mouseX >= xPosition) && (mouseY >= yPosition) && (mouseX < xPosition + width)
					&& (mouseY < yPosition + height));

			this.timer.updateTimer();

			for (int j = 0; j < this.timer.elapsedTicks; j++) {
				if (!hovered) {
					if (step != 0)
						step--;

				} else {
					if (step != 10)
						step++;

				}
			}

			if (!hovered) {
				this.drawRect(coord1, coord2 - step, this.coord1 + longeur, this.coord2 + longeur2 + step,
						Colors.getShopItemFont());
				this.drawRectangle(coord1, coord2 - step, this.coord1 + longeur, this.coord2 + longeur2 + step,
						Colors.getShopOrange());
				if (step > 1) {
					this.drawCenteredString(p_146112_1_.fontRenderer, this.texte2, xPosition + width / 2 + 1,
							yPosition + (height - 8) / 2 + 3 + (step / 3) + 5, Colors.getWhite().getRGB());
				}
			} else {
				this.drawRect(coord1, coord2 - step, this.coord1 + longeur, this.coord2 + longeur2 + step,
						Colors.getShopItemFont());
				this.drawRectangle(coord1, coord2 - step, this.coord1 + longeur, this.coord2 + longeur2 + step,
						Colors.getShopOrange());
				this.drawCenteredString(p_146112_1_.fontRenderer, this.texte2, xPosition + width / 2 + 1,
						yPosition + (height - 8) / 2 + 3 + (step / 3) + 5, Colors.getWhite().getRGB());
			}
			RenderHelper.enableGUIStandardItemLighting();
			this.drawItemInToScreen(itemStack, xPosition + width / 2 - 8,
					yPosition + (height - 8) / 2 - (step / 3) - 5, 10, false);
			RenderHelper.disableStandardItemLighting();
		}

	}
	
	public void hover(int x, int y){
		
		int xPosition = this.field_146128_h;
		int yPosition = this.field_146129_i;
		int width = this.field_146120_f;
		int height = this.field_146121_g;
		
		int xx = xPosition + width / 2 - 8;
		int yy = yPosition + (height - 8) / 2 - (step / 3) - 5;
		
		if (x >= xx - 5 && x <= xx + 20 && y >= yy - 5 && y <= yy + 20){
			Minecraft.getMinecraft().currentScreen.func_146285_a(itemStack, x, y);
		}
	}
	

}
