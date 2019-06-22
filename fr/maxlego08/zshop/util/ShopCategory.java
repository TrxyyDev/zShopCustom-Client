package fr.maxlego08.zshop.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum ShopCategory {

	BLOCK(0, "Blocs", Item.getItemFromBlock(Blocks.grass)),
	FARM(1, "Farms", Items.wheat),
	ORE(2, "Minerais", Items.diamond),
	FOOD(3, "Nourritures", Items.cooked_beef),
	MISCELLANEOUS(4, "Divers", Items.experience_bottle),
	POTION(5, "Potions", Items.glass_bottle),
	
	;
	
	private final int id;
	private final String title;
	private final Item item;
	
	private ShopCategory(int id, String title, Item item) {
		this.id = id;
		this.title = title;
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public static ShopCategory get(int id){
		for(ShopCategory c : ShopCategory.values()) if (c.getId() == id) return c; 
		return null;
	}
	
}
