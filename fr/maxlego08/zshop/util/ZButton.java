package fr.maxlego08.zshop.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public abstract class ZButton extends GuiButton{

	public ZButton(int p_i46323_1_, int p_i46323_2_, int p_i46323_3_, int p_i46323_4_, int p_i46323_5_,
			String p_i46323_6_) {
		super(p_i46323_1_, p_i46323_2_, p_i46323_3_, p_i46323_4_, p_i46323_5_, p_i46323_6_);
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
    private Minecraft mc = Minecraft.getMinecraft();
    
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
