package fr.maxlego08.zshop.util;


public enum ShopAction {

	SHOW_CATEGORIES(0),
	SHOW_SHOP(1),
	ADD_ITEMS(2),
	BUY_ITEMS(3),
	SELL_ITEMS(4),
	SEND_ERROR(5),
	
	;
	
	private final int id;

	private ShopAction(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static ShopAction get(int id){
		for(ShopAction c : ShopAction.values()) if (c.getId() == id) return c; 
		return null;
	}
	
}
