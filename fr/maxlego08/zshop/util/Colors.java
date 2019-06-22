package fr.maxlego08.zshop.util;

import java.awt.Color;

public class Colors {
	
	private Integer[] textTransparent = {100,80,70,60,50,40,30,20,10,0,0,0};
	
	
	public static Color getRed(){
		return new Color(255, 60, 60);
	}
	
	public static Color getGreen(){
		return new Color(60, 255, 60);
	}
	
	public static Color getGray(){
		return new Color(50, 50, 50);
	}
	
	public static Color getGrayTransprent(){
		return new Color(50, 50, 50, 220);
	}
	
	public static Color getBlock(){
		return new Color(20, 20, 20, 200);
	}
	
	public static Color getWhite()
	{
		return new Color(200, 200, 200);
	}
	  
	public static Color getBlue()
	{
		return new Color(16, 115, 191);
	}
	
	public static Color getEchapColor(){
		return new Color(77, 63, 60);
	}
	
	public static Color getPurple(){
		return new Color(12, 6, 29);
	}
	
	public static Color getPurple2(){
		return new Color(12, 6, 10);
	}
	
	public static Color getSquarePass(){
		return new Color(26, 18, 13);
	}
	
	public static Color getYellow(){
		return new Color(217, 187, 0);
	}
	
	public static Color getOrange(){
		return new Color(217, 115, 0);
	}
	
	public static Color getPassBlue(){
		return new Color(0, 78, 95);
	}
	
	public static Color getPassBlue2(){
		return new Color(0, 81, 165);
	}
	
	public static Color getYellowTranparent(){
		return new Color(225, 228, 0, 200);	
	}
	
	public static Color getProgressBar(){
		return new Color(86, 18, 0);	
	}
	
	public static int getMainButton(){
		return new Color(255, 120, 254, 100).getRGB();
	}
	
	public static int getMainButtonBorder(){
		return new Color(255, 36, 254).getRGB();
	}
	
	public static int getTextColor(){
		return new Color(254, 0, 255).getRGB();
	}
	
	/*
	 * Color shop
	 * */
	
	public static int getBackgroundShop(){
		return new Color(10, 19, 10, 245).getRGB();
	}
	
	public static int getBlack(){	
		return new Color(0, 0, 0).getRGB();
	}
	
	public static int getShopOrange(){
		return new Color(255, 63, 4).getRGB();
	}
	
	public static int getShopItemFont(){
		return new Color(24, 5, 6, 100).getRGB();
	}
	
	
}
