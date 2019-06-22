package fr.maxlego08.zshop.gui.buttons;

import fr.maxlego08.zshop.util.Colors;
import fr.maxlego08.zshop.util.ZButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.Timer;

public class GuiButtonShopBack extends ZButton{

	public enum BackType{
		LEFT,
		RIGHT,
		MIDDLE;
	}
	
	private static final String __OBFID = "CL_00000672";
	public final int coord1;
	public final int coord2;
	public final int longeur;
	public final int longeur2;
	public final String texte;
	
	private Timer timer = new Timer(50F);
	
	private int step;
	
	private final BackType type;
	
	public GuiButtonShopBack(int p_i1041_1_, int p_i1041_2_, int p_i1041_3_, int largeur, int largeur2, String text, BackType type)
	{
		super(p_i1041_1_, p_i1041_2_, p_i1041_3_, largeur, largeur2, text);
		this.coord1 = p_i1041_2_;
		this.coord2 = p_i1041_3_;
		this.longeur = largeur;
		this.longeur2 = largeur2;
		this.texte = text;
		this.step = 0;
		this.type = type;
	}
	
	private final RenderItem itemRenderer = new RenderItem();
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY){
		if (this.enabled){
			int xPosition = this.field_146128_h;
			int yPosition = this.field_146129_i;
			int width = this.field_146120_f;
			int height = this.field_146121_g;
			  
			boolean hovered = ((mouseX >= xPosition) && (mouseY >= yPosition) && (mouseX < xPosition + width) && (mouseY < yPosition + height));
			
			this.timer.updateTimer();
			
			for(int j = 0; j < this.timer.elapsedTicks; j++){			
				if (!hovered){
					if (step != 0){
						step--;
					}				
				}else{	
					if (step != 10){
						step++;
					}
				}
			}
			
			switch (type) {
				case MIDDLE:{
					if (!hovered){		
						this.drawRect(coord1 - step, coord2, this.coord1 + longeur + step, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1 - step, coord2, this.coord1 + longeur + step, this.coord2 + longeur2, Colors.getShopOrange()); 
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}else{
						this.drawRect(coord1 - step, coord2, this.coord1 + longeur + step, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1 - step, coord2, this.coord1 + longeur + step, this.coord2 + longeur2, Colors.getShopOrange());
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}
					break;
				}
				case LEFT:{								
					if (!hovered){		
						this.drawRect(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopOrange());
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2 - step,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}else{
						this.drawRect(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopOrange());
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2 - step,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}
					break;
				}
				case RIGHT:{
					if (!hovered){		
						this.drawRect(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopOrange());
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2 + step,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}else{
						this.drawRect(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopItemFont());
						this.drawRectangle(coord1, coord2, this.coord1 + longeur, this.coord2 + longeur2, Colors.getShopOrange());
						this.drawCenteredString(mc.fontRenderer, this.texte, xPosition + width / 2 + step,  yPosition + (height - 8) / 2, Colors.getWhite().getRGB());
					}
					break;
				}
			}
			
		}
	}
	
}