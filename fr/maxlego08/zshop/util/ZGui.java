package fr.maxlego08.zshop.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public abstract class ZGui extends GuiScreen{

    protected void drawShop(String title){
		int posX = this.width / 10;
		int posY = this.height / 5;
		
    	this.drawRect(posX, posY, this.width-this.width/10, this.height-this.height/12, Colors.getBackgroundShop());    	
    	this.drawRectangle(posX, posY, this.width-this.width/10, this.height-this.height/12, Colors.getBlack());   	
    	this.drawRect(this.width / 3, this.height / 12, this.width-this.width/3, posY+1, Colors.getBackgroundShop());
    	this.drawRectangleShop(this.width / 3, this.height / 12, this.width-this.width/3, posY, Colors.getBlack());   	
		this.drawCenteredString(this.fontRendererObj, title, this.width / 2, posY - this.height / 18, Colors.getWhite().getRGB());
    	
    }
	
    protected void drawShopBuyItem(String title){
    	int posX = this.width / 6;
    	int posY = this.height / 5;
    	
    	this.drawRect(posX, posY, this.width-posX, this.height-this.height/12, Colors.getBackgroundShop());    	
    	this.drawRectangle(posX, posY, this.width-posX, this.height-this.height/12, Colors.getBlack());   	
    	
    	this.drawRect(this.width / 3, this.height / 12, this.width-this.width/3, posY+1, Colors.getBackgroundShop());
    	this.drawRectangleShop(this.width / 3, this.height / 12, this.width-this.width/3, posY, Colors.getBlack());   	
    	this.drawCenteredString(this.fontRendererObj, title, this.width / 2, posY - this.height / 18, Colors.getWhite().getRGB());
    	
    }
    
    public void drawRectangle(int posX, int posY, int maxPosX, int maxPosY, int color){
    	this.drawHorizontalLine(posX, maxPosX, posY, color);		
    	this.drawHorizontalLine(posX, maxPosX, maxPosY, color);   	
    	this.drawVerticalLine(posX, posY, maxPosY, color);
    	this.drawVerticalLine(maxPosX, posY, maxPosY, color); 
    }
    
    public void drawRectangleShop(int posX, int posY, int maxPosX, int maxPosY, int color){
    	this.drawHorizontalLine(posX, maxPosX, posY, color);		
    	//his.drawHorizontalLine(posX, maxPosX, maxPosY, color);   	
    	this.drawVerticalLine(posX, posY, maxPosY, color);
    	this.drawVerticalLine(maxPosX, posY, maxPosY, color); 
    }
    
    private final RenderItem itemRenderer = new RenderItem();
    
    public void drawItemInToScreen(ItemStack item, int x, int y, float par3, boolean overlay)  
    {
    	if (item != null)
    	{
    	    float an = (float)item.animationsToGo - par3;
            if (an > 0.0F)
            {
            	GL11.glPushMatrix();
                float dec = 1.0F + an / 5.0F;
                GL11.glTranslatef((float)(x + 8), (float)(y + 12), 0.0F);
                GL11.glScalef(1.0F / dec, (dec + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }
            itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), item, x, y);
            if (an > 0.0F)
                GL11.glPopMatrix();
            if (overlay)
            	itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), item, y, y);
    	}
    }
	
}
