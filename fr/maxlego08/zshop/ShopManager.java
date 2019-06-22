package fr.maxlego08.zshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.maxlego08.zshop.gui.GuiShop;
import fr.maxlego08.zshop.packet.PacketServerShop;
import fr.maxlego08.zshop.util.ShopAction;
import fr.maxlego08.zshop.util.ShopCategory;
import net.minecraft.client.Minecraft;

public class ShopManager {

	public static Map<ShopCategory, List<ShopItem>> items = new HashMap<ShopCategory, List<ShopItem>>();

	static {

		if (!items.containsKey(ShopCategory.BLOCK))
			items.put(ShopCategory.BLOCK, new ArrayList<ShopItem>());
		if (!items.containsKey(ShopCategory.FARM))
			items.put(ShopCategory.FARM, new ArrayList<ShopItem>());
		if (!items.containsKey(ShopCategory.FOOD))
			items.put(ShopCategory.FOOD, new ArrayList<ShopItem>());
		if (!items.containsKey(ShopCategory.MISCELLANEOUS))
			items.put(ShopCategory.MISCELLANEOUS, new ArrayList<ShopItem>());
		if (!items.containsKey(ShopCategory.ORE))
			items.put(ShopCategory.ORE, new ArrayList<ShopItem>());
		if (!items.containsKey(ShopCategory.POTION))
			items.put(ShopCategory.POTION, new ArrayList<ShopItem>());

	}

	public static void addItem(ShopCategory category, ShopItem item) {
		if (!existe(category, item))
			items.get(category).add(item);
	}

	private static boolean e = false;
	
	private static boolean existe(ShopCategory category, final ShopItem item) {
		e = false;
		try{
			getItem(category).forEach(items -> {
				if (items.getBuyPrice() == item.getBuyPrice() && items.getCategory().equals(item.getCategory())
						&& items.getItem().getItem().equals(item.getItem().getItem()) && items.getSellPrice() == item.getSellPrice()) {
					e = true;
				}
			});
		}catch (Exception e) { }
		return e;
	}

	public static List<ShopItem> getItem(ShopCategory c) {
		return items.get(c);
	}

	public static void openShop(ShopCategory category) {
		if (items.get(category).isEmpty())
			Minecraft.getMinecraft().thePlayer.sendQueue
					.addToSendQueue(new PacketServerShop(ShopAction.SHOW_SHOP, category));
		else
			Minecraft.getMinecraft().displayGuiScreen(new GuiShop(category));
	}

	public static int getMaxPage(ShopCategory category) {
		return getItem(category) != null ? (getItem(category).size() / 12) + 1 : 1;
	}

}
