package fr.maxlego08.zshop;

import fr.maxlego08.zshop.util.ShopCategory;
import net.minecraft.item.ItemStack;

public class ShopItem {

	private final ShopCategory category;
	private final ItemStack item;
	private final double buyPrice;
	private final double sellPrice;
	
	public ShopItem(ShopCategory category, ItemStack item, double buyPrice, double sellPrice) {
		this.category = category;
		this.item = item;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}	
	/**
	 * @return the item
	 */
	public ItemStack getItem() {
		return item;
	}

	/**
	 * @return the buyPrice
	 */
	public double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @return the sellPrice
	 */
	public double getSellPrice() {
		return sellPrice;
	}

	/**
	 * @return the category
	 */
	public ShopCategory getCategory() {
		return category;
	}
	
	
	
}
